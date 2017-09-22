package breder.compiler.compiler.mobile.iphone;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import breder.compiler.compiler.Compiler;
import breder.compiler.node.standart.BBasicStruct;
import breder.compiler.node.standart.BMethod;
import breder.compiler.node.standart.BSource;

public class IPhoneCompiler extends Compiler {

	private String outputPath;

	public IPhoneCompiler() {
		this.outputPath = ".";
	}

	@Override
	protected void save() throws Exception {
		super.save();
		this.a();
	}

	@Override
	public String getOutput() {
		return new File(".", "binary.b").toString();
	}

	private void a() throws IOException {
		{
			PrintWriter output = new PrintWriter(new File(this.getOutputPath(),
					"binary.c"));
			InputStream input = new FileInputStream(this.getOutput());
			output.println();
			output.println("#include \"binary.h\"");
			output.println();
			this.b(output);
			output.close();
		}
		{
			PrintWriter output = new PrintWriter(new File(this.getOutputPath(),
					"binary.h"));
			output.println("#ifndef B_BINARY");
			output.println("#define B_BINARY");
			output.println();
			output.println("#include \"breder.h\"");
			output.println();
			this.c(output);
			output.println();
			output.println("#endif");
			output.close();
		}
	}

	private void c(PrintWriter output) {
		for (BSource source : this.getSources()) {
			if (source.getStruct() instanceof BBasicStruct) {
				BBasicStruct bstruct = (BBasicStruct) source.getStruct();
				for (BMethod method : bstruct.getMethods()) {
					if (method.isNative()) {
						if (method.isStatic() && !method.isConstructor()) {
							output.println(String.format(
									"b_bni_state_t %s(b_vm_t* vm);",
									method.getNativeName()));
						} else {
							output.println(String
									.format("b_bni_state_t %s(b_vm_t* vm, b_object_t* object);",
											method.getNativeName()));
						}
					}
				}
			}
		}
		output.println();
		for (BSource source : this.getSources()) {
			String name = source.getClassname().toLowerCase().replace('.', '_');
			output.println(String.format(
					"b_class_id_t b_bni_class_%s_id(b_vm_t* vm);", name));
		}
		output.println();
		for (BSource source : this.getSources()) {
			String cname = source.getClassname().toLowerCase()
					.replace('.', '_');
			if (source.getStruct() instanceof BBasicStruct) {
				BBasicStruct bstruct = (BBasicStruct) source.getStruct();
				for (BMethod method : bstruct.getMethods()) {
					String mname = method.getSimpleNativeName().toLowerCase();
					output.println(String.format(
							"b_method_id_t b_bni_method_%s$%s_id(b_vm_t* vm);",
							cname, mname));
				}
			}
		}
	}

	public void b(PrintWriter output) throws IOException {
		output.println(String.format("void* b_so_func_get(const char* name) {"));
		for (BSource source : this.getSources()) {
			if (source.getStruct() instanceof BBasicStruct) {
				BBasicStruct bstruct = (BBasicStruct) source.getStruct();
				for (BMethod method : bstruct.getMethods()) {
					if (method.isNative()) {
						output.println(String.format(
								"\tif(!strcmp(name,\"%s\")) return %s;",
								method.getNativeName(), method.getNativeName()));
					}
				}
			}
		}
		output.println("}");
		output.println();
		for (BSource source : this.getSources()) {
			String name = source.getClassname().toLowerCase().replace('.', '_');
			output.println(String.format(
					"b_bni_class_define(b_bni_class_%s_id, b_%s_id, \"%s\")",
					name, name, source.getClassname()));
		}
		output.println();
		for (BSource source : this.getSources()) {
			String cname = source.getClassname().toLowerCase()
					.replace('.', '_');
			if (source.getStruct() instanceof BBasicStruct) {
				BBasicStruct bstruct = (BBasicStruct) source.getStruct();
				for (BMethod method : bstruct.getMethods()) {
					String mname = method.getSimpleNativeName().toLowerCase();
					output.println(String
							.format("b_bni_method_define(b_bni_method_%s_%s_id, b_%s_%s_id, b_bni_class_%s_id, \"%s\")",
									cname, mname, cname, mname, cname,
									method.getCompleteName()));
				}
			}
		}
	}

	public void setOutputPath(String string) {
		this.outputPath = string;
	}

	public String getOutputPath() {
		return outputPath;
	}

}
