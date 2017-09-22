
package breder.compiler.node.rvalue;

import java.io.IOException;

import breder.compiler.compiler.BinaryOutputStream;
import breder.compiler.compiler.Context;
import breder.compiler.exception.BrederJRuntimeException;
import breder.compiler.node.standart.BStruct;
import breder.compiler.node.standart.BType;
import breder.compiler.parser.javacc.ParseException;
import breder.compiler.parser.javacc.Token;

public class RThis extends RValue {

	private final Token token;

	private int index;

	public RThis(Token token) {
		super();
		this.token = token;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void build(Context context, BinaryOutputStream output) throws IOException {
		output.printLoad(index, this.getToken());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void check(Context context) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void semantic(Context context) {
		index = context.getMethod().getDeclares().size() + context.getMethod().getParams().size();

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void syntax(Context context) throws ParseException {
		if (context.getMethod().isStaticOnly()) {
			throw new BrederJRuntimeException(context, this.getToken(), "can not use 'this' in a static method");
		}
		BStruct struct = context.getStruct();
		this.setTypes(new BType[] { new BType(struct.getName(), struct, false, true) });
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Token getToken() {
		return token;
	}

}
