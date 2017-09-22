
package breder.compiler.node.rvalue;

import breder.compiler.node.IRValue;
import breder.compiler.parser.javacc.Token;

public abstract class ValuableBinary extends Binary {

	public ValuableBinary(IRValue left, IRValue right, Token operatorToken) {
		super(left, right, operatorToken);
	}

}
