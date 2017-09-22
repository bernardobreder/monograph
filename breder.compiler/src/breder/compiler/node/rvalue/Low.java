
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

public class Low extends CompareConditionalBinary {

	public Low(IRValue left, IRValue right, Token operatorToken) {
		super(left, right, operatorToken);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BType getReturnType(Context context) throws ParseException {
		if (this.isPrimitive(this.getLeft())) {
			if (this.isPrimitiveNumber(this.getLeft())) {
				if (!this.isPrimitiveNumber(this.getRight())) {
					throw new OperatorUndefinedException(context, this.getToken());
				}
			} else if (this.isPrimitiveString(this.getLeft())) {
				if (!this.isPrimitiveString(this.getRight())) {
					throw new OperatorUndefinedException(context, this.getToken());
				}
			} else {
				throw new OperatorUndefinedException(context, this.getToken());
			}
		}
		return super.getReturnType(context);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getOperatorName() {
		return "operatorLow";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void buildPrimitive(Context context, BinaryOutputStream output) throws IOException {
		if (this.getLeft().getType().getStruct().getSource().getClassname().equals(CompilerConstant.STRING_CLASS)) {
			output.printLowString(this.getToken());
		} else {
			output.printLow(this.getToken());
		}
	}

}
