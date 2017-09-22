
package breder.compiler.node.rvalue;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import breder.compiler.compiler.BinaryOutputStream;
import breder.compiler.compiler.CompilerConstant;
import breder.compiler.compiler.Context;
import breder.compiler.exception.BrederJRuntimeException;
import breder.compiler.node.IRValue;
import breder.compiler.node.IValue;
import breder.compiler.node.standart.BMethod;
import breder.compiler.node.standart.BType;
import breder.compiler.node.standart.NullStruct;
import breder.compiler.parser.javacc.ParseException;
import breder.compiler.parser.javacc.Token;
import breder.compiler.parser.javacc.TokenUtil;

public abstract class Binary extends Unary {

	protected IRValue right;

	private BMethod method;

	public Binary(IRValue left, IRValue right, Token token) {
		super(left, token);
		this.right = right;
	}

	public abstract String getOperatorName();

	public BType getReturnType(Context context) throws ParseException {
		if (this.getLeft() instanceof RNull || this.getRight() instanceof RNull) {
			return new BType(null, context.getCompiler().findSource(CompilerConstant.BOOLEAN_CLASS).getStruct(), false,
					false);
		}
		if (this.getLeft().getTypes().length == 0) {
			throw new BrederJRuntimeException(context, this.getLeft().getToken(), "lvalue not return a value");
		}
		if (this.getRight().getTypes().length == 0) {
			throw new BrederJRuntimeException(context, this.getRight().getToken(), "rvalue not return a value");
		}
		BType ltype = this.getLeft().getType();
		BType rtype = this.getRight().getType();
		Token name = TokenUtil.newInstance(this.getOperatorName());
		List<BType> params = Arrays.asList(rtype);
		List<BMethod> list = ltype.getStruct().findMethod(context, this.getLeft(), name, params, false);
		if (list.size() == 0) {
			throw new BrederJRuntimeException(context, this.getToken(), "not found the %s", name.image);
		}
		this.method = this.getLeft().getType().getStruct().getMethodClosed(context, name, list, params);
		if (method.getReturns().size() > 1) {
			throw new BrederJRuntimeException(context, this.getToken(), "method return more than one value");
		} else if (method.getReturns().size() == 0) {
			return null;
		} else {
			return method.getReturns().get(0);
		}
	}

	public abstract void buildPrimitive(Context context, BinaryOutputStream output) throws IOException;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void syntax(Context context) throws ParseException {
		super.syntax(context);
		this.getRight().setParent(this);
		this.getRight().syntax(context);
		if (this.getLeft() instanceof RNull) {
			throw new BrederJRuntimeException(context, this.getToken(), "can not use operator with value null");
		}
		if (this.getLeft().getTypes().length == 0 || this.getRight().getTypes().length == 0) {
			this.throwWrongArgument(context);
		}
		if (!this.getLeft().getType().isNotNull() && (this.getRight() instanceof RNull == false)
				&& (this instanceof Equal == false) && (this instanceof NotEqual == false)) {
			throw new BrederJRuntimeException(context, this.getLeft().getToken(),
					"primitive operator with lvalue nullable");
		}
		if (!this.getRight().getType().isNotNull() && this.getRight() instanceof RNull == false
				&& this instanceof Equal == false && this instanceof NotEqual == false) {
			throw new BrederJRuntimeException(context, this.getRight().getToken(),
					"primitive operator with rvalue nullable");
		}
		if (this.isPrimitive(this.getLeft())) {
			if (checkLeftClass(CompilerConstant.NUMBER_CLASS) || checkLeftClass(CompilerConstant.INTEGER_CLASS)
					|| checkLeftClass(CompilerConstant.NATURAL_CLASS) || checkLeftClass(CompilerConstant.INDEX_CLASS)) {
				if (!(checkRightClass(CompilerConstant.NUMBER_CLASS)
						|| !checkRightClass(CompilerConstant.INTEGER_CLASS)
						|| !checkRightClass(CompilerConstant.NATURAL_CLASS) || !checkRightClass(CompilerConstant.INDEX_CLASS))) {
					this.throwWrongArgument(context);
				}
			} else if (checkLeftClass(CompilerConstant.STRING_CLASS)) {
				if (!checkRightClass(CompilerConstant.STRING_CLASS)) {
					Call call = new Call(this.getRight(), TokenUtil.newInstance("toString"));
					call.syntax(context);
					this.setRight(call);
				}
			} else if (checkLeftClass(CompilerConstant.BOOLEAN_CLASS)) {
				if (!checkRightClass(CompilerConstant.BOOLEAN_CLASS)) {
					this.throwWrongArgument(context);
				}
			}
		}
		BType rtype = this.getReturnType(context);
		if (this.getRight() instanceof RNull && (this instanceof Equal || this instanceof NotEqual)) {
			rtype.setNotNull(true);
		}
		if (rtype != null) {
			this.setTypes(new BType[] { rtype });
		} else {
			this.setTypes(new BType[] {});
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws ParseException
	 */
	@Override
	public void semantic(Context context) throws ParseException {
		this.getLeft().semantic(context);
		this.getRight().semantic(context);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws ParseException
	 */
	@Override
	public void check(Context context) throws ParseException {
		this.getLeft().check(context);
		this.right.check(context);
		if (this.isPrimitive(this.getLeft())) {
			if (checkLeftClass(CompilerConstant.NUMBER_CLASS) || checkLeftClass(CompilerConstant.INTEGER_CLASS)
					|| checkLeftClass(CompilerConstant.NATURAL_CLASS) || checkLeftClass(CompilerConstant.INDEX_CLASS)) {
				if (!(checkRightClass(CompilerConstant.NUMBER_CLASS)
						|| !checkRightClass(CompilerConstant.INTEGER_CLASS)
						|| !checkRightClass(CompilerConstant.NATURAL_CLASS) || !checkRightClass(CompilerConstant.INDEX_CLASS))) {
					this.throwWrongArgument(context);
				}
			} else if (checkLeftClass(CompilerConstant.STRING_CLASS)) {
				if (!checkRightClass(CompilerConstant.STRING_CLASS)) {
					this.throwWrongArgument(context);
				}
			} else if (checkLeftClass(CompilerConstant.BOOLEAN_CLASS)) {
				if (!checkRightClass(CompilerConstant.BOOLEAN_CLASS)) {
					this.throwWrongArgument(context);
				}
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void build(Context context, BinaryOutputStream output) throws IOException {
		IRValue left = this.getLeft();
		IRValue right = this.getRight();
		left.build(context, output);
		right.build(context, output);
		this.buildOperator(context, output);
	}

	protected void buildOperator(Context context, BinaryOutputStream output) throws IOException {
		if (this.isPrimitive(this.getLeft())) {
			this.buildPrimitive(context, output);
		} else {
			output.printOJump(1, this.method.getIndex(), this.getToken());
		}
	}

	protected void throwWrongArgument(Context context) {
		throw new BrederJRuntimeException(context, this.getToken(), "operator with wrong argument");
	}

	protected boolean checkLeftClass(String classname) {
		return this.getLeft().getType().getStruct().getClassname().equals(classname);
	}

	protected boolean checkRightClass(String classname) {
		return this.getRight().getType().getStruct() instanceof NullStruct
				|| this.getRight().getType().getStruct().getClassname().equals(classname);
	}

	public IRValue getRight() {
		return right;
	}

	public void setRight(IRValue right) {
		this.right = right;
	}

	public boolean isPrimitive(IValue value) {
		String name = value.getType().getStruct().getSource().getClassname();
		return value instanceof RNull || CompilerConstant.BOOLEAN_CLASS.equals(name)
				|| CompilerConstant.STRING_CLASS.equals(name) || this.isPrimitiveNumber(value);
	}

	public boolean isPrimitiveNumber(IValue value) {
		String name = value.getType().getStruct().getSource().getClassname();
		return value instanceof RNull || CompilerConstant.NUMBER_CLASS.equals(name) || this.isPrimitiveInteger(value);
	}

	public boolean isPrimitiveInteger(IValue value) {
		String name = value.getType().getStruct().getSource().getClassname();
		return CompilerConstant.INTEGER_CLASS.equals(name) || this.isPrimitiveNatural(value);
	}

	public boolean isPrimitiveNatural(IValue value) {
		String name = value.getType().getStruct().getSource().getClassname();
		return CompilerConstant.NATURAL_CLASS.equals(name) || this.isPrimitiveIndex(value);
	}

	public boolean isPrimitiveIndex(IValue value) {
		String name = value.getType().getStruct().getSource().getClassname();
		return CompilerConstant.INDEX_CLASS.equals(name);
	}

	public boolean isPrimitiveString(IValue value) {
		String name = value.getType().getStruct().getSource().getClassname();
		return value instanceof RNull || CompilerConstant.STRING_CLASS.equals(name);
	}

	public boolean isPrimitiveBoolean(IValue value) {
		String name = value.getType().getStruct().getSource().getClassname();
		return CompilerConstant.BOOLEAN_CLASS.equals(name);
	}
}
