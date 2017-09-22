
package breder.compiler.node.standart;

import breder.compiler.compiler.Context;
import breder.compiler.exception.BrederJRuntimeException;
import breder.compiler.parser.javacc.ParseException;

public class BConstructor extends BMethod {

	public BConstructor(BStruct struct) {
		super(struct);
	}

	@Override
	public void syntax(Context context) throws ParseException {
		if (!this.getName().equals(context.getStruct().getName().image)) {
			throw new BrederJRuntimeException(context, this.getNameToken(), "constructor must has the name '%s'",
					context.getStruct().getName().image);
		}
		super.syntax(context);
	}

	@Override
	public boolean isConstructor() {
		return true;
	}

	@Override
	public boolean isStatic() {
		return true;
	}

}
