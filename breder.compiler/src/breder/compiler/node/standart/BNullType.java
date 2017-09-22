
package breder.compiler.node.standart;

import breder.compiler.compiler.Context;
import breder.compiler.node.IValue;
import breder.compiler.parser.javacc.Token;

public class BNullType extends BType {

	public BNullType(Token token) {
		super(token, new NullStruct());
	}

	@Override
	public boolean canCastTo(Context context, IValue value, BType type) {
		return true;
	}

}
