
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

public class Div extends ValuableBinary {

	public Div(IRValue left, IRValue right, Token operatorToken) {
		super(left, right, operatorToken);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getOperatorName() {
		return "operatorDiv";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BType getReturnType(Context context) throws ParseException {
		if (this.isPrimitive(this.getLeft())) {
			String classname;
			if (this.isPrimitiveNumber(this.getLeft())) {
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
		output.printDiv(this.getToken());
	}

}
