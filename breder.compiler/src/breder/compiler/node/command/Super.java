
package breder.compiler.node.command;

import java.io.IOException;
import java.util.List;

import breder.compiler.compiler.BinaryOutputStream;
import breder.compiler.compiler.Context;
import breder.compiler.exception.BrederJRuntimeException;
import breder.compiler.node.ICommand;
import breder.compiler.node.IRValue;
import breder.compiler.node.standart.BClass;
import breder.compiler.node.standart.BMethod;
import breder.compiler.node.standart.BStruct;
import breder.compiler.node.standart.BType;
import breder.compiler.node.standart.Command;
import breder.compiler.parser.javacc.ParseException;
import breder.compiler.parser.javacc.Token;
import breder.compiler.util.ConvertUtil;
import breder.compiler.util.LightArrayList;

public class Super extends Command implements ICommand {

	private Token superToken;

	private final List<BMethod> constructors = new LightArrayList<BMethod>();

	private final List<List<IRValue>> params = new LightArrayList<List<IRValue>>();

	@Override
	public void syntax(Context context) throws ParseException {
		if (context.getMethod().isStaticOnly()) {
			throw new BrederJRuntimeException(context, this.getSuperToken(), "can not use 'super' in a static method");
		}
		if (!context.getMethod().isConstructor()) {
			throw new BrederJRuntimeException(context, context.getMethod().getNameToken(),
					"method '%s' not a constructor", context.getMethod().getName());
		}
		for (List<IRValue> params : this.params) {
			for (IRValue param : params) {
				param.setParent(this);
				param.syntax(context);
			}
		}
	}

	@Override
	public void semantic(Context context) throws ParseException {
		for (int n = 0; n < this.params.size(); n++) {
			List<IRValue> params = this.params.get(n);
			for (IRValue param : params) {
				param.semantic(context);
			}
			List<BType> types = ConvertUtil.convertRValuesToTypes(context, params);
			BClass clazz = (BClass) context.getStruct();
			BStruct extendStruct = clazz.getExtends().get(n).getStruct();
			List<BMethod> methods = extendStruct.findMethod(context, null, extendStruct.getName(), types, true);
			if (methods.size() == 0) {
				throw new BrederJRuntimeException(context, this.superToken,
						"not found the construtor with this parameters");
			}
			this.constructors.add(methods.get(0));
		}
	}

	@Override
	public void check(Context context) throws ParseException {
		for (List<IRValue> params : this.params) {
			for (IRValue param : params) {
				param.check(context);
			}
		}
	}

	@Override
	public void build(Context context, BinaryOutputStream output) throws IOException {
		for (int n = 0; n < this.params.size(); n++) {
			{
				int index = context.getMethod().getParams().size() + context.getMethod().getDeclares().size();
				output.printLoad(index, this.getSuperToken());
			}
			List<IRValue> params = this.params.get(n);
			for (int m = params.size() - 1; m >= 0; m--) {
				params.get(m).build(context, output);
			}
			output.printOJump(params.size(), this.constructors.get(n).getIndex(), this.getSuperToken());
			{
				output.printDec(1, this.getSuperToken());
				output.decStack(1);
			}
		}
	}

	public List<IRValue> getParams(int index) {
		return this.params.get(index);
	}

	public List<IRValue> getParams() {
		return this.params.get(this.params.size() - 1);
	}

	public void nextParams() {
		this.params.add(new LightArrayList<IRValue>());
	}

	public Token getSuperToken() {
		return superToken;
	}

	public void setSuperToken(Token superToken) {
		this.superToken = superToken;
	}

}
