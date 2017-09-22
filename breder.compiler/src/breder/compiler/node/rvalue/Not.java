
package breder.compiler.node.rvalue;

import java.io.IOException;

import breder.compiler.compiler.BinaryOutputStream;
import breder.compiler.compiler.CompilerConstant;
import breder.compiler.compiler.Context;
import breder.compiler.node.IRValue;
import breder.compiler.node.standart.BType;
import breder.compiler.parser.javacc.ParseException;
import breder.compiler.parser.javacc.Token;

public class Not extends Unary {

	public Not(IRValue left, Token token) {
		super(left, token);
	}

	@Override
	public void syntax(Context context) throws ParseException {
		this.setTypes(new BType[] { context.getCompiler().findType(CompilerConstant.BOOLEAN_CLASS) });
		super.syntax(context);
	}

	@Override
	public void build(Context context, BinaryOutputStream output) throws IOException {
		this.getLeft().build(context, output);
		output.printNot(this.getToken());
	}
}
