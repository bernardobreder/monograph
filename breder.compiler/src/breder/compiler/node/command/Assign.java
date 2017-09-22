
package breder.compiler.node.command;

import java.io.IOException;
import java.util.List;

import breder.compiler.compiler.BinaryOutputStream;
import breder.compiler.compiler.Context;
import breder.compiler.exception.BrederJRuntimeException;
import breder.compiler.node.ICommand;
import breder.compiler.node.ILValue;
import breder.compiler.node.IRValue;
import breder.compiler.node.lvalue.LSetIdentify;
import breder.compiler.node.rvalue.Div;
import breder.compiler.node.rvalue.Mul;
import breder.compiler.node.rvalue.RIdentify;
import breder.compiler.node.rvalue.RNumber;
import breder.compiler.node.rvalue.Sub;
import breder.compiler.node.rvalue.Sum;
import breder.compiler.node.standart.BType;
import breder.compiler.node.standart.Command;
import breder.compiler.parser.javacc.ParseException;
import breder.compiler.parser.javacc.Token;
import breder.compiler.util.LightArrayList;

public class Assign extends Command implements ICommand {

	private final List<ILValue> lvalues;

	private final List<IRValue> rvalues;

	private AssignType type;

	private Token token;

	public Assign() {
		this(new LightArrayList<ILValue>(), new LightArrayList<IRValue>());
	}

	public Assign(List<ILValue> lvalues, List<IRValue> rvalues) {
		this.type = AssignType.NORMAL;
		this.lvalues = lvalues;
		this.rvalues = rvalues;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void syntax(Context context) throws ParseException {
		for (int n = 0; n < lvalues.size(); n++) {
			ILValue lvalue = this.lvalues.get(n);
			IRValue rvalue = this.rvalues.get(n);
			lvalue.setParent(this);
			rvalue.setParent(this);
			switch (type) {
				case SUM: {
					IRValue newrvalue = new Sum(lvalue.buildRValue(context), rvalue, token);
					rvalues.set(n, newrvalue);
					break;
				}
				case SUB: {
					IRValue newrvalue = new Sub(lvalue.buildRValue(context), rvalue, token);
					rvalues.set(n, newrvalue);
					break;
				}
				case MUL: {
					IRValue newrvalue = new Mul(lvalue.buildRValue(context), rvalue, token);
					rvalues.set(n, newrvalue);
					break;
				}
				case DIV: {
					IRValue newrvalue = new Div(lvalue.buildRValue(context), rvalue, token);
					rvalues.set(n, newrvalue);
					break;
				}
			}
		}
		for (IRValue value : rvalues) {
			value.syntax(context);
		}
		for (ILValue value : lvalues) {
			value.syntax(context);
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws ParseException
	 */
	@Override
	public void semantic(Context context) throws ParseException {
		for (IRValue value : rvalues) {
			value.semantic(context);
		}
		for (ILValue value : lvalues) {
			value.semantic(context);
		}
		{
			int size = Math.min(rvalues.size(), lvalues.size());
			for (int n = 0; n < size; n++) {
				IRValue rvalue = rvalues.get(n);
				ILValue lvalue = lvalues.get(n);
				if (rvalue.getTypes().length == 0) {
					throw new BrederJRuntimeException(context, rvalue.getToken(), "rvalue not return a value");
				}
				BType rtype = rvalue.getType();
				BType ltype = lvalue.getType();
				if (!rtype.canCastTo(context, rvalue, ltype)) {
					throw new BrederJRuntimeException(context, rvalues.get(n).getToken(), "cannot cast %s to %s",
							rtype, ltype);
				}
				ltype.setNotNull(rtype.isNotNull());
			}
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws ParseException
	 */
	@Override
	public void check(Context context) throws ParseException {
		for (IRValue value : rvalues) {
			value.check(context);
		}
		for (ILValue value : lvalues) {
			value.check(context);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void build(Context context, BinaryOutputStream output) throws IOException {
		int size = this.rvalues.size();
		for (int n = 0; n < size; n++) {
			IRValue rvalue = this.getRValues().get(n);
			if (n < this.lvalues.size()) {
				ILValue lvalue = this.getLValues().get(size - n - 1);
				if (lvalue instanceof LSetIdentify && rvalue instanceof Sum
						&& ((Sum) rvalue).getLeft() instanceof RIdentify
						&& ((RIdentify) ((Sum) rvalue).getLeft()).getIndex() == ((LSetIdentify) lvalue).getIndex()
						&& ((Sum) rvalue).getRight() instanceof RNumber
						&& ((RNumber) ((Sum) rvalue).getRight()).getValue() == 1d) {
					output.printIncIndex(((LSetIdentify) lvalue).getIndex(), lvalue.getToken());
				} else {
					rvalue.build(context, output);
					lvalue.build(context, output);
				}
			} else {
				rvalue.build(context, output);
				output.decStack(1);
			}
		}
	}

	public List<ILValue> getLValues() {
		return lvalues;
	}

	public List<IRValue> getRValues() {
		return rvalues;
	}

	public Token getToken() {
		return token;
	}

	public void setToken(Token equalToken) {
		this.token = equalToken;
	}

	public void setType(AssignType type) {
		this.type = type;
	}

	public static enum AssignType {
		NORMAL, SUM, SUB, MUL, DIV;
	}

}
