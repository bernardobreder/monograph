
package breder.compiler.node.standart;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import breder.compiler.compiler.CompilerConstant;
import breder.compiler.compiler.Context;
import breder.compiler.exception.BrederJRuntimeException;
import breder.compiler.node.IValue;
import breder.compiler.parser.javacc.ParseException;
import breder.compiler.parser.javacc.Token;
import breder.compiler.util.LightArrayList;

public abstract class BBasicStruct extends BStruct {

	public BBasicStruct(BSource source) {
		super(source);
	}

	@Override
	public void syntax(Context context) throws ParseException {
		super.syntax(context);
		context.pushStruct(this);
		for (BGeneric generic : this.getGenerics()) {
			if (generic.getType() == null) {
				generic.setType(new BType(null, context.getCompiler().findSource(CompilerConstant.OBJECT_CLASS)
						.getStruct()));
			}
			generic.getType().syntax(context);
		}
		for (BType extend : this.getExtends()) {
			extend.syntax(context);
			if (extend.getStruct() instanceof BClass) {
				BClass bstruct = (BClass) extend.getStruct();
				if (bstruct.isFinal()) {
					throw new BrederJRuntimeException(context, extend.getName(), "can not extends a final struct");
				}
			}
			this.checkGeneric(context, extend);
		}
		context.popStruct();
	}

	@Override
	public void semanticHeader(Context context) throws ParseException {
		this.checkExtendCircle(context);
	}

	private void checkExtendCircle(Context context) {
		Set<BStruct> set = new HashSet<BStruct>();
		set.add(this);
		this.checkExtendCircle(context, set);
	}

	private void checkExtendCircle(Context context, Set<BStruct> set) {
		List<BType> types = new LightArrayList<BType>();
		types.addAll(this.getExtends());
		types.addAll(this.getImplements());
		for (BType type : types) {
			if (set.contains(type.getStruct())) {
				throw new BrederJRuntimeException(context, this.getSource(), type.getName(),
						"a class with circle extends");
			} else {
				set.add(type.getStruct());
				((BBasicStruct) type.getStruct()).checkExtendCircle(context, set);
				set.remove(type.getStruct());
			}
		}
	}

	@Override
	public void check(Context context) throws ParseException {
		for (BType extend : this.getExtends()) {
			if (extend.getStruct().isPrimitive() && !this.isPrimitive()) {
				throw new BrederJRuntimeException(context, extend.getName(), "can not extend a primitive class");
			}
		}
		for (BMethod m1 : this.getMethods()) {
			for (BMethod m2 : this.getMethods()) {
				if (m1 != m2) {
					if (m1.getName().equals(m2.getName())) {
						if (m1.getParams().size() == m2.getParams().size()) {
							boolean found = true;
							for (int n = 0; n < m1.getParams().size(); n++) {
								if (!m1.getParams().get(n).getType().getStruct()
										.equals(m2.getParams().get(n).getType().getStruct())) {
									found = false;
									break;
								}
							}
							if (found) {
								throw new BrederJRuntimeException(context, m2.getNameToken(),
										"method already declared at the same class");
							}
						}
					}
				}
			}
		}
	}

	protected void checkGeneric(Context context, BType type) {
		BBasicStruct bextend = (BBasicStruct) type.getStruct();
		if (type.getGenerics().size() > 0 && bextend.getGenerics().size() != type.getGenerics().size()) {
			throw new BrederJRuntimeException(context, type.getName(), "generic not complete");
		}
	}

	@Override
	public void checkMethod(Context context, BMethod method) {
	}

	@Override
	public List<BMethod> findMethod(Context context, IValue value, Token name, List<BType> params, boolean isStatic) {
		List<BMethod> methods = new LightArrayList<BMethod>();
		BBasicStruct.findMethodAux(context, this, value, name, params, methods, isStatic);
		return methods;
	}

	private static void findMethodAux(Context context, BBasicStruct struct, IValue value, Token name,
			List<BType> params, List<BMethod> methods, boolean isStatic) {
		BBasicStruct.findMethodAuxAux(context, struct, value, name, params, methods, isStatic);
		for (int n = 0; n < struct.getExtends().size(); n++) {
			BType extend = struct.getExtends().get(n);
			BBasicStruct c = (BBasicStruct) extend.getStruct();
			BBasicStruct.findMethodAux(context, c, value, name, params, methods, isStatic);
		}
	}

	public static List<BMethod> findConstructor(Context context, BBasicStruct superStruct, BBasicStruct struct,
			IValue value, Token name, List<BType> params, boolean isStatic) {
		List<BMethod> methods = new LightArrayList<BMethod>();
		for (int n = 0; n < struct.getExtends().size(); n++) {
			BType extend = struct.getExtends().get(n);
			BBasicStruct c = (BBasicStruct) extend.getStruct();
			BBasicStruct.findMethodAuxAux(context, c, value, name, params, methods, isStatic);
		}
		return methods;
	}

	public static List<BMethod> findMethodAuxAux(Context context, BBasicStruct struct, IValue value, Token name,
			List<BType> params, List<BMethod> methods, boolean isStatic) {
		for (int m = 0; m < struct.getMethods().size(); m++) {
			BMethod method = struct.getMethods().get(m);
			if (method.isStatic() == isStatic) {
				if (method.getName().equals(name.image)) {
					if (method.getParams().size() == params.size()) {
						boolean found = true;
						for (int n = 0; n < params.size(); n++) {
							BType type1 = params.get(n);
							BType type2 = method.getParams().get(n).getType();
							if (!type1.canCastTo(context, value, type2)) {
								found = false;
								break;
							}
						}
						if (found) {
							methods.add(method);
						}
					}
				}
			}
		}
		return methods;
	}

	protected BMethod getMethodClosedAux(Context context, Token token, List<BMethod> methods) {
		BMethod method = super.getMethodClosedAux(context, token, methods);
		if (method == null) {
			for (BType type : this.getExtends()) {
				BMethod aux = type.getStruct().getMethodClosedAux(context, token, methods);
				if (aux != null && method != null) {
					throw new BrederJRuntimeException(context, token, "ambiguidade entre metodos");
				}
				method = aux;
			}
		}
		return method;
	}

	public BType getRelativeType(BType extend) {
		return this.getRelativeType(extend, new HashMap<String, BType>());
	}

	public BType getRelativeType(BType extend, Map<String, BType> map) {
		{
			List<BType> types = this.getExtends();
			for (BType type : types) {
				BBasicStruct struct = (BBasicStruct) type.getStruct();
				if (extend.getStruct().equals(struct)) {
					BType rtype = new BType(struct);
					for (BGeneric generic : struct.getGenerics()) {
						rtype.addGeneric(map.get(generic.getToken().image));
					}
					return rtype;
				}
				if (struct.getGenerics().size() > 0) {
					Map<String, BType> emap = new HashMap<String, BType>();
					for (BGeneric generic : struct.getGenerics()) {
						emap.put(generic.getToken().image, generic.getType());
					}
					BType rtype = struct.getRelativeType(extend, emap);
					if (rtype != null) {
						return rtype;
					}
				}
			}
		}
		if (this.isClassStruct()) {
			List<BType> types = this.getImplements();
			for (BType type : types) {
				BBasicStruct struct = (BBasicStruct) type.getStruct();
				if (struct.getGenerics().size() > 0) {
					Map<String, BType> emap = new HashMap<String, BType>();
					for (int n = 0; n < struct.getGenerics().size(); n++) {
						emap.put(struct.getGenerics().get(n).getToken().image, type.getGenerics().get(n));
					}
					BType rtype = struct.getRelativeType(extend, emap);
					if (rtype != null) {
						return rtype;
					}
				}
			}
		}
		return extend;
	}

}
