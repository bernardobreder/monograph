
package breder.compiler.node.command;

import java.io.IOException;

import breder.compiler.compiler.BinaryOutputStream;
import breder.compiler.compiler.Context;
import breder.compiler.exception.BrederJRuntimeException;
import breder.compiler.node.ICommand;
import breder.compiler.node.IRValue;
import breder.compiler.node.standart.Command;
import breder.compiler.parser.javacc.ParseException;
import breder.compiler.parser.javacc.Token;

public class For extends Command implements ICommand {

	private Declare declare;

	private IRValue condition;

	private ICommand iterator;

	private Block block;

	private Token token;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void syntax(Context context) throws ParseException {
		context.pushBlock(this.getBlock());
		this.getBlock().setParent(this);
		if (this.declare != null) {
			this.declare.syntax(context);
		}
		if (this.condition != null) {
			this.condition.syntax(context);
		}
		if (this.iterator != null) {
			this.iterator.syntax(context);
		}
		this.block.syntax(context);
		context.popBlock();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws ParseException
	 */
	@Override
	public void semantic(Context context) throws ParseException {
		context.pushBlock(this.getBlock());
		if (this.declare != null) {
			this.declare.semantic(context);
		}
		if (this.condition != null) {
			this.condition.semantic(context);
		}
		if (this.iterator != null) {
			this.iterator.semantic(context);
		}
		this.block.semantic(context);
		context.popBlock();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws ParseException
	 */
	@Override
	public void check(Context context) throws ParseException {
		context.pushBlock(this.getBlock());
		if (this.declare != null) {
			this.declare.check(context);
		}
		if (this.condition != null) {
			if (this.condition.getTypes().length == 0) {
				throw new BrederJRuntimeException(context, this.condition.getToken(), "rvalue not return a value");
			}
			if (!this.condition.getType().getStruct().isBoolean()) {
				throw new BrederJRuntimeException(context, this.condition.getToken(),
						"condition of the if statement need be a boolean expression");
			}
			this.condition.check(context);
		}
		if (this.iterator != null) {
			this.iterator.check(context);
		}
		this.block.check(context);
		context.popBlock();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void build(Context context, BinaryOutputStream output) throws IOException {
		this.buildStartBeginEndStack(context, output);
		if (declare != null) {
			this.declare.build(context, output);
		}
		if (this.condition != null) {
			this.condition.build(context, output);
			int index;
			{
				index = this.block.instCount(context) + 2;
				if (this.iterator != null) {
					index += this.iterator.instCount(context);
				}
			}
			output.printJumpFalse(index, this.getCondition().getToken());
		}
		this.block.build(context, output);
		if (this.iterator != null) {
			output.setStack(0);
			this.iterator.build(context, output);
		}
		{
			int index;
			{
				index = this.block.instCount(context);
				if (this.condition != null) {
					index += this.condition.instCount(context) + 1;
				}
				if (this.iterator != null) {
					index += this.iterator.instCount(context);
				}
			}
			output.printJump(-index, this.getToken());
		}
		this.buildEndBeginEndStack(context, output);
	}

	private void buildStartBeginEndStack(Context context, BinaryOutputStream output) throws IOException {
		context.getMethod().pushBeginLoop(output.getCounter());
		int index = 0;
		if (this.getDeclare() != null) {
			index += this.getDeclare().instCount(context);
		}
		if (this.getIterator() == null) {
			context.getMethod().pushBeginRepeat(index);
		}
		index++;
		{
			if (this.getCondition() != null) {
				index += this.getCondition().instCount(context) + 1;
			}
			context.getMethod().pushEndRepeat(-1);
			if (context.getMethod().isEmptyBeginRepeat()) {
				context.getMethod().pushBeginRepeat(-1);
				index += this.getBlock().instCount(context);
				context.getMethod().popBeginRepeat();
			} else {
				index += this.getBlock().instCount(context);
			}
			context.getMethod().popEndRepeat();
			if (this.getIterator() != null) {
				context.getMethod().pushBeginRepeat(index - 1);
				index += this.getIterator().instCount(context);
			}
		}
		context.getMethod().pushEndRepeat(index);
	}

	private void buildEndBeginEndStack(Context context, BinaryOutputStream output) {
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

	public Declare getDeclare() {
		return declare;
	}

	public void setDeclare(Declare declares) {
		this.declare = declares;
	}

	public ICommand getIterator() {
		return iterator;
	}

	public void setIterator(ICommand iterator) {
		this.iterator = iterator;
	}

	public void setCondition(IRValue condition) {
		this.condition = condition;
	}

	public void setBlock(Block trueBlock) {
		this.block = trueBlock;
	}

	public Token getToken() {
		return token;
	}

	public void setToken(Token token) {
		this.token = token;
	}

}
