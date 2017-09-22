
package breder.compiler.node.rvalue;

import breder.compiler.compiler.Context;
import breder.compiler.node.IRValue;
import breder.compiler.parser.javacc.ParseException;
import breder.compiler.parser.javacc.Token;

public abstract class Unary extends RValue {

	protected IRValue left;

	private Token token;

	public Unary(IRValue left, Token token) {
		this.left = left;
		this.token = token;
	}

	@Override
	public void check(Context context) throws ParseException {
		this.getLeft().check(context);
		this.setTypes(this.getLeft().getTypes());
	}

	@Override
	public void semantic(Context context) throws ParseException {
		this.getLeft().semantic(context);
	}

	@Override
	public void syntax(Context context) throws ParseException {
		this.getLeft().setParent(this);
		this.getLeft().syntax(context);
	}

	public IRValue getLeft() {
		return left;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Token getToken() {
		return token;
	}

}
