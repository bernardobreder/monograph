
package breder.compiler.node.lvalue;

import java.io.IOException;

import breder.compiler.compiler.BinaryOutputStream;
import breder.compiler.compiler.Context;
import breder.compiler.exception.BrederJRuntimeException;
import breder.compiler.parser.javacc.ParseException;
import breder.compiler.parser.javacc.Token;

public class LSetIdentify extends LIdentify {

	public LSetIdentify(Token name) {
		super(name);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void build(Context context, BinaryOutputStream output) throws IOException {
		if (this.index != null) {
			output.printStore(index, this.getToken());
		} else if (this.param != null) {
			throw new RuntimeException();
		} else {
		}
	}

	@Override
	public void syntax(Context context) throws ParseException {
		super.syntax(context);
		if (this.getDeclare() == null && this.index == null) {
			throw new BrederJRuntimeException(context, this.getName(), "not found the local and global identify");
		}
	}

}
