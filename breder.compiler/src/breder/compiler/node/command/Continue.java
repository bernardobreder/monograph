
package breder.compiler.node.command;

import java.io.IOException;

import breder.compiler.compiler.BinaryOutputStream;
import breder.compiler.compiler.Context;
import breder.compiler.exception.BrederJRuntimeException;
import breder.compiler.node.ICommand;
import breder.compiler.node.INode;
import breder.compiler.node.standart.Command;
import breder.compiler.parser.javacc.ParseException;
import breder.compiler.parser.javacc.Token;

public class Continue extends Command implements ICommand {

	private Token token;

	public Continue(Token token) {
		this.token = token;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void syntax(Context context) throws ParseException {
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws ParseException
	 */
	@Override
	public void semantic(Context context) throws ParseException {
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws ParseException
	 */
	@Override
	public void check(Context context) throws ParseException {
		{
			INode node = this.getParent();
			while (!(node instanceof While || node instanceof Repeat || node instanceof For)) {
				node = node.getParent();
				if (node == null) {
					throw new BrederJRuntimeException(context, getToken(), "continue without generic loop statement");
				}
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void build(Context context, BinaryOutputStream output) throws IOException {
		int index = context.getMethod().peekBeginLoop() + context.getMethod().peekBeginRepeat();
		output.printJumpAbs(index, this.getToken());
	}

	public Token getToken() {
		return token;
	}

}
