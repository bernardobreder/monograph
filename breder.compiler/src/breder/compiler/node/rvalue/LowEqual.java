
package breder.compiler.node.rvalue;

import java.io.IOException;

import breder.compiler.compiler.BinaryOutputStream;
import breder.compiler.compiler.CompilerConstant;
import breder.compiler.compiler.Context;
import breder.compiler.node.IRValue;
import breder.compiler.parser.javacc.Token;

public class LowEqual extends CompareConditionalBinary {

	public LowEqual(IRValue left, IRValue right, Token operatorToken) {
		super(left, right, operatorToken);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getOperatorName() {
		return "operatorLowEqual";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void buildPrimitive(Context context, BinaryOutputStream output) throws IOException {
		if (this.getLeft().getType().getStruct().getSource().getClassname().equals(CompilerConstant.STRING_CLASS)) {
			output.printLowEqualString(this.getToken());
		} else {
			output.printLowEqual(this.getToken());
		}
	}

}
