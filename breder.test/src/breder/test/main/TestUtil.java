package breder.test.main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import breder.deploy.builder.BrederProfileCompiler;
import breder.test.BrederTestConfigurator;
import breder.test.execute.BVMOpcode;
import breder.test.execute.BVMProcess;
import breder.test.model.ITest;
import breder.test.model.Source;
import breder.util.io.BrederLanguageFile;
import breder.util.io.HomeFile;
import breder.util.so.SoUtil;
import breder.util.util.FileUtil;
import breder.util.util.InputStreamUtil;

public class TestUtil {

	public static void compile(File dir, ITest test) throws Throwable {
		FileUtil.remove(dir);
		for (Source source : test.getSources()) {
			File file = new File(dir, source.getName().replace('.',
					File.separatorChar)
					+ ".breder");
			file.getParentFile().mkdirs();
			file.createNewFile();
			FileOutputStream output = new FileOutputStream(file);
			output.write(source.getCode().getBytes());
			output.close();
		}
		String mainClass = getMainSource(test).getName();
		List<String> commands = new ArrayList<String>();
		{
			commands.addAll(Arrays.asList(BrederTestConfigurator
					.getInstance()
					.check("breder.test.app.brederc",
							new BrederLanguageFile("bin", "brederc"
									+ SoUtil.getExtension()).toString())
					.split(" ")));
			commands.addAll(Arrays.asList(mainClass, "-cp", dir.toString(),
					"-o", new File(dir, "binary.b").toString()));
			commands.addAll(Arrays.asList(BrederTestConfigurator.getInstance()
					.check("breder.test.app.brederc.arg", "").split(" ")));
		}
		Process process = new ProcessBuilder(commands).start();
		process(test, process);
		{
			String text = new String(InputStreamUtil.getBytes(process
					.getErrorStream()));
			if (text.length() > 0) {
				throw new IOException(text);
			}
			text = new String(
					InputStreamUtil.getBytes(process.getInputStream()));
			if (text.length() > 0) {
				throw new IOException(text);
			}
		}
	}

	public static void process(ITest test, Process process) throws IOException {
		InterruptedThread thread = new InterruptedThread(60000);
		try {
			thread.start();
			process.waitFor();
			thread.interrupt();
		} catch (InterruptedException e) {
			process.destroy();
			String text = "program fail : "
					+ ((Integer) process.exitValue()).toString();
			throw new IOException(text);
		}
	}

	public static boolean expected(ITest test, List<String> lines) {
		List<String> expecteds = test.getExpected();
		if (lines.size() == 0 && expecteds.size() == 1
				&& expecteds.get(0).trim().length() == 0) {
			return true;
		}
		if (lines.size() != expecteds.size()) {
			return false;
		}
		for (int n = 0; n < expecteds.size(); n++) {
			String line = lines.get(n).trim();
			String expected = expecteds.get(n).trim();
			if (expected.length() > 0) {
				if (!line.equals(expected)) {
					return false;
				}
			} else {
				if (line.length() > 0) {
					return false;
				}
			}
		}
		return true;
	}

	public static boolean expected(ITest test, String text) {
		return expected(
				test,
				text.trim().length() == 0 ? new ArrayList<String>() : Arrays
						.asList(text.split("\n")));
	}

	public static Source getMainSource(ITest test) {
		String main = test.getMainClass();
		if (main == null) {
			return test.getSources().get(0);
		} else {
		}
		for (Source source : test.getSources()) {
			if (source.getName().equals(main)) {
				return source;
			}
		}
		throw new RuntimeException("not found the main source");
	}

	public static File compileBrederVM(File dir) throws InterruptedException,
			IOException {
		return new BrederProfileCompiler().execute(dir, new File(
				BrederTestConfigurator.getInstance().check("breder.projects")))[0];
	}

	public static BVMProcess execute(File brederProfileFile, File binaryFile)
			throws IOException, InterruptedException {
		List<String> commands = new ArrayList<String>();
		{
			commands.add(brederProfileFile.toString());
			commands.add(binaryFile.toString());
		}
		Process process = new ProcessBuilder(commands).start();
		process.waitFor();
		String err = new String(InputStreamUtil.getBytes(process
				.getErrorStream())).trim();
		String input = new String(InputStreamUtil.getBytes(process
				.getInputStream())).trim();
		if (err.length() > 0) {
			throw new RuntimeException(err);
		}
		List<BVMOpcode> opcodes = new ArrayList<BVMOpcode>();
		BVMOpcode opcode = null;
		System.out.println(input);
		String[] lines = input.split("\n");
		int count = 0;
		for (String line : lines) {
			line = line.trim();
			if (line.startsWith("[info] :")) {
			} else if (line.startsWith("[")) {
				Integer pc = new Integer(line.substring(1, 9).trim());
				String opcodeStr = line.substring(10).trim();
				opcodes.add(opcode = new BVMOpcode(pc, opcodeStr));
				if (++count == 20) {
					break;
				}
			} else if (line.startsWith("so={")) {
				String sosLine = line.substring(4, line.length() - 1);
				String[] sos = sosLine.split(";");
				for (String so : sos) {
					opcode.getStackObject().add(so);
				}
			} else if (line.startsWith("st={")) {
				String stsLine = line.substring(4, line.length() - 1);
				String[] sts = stsLine.split(";");
				for (String st : sts) {
					opcode.getStackTrace().add(st);
				}
			} else if (opcode != null) {
				opcode.getOutput().add(line);
			}
		}
		return new BVMProcess("",
				opcodes.toArray(new BVMOpcode[opcodes.size()]));
	}

	public static String execute(File dir, ITest test) throws IOException {
		return execute(dir, test, new ArrayList<String>());
	}

	public static void execute(File dir, ITest test, Long memory)
			throws IOException {
		execute(dir, test, Arrays.asList("-debug_memory", memory.toString()));
	}

	private static String execute(File dir, ITest test, List<String> flags)
			throws IOException {
		List<String> commands = new ArrayList<String>();
		commands.add(BrederTestConfigurator.getInstance().check(
				"breder.test.app.breder",
				new File(new File(new File(new HomeFile(), "blng"), "bin"),
						"breder").getAbsolutePath())+ SoUtil.getExtension());
		commands.add(new File("binary.b").toString());
		commands.addAll(Arrays.asList(BrederTestConfigurator.getInstance()
				.check("breder.test.app.breder.arg", "").split(" ")));
		commands.addAll(flags);
		long timer = System.currentTimeMillis();
		Process process = new ProcessBuilder(commands).directory(dir).start();
		TestUtil.process(test, process);
		timer = System.currentTimeMillis() - timer;
		test.setTimer(timer);
		int err = process.getErrorStream().read();
		if (err != -1) {
			final byte[] bytes = InputStreamUtil.getBytes(process
					.getErrorStream());
			String text = (char) err + new String(bytes);
			throw new IOException(text);
		}
		int in = process.getInputStream().read();
		if (in != -1) {
			final byte[] bytes = InputStreamUtil.getBytes(process
					.getInputStream());
			String text = (char) in + new String(bytes);
			test.setOk(TestUtil.expected(test, text));
			return text;
		}
		test.setOk(TestUtil.expected(test, ""));
		return "";
	}

}
