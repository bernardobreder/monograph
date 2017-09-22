
package breder.compiler.compiler.mobile.iphone;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import breder.compiler.GenericCompiler;
import breder.compiler.compiler.Compiler;
import breder.compiler.node.standart.BBasicStruct;
import breder.compiler.node.standart.BMethod;
import breder.compiler.node.standart.BSource;
import breder.compiler.util.BrederLanguageFile;
import breder.compiler.util.InputStreamUtil;

public class ArduinoMobileCompiler extends Compiler {

	@Override
	protected void save() throws Exception {
		super.save();
		this.build();
	}

	@Override
	public String getOutput() {
		return new File(".", "binary.b").toString();
	}

	private void build() throws IOException {
		File brederVmFile = new File("../blng.vm");
		File brederLangFile = new File("../blng.lang");
		File brederUtilFile = new File("../blng.util");
		File brederMathFile = new File("../blng.math");
		if (!brederVmFile.exists() || !brederLangFile.exists() || !brederUtilFile.exists() || !brederMathFile.exists()) {
			throw new FileNotFoundException();
		}
		File project = brederVmFile;
		PrintWriter incOutput = new PrintWriter(new File(
				"/Applications/Programming/Arduino.app/Contents/Resources/Java/hardware/arduino/cores/arduino",
				"main.h"));
		{
			incOutput.println("#include <WProgram.h>");
			incOutput.println("#define SO_FILE_SEPARATOR \"/\"");
			incOutput.println("void* b_so_func_get(const char* name);");
			{
				Set<File> files = new HashSet<File>();
				File sourceDir = new File(project, "inc");
				if (sourceDir.exists() && !sourceDir.isHidden()) {
					files.addAll(Arrays.asList(sourceDir.listFiles()));
					files.remove(new File(sourceDir, "b_config_macos.h"));
					files.remove(new File(sourceDir, "b_config_linux.h"));
					files.remove(new File(sourceDir, "b_config_windows.h"));
					files.remove(new File(sourceDir, "b_platform_pc.h"));
					files.remove(new File(sourceDir, "breder.h"));
					files.remove(new File(sourceDir, "b_config.h"));
					files.remove(new File(sourceDir, "b_platform.h"));
					files.remove(new File(sourceDir, "b_platform_arduino.h"));
					files.remove(new File(sourceDir, "b_typedef.h"));
					files.remove(new File(sourceDir, "b_index.h"));
					addFile(incOutput, new File(sourceDir, "b_platform_arduino.h"), 0);
					addFile(incOutput, new File(sourceDir, "b_typedef.h"), 0);
					files.remove(new File(sourceDir, "b_define.h"));
					addFile(incOutput, new File(sourceDir, "b_define.h"), 0);
					addFile(incOutput, new File(sourceDir, "b_config_mobile.h"), -1);
					for (File sourceFile : files) {
						if (!sourceFile.isHidden()) {
							addFile(incOutput, sourceFile, 0);
						}
					}
				}
			}
			this.buildHeader(incOutput);
		}
		PrintWriter srcOutput = new PrintWriter(new File(
				"/Applications/Programming/Arduino.app/Contents/Resources/Java/hardware/arduino/cores/arduino",
				"main.cpp"));
		{
			srcOutput.println("#include <WProgram.h>");
			srcOutput.println("#include \"main.h\"");
			{
				Set<File> files = new HashSet<File>();
				File sourceDir = new File(project, "src");
				if (sourceDir.exists() && !sourceDir.isHidden()) {
					files.addAll(Arrays.asList(sourceDir.listFiles()));
					files.remove(new File(sourceDir, "b_config_unix.c"));
					files.remove(new File(sourceDir, "b_config_windows.c"));
					files.remove(new File(sourceDir, "b_so_pc.c"));
					files.remove(new File(sourceDir, "b_main.c"));
					files.remove(new File(sourceDir, "b_so_mobile.c"));
					files.remove(new File(sourceDir, "b_index.c"));
					files.remove(new File(sourceDir, "b_file.c"));
					addFile(srcOutput, new File(sourceDir, "b_so_mobile.c"), -1);
					for (File sourceFile : files) {
						if (!sourceFile.isHidden()) {
							addFile(srcOutput, sourceFile, 0);
						}
					}
				}
			}
			this.buildBinary(srcOutput, incOutput);
			// this.buildShared(incOutput, srcOutput,
			// "breder_lang_standard_native");
			// this.buildShared(incOutput, srcOutput,
			// "breder_util_standard_native");
			// this.buildShared(incOutput, srcOutput,
			// "breder_math_standard_native");
			this.buildSource(srcOutput);
			srcOutput.println("int main(void) {\n" + "\tinit();\n" + "\tsetup();\n" + "\treturn 0;\n" + "}");
		}
		incOutput.close();
		srcOutput.close();
	}

	private void buildBinary(PrintWriter srcOutput, PrintWriter incOutput) throws IOException {
		{
			InputStream input = new FileInputStream("../mbreder_compiler/binary.mb");
			srcOutput.println("static const char binary[] = {");
			int len = 0;
			int n = input.read();
			int m = 0;
			while (n != -1) {
				srcOutput.print(new Integer(n).toString());
				len++;
				if (m++ == 20) {
					srcOutput.println();
					m = 0;
				}
				n = input.read();
				if (n != -1) {
					srcOutput.print(",");
				} else {
					System.out.println();
				}
			}
			srcOutput.println("};");
			srcOutput.println();
			srcOutput.println("const char* b_binary_get() {");
			srcOutput.println("\treturn binary;");
			srcOutput.println("}");
			srcOutput.println();
			srcOutput.println("int b_binary_size() {");
			srcOutput.println(String.format("\treturn %s;", len));
			srcOutput.println("}");
			srcOutput.println();
		}
		{
			incOutput.println("const char* b_binary_get();");
			incOutput.println("int b_binary_size();");
		}
	}

	private void buildShared(PrintWriter incOutput, PrintWriter srcOutput, String project) throws IOException {
		File projectDir = new File("../" + project);
		File sourceDir = new File(projectDir, "src");
		for (File sourceFile : sourceDir.listFiles()) {
			if (!sourceFile.isHidden() && sourceFile.isFile()) {
				LineNumberReader reader = new LineNumberReader(new InputStreamReader(new FileInputStream(sourceFile)));
				String line = reader.readLine();
				while (line != null) {
					if (!line.startsWith("#include")) {
						srcOutput.println(line);
					}
					line = reader.readLine();
				}
				reader.close();
			}
		}
		File includeDir = new File(projectDir, "inc");
		for (File includeFile : includeDir.listFiles()) {
			if (!includeFile.isHidden() && includeFile.isFile()) {
				LineNumberReader reader = new LineNumberReader(new InputStreamReader(new FileInputStream(includeFile)));
				String line = reader.readLine();
				while (line != null) {
					if (!line.startsWith("#include") && !line.startsWith("#ifndef")
							&& !(line.startsWith("#define") && line.endsWith("_H_")) && !line.startsWith("#endif")) {
						incOutput.println(line);
					}
					line = reader.readLine();
				}
				reader.close();
			}
		}
	}

	public void buildHeader(PrintWriter output) {
		for (BSource source : this.getSources()) {
			if (source.getStruct() instanceof BBasicStruct) {
				BBasicStruct bstruct = (BBasicStruct) source.getStruct();
				for (BMethod method : bstruct.getMethods()) {
					if (method.isNative()) {
						if (method.isStatic() && !method.isConstructor()) {
							/*
							 * output.println(String.format(
							 * "b_bni_state_t %s(b_vm_t* vm);",
							 * method.getNativeName()));
							 */
						} else {
							/*
							 * output.println(String .format(
							 * "b_bni_state_t %s(b_vm_t* vm, b_object_t* object);"
							 * , method.getNativeName()));
							 */
						}
					}
				}
			}
		}
		output.println();
		// for (BSource source : this.getSources()) {
		// if (source.getPackageToken().image.startsWith("breder.lang")
		// || source.getPackageToken().image.startsWith("breder.util")
		// || source.getPackageToken().image.startsWith("breder.math")) {
		// String name =
		// source.getStruct().getName().image.toLowerCase().replace('.', '_');
		// int index = source.getStruct().getIndex();
		// output.println(String.format("#define b_bni_class_%s_id(VM) %d",
		// name, index));
		// }
		// }
		// output.println();
		// for (BSource source : this.getSources()) {
		// if (source.getPackageToken().image.startsWith("breder.lang")
		// || source.getPackageToken().image.startsWith("breder.util")
		// || source.getPackageToken().image.startsWith("breder.math")) {
		// String cname =
		// source.getStruct().getName().image.toLowerCase().replace('.', '_');
		// if (source.getStruct() instanceof BBasicStruct) {
		// BBasicStruct bstruct = (BBasicStruct) source.getStruct();
		// for (BMethod method : bstruct.getMethods()) {
		// String mname;
		// if (method.isConstructor()) {
		// mname = "init";
		// } else {
		// mname = method.getName().toLowerCase();
		// }
		// int index = method.getIndex();
		// output.println(String.format("#define b_bni_method_%s_%s_id(VM) %d",
		// cname, mname, index));
		// }
		// }
		// }
		// }
	}

	public void buildSource(PrintWriter output) throws IOException {
		// output.println(String.format("void* b_so_func_get(const char* name) {"));
		// for (BSource source : this.getSources()) {
		// if (source.getStruct() instanceof BBasicStruct) {
		// BBasicStruct bstruct = (BBasicStruct) source.getStruct();
		// for (BMethod method : bstruct.getMethods()) {
		// if (method.isNative()) {
		// output.println(String.format("\tif(!strcmp(name,\"%s\")) return (void*) %s;",
		// method.getNativeName(), method.getNativeName()));
		// }
		// }
		// }
		// }
		// output.println("}");
		// output.println();
		// for (BSource source : this.getSources()) {
		// String name = source.getClassname().toLowerCase().replace('.', '_');
		// output.println(String.format(
		// "b_bni_class_define(b_bni_class_%s_id, b_%s_id, \"%s\")",
		// name, name, source.getClassname()));
		// }
		// output.println();
		// for (BSource source : this.getSources()) {
		// String cname = source.getClassname().toLowerCase()
		// .replace('.', '_');
		// if (source.getStruct() instanceof BBasicStruct) {
		// BBasicStruct bstruct = (BBasicStruct) source.getStruct();
		// for (BMethod method : bstruct.getMethods()) {
		// String mname = method.getSimpleNativeName().toLowerCase();
		// output.println(String
		// .format("b_bni_method_define(b_bni_method_%s_%s_id, b_%s_%s_id, b_bni_class_%s_id, \"%s\")",
		// cname, mname, cname, mname, cname,
		// method.toString()));
		// }
		// }
		// }
	}

	private static void addFile(PrintWriter output, File sourceFile, int level) throws IOException {
		FileInputStream input = new FileInputStream(sourceFile);
		String content = new String(InputStreamUtil.getBytes(input));
		String[] lines = content.split("\n");
		addFile(output, sourceFile, lines, 0, level);
		input.close();
	}

	private static int addFile(PrintWriter output, File sourceFile, String[] lines, int index, int level)
			throws IOException {
		int n;
		for (n = index; n < lines.length; n++) {
			String line = lines[n];
			if (line.startsWith("#ifndef")) {
				n++;
			} else if (line.startsWith("#ifdef")) {
				n = addFile(output, sourceFile, lines, n + 1, level + 1);
			} else if (line.startsWith("#else")) {
			} else if (line.trim().startsWith("b_log_print")) {
			} else if (line.startsWith("#endif")) {
				return n;
			} else if (accept(line.trim())) {
				if (level <= 0) {
					output.println(line);
				}
			}
		}
		return n;
	}

	private static boolean accept(String line) {
		if (line.startsWith("#include")) {
			return false;
		}
		if (line.startsWith("#ifndef")) {
			return false;
		}
		if (line.startsWith("#endif")) {
			return false;
		}
		if (line.startsWith("#define B_") && line.endsWith("_H_")) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) throws Throwable {
		Compiler compiler = new ArduinoMobileCompiler();
		compiler.addClasspath(new File(new BrederLanguageFile(), "src").toString());
		compiler.addClasspath(new File("../breder.compiler/tst").toString());
		GenericCompiler.main(compiler, "breder.test.Test");
	}

}
