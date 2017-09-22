package breder.compiler.node;

import breder.compiler.compiler.Context;
import breder.compiler.node.rvalue.RValue;
import breder.compiler.parser.javacc.ParseException;

public interface ILValue extends IValue {

	public RValue buildRValue(Context context) throws ParseException;

}
