
package breder.compiler.compiler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import breder.compiler.exception.BrederJRuntimeException;
import breder.compiler.node.standart.BField;
import breder.compiler.node.standart.BMethod;
import breder.compiler.node.standart.BSource;
import breder.compiler.node.standart.BStruct;
import breder.compiler.parser.javacc.ParseException;

public class Compiler extends AbstractCompiler {

	private BMethod method;

	@Override
	protected void save() throws Exception {
		{
			int mindex = 0;
			for (int n = 0; n < this.sources.size(); n++) {
				BSource source = this.sources.get(n);
				BStruct clazz = source.getStruct();
				int sizem = clazz.getMethods().size();
				for (int m = 0; m < sizem; m++) {
					BMethod method = clazz.getMethods().get(m);
					method.setIndex(mindex++);
				}
			}
		}
		for (BSource source : sources) {
			source.memory(context);
		}
		try {
			this.method = this.findSource(classname).getStruct()
					.getMethods("() main (breder.util.IList<breder.lang.standard.String>)");
			if (method == null) {
				throw new BrederJRuntimeException(
						context,
						this.findSource(classname),
						null,
						"in the class '%s', not found the method starter '() main (breder.util.IList<breder.lang.standard.String>)'",
						classname);
			}
			this.save(classname);
		} catch (IOException e) {
			throw new BrederJRuntimeException(this.context, null, e.getMessage());
		}
	}

	/**
	 * Salva todo arquivo compilado.
	 * 
	 * @param classname
	 *            classe inicializadora
	 * @throws IOException
	 * @throws ParseException
	 */
	public void save(String classname) throws IOException, ParseException {
		new File(this.getOutput()).getParentFile().mkdirs();
		BinaryOutputStream output = null;
		try {
			SkipByteArrayOutputStream bytes = new SkipByteArrayOutputStream();
			output = new BinaryOutputStream(context, bytes);
			output.begin();
			output.save(method.getIndex());
			this.saveLibrary(output);
			this.saveClasses(output);
			this.saveFields(output);
			this.saveMethods(output);
			this.saveNativeMethod(output);
			this.saveString(output);
			this.saveNumber(output);
			output.setCounter(0);
			output.printEnd((short) 0, (short) 0);
			for (BSource source : this.sources) {
				source.build(context, output);
			}
			FileOutputStream stream = new FileOutputStream(this.getOutput());
			try {
				stream.write(bytes.toByteArray());
			} finally {
				stream.close();
			}
		} finally {
			output.close();
		}
	}

	private void saveString(BinaryOutputStream output) throws IOException {
		output.save(this.stringConstants.size());
		for (String constant : this.stringConstants) {
			output.save(constant);
		}
	}

	private void saveNumber(BinaryOutputStream output) throws IOException {
		output.save(this.doubleConstants.size());
		for (Double constant : this.doubleConstants) {
			output.save(constant.toString());
		}
	}

	private void saveLibrary(BinaryOutputStream output) throws IOException {
		output.save(this.librarys.size());
		for (String library : this.librarys) {
			output.save(library);
		}
	}

	private void saveNativeMethod(BinaryOutputStream output) throws IOException {
		output.save(this.nativeMethod.size());
		for (BMethod method : this.nativeMethod) {
			int index = this.indexOf(method);
			output.save(new int[] { index });
			output.save(method.getNativeName());
		}
	}

	private void saveClasses(BinaryOutputStream output) throws IOException {
		output.save(this.sources.size());
		for (int n = 0; n < this.sources.size(); n++) {
			BSource source = this.sources.get(n);
			BStruct struct = source.getStruct();
			output.save(source.getClassname());
			{
				int[] array = new int[struct.getExtends().size()];
				for (int m = 0; m < array.length; m++) {
					array[m] = struct.getExtends().get(m).getStruct().getIndex();
				}
				output.save(array);
			}
			{
				int[] array = new int[struct.getImplements().size()];
				for (int m = 0; m < array.length; m++) {
					array[m] = struct.getImplements().get(m).getStruct().getIndex();
				}
				output.save(array);
			}
		}
	}

	private void saveFields(BinaryOutputStream output) throws IOException {
		{
			int counter = 0;
			for (int n = 0; n < this.sources.size(); n++) {
				BSource source = this.sources.get(n);
				counter += source.getStruct().getFields().size();
			}
			if (counter > Math.pow(2, 23) - 1) {
				throw new BrederJRuntimeException(context, this.getSources()[0].getStruct().getMethods().get(0)
						.getNameToken(), "The binary file has method more than %d", Math.pow(2, 23) - 1);
			}
			output.save(counter);
		}
		int findex = 0;
		for (int n = 0; n < this.sources.size(); n++) {
			BSource source = this.sources.get(n);
			BStruct clazz = source.getStruct();
			int size = clazz.getFields().size();
			output.save(size);
			for (int m = 0; m < size; m++) {
				BField field = clazz.getFields().get(m);
				field.setGlobalIndex(findex++);
				output.save(field.isStatic() ? 1 : 0);
				output.save(field.getCompleteName());
			}
		}
	}

	private void saveMethods(BinaryOutputStream output) throws IOException {
		{
			int counter = 0;
			for (int n = 0; n < this.sources.size(); n++) {
				BSource source = this.sources.get(n);
				counter += source.getStruct().getMethods().size();
			}
			if (counter > Math.pow(2, 23) - 1) {
				throw new BrederJRuntimeException(context, this.getSources()[0].getStruct().getMethods().get(0)
						.getNameToken(), "The binary file has method more than %d", Math.pow(2, 23) - 1);
			}
			output.save(counter);
		}
		for (int n = 0; n < this.sources.size(); n++) {
			BSource source = this.sources.get(n);
			BStruct clazz = source.getStruct();
			int sizem = clazz.getMethods().size();
			output.save(sizem);
			{
				for (int m = 0; m < sizem; m++) {
					BMethod method = clazz.getMethods().get(m);
					{
						try {
							output.save(method.toNativeNameString(context));
						} catch (ParseException e) {
							throw new IOException(e);
						}
					}
					{
						int returns = method.getReturns().size();
						int isStatic = method.isStatic() ? 1 : 0;
						int isAbstract = method.isAbstract() ? 1 : 0;
						int isConstructor = method.isConstructor() ? 1 : 0;
						int methodMemory = method.getMemIndex();
						output.save(new int[] { returns, isStatic, isAbstract, isConstructor, methodMemory });
					}
					{
						int[] list = new int[method.getParams().size()];
						for (int p = 0; p < method.getParams().size(); p++) {
							list[p] = method.getParams().get(p).getType().getStruct().getIndex();
						}
						output.save(list);
					}
				}
			}
		}

	}

}
