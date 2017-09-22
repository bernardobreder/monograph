
package breder.compiler.node.standart;

import breder.compiler.compiler.Context;
import breder.compiler.node.IValue;
import breder.compiler.parser.javacc.ParseException;
import breder.compiler.parser.javacc.Token;

public class BThisType extends BType {

	public BThisType(BStruct struct) {
		super(struct);
	}

	public BThisType(Token token, BStruct struct, boolean isGeneric, boolean notnull) {
		super(token, struct, isGeneric, notnull);
	}

	public BThisType(Token token, BStruct struct) {
		super(token, struct);
	}

	public BThisType(Token token) {
		super(token);
	}

	public BThisType(Token token, Token notnull) {
		super(token, null, false, notnull != null);
	}

	@Override
	public void syntax(Context context) throws ParseException {
		this.setStruct(context.getStruct());
		BBasicStruct bstruct = (BBasicStruct) this.getStruct();
		for (BGeneric generic : bstruct.getGenerics()) {
			this.addGeneric(new BType(generic.getToken(), generic.getType().getStruct(), true, true));
		}
		this.toString = null;
		super.syntax(context);
	}

	public BType getTypeGenericed(IValue value) {
		if (value != null) {
			return value.getType();
		} else {
			return this;
		}
	}

}
