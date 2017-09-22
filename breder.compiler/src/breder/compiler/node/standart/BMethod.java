
package breder.compiler.node.standart;

import java.io.IOException;
import java.util.List;
import java.util.Stack;

import breder.compiler.compiler.BinaryOutputStream;
import breder.compiler.compiler.Context;
import breder.compiler.exception.BrederJRuntimeException;
import breder.compiler.node.IAccess;
import breder.compiler.node.ICommand;
import breder.compiler.node.IStruct;
import breder.compiler.node.command.Block;
import breder.compiler.node.command.Super;
import breder.compiler.parser.javacc.ParseException;
import breder.compiler.parser.javacc.Token;
import breder.compiler.parser.javacc.TokenUtil;
import breder.compiler.util.CollectionsUtil;
import breder.compiler.util.ConvertUtil;
import breder.compiler.util.LightArrayList;

public class BMethod implements IStruct, IAccess {

	private BStruct struct;

	private final List<BType> returns = new LightArrayList<BType>();

	private final List<BParam> params = new LightArrayList<BParam>();

	private final List<VariableDeclare> declares = new LightArrayList<VariableDeclare>();

	private final Stack<Block> blocks = new Stack<Block>();

	private BAccess access;

	private List<BType> throwses = new LightArrayList<BType>();

	private Token nameToken;

	private String name;

	private int nameIndex;

	private Integer fullnameIndex;

	private int memIndex;

	private Block block;

	private boolean isStatic;

	private boolean isNative;

	private boolean isAbstract;

	private boolean isFinal;

	private Integer nativeIndex;

	private boolean superExplicity;

	private List<BMethod> constructor;

	private int index;

	private String nativeName;

	private String toString;

	private final Stack<Integer> beginRepeat = new Stack<Integer>();

	private final Stack<Integer> endRepeat = new Stack<Integer>();

	private final Stack<Integer> beginLoop = new Stack<Integer>();

	private BMethod superMethod;

	public BMethod(BStruct struct) {
		super();
		this.constructor = new LightArrayList<BMethod>();
		this.setStruct(struct);
	}

	public void checkPre(Context context) {
	}

	public void syntax(Context context) throws ParseException {
		context.pushMethod(this);
		if (this.getName().equals(this.getStruct().getName().image) && !this.isConstructor()) {
			throw new BrederJRuntimeException(context, this.getNameToken(),
					"method with this name need be a constructor.");
		}
		this.checkPre(context);
		for (BType type : returns) {
			type.syntax(context);
		}
		for (BParam param : params) {
			param.syntax(context);
		}
		for (BType type : this.throwses) {
			type.syntax(context);
		}
		if (this.isConstructor() && this.isFinal()) {
			throw new BrederJRuntimeException(context, this.getNameToken(), "can not have a final constructor");
		}
		context.popMethod();
	}

	public void body(Context context) throws ParseException {
		context.pushMethod(this);
		if (this.block != null) {
			block.syntax(context);
		}
		context.popMethod();
	}

	public void semanticHeader(Context context) throws ParseException {
		context.pushMethod(this);
		if (this.isNative) {
			nativeIndex = context.getCompiler().addNativeMethod(context, this);
		}
		if (this.nameToken != null) {
			this.nameIndex = context.getCompiler().addConstant(this.toNativeNameString(context));
		}
		{
			if (context.getStruct() instanceof BClass) {
				BBasicStruct clazz = (BBasicStruct) context.getStruct();
				if (clazz.getExtends().size() > 0) {
					for (int n = 0; n < clazz.getExtends().size(); n++) {
						BType extendType = clazz.getExtends().get(n);
						BMethod method = null;
						List<BType> paramTypes = ConvertUtil.convertParamsToTypes(this.getParams());
						BBasicStruct bstruct = (BBasicStruct) extendType.getStruct();
						Token name = this.getNameToken();
						if (name == null) {
							name = TokenUtil.newInstance(this.getName());
						}
						List<BMethod> methods = BBasicStruct.findConstructor(context, bstruct, bstruct, null, name,
								paramTypes, true);
						method = struct.getMethodClosed(context, name, methods, paramTypes);
						if (method != null && method.isFinal()) {
							throw new BrederJRuntimeException(context, this.getNameToken(),
									"can not override a final method");
						}
					}
				}
			}
		}
		context.popMethod();
	}

	public void semanticBody(Context context) throws ParseException {
		context.pushMethod(this);
		if (this.getBlock() != null) {
			block.semantic(context);
		}
		if (this.isConstructor()) {
			BClass clazz = (BClass) context.getStruct();
			if (clazz.getExtends().size() > 0 && !this.isNative) {
				Super superCmd = null;
				for (ICommand cmd : block.getCommands()) {
					if (cmd instanceof Super) {
						superCmd = (Super) cmd;
					}
				}
				this.superExplicity = superCmd != null;
				for (int n = 0; n < clazz.getExtends().size(); n++) {
					BType extendType = clazz.getExtends().get(n);
					BMethod method = null;
					Token token = extendType.getStruct().getName();
					List<BType> paramTypes;
					if (this.superExplicity) {
						paramTypes = ConvertUtil.convertRValuesToTypes(context, superCmd.getParams(n));
					} else {
						paramTypes = new LightArrayList<BType>();
					}
					BBasicStruct bstruct = (BBasicStruct) struct;
					List<BMethod> methods = BBasicStruct.findConstructor(context, bstruct, bstruct, null, token,
							paramTypes, true);
					method = struct.getMethodClosed(context, token, methods, paramTypes);
					if (method != null) {
						this.constructor.add(method);
					} else {
						if (superCmd != null) {
							throw new BrederJRuntimeException(context, superCmd.getSuperToken(),
									"super with wrong number of parameter");
						} else {
							throw new BrederJRuntimeException(context, this.getStruct().getName(),
									"super with wrong number of parameter");
						}
					}
				}
			}
		}
		context.popMethod();
	}

	public void check(Context context) throws ParseException {
		context.pushMethod(this);
		this.getStruct().checkMethod(context, this);
		this.checkReturns(context);
		if (this.getBlock() != null) {
			block.check(context);
		}
		if (this.isConstructor()) {
			if (!this.getName().equals(context.getStruct().getName().image)) {
				throw new BrederJRuntimeException(context, context.getStruct().getName(),
						"constructor need to be the same name of the struct");
			}
		}
		context.popMethod();
	}

	public BMethod getSuperMethod(Context context) throws ParseException {
		if (this.superMethod == null) {
			if (!this.isStatic()) {
				this.getSuperMethod(context, context.getCompiler()
						.findType(this.getStruct().getSource().getClassname()));
			}
		}
		return this.superMethod;
	}

	private void getSuperMethod(Context context, BType type) {
		for (BMethod method : type.getStruct().getMethods()) {
			if (!method.isStatic()) {
				if (this.getParams().size() == method.getParams().size()) {
					if (this.getName().equals(method.getName())) {
						boolean found = true;
						List<BType> ltypes = ConvertUtil.convertParamsToTypes(this.getParams());
						List<BType> rtypes = ConvertUtil.convertParamsToTypes(method.getParams());
						for (int n = 0; n < ltypes.size(); n++) {
							if (!ltypes.get(n).equals(rtypes.get(n))) {
								found = false;
							}
						}
						if (found && !this.getStruct().equals(method.getStruct())) {
							this.superMethod = method;
						}
					}
				}
			}
		}
		if (type.getStruct() instanceof BBasicStruct) {
			BBasicStruct struct = (BBasicStruct) type.getStruct();
			for (BType extendType : struct.getExtends()) {
				this.getSuperMethod(context, extendType);
			}
			for (BType implementType : struct.getImplements()) {
				this.getSuperMethod(context, implementType);
			}
		}
	}

	public void checkReturns(Context context) throws ParseException {
		if (!this.isStatic()) {
			this.checkReturns(context, context.getCompiler().findType(this.getStruct().getSource().getClassname()));
		}
	}

	private void checkReturns(Context context, BType type) {
		for (BMethod method : type.getStruct().getMethods()) {
			if (!method.isStatic()) {
				if (this.getParams().size() == method.getParams().size()) {
					if (this.getName().equals(method.getName())) {
						boolean found = true;
						List<BType> ltypes = ConvertUtil.convertParamsToTypes(this.getParams());
						List<BType> rtypes = ConvertUtil.convertParamsToTypes(method.getParams());
						for (int n = 0; n < ltypes.size(); n++) {
							if (!ltypes.get(n).canCastTo(context, null, rtypes.get(n))) {
								found = false;
							}
						}
						if (found) {
							if (this.getReturns().size() != method.getReturns().size()) {
								throw new BrederJRuntimeException(context, this.getNameToken(),
										"override of method with different number of returns");
							}
							for (int n = 0; n < this.getReturns().size(); n++) {
								BType ltype = this.getReturns().get(n);
								BType rtype = method.getReturns().get(n);
								if (rtype instanceof BThisType && ltype instanceof BThisType == false
										|| !ltype.canCastTo(context, null, rtype)) {
									throw new BrederJRuntimeException(context, this.getNameToken(),
											"super class only override a method with super returns");
								}
							}
						}
					}
				}
			}
		}
		if (type.getStruct() instanceof BBasicStruct) {
			BBasicStruct struct = (BBasicStruct) type.getStruct();
			for (BType extendType : struct.getExtends()) {
				this.checkReturns(context, extendType);
			}
			for (BType implementType : struct.getImplements()) {
				this.checkReturns(context, implementType);
			}
		}
	}

	public void build(Context context, BinaryOutputStream output) throws IOException {
		context.pushMethod(this);
		int declares = this.getDeclares().size();
		if (declares > 0) {
			output.printInc(declares, this.getNameToken());
		}
		if (this.isConstructor() && !this.superExplicity) {
			boolean found = context.getMethod().getDeclares().size() + context.getMethod().getParams().size() > 0;
			for (BMethod method : this.constructor) {
				if (found) {
					output.printLoad(declares + this.getParams().size(), method.getNameToken());
				}
				output.printOJump(method.getParams().size(), method.getIndex(), method.getNameToken());
				if (found) {
					output.printDec(1, method.getNameToken());
					output.decStack(1);
				}

			}
		}
		if (this.getBlock() != null) {
			block.build(context, output);
		}
		if (this.declares.size() > 0 && this.returns.size() == 0) {
			output.printDec(this.getDeclares().size(), this.getNameToken());
		}

		if (!this.isNative() && !this.isAbstract() && this.returns.size() == 0) {
			int index = this.getParams().size();
			if (this.isStatic()) {
				output.printReturn(index, this.getNameToken());
			} else {
				output.printReturn(index + 1, this.getNameToken());
			}
		}
		context.popMethod();
	}

	public static int returnPop(Context context) {
		BMethod method = context.getMethod();
		int params = method.getParams().size();
		int returns = method.getReturns().size();
		int index = 0;
		if (method.isConstructor()) {
			index = params;
		} else if (params + 1 > returns) {
			index = params - returns + 1;
		}
		return index;
	}

	public String getFullName() {
		return this.getStruct().getClassname() + "_" + this.getCompleteName();
	}

	public void addThrows(BType type) {
		this.throwses.add(type);
	}

	public List<BType> getThrowses() {
		return this.throwses;
	}

	public boolean isConstructor() {
		return false;
	}

	public Block getBlock() {
		return block;
	}

	public void setBlock(Block block) {
		this.block = block;
	}

	public Token getNameToken() {
		return nameToken;
	}

	public void setName(Token name) {
		this.nameToken = name;
		this.name = name.image;
	}

	public List<BParam> getParams() {
		return params;
	}

	public List<BType> getReturns() {
		return returns;
	}

	public int getNameIndex() {
		return nameIndex;
	}

	public int getFullNameIndex() {
		return fullnameIndex;
	}

	public void setNameIndex(int nameIndex) {
		this.nameIndex = nameIndex;
	}

	public int getMemIndex() {
		return memIndex;
	}

	public void setMemIndex(int memIndex) {
		this.memIndex = memIndex;
	}

	public List<VariableDeclare> getDeclares() {
		return declares;
	}

	public boolean isStatic() {
		return isStatic || this.isConstructor();
	}

	public boolean isStaticOnly() {
		return isStatic;
	}

	public void setStatic(boolean isStatic) {
		this.isStatic = isStatic;
	}

	public BStruct getStruct() {
		return struct;
	}

	public boolean isNative() {
		return isNative;
	}

	public void setNative(boolean isNative) {
		this.isNative = isNative;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNativeIndex() {
		return nativeIndex;
	}

	public void setNativeIndex(Integer nativeIndex) {
		this.nativeIndex = nativeIndex;
	}

	public String getNativeName() {
		if (this.nativeName == null) {
			this.nativeName = struct.getClassname().replace('.', '_') + "_"
					+ (this.isStatic() && !this.isConstructor() ? "_" : "") + this.getSimpleNativeName();
		}
		return this.nativeName;
	}

	public String getSimpleNativeName() {
		String params = "";
		for (BParam param : this.getParams()) {
			params += param.getType().getStruct().getNativeName() + "_";
		}
		if (params.length() != 0) {
			params = "_" + params.substring(0, params.length() - 1);
		}
		return this.getName() + params;
	}

	@Override
	public String toString() {
		return this.getCompleteName();
	}

	public String getCompleteName() {
		return CollectionsUtil.toString(this.getReturns()) + " " + this.getName() + " "
				+ CollectionsUtil.toString(this.getParams());
	}

	public String toNativeNameString(Context context) throws ParseException {
		if (this.toString == null) {
			BMethod method = this.getSuperMethod(context);
			if (method == null) {
				this.toString = CollectionsUtil.toString(this.getReturns()) + " " + this.getName() + " "
						+ CollectionsUtil.toString(this.getParams());
			} else {
				this.toString = CollectionsUtil.toString(method.getReturns()) + " " + method.getName() + " "
						+ CollectionsUtil.toString(method.getParams());
			}
		}
		return this.toString;
	}

	public String getQualifiedName() {
		return this.getStruct().getSource().getClassname() + "." + this.getName() + " "
				+ CollectionsUtil.toString(this.getParams());
	}

	public BParam findDeclare(String name) {
		for (BParam declare : this.declares) {
			if (declare.getName().image.equals(name)) {
				return declare;
			}
		}
		return null;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public BAccess getAccess() {
		return access;
	}

	public void setAccess(BAccess access) {
		this.access = access;
	}

	public boolean isAbstract() {
		return isAbstract;
	}

	public void setAbstract(boolean isAbstract) {
		this.isAbstract = isAbstract;
	}

	public boolean isFinal() {
		return isFinal;
	}

	public void setFinal(boolean isFinal) {
		this.isFinal = isFinal;
	}

	@Override
	public void setStruct(BStruct struct) {
		this.struct = struct;
	}

	public void addDeclare(VariableDeclare declare) {
		declare.setIndex(this.declares.size());
		this.declares.add(declare);
		this.blocks.peek().addVariable(declare);
	}

	public void pushBlock(Block value) {
		if (this.blocks.size() > 0) {
			value.setParent(this.blocks.peek());
		}
		this.blocks.push(value);
	}

	public void popBlock() {
		this.blocks.pop();
	}

	public int peekBeginRepeat() {
		return this.beginRepeat.peek();
	}

	public void pushBeginRepeat(int index) {
		this.beginRepeat.push(index);
	}

	public int popBeginRepeat() {
		return this.beginRepeat.pop();
	}

	public boolean isEmptyBeginRepeat() {
		return this.beginRepeat.isEmpty();
	}

	public int peekEndRepeat() {
		return this.endRepeat.peek();
	}

	public void pushEndRepeat(int index) {
		this.endRepeat.push(index);
	}

	public int popEndRepeat() {
		return this.endRepeat.pop();
	}

	public boolean isEmptyEndRepeat() {
		return this.endRepeat.isEmpty();
	}

	public int peekBeginLoop() {
		return this.beginLoop.peek();
	}

	public void pushBeginLoop(int index) {
		this.beginLoop.push(index);
	}

	public int popBeginLoop() {
		return this.beginLoop.pop();
	}

	public boolean isEmptyBeginLoop() {
		return this.beginLoop.isEmpty();
	}

	public String toAbsoluteString() {
		return String.format("%s %s", this.access, this);
	}

}
