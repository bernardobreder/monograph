
package breder.compiler.node.rvalue;

import java.io.IOException;

import breder.compiler.compiler.BinaryOutputStream;
import breder.compiler.compiler.CompilerConstant;
import breder.compiler.compiler.Context;
import breder.compiler.node.standart.BSource;
import breder.compiler.node.standart.BType;
import breder.compiler.parser.javacc.ParseException;
import breder.compiler.parser.javacc.Token;

public class RBoolean extends RPrimitive {

	private int index;

	public RBoolean(Token token) {
		super(token);
		if (token.image.equals("true")) {
			index = 1;
		} else {
			index = 0;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void syntax(Context context) throws ParseException {
		BSource source = context.getCompiler().findSource(CompilerConstant.BOOLEAN_CLASS);
		this.setTypes(new BType[] { new BType(source.getStruct().getName(), source.getStruct(), false, true) });
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
		output.printConstB(index, this.getToken());
	}

}
