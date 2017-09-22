
package breder.lang.deploy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.zip.ZipInputStream;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import breder.deploy.Deploy;
import breder.lang.deploy.executable.BrederExecuteCompilerDeploy;
import breder.lang.deploy.executable.BrederExecuteVMDeploy;
import breder.lang.deploy.include.BrederIncludeDeploy;
import breder.lang.deploy.library.BrederLibraryDeploy;
import breder.lang.deploy.shared.BrederSharedCompilerDeploy;
import breder.lang.deploy.shared.BrederSharedGuiOpenglDeploy;
import breder.lang.deploy.shared.BrederSharedGuiSdlDeploy;
import breder.lang.deploy.shared.BrederSharedIoDeploy;
import breder.lang.deploy.shared.BrederSharedLangDeploy;
import breder.lang.deploy.shared.BrederSharedMathDeploy;
import breder.lang.deploy.shared.BrederSharedUtilDeploy;
import breder.lang.deploy.source.BrederSource;
import breder.lang.deploy.util.RunnableThread;
import breder.util.exception.SOSupportedException;
import breder.util.io.BrederLanguageFile;
import breder.util.lookandfeel.LookAndFeel;
import breder.util.net.BrederFtp;
import breder.util.so.SoUtil;
import breder.util.task.ThreadUtil;
import breder.util.util.DateUtil;
import breder.util.util.FileUtil;
import breder.util.util.InputStreamUtil;

public class BrederDeploy extends Deploy {

	private static final String NATIVE_FOLDER = "nat";

	private static final String BINARY_FOLDER = "bin";

	private static final String SOURCE_FOLDER = "src";

	private static final String INCLUDE_FOLDER = "inc";

	private static final String LIBRARY_FOLDER = "lib";

	private BrederProperties properties = new BrederProperties();

	private File brederDir;

	private File projectDir;

	private File libraryDir;

	private File sourceDir;

	private File nativeDir;

	private File binaryDir;

	private File includeDir;

	public BrederDeploy() throws Exception {
		String name;
		{
			if (SoUtil.isWindow()) {
				name = "brederw.zip";
			} else if (SoUtil.isLinux()) {
				name = "brederl.zip";
			} else if (SoUtil.isMacOs()) {
				name = "brederm.zip";
			} else
				throw new RuntimeException();
		}
		// this.build();
		{
			String path = "config_" + SoUtil.getName().toLowerCase() + ".properties";
			if (new File(path).exists()) {
				this.properties.load(new FileInputStream(path));
			} else {
				String path2 = "../breder.lang.deploy/" + path;
				if (new File(path2).exists()) {
					this.properties.load(new FileInputStream(path2));
				} else {
					throw new FileNotFoundException(path);
				}
			}
		}
		String[] posBrederHomes;
		String arch = JOptionPane.showInputDialog("Which arch : 32|64 ?").trim();
		if (arch.equals("32")) {
			posBrederHomes = new String[] { "32" };
		} else if (arch.equals("64")) {
			posBrederHomes = new String[] { "64" };
		} else if (arch.equals("32|64") || arch.equals("64|32")) {
			posBrederHomes = new String[] { "32", "64" };
		} else {
			throw new RuntimeException("wrong arch");
		}
		for (String posBrederHome : posBrederHomes) {
			{
				brederDir = new BrederLanguageFile();
				if (!brederDir.exists()) {
					if (!brederDir.mkdirs()) {
						throw new RuntimeException("properties 'breder.home' not existe with "
								+ brederDir.getAbsolutePath());
					}
				}
			}
			{
				binaryDir = new File(brederDir, BINARY_FOLDER);
				nativeDir = new File(brederDir, NATIVE_FOLDER);
				sourceDir = new File(brederDir, SOURCE_FOLDER);
				libraryDir = new File(brederDir, LIBRARY_FOLDER);
				includeDir = new File(brederDir, INCLUDE_FOLDER);
			}
			{
				projectDir = new File(this.properties.checkProperty("project.home"));
				if (!projectDir.exists()) {
					throw new RuntimeException("properties 'project.home' not existe with "
							+ projectDir.getAbsolutePath());
				}
			}
			this.cleanDir(binaryDir);
			this.cleanDir(nativeDir);
			this.cleanDir(sourceDir);
			this.cleanDir(libraryDir);
			this.cleanDir(includeDir);
			this.copyBrederVm(posBrederHome.equals("32"));
			this.copyBrederInclude();
			this.copyBrederLibrary(posBrederHome.equals("32"));
			this.copyBrederNative(posBrederHome.equals("32"));
			this.copyBrederSource();
			this.copyBrederCompiler();
			this.copyBrederBinFiles();
			ThreadUtil.join(RunnableThread.GROUP);
			if (SoUtil.isWindow()) {
				this.buildBrederCompiler();
			} else {
				this.buildBrederCompilerUnix();
			}
			File zipFile = new File(FileUtil.buildTmp(), name);
			try {
				this.buildZip(zipFile, binaryDir, includeDir, nativeDir, sourceDir, libraryDir);
				if (posBrederHome.equals("64")) {
					this.extractZip(new ZipInputStream(new FileInputStream(zipFile)), new BrederLanguageFile());
					if (SoUtil.isUnix()) {
						new ProcessBuilder("chmod", "+x", new BrederLanguageFile("bin", "breder").getAbsolutePath())
								.start().waitFor();
						new ProcessBuilder("chmod", "+x", new BrederLanguageFile("bin", "brederc").getAbsolutePath())
								.start().waitFor();
					}
				}
				this.publish(zipFile, posBrederHome);
			} finally {
				FileUtil.remove(zipFile);
			}
		}
	}

	private void publish(File file, String arch) throws Exception {
		String so;
		if (SoUtil.isMacOs()) {
			so = "mac";
		} else if (SoUtil.isLinux()) {
			so = "linux";
		} else if (SoUtil.isWindow()) {
			so = "windows";
		} else {
			throw new SOSupportedException();
		}
		String url = "../webapps/pub/";
		{
			String path = String.format("breder_%s_%s.zip", so, arch);
			InputStream input = new FileInputStream(file);
			OutputStream output = new BrederFtp(url + path).getOutputStream();
			copyStream(input, output);
			input.close();
			output.close();
			System.out.println("published " + path);
		}
		{
			Number version = 1;
			Number month = DateUtil.getMonth(new Date()) + 1;
			Number year = DateUtil.getYear(new Date()) - 2000;
			String path = String.format("breder_%s_%s_%d_%d_%d.zip", so, arch, version, year, month);
			InputStream input = new FileInputStream(file);
			OutputStream output = new BrederFtp(url + path).getOutputStream();
			copyStream(input, output);
			input.close();
			output.close();
			System.out.println("published " + path);
		}

	}

	private void copyBrederBinFiles() throws IOException {
		String property = this.properties.getProperty("breder.bin.copy");
		if (property != null) {
			String[] splits = property.split(";");
			for (String split : splits) {
				if (split.trim().length() > 0) {
					copy(new File(split.trim()), binaryDir);
				}
			}
		}
	}

	private void copyBrederInclude() throws IOException {
		new BrederIncludeDeploy(new BrederLanguageFile("inc")).start();
	}

	private void copyBrederLibrary(boolean is386) throws IOException {
		new BrederLibraryDeploy(new BrederLanguageFile("lib"), is386).start();
	}

	private void buildBrederCompilerUnix() throws IOException {
		File binaryDir = new File(brederDir, BINARY_FOLDER);
		{
			FileOutputStream output = new FileOutputStream(new File(binaryDir, "brederc"));
			output.write("java -jar ~/blng/bin/brederc.jar $*".getBytes());
			output.close();
		}
		{
			new ProcessBuilder("chmod", "+x", new File(binaryDir, "brederc").getAbsolutePath()).start();
			new ProcessBuilder("chmod", "+x", new File(binaryDir, "breder").getAbsolutePath()).start();
		}
	}

	public void cleanDir(File dir) {
		this.remove(dir);
		dir.mkdir();
	}

	private void copyBrederVm(boolean is386) throws IOException {
		new BrederExecuteVMDeploy(new BrederLanguageFile("bin"), is386).start();
	}

	private void copyBrederNative(boolean is386) throws IOException {
		File binDir = new File(brederDir, NATIVE_FOLDER);
		new BrederSharedLangDeploy(binDir, is386).start();
		new BrederSharedUtilDeploy(binDir, is386).start();
		new BrederSharedMathDeploy(binDir, is386).start();
		new BrederSharedIoDeploy(binDir, is386).start();
		// new BrederSharedCompilerDeploy(binDir, is386).start();
		// new BrederSharedGuiOpenglDeploy(binDir, is386).start();
		// new BrederSharedGuiSdlDeploy(binDir, is386).start();
	}

	private void copyBrederSource() throws IOException {
		if (true) {
			new BrederSource("../blng.lang").start();
			new BrederSource("../blng.util").start();
			new BrederSource("../blng.io").start();
			new BrederSource("../blng.math").start();
			new BrederSource("../blng.net").start();
			new BrederSource("../blng.sql").start();
			new BrederSource("../blng.test").start();
			new BrederSource("../blng.gui").start();
			// new BrederSource("../blng.gui.opengl").start();
			// new BrederSource("../blng.compiler").start();
			// new BrederSource("../blng.gui.sdl").start();
			new BrederLanguageFile("src", "Main.breder").deleteOnExit();
		} else {
			File bsl = new File(brederDir, SOURCE_FOLDER);
			String string = this.properties.checkProperty("project.breder.compiler.path");
			File file = new File(projectDir, string);
			string = this.properties.checkProperty("project.breder.compiler.tst");
			file = new File(file, string);
			if (!file.exists()) {
				throw new FileNotFoundException(file.getAbsolutePath());
			}
			copyDir(file, bsl);
		}
	}

	private void copyBrederCompiler() throws IOException {
		new BrederExecuteCompilerDeploy(new BrederLanguageFile("bin")).start();
	}

	private void buildBrederCompiler() throws Exception {
		File file = new File("launch4j.xml");
		String launch4j = this.properties.checkProperty("launch4j.home");
		File dir = new File(launch4j);
		dir = new File(dir, this.properties.checkProperty("launch4j.exe"));
		ProcessBuilder builder = new ProcessBuilder(Arrays.asList(dir.getAbsolutePath(), file.getAbsolutePath()));
		Process process = builder.start();
		process.waitFor();
		String err = new String(InputStreamUtil.getBytes(process.getErrorStream()));
		if (err.trim().length() > 0) {
			throw new IOException(err);
		}
		String in = new String(InputStreamUtil.getBytes(process.getInputStream()));
		if (in.trim().length() > 0) {
			System.out.println(in);
		}
	}

	public static void main(String[] args) throws Exception {
		SwingUtilities.invokeAndWait(new Runnable() {

			@Override
			public void run() {
				LookAndFeel.getInstance().installNimbus();
			}
		});
		new BrederDeploy();
	}

}
