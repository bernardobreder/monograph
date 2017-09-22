
package breder.compiler.node.lvalue;

import java.io.IOException;

import breder.compiler.compiler.BinaryOutputStream;
import breder.compiler.compiler.Context;
import breder.compiler.exception.BrederJRuntimeException;
import breder.compiler.node.rvalue.RThis;
import breder.compiler.node.rvalue.RValue;
import breder.compiler.node.standart.BStruct;
import breder.compiler.node.standart.BType;
import breder.compiler.parser.javacc.ParseException;
import breder.compiler.parser.javacc.Token;

public class LThis extends LValue {

	private Token token;

	private int index;

	public LThis(Token token) {
		super();
		this.token = token;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void syntax(Context context) {
		if (context.getMethod().isStaticOnly()) {
			throw new BrederJRuntimeException(context, this.getToken(), "can not use 'this' in a static method");
		}
		{
			int index = 0;
			index += context.getMethod().getParams().size();
			index += context.getMethod().getDeclares().size();
			this.index = index;
		}
		{
			BStruct struct = context.getStruct();
			this.setTypes(new BType[] { new BType(struct.getName(), struct) });
		}
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

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void build(Context context, BinaryOutputStream output) throws IOException {
		output.printLoad(index, this.getToken());
	}

	@Override
	public Token getToken() {
		return token;
	}

	@Override
	public RValue buildRValue(Context context) throws ParseException {
		return new RThis(token);
	}

}
