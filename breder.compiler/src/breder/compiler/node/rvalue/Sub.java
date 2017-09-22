
package breder.compiler.node.rvalue;

import java.io.IOException;

import breder.compiler.compiler.BinaryOutputStream;
import breder.compiler.compiler.CompilerConstant;
import breder.compiler.compiler.Context;
import breder.compiler.exception.OperatorUndefinedException;
import breder.compiler.node.IRValue;
import breder.compiler.node.standart.BType;
import breder.compiler.parser.javacc.ParseException;
import breder.compiler.parser.javacc.Token;

public class Sub extends ValuableBinary {

	public Sub(IRValue left, IRValue right, Token operatorToken) {
		super(left, right, operatorToken);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getOperatorName() {
		return "operatorSub";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BType getReturnType(Context context) throws ParseException {
		if (this.isPrimitive(this.getLeft())) {
			String classname;
			if (this.isPrimitiveInteger(this.getLeft())) {
				if (this.isPrimitiveInteger(this.getRight())) {
					classname = CompilerConstant.INTEGER_CLASS;
				} else if (this.isPrimitiveNumber(this.getRight())) {
					classname = CompilerConstant.NUMBER_CLASS;
				} else {
					throw new OperatorUndefinedException(context, this.getToken());
				}
			} else if (this.isPrimitiveNumber(this.getLeft())) {
				if (this.isPrimitiveNumber(this.getRight())) {
					classname = CompilerConstant.NUMBER_CLASS;
				} else {
					throw new OperatorUndefinedException(context, this.getToken());
				}
			} else {
				throw new OperatorUndefinedException(context, this.getToken());
			}
			boolean notnull = this.getLeft().getType().isNotNull() && this.getRight().getType().isNotNull();
			return new BType(null, context.getCompiler().findSource(classname).getStruct(), false, notnull);
		} else {
			return super.getReturnType(context);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void buildPrimitive(Context context, BinaryOutputStream output) throws IOException {
		output.printSub(this.getToken());
	}

}
