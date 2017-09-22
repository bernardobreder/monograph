
package breder.compiler.node.standart;

import java.io.IOException;

import breder.compiler.compiler.BinaryOutputStream;
import breder.compiler.compiler.CompilerConstant;
import breder.compiler.compiler.Context;
import breder.compiler.exception.BrederJRuntimeException;
import breder.compiler.node.command.Block;
import breder.compiler.parser.javacc.ParseException;
import breder.compiler.parser.javacc.Token;

public class BClass extends BBasicStruct {

	private boolean isFinal;

	private boolean isAbstract;

	public BClass(BSource source) {
		super(source);
	}

	public void body(Context context) throws ParseException {
		context.pushStruct(this);
		for (BMethod method : this.getMethods()) {
			method.body(context);
		}
		context.popStruct();
	}

	public void syntax(Context context) throws ParseException {
		context.pushStruct(this);
		if (this.getExtends().size() == 0 && !this.getSource().getClassname().equals(CompilerConstant.OBJECT_CLASS)) {
			BType type = context.getCompiler().findType(CompilerConstant.OBJECT_CLASS);
			this.addExtends(type);
		}
		super.syntax(context);
		for (BType inplement : this.getImplements()) {
			inplement.syntax(context);
			for (BType generic : inplement.getGenerics()) {
				generic.syntax(context);
			}
			this.checkGeneric(context, inplement);
		}
		{
			boolean found = false;
			for (BMethod method : this.getMethods()) {
				if (method.isConstructor()) {
					found = true;
				}
			}
			if (!found) {
				BConstructor constructor = new BConstructor(this);
				constructor.setAccess(BAccess.PUBLIC);
				constructor.setName(this.getName().image);
				// constructor.setNameIndex(context.getCompiler().addConstant(
				// constructor.toNativeNameString(context)));
				constructor.setBlock(new Block());
				this.addMethod(constructor);
			}
		}
		for (BField field : this.getFields()) {
			field.syntax(context);
		}
		for (BMethod method : this.getMethods()) {
			method.syntax(context);
		}
		context.popStruct();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws ParseException
	 */
	@Override
	public void semanticHeader(Context context) throws ParseException {
		context.pushStruct(this);
		super.semanticHeader(context);
		for (BField field : this.getFields()) {
			field.semantic(context);
		}
		for (BMethod method : this.getMethods()) {
			method.semanticHeader(context);
		}
		if (!this.isAbstract()) {
			this.checkMethodImplementedOfInterface(context, this);
		}
		context.popStruct();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws ParseException
	 */
	@Override
	public void semanticBody(Context context) throws ParseException {
		context.pushStruct(this);
		for (BMethod method : this.getMethods()) {
			method.semanticBody(context);
		}
		context.popStruct();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws ParseException
	 */
	@Override
	public void check(Context context) throws ParseException {
		context.pushStruct(this);
		super.check(context);
		for (BField field : this.getFields()) {
			field.check(context);
		}
		for (BMethod method : this.getMethods()) {
			method.check(context);
		}
		this.checkFieldDup(context);
		this.checkMethodImplementedOfAbstract(context);
		context.popStruct();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws IOException
	 */
	@Override
	public void memory(Context context) throws IOException {
		BinaryOutputStream output = new BFacateBinaryOutputStream(context);
		output.print("BB");
		context.pushStruct(this);
		context.incMemory(0);
		for (BMethod method : this.getMethods()) {
			if (!method.isNative()) {
				method.setMemIndex(context.getMemory());
				try {
					output.setStack(0);
					method.build(context, output);
				} catch (IOException e) {
				}
				context.incMemory(output.getCounter());
				output.setCounter(0);
			}
		}
		context.popStruct();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void build(Context context, BinaryOutputStream output) throws IOException {
		context.pushStruct(this);
		for (BMethod method : this.getMethods()) {
			output.setStack(0);
			int begin = output.getCounter();
			method.build(context, output);
			int end = output.getCounter();
			// this.otimize(context, output, begin, (end - begin) * 12);
		}
		context.popStruct();
	}

	private void otimize(Context context, BinaryOutputStream output, int begin, int length) {
		byte[] bytes = output.getOutput().sub(begin, length);
		for (int n = 0; n < bytes.length;) {
			{
				int h1, h2, h3, h4;
				int inst = (h1 = bytes[n++]) << 24;
				inst += (h2 = bytes[n++]) << 16;
				inst += (h3 = bytes[n++]) << 8;
				inst += (h4 = bytes[n++]);

			}
			{
				int h1, h2, h3, h4, h5, h6, h7, h8;
				long inst = (h1 = bytes[n++]) << 56;
				inst += (h2 = bytes[n++]) << 48;
				inst += (h3 = bytes[n++]) << 40;
				inst += (h4 = bytes[n++]) << 32;
				inst += (h5 = bytes[n++]) << 24;
				inst += (h6 = bytes[n++]) << 16;
				inst += (h7 = bytes[n++]) << 8;
				inst += (h8 = bytes[n++]);
			}
		}
	}

	public BField findField(Context context, Token name, boolean isStatic) {
		return BClass.findFieldAux(context, this, name, isStatic);
	}

	private static BField findFieldAux(Context context, BClass clazz, Token name, boolean isStatic) {
		for (BField field : clazz.getFields()) {
			if (field.isStatic() == isStatic) {
				if (field.getName().image.equals(name.image)) {
					return field;
				}
			}
		}
		BField found = null;
		for (int n = 0; n < clazz.getExtends().size(); n++) {
			BType extend = clazz.getExtends().get(n);
			BClass c = (BClass) extend.getStruct();
			BField aux = findFieldAux(context, c, name, isStatic);
			if (aux != null) {
				if (found != null) {
					throw new BrederJRuntimeException(context, name, "ambiguidade entre campos");
				} else {
					found = aux;
				}
			}
		}
		return found;
	}

	public void checkFieldDup(Context context) {
		for (BField f1 : this.getFields()) {
			for (BField f2 : this.getFields()) {
				if (f1 != f2) {
					if (f1.getName().image.equals(f2.getName().image)) {
						throw new BrederJRuntimeException(context, f1.getName(),
								"field already declared at the same class");
					}
				}
			}
		}
	}

	@Override
	public void checkMethod(Context context, BMethod method) {
		if (method.isAbstract()) {
			if (!this.isAbstract()) {
				throw new BrederJRuntimeException(context, this.getName(), "class need be abstract");
			}
		}
	}

	private void checkMethodImplementedOfAbstract(Context context) {
		this.checkMethodImplementedOfAbstract(context, this);
	}

	private void checkMethodImplementedOfAbstract(Context context, BStruct type) {
		for (BType textend : ((BBasicStruct) type).getExtends()) {
			this.checkMethodImplementedOfAbstract(context, textend.getStruct());
		}
		for (BMethod method : type.getMethods()) {
			if (method.isAbstract()) {
				if (!this.checkMethodImplementedOfAbstract(context, this, (BBasicStruct) type, method)) {
					if (!this.isAbstract()) {
						throw new BrederJRuntimeException(context, this.getName(),
								"the class not implemented the method '%s'", method.getCompleteName());
					}
				}
			}
		}
	}

	private boolean checkMethodImplementedOfAbstract(Context context, BBasicStruct beginStruct, BBasicStruct endStruct,
			BMethod method) {
		for (BMethod m : beginStruct.getMethods()) {
			if (method.getCompleteName().equals(m.getCompleteName())) {
				return true;
			}
		}
		for (BType extend : beginStruct.getExtends()) {
			if (extend.getStruct().getSource().equals(endStruct.getSource())) {
				return false;
			}
		}
		for (BType extend : beginStruct.getExtends()) {
			if (this.checkMethodImplementedOfAbstract(context, (BBasicStruct) extend.getStruct(),
					(BBasicStruct) endStruct, method)) {
				return true;
			}
		}
		return false;
	}

	private void checkMethodImplementedOfInterface(Context context, BBasicStruct struct) {
		for (BType type : struct.getImplements()) {
			this.checkMethodImplementedOfInterface(context, type);
		}
		for (BType type : struct.getExtends()) {
			checkMethodImplementedOfInterface(context, (BBasicStruct) type.getStruct());
		}
	}

	private void checkMethodImplementedOfInterface(Context context, BType type) {
		for (BMethod method : type.getStruct().getMethods()) {
			if (!method.isConstructor()) {
				this.checkMethodImplementedOfInterface(context, this, type, method);
			}
		}
		if (type.getStruct() instanceof BInterface) {
			BInterface i = (BInterface) type.getStruct();
			for (BType extend : i.getExtends()) {
				BType relativeExtend = this.getRelativeType(extend);
				this.checkMethodImplementedOfInterface(context, relativeExtend);
			}
		}
	}

	private boolean checkMethodImplementedOfInterface(Context context, BStruct struct, BType type, BMethod method) {
		for (BMethod m : struct.getMethods()) {
			if (method.getParams().size() == m.getParams().size()) {
				if (method.getName().equals(m.getName())) {
					boolean found = true;
					for (int n = 0; n < method.getParams().size(); n++) {
						BParam param1 = method.getParams().get(n);
						BParam param2 = m.getParams().get(n);
						BStruct struct1 = param1.getType().getStruct(type);
						BStruct struct2 = param2.getType().getStruct(type);
						if (!struct1.getClassname().equals(struct2.getClassname())) {
							found = false;
							break;
						}
					}
					if (found) {
						return true;
					}
				}
			}
		}
		if (struct instanceof BBasicStruct) {
			BBasicStruct bstruct = (BBasicStruct) struct;
			for (BType extend : bstruct.getExtends()) {
				if (this.checkMethodImplementedOfInterface(context, extend.getStruct(), type, method)) {
					return true;
				}
			}
		}
		throw new BrederJRuntimeException(context, this.getName(), "the class not implemented the method '%s'",
				method.getCompleteName());
	}

	@Override
	public boolean isInstanceable() {
		return !this.isAbstract();
	}

	public boolean isAbstract() {
		return isAbstract;
	}

	public void setAbstract(boolean isAbstract) {
		this.isAbstract = isAbstract;
	}

	public boolean isFinal() {
		return isFinal;
	}

	public void setFinal(boolean isFinal) {
		this.isFinal = isFinal;
	}

}
