
package breder.compiler.node.standart;

import java.io.IOException;
import java.util.List;

import breder.compiler.compiler.BinaryOutputStream;
import breder.compiler.compiler.Context;
import breder.compiler.node.IValue;
import breder.compiler.parser.javacc.ParseException;
import breder.compiler.parser.javacc.Token;

public class NullStruct extends BStruct {

	public NullStruct() {
		super(new NullSource());
	}

	@Override
	public void build(Context context, BinaryOutputStream output) throws IOException {
	}

	@Override
	public boolean canCastTo(BStruct struct) {
		return true;
	}

	@Override
	public void check(Context context) throws ParseException {
	}

	@Override
	public void checkMethod(Context context, BMethod bMethod) {
	}

	@Override
	public List<BMethod> findMethod(Context context, IValue value, Token name, List<BType> params, boolean isStatic) {
		return null;
	}

	@Override
	public boolean isInstanceable() {
		return false;
	}

	@Override
	public void memory(Context context) throws IOException {
	}

	@Override
	public void semanticBody(Context context) throws ParseException {
	}

	@Override
	public void semanticHeader(Context context) throws ParseException {
	}

	@Override
	public void syntax(Context context) throws ParseException {
	}

	@Override
	public String toString() {
		return "null";
	}

}
