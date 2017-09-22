
package breder.compiler.node.command;

import java.io.IOException;

import breder.compiler.compiler.BinaryOutputStream;
import breder.compiler.compiler.Context;
import breder.compiler.exception.BrederJRuntimeException;
import breder.compiler.node.ICommand;
import breder.compiler.node.INode;
import breder.compiler.node.IRValue;
import breder.compiler.node.standart.BMethod;
import breder.compiler.node.standart.BType;
import breder.compiler.node.standart.Command;
import breder.compiler.node.standart.VariableDeclare;
import breder.compiler.parser.javacc.ParseException;

public class Throw extends Command implements ICommand {

	private IRValue value;

	private int index;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void syntax(Context context) throws ParseException {
		this.value.syntax(context);
		if (!value.getType().canCastTo(context, null,
				new BType(context.getCompiler().findSource("breder.lang.standard.Throw").getStruct()))) {
			throw new BrederJRuntimeException(context, this.value.getToken(), "only Throw instance");
		}
		this.index = BMethod.returnPop(context);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws ParseException
	 */
	@Override
	public void semantic(Context context) throws ParseException {
		this.value.semantic(context);
		if (value.getTypes().length == 0) {
			throw new BrederJRuntimeException(context, value.getToken(), "rvalue not return a value");
		}
	}

	private void checkException(Context context) throws ParseException {
		BType type = this.value.getType();
		if (!type.canCastTo(context, null, context.getCompiler().findType("breder.lang.standard.CompileException"))) {
			return;
		}
		INode parent = this.getParent();
		while (parent != null) {
			if (parent instanceof Try) {
				Try tryNode = (Try) parent;
				for (VariableDeclare declare : tryNode.getCatchs()) {
					if (type.canCastTo(context, null, declare.getType())) {
						return;
					}
				}
			}
			parent = parent.getParent();
		}
		for (BType mtype : context.getMethod().getThrowses()) {
			if (type.canCastTo(context, null, mtype)) {
				return;
			}
		}
		throw new BrederJRuntimeException(context, this.getValue().getToken(), "exception not checked");
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws ParseException
	 */
	@Override
	public void check(Context context) throws ParseException {
		this.checkException(context);
		this.value.check(context);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void build(Context context, BinaryOutputStream output) throws IOException {
		this.value.build(context, output);
		output.printThrow(index, this.getValue().getToken());
	}

	@Override
	public boolean isReturned() {
		return true;
	}

	public IRValue getValue() {
		return value;
	}

	public void setValue(IRValue condition) {
		this.value = condition;
	}

}
