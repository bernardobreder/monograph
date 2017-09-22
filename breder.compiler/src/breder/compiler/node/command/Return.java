
package breder.compiler.node.command;

import java.io.IOException;
import java.util.List;

import breder.compiler.compiler.BinaryOutputStream;
import breder.compiler.compiler.Context;
import breder.compiler.exception.BClassClastException;
import breder.compiler.exception.BrederJRuntimeException;
import breder.compiler.node.ICommand;
import breder.compiler.node.IRValue;
import breder.compiler.node.rvalue.Call;
import breder.compiler.node.rvalue.RThis;
import breder.compiler.node.rvalue.RValue;
import breder.compiler.node.standart.BMethod;
import breder.compiler.node.standart.BThisType;
import breder.compiler.node.standart.BType;
import breder.compiler.node.standart.Command;
import breder.compiler.node.standart.NullStruct;
import breder.compiler.parser.javacc.ParseException;
import breder.compiler.parser.javacc.Token;
import breder.compiler.util.LightArrayList;

public class Return extends Command implements ICommand {

	private final List<IRValue> values = new LightArrayList<IRValue>();

	private Token returnToken;

	private int[] storeIndexs;

	private int decIndex;

	private int returns;

	public Return(RValue... rvalues) {
		super();
		for (RValue rvalue : rvalues) {
			rvalue.setParent(this);
			this.values.add(rvalue);
		}
	}

	@Override
	public void syntax(Context context) throws ParseException {
		for (IRValue value : this.values) {
			value.syntax(context);
		}

	}

	@Override
	public void semantic(Context context) throws ParseException {
		for (IRValue value : this.values) {
			value.semantic(context);
		}
		if (this.values.size() != context.getMethod().getReturns().size()) {
			throw new BrederJRuntimeException(context, returnToken, "Number of returns values is diferente.");
		}
		{
			List<BType> returns = context.getMethod().getReturns();
			for (int n = 0; n < returns.size(); n++) {
				IRValue rvalue = this.values.get(n);
				if (rvalue.getTypes().length == 0) {
					throw new BrederJRuntimeException(context, rvalue.getToken(), "rvalue not return a value");
				}
				BType rtype = rvalue.getType();
				BType ltype = returns.get(n);
				if (ltype.isNotNull() && !rtype.isNotNull()) {
					throw new BrederJRuntimeException(context, rvalue.getToken(), "rvalue '%s' not notnull",
							rvalue.getToken());
				}
				if (rtype.getStruct().getClass() != NullStruct.class) {
					if (ltype.isGeneric()) {
						if (!rtype.getName().image.equals(ltype.getName().image)) {
							throw new BClassClastException(context, rvalue.getToken());
						}
					} else {
						if (ltype instanceof BThisType) {
							if (rvalue instanceof RThis == false
									&& !(rvalue instanceof Call && ((Call) rvalue).getMethod().getReturns()
											.get(returns.size() == 1 ? 0 : n) instanceof BThisType)) {
								throw new BrederJRuntimeException(context, rvalue.getToken(),
										"can not cast this with other type");
							}
						}
						if (!rtype.canCastTo(context, null, ltype)) {
							throw new BClassClastException(context, rvalue.getToken());
						}
					}
				}
			}
		}
		BMethod method = context.getMethod();
		this.storeIndexs = new int[this.values.size()];
		int declares = method.getDeclares().size();
		int params = method.getParams().size();
		if (!method.isStatic()) {
			params++;
		}
		for (int n = 0; n < values.size(); n++) {
			int returns = 0;
			int mreturn = context.getMethod().getReturns().size();
			int mparam = context.getMethod().getParams().size();
			if (mreturn > 1 && mparam == 0) {
				returns = context.getMethod().getReturns().size();
			}
			int index = Math.max(1, params + declares + returns);
			index += mparam != 0 && mreturn > mparam ? Math.max(0, mreturn - mparam) : 0;
			this.storeIndexs[n] = index;
		}
		{
			int p = method.getParams().size();
			int r = method.getReturns().size();
			if (method.isStatic()) {
				this.returns = Math.max(0, p - r);
			} else {
				if (p + 1 > r) {
					this.returns = p + 1 - r;
				} else {
					this.returns = 0;
				}
			}
		}
		{
			this.decIndex = declares;
		}
	}

	@Override
	public void check(Context context) throws ParseException {
		for (IRValue value : this.values) {
			value.check(context);
		}
	}

	@Override
	public void build(Context context, BinaryOutputStream output) throws IOException {
		for (IRValue value : this.values) {
			value.build(context, output);
			if (value.getTypes().length > 1) {
				output.printDec(value.getTypes().length - 1, value.getToken());
			}
		}
		{
			for (int n = 0; n < this.storeIndexs.length; n++) {
				output.printStoreAbs(this.storeIndexs[n], this.getValues().get(n).getToken());
			}
		}
		{
			if (this.decIndex > 0) {
				output.printDec(this.decIndex, this.getReturnToken());
			}
		}
		output.printReturn(this.returns, this.getReturnToken());
	}

	@Override
	public boolean isReturned() {
		return true;
	}

	public List<IRValue> getValues() {
		return values;
	}

	public Token getReturnToken() {
		return returnToken;
	}

	public void setReturnToken(Token returnToken) {
		this.returnToken = returnToken;
	}

}
