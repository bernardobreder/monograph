
package breder.compiler.node.rvalue;

import java.io.IOException;

import breder.compiler.compiler.BinaryOutputStream;
import breder.compiler.compiler.Context;
import breder.compiler.exception.BrederJRuntimeException;
import breder.compiler.node.IRValue;
import breder.compiler.parser.javacc.Token;

public class Equal extends EqualConditionalBinary {

	public Equal(IRValue left, IRValue right, Token operatorToken) {
		super(left, right, operatorToken);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getOperatorName() {
		return "operatorEqual";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void buildPrimitive(Context context, BinaryOutputStream output) throws IOException {
		if (this.isPrimitiveBoolean(this.getLeft())) {
			output.printEqualBoolean(this.getToken());
		} else if (this.isPrimitiveString(this.getLeft())) {
			output.printEqualString(this.getToken());
		} else if (this.isPrimitiveNumber(this.getLeft())) {
			output.printEqualNumber(this.getToken());
		} else {
			throw new BrederJRuntimeException(context, this.getToken(), "not expected");
		}
	}

	@Override
	protected void buildPrimitiveCompare(BinaryOutputStream output) throws IOException {
		output.printIsEqual(this.getToken());
	}

}
