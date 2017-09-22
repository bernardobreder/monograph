
package breder.compiler.node.command;

import java.io.IOException;
import java.util.List;

import breder.compiler.compiler.BinaryOutputStream;
import breder.compiler.compiler.Context;
import breder.compiler.exception.BrederJRuntimeException;
import breder.compiler.exception.GenericException;
import breder.compiler.node.ICommand;
import breder.compiler.node.IRValue;
import breder.compiler.node.standart.BBasicStruct;
import breder.compiler.node.standart.BParam;
import breder.compiler.node.standart.BType;
import breder.compiler.node.standart.Command;
import breder.compiler.node.standart.VariableDeclare;
import breder.compiler.parser.javacc.ParseException;
import breder.compiler.parser.javacc.Token;
import breder.compiler.util.LightArrayList;

public class Declare extends Command implements ICommand {

	private final List<VariableDeclare> declares = new LightArrayList<VariableDeclare>();

	private final List<IRValue> values = new LightArrayList<IRValue>();

	private final List<Integer> indexs = new LightArrayList<Integer>();

	private Token equalToken;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void syntax(Context context) throws ParseException {
		for (VariableDeclare declare : declares) {
			declare.syntax(context);
		}
		for (VariableDeclare declare : declares) {
			declare.setDeclared(true);
		}
		for (int n = 0; n < this.values.size(); n++) {
			IRValue value = values.get(n);
			value.setParent(this);
			value.syntax(context);
			if (n < this.getDeclares().size() && value.getTypes().length > 0) {
				VariableDeclare declare = this.declares.get(n);
				declare.getType().setNotNull(value.getType().isNotNull());
			}
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws ParseException
	 */
	@Override
	public void semantic(Context context) throws ParseException {
		for (IRValue value : values) {
			value.semantic(context);
			if (value.getTypes().length == 0) {
				throw new BrederJRuntimeException(context, this.equalToken, "rvalue not return a value");
			}
		}
		for (BParam declare : declares) {
			declare.semantic(context);
		}
		for (BParam declare : this.declares) {
			if (declare.getType().getStruct() instanceof BBasicStruct) {
				BBasicStruct struct = (BBasicStruct) declare.getType().getStruct();
				if (struct.getGenerics().size() != declare.getType().getGenerics().size()) {
					throw new GenericException(context, declare.getType().getName());
				}
			}
		}
		if (this.values.size() > 0) {
			// if ((this.values.size() == 1 ?
			// this.values.get(0).getTypes().size()
			// : this.values.size()) != this.declares.size()) {
			// throw new BrederJRuntimeException(context, this.equalToken,
			// "number of returns is diferent from number of declare");
			// }
			{
				for (BParam param : this.declares) {
					indexs.add(param.getIndex());
				}
			}
			{
				if (values.size() == 1) {
					int size = Math.min(declares.size(), values.get(0).getTypes().length);
					IRValue rvalue = values.get(0);
					for (int n = 0; n < size; n++) {
						BType rtype = rvalue.getTypes()[n];
						BType ltype = declares.get(n).getType();
						if (!rtype.canCastTo(context, rvalue, ltype)) {
							throw new BrederJRuntimeException(context, values.get(n).getToken(),
									"cannot cast %s to %s", rtype, ltype);
						}
						ltype.setNotNull(rtype.isNotNull());
					}
				} else {
					int size = Math.min(declares.size(), values.size());
					for (int n = 0; n < size; n++) {
						IRValue rvalue = values.get(n);
						BType rtype = rvalue.getType();
						BType ltype = declares.get(n).getType();
						if (!rtype.canCastTo(context, rvalue, ltype)) {
							throw new BrederJRuntimeException(context, values.get(n).getToken(),
									"cannot cast %s to %s", rtype, ltype);
						}
						ltype.setNotNull(rtype.isNotNull());
					}
				}
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
		for (IRValue value : values) {
			value.check(context);
		}
		for (BParam declare : declares) {
			declare.check(context);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void build(Context context, BinaryOutputStream output) throws IOException {
		if (this.values.size() > 0) {
			for (IRValue rvalue : this.values) {
				rvalue.build(context, output);
			}
			{
				int size = Math.max(0, this.values.size() - this.declares.size());
				for (int n = 0; n < size; n++) {
					output.printDec(1, this.values.get(this.values.size() - n - 1).getToken());
				}
			}
			{
				int size;
				if (this.values.size() == 1) {
					size = this.values.get(0).getTypes().length;
				} else {
					size = Math.min(this.declares.size(), this.values.size());
				}
				output.setStack(size);
				int delta = this.values.size() == 1 ? 0 : Math.max(0, this.declares.size() - this.values.size());
				for (int n = size - 1; n >= 0; n--) {
					int index = this.indexs.get(n) - n;
					index = context.getMethod().getDeclares().size() - this.indexs.get(n) - 1;
					output.printStore(index + delta, this.getDeclares().get(n).getName());
				}
			}
		}
	}

	public void addDeclare(VariableDeclare vd) {
		this.declares.add(vd);
	}

	public List<VariableDeclare> getDeclares() {
		return declares;
	}

	public List<IRValue> getValues() {
		return values;
	}

	public List<Integer> getIndexs() {
		return indexs;
	}

	public Token getEqualToken() {
		return equalToken;
	}

	public void setEqualToken(Token equalToken) {
		this.equalToken = equalToken;
	}

}
