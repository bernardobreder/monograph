
package breder.compiler.node.rvalue;

import java.io.IOException;

import breder.compiler.compiler.BinaryOutputStream;
import breder.compiler.compiler.Context;
import breder.compiler.node.ILValue;
import breder.compiler.node.IRValue;
import breder.compiler.node.command.Assign;
import breder.compiler.node.standart.BType;
import breder.compiler.parser.javacc.ParseException;

public class RAssign extends Assign implements IRValue {

	private BType[] types;

	@Override
	public void syntax(Context context) throws ParseException {
		super.syntax(context);
		BType[] types = new BType[this.getLValues().size()];
		for (int n = 0; n < this.getLValues().size(); n++) {
			ILValue lvalue = this.getLValues().get(n);
			types[n] = lvalue.getType();
		}
		this.setTypes(types);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void build(Context context, BinaryOutputStream output) throws IOException {
		if (this.getRValues().size() > 0) {
			for (IRValue rvalue : this.getRValues()) {
				rvalue.build(context, output);
				output.printDup(rvalue.getToken());
			}
			for (ILValue lvalue : this.getLValues()) {
				lvalue.build(context, output);
			}
		}
	}

	public BType[] getTypes() {
		return types;
	}

	public void setTypes(BType[] types) {
		this.types = types;
	}

	public BType getType() {
		if (types == null) {
			throw new IllegalStateException();
		}
		return types[0];
	}

}
