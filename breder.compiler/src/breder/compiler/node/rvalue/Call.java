
package breder.compiler.node.rvalue;

import java.io.IOException;
import java.util.List;

import breder.compiler.compiler.BinaryOutputStream;
import breder.compiler.compiler.Context;
import breder.compiler.exception.BrederJRuntimeException;
import breder.compiler.node.INode;
import breder.compiler.node.IRValue;
import breder.compiler.node.command.Try;
import breder.compiler.node.standart.BMethod;
import breder.compiler.node.standart.BParam;
import breder.compiler.node.standart.BStruct;
import breder.compiler.node.standart.BType;
import breder.compiler.node.standart.VariableDeclare;
import breder.compiler.parser.javacc.ParseException;
import breder.compiler.parser.javacc.Token;
import breder.compiler.util.CollectionsUtil;
import breder.compiler.util.ConvertUtil;
import breder.compiler.util.LightArrayList;

public class Call extends RValue {

	private final IRValue left;

	private final Token name;

	private final List<IRValue> params = new LightArrayList<IRValue>();

	private BMethod method;

	public Call(IRValue left, Token name) {
		super();
		this.left = left;
		this.name = name;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void syntax(Context context) throws ParseException {
		this.left.setParent(this);
		left.syntax(context);
		for (IRValue param : params) {
			param.setParent(this);
			param.syntax(context);
		}
		BStruct struct = left.getType().getStruct();
		List<BType> paramTypes = ConvertUtil.convertRValuesToTypes(context, params);
		boolean isStatic = left instanceof RIdentify ? ((RIdentify) (left)).isStatic() : false;
		List<BMethod> methods = struct.findMethod(context, this.left, name, paramTypes, isStatic);
		if (methods.size() > 0 && this.getLeft() instanceof RSuper) {
			if (methods.get(0).getStruct().equals(context.getStruct())) {
				methods.remove(0);
			}
		}
		this.method = struct.getMethodClosed(context, name, methods, paramTypes);
		if (this.method == null) {
			throw new BrederJRuntimeException(context, this.name, "not found the method %s.%s %s", struct.getSource()
					.getClassname(), name.image, CollectionsUtil.toString(paramTypes));
		}
		if (!Call.checkAccess(context, this.getLeft(), this.getMethod(), this.getName())) {
			throw new BrederJRuntimeException(context, this.getName(), "can not access the method");
		}
		this.checkNotNullParameters(context);
		BType[] types = new BType[this.method.getReturns().size()];
		for (int n = 0; n < this.method.getReturns().size(); n++) {
			BType type = this.method.getReturns().get(n);
			types[n] = type.getTypeGenericed(this.getLeft());
		}
		this.setTypes(types);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws ParseException
	 */
	@Override
	public void semantic(Context context) throws ParseException {
		left.semantic(context);
		if (left.getTypes().length == 0) {
			throw new BrederJRuntimeException(context, left.getToken(), "lvalue not return a value");
		}
		if (this.getLeft() instanceof RIdentify && !this.getLeft().getType().isNotNull()) {
			throw new BrederJRuntimeException(context, this.getLeft().getToken(), "lvalue must be notnull");
		}
		for (IRValue param : params) {
			param.semantic(context);
			if (param.getTypes().length > 1) {
				throw new BrederJRuntimeException(context, param.getToken(), "rvalue with multi-return as parameter");
			}
		}

	}

	private void checkNotNullParameters(Context context) throws ParseException {
		Call.checkException(context, this, this.getMethod(), this.getName());
		List<BParam> paramsl = this.getMethod().getParams();
		List<IRValue> paramsr = this.getParams();
		for (int n = 0; n < paramsr.size(); n++) {
			IRValue rvalue = paramsr.get(n);
			BType paramr = rvalue.getType();
			BParam paraml = paramsl.get(n);
			if (paraml.getType().isNotNull()) {
				if (!paramr.isNotNull()) {
					throw new BrederJRuntimeException(context, rvalue.getToken(), "param '%s' must be notnull",
							rvalue.getToken());
				}
			}
		}
	}

	public static void checkException(Context context, INode node, BMethod method, Token token) throws ParseException {
		BStruct exceptionStruct = context.getCompiler().findSource("breder.lang.standard.CompileException").getStruct();
		for (BType throwType : method.getThrowses()) {
			if (throwType.getStruct().canCastTo(exceptionStruct)) {
				boolean found = false;
				if (!found) {
					INode parent = node.getParent();
					while (parent != null) {
						if (parent instanceof Try) {
							Try tryNode = (Try) parent;
							for (VariableDeclare declare : tryNode.getCatchs()) {
								if (throwType.canCastTo(context, null, declare.getType())) {
									found = true;
									break;
								}
							}
							if (found) {
								break;
							}
						}
						parent = parent.getParent();
					}
				}
				if (!found) {
					for (BType throwsType : context.getMethod().getThrowses()) {
						if (throwType.canCastTo(context, null, throwsType)) {
							found = true;
							break;
						}
					}
				}
				if (!found) {
					throw new BrederJRuntimeException(context, token, "the method has exception of type '%s'",
							throwType.getStruct().getSource().getClassname());
				}
			}
		}
	}

	protected static boolean checkAccess(Context context, IRValue rvalue, BMethod method, Token token) {
		switch (method.getStruct().getAccess()) {
		}
		switch (method.getAccess()) {
			case PRIVATE: {
				if (!method.getStruct().getSource().getClassname()
						.equals(context.getStruct().getSource().getClassname())) {
					return false;
				}
				if (method.isStatic()) {
					return true;
				}
				if (!(!method.isStatic() && rvalue instanceof RThis)) {
					return false;
				}
				break;
			}
			case PROTECTED: {
				if (!context.getStruct().canCastTo(method.getStruct())) {
					return false;
				}
				if (method.isStatic()) {
					return true;
				}
				if (!(!method.isStatic() && rvalue instanceof RThis)) {
					return false;
				}
				break;
			}
		}
		return true;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws ParseException
	 */
	@Override
	public void check(Context context) throws ParseException {
		this.left.check(context);
		for (IRValue param : this.params) {
			param.check(context);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void build(Context context, BinaryOutputStream output) throws IOException {
		BMethod method = this.getMethod();
		int oldStack = output.getStack();
		int stack = method.getParams().size();
		{
			if (!method.isStatic()) {
				stack++;
			}
			if (method.getReturns().size() > stack) {
				int delta = method.getReturns().size() - stack;
				output.printInc(delta, this.getToken());
				output.incStack(delta);
			}
		}
		if (!method.isStatic()) {
			left.build(context, output);
		}
		for (int n = this.params.size() - 1; n >= 0; n--) {
			this.params.get(n).build(context, output);
		}
		if (method.isStatic()) {
			output.printSJump(method.getIndex(), this.getName());
			output.decStack(method.getParams().size());
		} else {
			if (this.getLeft() instanceof RSuper) {
				output.printAJump(method.getParams().size(), method.getIndex(), this.getName());
			} else {
				output.printOJump(method.getParams().size(), method.getIndex(), this.getName());
			}
		}
		output.setStack(oldStack + method.getReturns().size());
	}

	public BMethod getMethod() {
		return method;
	}

	@Override
	public Token getToken() {
		return this.name;
	}

	public IRValue getLeft() {
		return left;
	}

	public Token getName() {
		return name;
	}

	public List<IRValue> getParams() {
		return params;
	}

}
