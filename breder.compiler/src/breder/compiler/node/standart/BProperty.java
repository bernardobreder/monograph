
package breder.compiler.node.standart;

import java.util.List;

import breder.compiler.compiler.Context;
import breder.compiler.node.ILValue;
import breder.compiler.node.IRValue;
import breder.compiler.node.command.Assign;
import breder.compiler.node.command.Block;
import breder.compiler.node.command.Return;
import breder.compiler.node.lvalue.LIdentify;
import breder.compiler.node.lvalue.LSetField;
import breder.compiler.node.lvalue.LThis;
import breder.compiler.node.rvalue.RGetField;
import breder.compiler.node.rvalue.RIdentify;
import breder.compiler.node.rvalue.RThis;
import breder.compiler.parser.javacc.ParseException;
import breder.compiler.util.LightArrayList;

public class BProperty extends BField {

	private BAccess access;

	@Override
	public void preCheck(Context context) {
	}

	@Override
	public void syntax(Context context) throws ParseException {
		super.syntax(context);
		boolean gfound = false, sfound = false;
		String getname = this.getName("get");
		String setname = this.getName("set");
		for (BMethod method : context.getStruct().getMethods()) {
			if (method.getName().equals(getname)) {
				if (method.getParams().size() == 0) {
					gfound = true;
				}
			} else if (method.getName().equals(setname)) {
				if (method.getParams().size() == 1 && this.getType().equals(method.getParams().get(0).getType())) {
					sfound = true;
				}
			}
		}
		if (!gfound) {
			BMethod method = this.buildGet(context, this.build(context, "get"));
			// method.syntax(context);
			context.getStruct().addMethod(method);
			method.setNameIndex(context.getCompiler().addConstant(method.toString()));
		}
		if (!sfound) {
			BMethod method = this.buildSet(context, this.build(context, "set"));
			// method.syntax(context);
			context.getStruct().addMethod(method);
			method.setNameIndex(context.getCompiler().addConstant(method.toString()));
		}
	}

	private String getName(String pre) {
		StringBuilder name = new StringBuilder(this.getName().image);
		name.setCharAt(0, Character.toUpperCase(name.charAt(0)));
		name.insert(0, pre);
		return name.toString();
	}

	private BMethod build(Context context, String pre) {
		BMethod method = new BMethod(context.getStruct());
		method.setAccess(this.access);
		method.setName(this.getName(pre));
		method.setBlock(new Block());
		method.setStatic(this.isStatic());
		method.setAccess(this.getAccess());
		return method;
	}

	private BMethod buildGet(Context context, BMethod method) {
		method.getReturns().add(this.getType());
		if (this.isStatic()) {
			method.getBlock().getCommands()
					.add(new Return(new RGetField(new RIdentify(context.getStruct().getName()), this.getName())));
		} else {
			method.getBlock().getCommands().add(new Return(new RGetField(new RThis(null), this.getName())));
		}
		return method;
	}

	private BMethod buildSet(Context context, BMethod method) throws ParseException {
		if (!method.isStatic()) {
			method.getReturns().add(context.getCompiler().findType(context.getClassname()));
		}
		method.getParams().add(new BParam(this.getType(), this.getName()));
		List<ILValue> lvalues = new LightArrayList<ILValue>();
		List<IRValue> rvalues = new LightArrayList<IRValue>();
		if (this.isStatic()) {
			lvalues.add(new LSetField(new LIdentify(context.getStruct().getName()), this.getName()));
		} else {
			lvalues.add(new LSetField(new LThis(null), this.getName()));
		}
		rvalues.add(new RIdentify(this.getName()));
		method.getBlock().getCommands().add(new Assign(lvalues, rvalues));
		if (!method.isStatic()) {
			method.getBlock().getCommands().add(new Return(new RThis(null)));
		}
		return method;
	}

	public BAccess getAccess() {
		return access;
	}

	public void setAccess(BAccess access) {
		this.access = access;
	}

}
