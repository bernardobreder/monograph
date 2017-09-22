
package breder.compiler.node.rvalue;

import breder.compiler.compiler.Context;
import breder.compiler.exception.OperatorUndefinedException;
import breder.compiler.node.IRValue;
import breder.compiler.node.standart.BType;
import breder.compiler.parser.javacc.ParseException;
import breder.compiler.parser.javacc.Token;

public abstract class CompareConditionalBinary extends ConditionalBinary {

	public CompareConditionalBinary(IRValue left, IRValue right, Token operatorToken) {
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

}
