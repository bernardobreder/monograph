
package breder.compiler.node.command;

import java.io.IOException;
import java.util.List;

import breder.compiler.compiler.BinaryOutputStream;
import breder.compiler.compiler.Context;
import breder.compiler.exception.BrederJRuntimeException;
import breder.compiler.node.ICommand;
import breder.compiler.node.standart.BFacateBinaryOutputStream;
import breder.compiler.node.standart.Command;
import breder.compiler.node.standart.VariableDeclare;
import breder.compiler.parser.javacc.ParseException;
import breder.compiler.parser.javacc.Token;
import breder.compiler.util.LightArrayList;

public class Block extends Command implements ICommand {

	private Token beginToken, endToken;

	private final List<ICommand> commands = new LightArrayList<ICommand>();

	private final List<VariableDeclare> variables = new LightArrayList<VariableDeclare>();

	private Block blockParent;

	private Return returnCommand;

	public void syntax(Context context) throws ParseException {
		context.pushBlock(this);
		for (ICommand command : commands) {
			command.setParent(this);
			if (command instanceof Return) {
				this.returnCommand = (Return) command;
			}
			command.syntax(context);
		}
		context.popBlock();
		if (context.getMethod().getReturns().size() > 0 && this.getParent() == null) {
			if (!this.isReturned()) {
				throw new BrederJRuntimeException(context, context.getMethod().getNameToken(),
						"not found return one of block of the method");
			}
		}
	}

	public void semantic(Context context) throws ParseException {
		context.pushBlock(this);
		for (ICommand command : commands) {
			command.semantic(context);
		}
		context.popBlock();
	}

	public void check(Context context) throws ParseException {
		for (ICommand command : commands) {
			command.check(context);
		}
		{
			for (VariableDeclare param1 : this.variables) {
				for (VariableDeclare param2 : this.variables) {
					if (param1 != param2) {
						if (param1.getName().image.equals(param2.getName().image)) {
							throw new BrederJRuntimeException(context, param2.getName(), "variable already declared");
						}
					}
				}
			}
		}
	}

	public void build(Context context, BinaryOutputStream output) throws IOException {
		for (ICommand command : commands) {
			command.build(context, output);
			output.setStack(0);
		}
	}

	public int instCount(Context context) throws IOException {
		BinaryOutputStream output = new BFacateBinaryOutputStream(context);
		for (ICommand command : commands) {
			command.build(context, output);
		}
		return output.getCounter();
	}

	public void addVariable(VariableDeclare variable) {
		this.variables.add(variable);
	}

	public List<VariableDeclare> getVariables() {
		return variables;
	}

	public List<ICommand> getCommands() {
		return commands;
	}

	public Return getReturnCommand() {
		return returnCommand;
	}

	public Block getBlockParent() {
		return blockParent;
	}

	public void setBlockParent(Block parent) {
		this.blockParent = parent;
	}

	public VariableDeclare findDeclare(String name) {
		for (VariableDeclare vd : this.variables) {
			if (vd.getName().image.equals(name)) {
				return vd;
			}
		}
		return null;
	}

	@Override
	public boolean isReturned() {
		for (ICommand command : this.getCommands()) {
			if (command.isReturned()) {
				return true;
			}
		}
		return false;
	}

	public Token getEndToken() {
		return endToken;
	}

	public void setEndToken(Token endToken) {
		this.endToken = endToken;
	}

	public void setReturnCommand(Return returnCommand) {
		this.returnCommand = returnCommand;
	}

	public Token getBeginToken() {
		return beginToken;
	}

	public void setBeginToken(Token beginToken) {
		this.beginToken = beginToken;
	}

}
