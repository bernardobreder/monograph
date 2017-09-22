
package breder.compiler.node.command;

import java.io.IOException;

import breder.compiler.compiler.BinaryOutputStream;
import breder.compiler.compiler.Context;
import breder.compiler.node.ICommand;

public class Repeat extends While {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void build(Context context, BinaryOutputStream output) throws IOException {
		this.buildStartBeginEndStack(context, output);
		int index = output.getCounter();
		this.getBlock().build(context, output);
		this.getCondition().build(context, output);
		output.printJumpTrueAbs(index, this.getCondition().getToken());
		this.buildEndBeginEndStack(context, output);
	}

	protected void buildStartBeginEndStack(Context context, BinaryOutputStream output) throws IOException {
		context.getMethod().pushBeginLoop(output.getCounter());
		int index = 0;
		context.getMethod().pushBeginRepeat(index);
		index++;
		{
			if (this.getCondition() != null) {
				index += this.getCondition().instCount(context);
			}
			context.getMethod().pushEndRepeat(-1);
			index += this.getBlock().instCount(context);
			context.getMethod().popEndRepeat();
		}
		context.getMethod().pushEndRepeat(index);
	}

	@Override
	public boolean isReturned() {
		for (ICommand command : this.getBlock().getCommands()) {
			if (command.isReturned()) {
				return true;
			}
		}
		return false;
	}

}
