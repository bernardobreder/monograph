
package breder.compiler.node.command;

import java.io.IOException;

import breder.compiler.compiler.BinaryOutputStream;
import breder.compiler.compiler.Context;
import breder.compiler.node.ICommand;
import breder.compiler.node.IRValue;
import breder.compiler.node.standart.Command;
import breder.compiler.parser.javacc.ParseException;

public class Expression extends Command implements ICommand {

	private final IRValue value;

	public Expression(IRValue value) {
		super();
		this.value = value;
	}

	@Override
	public void build(Context context, BinaryOutputStream output) throws IOException {
		value.build(context, output);
		output.setStack(0);
		{
			int size = value.getTypes().length;
			if (size > 0) {
				output.printDec(size, this.getValue().getToken());
				output.decStack(size);
			}
		}
	}

	@Override
	public void check(Context context) throws ParseException {
		value.check(context);
	}

	@Override
	public void semantic(Context context) throws ParseException {
		value.semantic(context);
	}

	@Override
	public void syntax(Context context) throws ParseException {
		this.value.setParent(this);
		value.syntax(context);
	}

	public IRValue getValue() {
		return value;
	}

}
