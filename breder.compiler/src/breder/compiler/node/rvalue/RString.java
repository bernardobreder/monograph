
package breder.compiler.node.rvalue;

import java.io.IOException;

import breder.compiler.compiler.BinaryOutputStream;
import breder.compiler.compiler.CompilerConstant;
import breder.compiler.compiler.Context;
import breder.compiler.node.standart.BSource;
import breder.compiler.node.standart.BType;
import breder.compiler.parser.javacc.ParseException;
import breder.compiler.parser.javacc.Token;

public class RString extends RPrimitive {

	private int index;

	private String content;

	public RString(Token token) {
		super(token);
		this.content = token.image.substring(1, token.image.length() - 1);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void syntax(Context context) throws ParseException {
		BSource source = context.getCompiler().findSource(CompilerConstant.STRING_CLASS);
		this.setTypes(new BType[] { new BType(source.getStruct().getName(), source.getStruct(), false, true) });
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void semantic(Context context) {
		index = context.getCompiler().addStringConstant(content);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void build(Context context, BinaryOutputStream output) throws IOException {
		output.printConstS(index, this.getToken());
	}

}
