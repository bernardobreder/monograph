
package breder.compiler.node.standart;

import java.io.IOException;

import breder.compiler.compiler.BinaryOutputStream;
import breder.compiler.compiler.CompilerConstant;
import breder.compiler.compiler.Context;
import breder.compiler.exception.BrederJRuntimeException;
import breder.compiler.parser.javacc.ParseException;

public class BInterface extends BBasicStruct {

	public BInterface(BSource source) {
		super(source);
	}

	@Override
	public void addMethod(BMethod method) {
		super.addMethod(method);
		method.setAbstract(true);
	}

	@Override
	public void syntax(Context context) throws ParseException {
		if (this.getExtends().size() == 0 && !this.getSource().getClassname().equals(CompilerConstant.IOBJECT_CLASS)) {
			BType type = context.getCompiler().findType(CompilerConstant.IOBJECT_CLASS);
			this.addExtends(type);
		}
		super.syntax(context);
		context.pushStruct(this);
		for (BType extend : this.getExtends()) {
			extend.syntax(context);
			if (!extend.getStruct().isInterfaceStruct()) {
				throw new BrederJRuntimeException(context, extend.getName(), "interface can extend only interfaces");
			}
		}
		for (BMethod method : this.getMethods()) {
			method.syntax(context);
		}
		context.popStruct();
	}

	public void memory(Context context) throws IOException {
	}

	@Override
	public void build(Context context, BinaryOutputStream output) throws IOException {
	}

	@Override
	public boolean isInstanceable() {
		return false;
	}

	@Override
	public void check(Context context) throws ParseException {
		super.check(context);
	}

	@Override
	public void semanticBody(Context context) throws ParseException {
		// TODO Auto-generated method stub

	}

	@Override
	public void semanticHeader(Context context) throws ParseException {
		context.pushStruct(this);
		super.semanticHeader(context);
		context.popStruct();
	}

}
