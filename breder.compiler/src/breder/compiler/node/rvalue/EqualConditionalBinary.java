
package breder.compiler.node.rvalue;

import java.io.IOException;

import breder.compiler.compiler.BinaryOutputStream;
import breder.compiler.compiler.Context;
import breder.compiler.node.IRValue;
import breder.compiler.parser.javacc.Token;

public abstract class EqualConditionalBinary extends ConditionalBinary {

	public EqualConditionalBinary(IRValue left, IRValue right, Token operatorToken) {
		super(left, right, operatorToken);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void build(Context context, BinaryOutputStream output) throws IOException {
		IRValue left = this.getLeft();
		IRValue right = this.getRight();
		left.build(context, output);
		if (right instanceof RNull) {
			this.buildPrimitiveCompare(output);
		} else {
			right.build(context, output);
			this.buildOperator(context, output);
		}
	}

	protected abstract void buildPrimitiveCompare(BinaryOutputStream output) throws IOException;

}
