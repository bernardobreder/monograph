
package breder.compiler.node.command;

import java.io.IOException;

import breder.compiler.compiler.BinaryOutputStream;
import breder.compiler.compiler.Context;
import breder.compiler.exception.BrederJRuntimeException;
import breder.compiler.node.ICommand;
import breder.compiler.node.IRValue;
import breder.compiler.node.rvalue.NotEqual;
import breder.compiler.node.rvalue.RNull;
import breder.compiler.node.standart.Command;
import breder.compiler.parser.javacc.ParseException;

public class While extends Command implements ICommand {

	private IRValue condition;

	private Block block;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void syntax(Context context) throws ParseException {
		this.condition.syntax(context);
		this.block.setParent(this);
		this.block.syntax(context);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws ParseException
	 */
	@Override
	public void semantic(Context context) throws ParseException {
		this.condition.semantic(context);
		if (this.condition.getTypes().length == 0) {
			throw new BrederJRuntimeException(context, this.condition.getToken(), "rvalue not return a value");
		}
		if (!this.condition.getType().getStruct().isBoolean()) {
			this.condition = new NotEqual(this.condition, new RNull(null), null);
		}
		this.block.semantic(context);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws ParseException
	 */
	@Override
	public void check(Context context) throws ParseException {
		this.condition.check(context);
		if (!this.condition.getType().getStruct().isBoolean()) {
			throw new BrederJRuntimeException(context, this.condition.getToken(),
					"condition of the while statement need be a boolean expression");
		}
		this.block.check(context);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void build(Context context, BinaryOutputStream output) throws IOException {
		this.buildStartBeginEndStack(context, output);
		this.condition.build(context, output);
		output.printJumpFalse(this.block.instCount(context) + 2, this.getBlock().getEndToken());
		this.block.build(context, output);
		output.printJump(-(this.block.instCount(context) + this.condition.instCount(context) + 1), this.getCondition()
				.getToken());
		this.buildEndBeginEndStack(context, output);
	}

	protected void buildStartBeginEndStack(Context context, BinaryOutputStream output) throws IOException {
		context.getMethod().pushBeginLoop(output.getCounter());
		int index = 0;
		context.getMethod().pushBeginRepeat(index);
		index++;
		{
			if (this.condition != null) {
				index += this.condition.instCount(context) + 1;
			}
			context.getMethod().pushEndRepeat(-1);
			index += this.block.instCount(context);
			context.getMethod().popEndRepeat();
		}
		context.getMethod().pushEndRepeat(index);
	}

	protected void buildEndBeginEndStack(Context context, BinaryOutputStream output) {
		context.getMethod().popBeginRepeat();
		context.getMethod().popEndRepeat();
		context.getMethod().popBeginLoop();
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

	public IRValue getCondition() {
		return condition;
	}

	public Block getBlock() {
		return block;
	}

	public void setCondition(IRValue condition) {
		this.condition = condition;
	}

	public void setBlock(Block trueBlock) {
		this.block = trueBlock;
	}

}
