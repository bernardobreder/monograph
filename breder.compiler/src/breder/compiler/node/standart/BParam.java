
package breder.compiler.node.standart;

import breder.compiler.compiler.Context;
import breder.compiler.parser.javacc.ParseException;
import breder.compiler.parser.javacc.Token;

public class BParam {

	private int index;

	private BType type;

	private Token name;

	public BParam() {
		this(null, null);
	}

	public BParam(BType type, Token name) {
		super();
		this.type = type;
		this.name = name;
	}

	public void syntax(Context context) throws ParseException {
		context.getCompiler().addConstant(name.image);
		type.syntax(context);
	}

	public void semantic(Context context) {
	}

	public void check(Context context) {
	}

	public BType getType() {
		return type;
	}

	public Token getName() {
		return name;
	}

	public void setName(Token name) {
		this.name = name;
	}

	public void setType(BType type) {
		this.type = type;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	@Override
	public String toString() {
		return type.toString();
	}

}
