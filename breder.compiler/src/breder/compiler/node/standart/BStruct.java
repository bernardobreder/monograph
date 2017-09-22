
package breder.compiler.node.standart;

import java.io.IOException;
import java.util.List;

import breder.compiler.compiler.BinaryOutputStream;
import breder.compiler.compiler.CompilerConstant;
import breder.compiler.compiler.Context;
import breder.compiler.exception.BrederJRuntimeException;
import breder.compiler.node.IAccess;
import breder.compiler.node.IValue;
import breder.compiler.parser.javacc.ParseException;
import breder.compiler.parser.javacc.Token;
import breder.compiler.util.LightArrayList;

public abstract class BStruct implements IAccess {

	private final BSource source;

	private Token name;

	private final List<BMethod> methods = new LightArrayList<BMethod>();

	private short index;

	private BAccess access;

	private final List<BType> extend = new LightArrayList<BType>();

	private final List<BGeneric> generics = new LightArrayList<BGeneric>();

	private final List<BField> fields = new LightArrayList<BField>();

	private final List<BType> implement = new LightArrayList<BType>();

	public BStruct(BSource source) {
		super();
		this.source = source;
		this.source.setStruct(this);

	}

	public abstract boolean isInstanceable();

	public abstract List<BMethod> findMethod(Context context, IValue value, Token name, List<BType> params,
			boolean isStatic);

	public List<BMethod> findMethod(Context context, IValue value, Token name, boolean isStatic) {
		List<BMethod> methods = new LightArrayList<BMethod>();
		BStruct.findMethodAux(context, this, value, name, methods, isStatic);
		return methods;
	}

	private static void findMethodAux(Context context, BStruct struct, IValue value, Token name, List<BMethod> methods,
			boolean isStatic) {
		BStruct.findMethodAuxAux(context, struct, value, name, methods, isStatic);
		for (int n = 0; n < struct.getExtends().size(); n++) {
			BType extend = struct.getExtends().get(n);
			BStruct c = extend.getStruct();
			BStruct.findMethodAux(context, c, value, name, methods, isStatic);
		}
	}

	public static List<BMethod> findMethodAuxAux(Context context, BStruct struct, IValue value, Token name,
			List<BMethod> methods, boolean isStatic) {
		for (int m = 0; m < struct.getMethods().size(); m++) {
			BMethod method = struct.getMethods().get(m);
			if (method.isStatic() == isStatic) {
				if (method.getName().equals(name.image)) {
					methods.add(method);
				}
			}
		}
		return methods;
	}

	public void syntax(Context context) throws ParseException {
		{
			String name = context.getClassname();
			name = name.substring(name.lastIndexOf('.') + 1);
			if (!this.getName().image.equalsIgnoreCase(name)) {
				throw new BrederJRuntimeException(context, this.getName(),
						"the class need to have the same name of the classname");
			}
		}
	}

	public abstract void semanticHeader(Context context) throws ParseException;

	public abstract void semanticBody(Context context) throws ParseException;

	public abstract void check(Context context) throws ParseException;

	public abstract void build(Context context, BinaryOutputStream output) throws IOException;

	public abstract void memory(Context context) throws IOException;

	public BMethod getMethodClosed(Context context, Token token, List<BMethod> methods, List<BType> paramTypes) {
		if (methods.size() == 0) {
			return null;
		} else if (methods.size() == 1) {
			return methods.get(0);
		} else if (methods.get(0).getParams().size() == 0) {
			BMethod method = this.getMethodClosedAux(context, token, methods);
			if (method != null) {
				return method;
			}
		}
		{
			boolean allInterfaces = true;
			for (BMethod method : methods) {
				if (!method.getStruct().isInterfaceStruct()) {
					allInterfaces = false;
					break;
				}
			}
			if (allInterfaces) {
				return methods.get(0);
			}
		}
		List<List<BType>> list = new LightArrayList<List<BType>>();
		for (int n = 0; n < methods.get(0).getParams().size(); n++) {
			list.add(new LightArrayList<BType>());
		}
		for (BMethod method : methods) {
			for (int n = 0; n < method.getParams().size(); n++) {
				BParam param = method.getParams().get(n);
				list.get(n).add(param.getType());
			}
		}
		int cur = 0;
		int len = 0;
		for (int n = 0; n < list.size(); n++) {
			List<BType> types = list.get(n);
			boolean equal = true;
			for (int m = 0; m < types.size(); m++) {
				BType type = types.get(m);
				if (!types.get(0).equals(type)) {
					equal = false;
					break;
				}
			}
			if (!equal) {
				len++;
				cur = n;
			}
		}
		if (len > 1) {
			throw new BrederJRuntimeException(context, token,
					"Ambiguidade entre metodos com mesmo nome porem com mais de 1 argumento diferete");
		}
		{
			int levels = Integer.MAX_VALUE, resultIndex = 0;
			List<BType> types = list.get(cur);
			BType type1 = paramTypes.get(cur);
			if (type1 instanceof BNullType && types.size() > 0) {
				throw new BrederJRuntimeException(context, token, "ambiguidade");
			}
			for (int m = 0; m < types.size(); m++) {
				BType type2 = types.get(m);
				int level = this.getLevel(type1, type2);
				if (level < levels) {
					levels = level;
					resultIndex = m;
				}
			}
			return methods.get(resultIndex);
		}
	}

	protected BMethod getMethodClosedAux(Context context, Token token, List<BMethod> methods) {
		BMethod selected = null;
		if (this instanceof BInterface == false) {
			for (BMethod method : this.getMethods()) {
				if (method.isStatic() == methods.get(0).isStatic()) {
					if (method.toString().equals(methods.get(0).toString())) {
						for (BMethod method2 : methods) {
							if (method == method2) {
								if (selected == null) {
									selected = method2;
								} else {
									throw new BrederJRuntimeException(context, this.getName(),
											"ambiguidade entre metodos de mesma hierarquia with '%s' and '%s'",
											method.getQualifiedName(), method2.getQualifiedName());
								}
							}
						}
						if (selected != null) {
							return selected;
						}
					}
				}
			}
		}
		return null;
	}

	public int getLevel(BBasicStruct class1, BBasicStruct class2) {
		return this.getLevelAux(class1, class2, 0);
	}

	public int getLevel(BType type1, BType type2) {
		return this.getLevelAux(type1, type2, 0);
	}

	private int getLevelAux(BType type1, BType type2, int level) {
		return this.getLevelAux((BBasicStruct) type1.getStruct(), (BBasicStruct) type2.getStruct(), level);
	}

	private int getLevelAux(BBasicStruct class1, BBasicStruct class2, int level) {
		if (class1.getSource().getClassname().equals(class2.getSource().getClassname())) {
			return level;
		} else {
			BBasicStruct clazz = class1;
			for (BType type : clazz.getExtends()) {
				Integer l = this.getLevelAux((BClass) type.getStruct(), class2, level + 1);
				if (l > 0) {
					return l;
				}
			}
			return -1;
		}
	}

	public BAccess getAccess() {
		return access;
	}

	public void setAccess(BAccess access) {
		this.access = access;
	}

	@Override
	public String toString() {
		return this.getClassname();
	}

	public String getClassname() {
		return this.getSource().getClassname();
	}

	public abstract void checkMethod(Context context, BMethod bMethod);

	public boolean canCastTo(BStruct struct) {
		if (this.toString().equals(struct.toString())) {
			return true;
		}
		{
			for (BType type : this.getExtends()) {
				if (type.getStruct().canCastTo(struct)) {
					return true;
				}
			}
			for (BType type : this.getImplements()) {
				if (type.getStruct().canCastTo(struct)) {
					return true;
				}
			}
			return false;
		}
	}

	public boolean isPrimitive() throws ParseException {
		String name = this.getSource().getClassname();
		return name.equals(CompilerConstant.STRING_CLASS) || name.equals(CompilerConstant.BOOLEAN_CLASS)
				|| name.equals(CompilerConstant.NUMBER_CLASS) || name.equals(CompilerConstant.INTEGER_CLASS)
				|| name.equals(CompilerConstant.NATURAL_CLASS) || name.equals(CompilerConstant.INDEX_CLASS);
	}

	public boolean isInteger() {
		String name = this.getSource().getClassname();
		return name.equals(CompilerConstant.INTEGER_CLASS) || name.equals(CompilerConstant.NATURAL_CLASS)
				|| name.equals(CompilerConstant.INDEX_CLASS);
	}

	public boolean isNumber() {
		String name = this.getSource().getClassname();
		return name.equals(CompilerConstant.NUMBER_CLASS) || this.isInteger();
	}

	public boolean isBoolean() throws ParseException {
		String name = this.getSource().getClassname();
		return name.equals(CompilerConstant.BOOLEAN_CLASS);
	}

	public String getNativeName() {
		return this.toString().replace('.', '_');
	}

	public boolean isInterfaceStruct() {
		return this instanceof BInterface;
	}

	public boolean isClassStruct() {
		return this instanceof BClass;
	}

	public List<BGeneric> getGenerics() {
		return this.generics;
	}

	public void addGeneric(Token name, BType type) {
		this.generics.add(new BGeneric(name, type));
	}

	public List<BType> getExtends() {
		return this.extend;
	}

	public void addExtends(BType extend) {
		this.extend.add(extend);
	}

	public List<BField> getFields() {
		return fields;
	}

	public void addField(BField field) {
		field.setStruct(this);
		this.fields.add(field);
	}

	public List<BType> getImplements() {
		return this.implement;
	}

	public void addImplements(BType extend) {
		this.implement.add(extend);
	}

	public Token getName() {
		return name;
	}

	public void setName(Token name) {
		this.name = name;
	}

	public BSource getSource() {
		return source;
	}

	public List<BMethod> getMethods() {
		return this.methods;
	}

	public void addMethod(BMethod method) {
		this.methods.add(method);
	}

	public short getIndex() {
		return index;
	}

	public void setIndex(short index) {
		this.index = index;
	}

	public BMethod getMethods(String fullname) {
		for (BMethod method : this.getMethods()) {
			if (method.toString().equals(fullname)) {
				return method;
			}
		}
		return null;
	}

	public void body(Context context) throws ParseException {
	}

}
