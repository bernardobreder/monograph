
package breder.compiler.node.standart;

import breder.compiler.compiler.Context;
import breder.compiler.parser.javacc.Token;

public class VariableDeclare extends BParam {

	private boolean declared;

	public VariableDeclare(BParam param) {
		this(param.getType(), param.getName());
	}

	public VariableDeclare(BType type, Token name) {
		super(type, name);
		this.declared = false;
	}

	public boolean isDeclared() {
		return declared;
	}

	public void setDeclared(boolean declared) {
		this.declared = declared;
	}

	public int getIndex(Context context) {
		return context.getMethod().getDeclares().size() - this.getIndex() - 1;
	}

}
