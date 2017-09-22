
package breder.compiler.node.rvalue;

import java.io.IOException;

import breder.compiler.compiler.BinaryOutputStream;
import breder.compiler.compiler.Context;
import breder.compiler.exception.BrederJRuntimeException;
import breder.compiler.node.IRValue;
import breder.compiler.parser.javacc.Token;

public class NotEqual extends EqualConditionalBinary {

	public NotEqual(IRValue left, IRValue right, Token operatorToken) {
		super(left, right, operatorToken);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getOperatorName() {
		return "operatorNotEqual";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void buildPrimitive(Context context, BinaryOutputStream output) throws IOException {
		if (this.isPrimitiveBoolean(this.getLeft())) {
			output.printNotEqualBoolean(this.getToken());
		} else if (this.isPrimitiveString(this.getLeft())) {
			output.printNotEqualString(this.getToken());
		} else if (this.isPrimitiveNumber(this.getLeft())) {
			output.printNotEqualNumber(this.getToken());
		} else {
			throw new BrederJRuntimeException(context, this.getToken(), "not expected");
		}
	}

	@Override
	protected void buildPrimitiveCompare(BinaryOutputStream output) throws IOException {
		output.printIsNotEqual(this.getToken());
	}

}
