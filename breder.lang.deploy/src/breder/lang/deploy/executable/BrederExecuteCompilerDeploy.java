package breder.lang.deploy.executable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.jar.Attributes;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;

import breder.deploy.Deploy;
import breder.lang.deploy.util.RunnableThread;
import breder.util.io.BrederLanguageFile;
import breder.util.util.FileUtil;

public class BrederExecuteCompilerDeploy extends RunnableThread {

	public BrederExecuteCompilerDeploy(File destDir) {
		super(destDir, false);
	}

	public void compile(File destDir, boolean is386) throws IOException {
		File destFile = new File(destDir, "brederc.jar");
		File srcDir = FileUtil.build("..", "breder.compiler", "bin");
		if (!srcDir.exists()) {
			throw new FileNotFoundException(srcDir.getAbsolutePath());
		}
		if (destFile.exists()) {
			destFile.delete();
		}
		Manifest manifest = new Manifest();
		manifest.getMainAttributes().put(Attributes.Name.MANIFEST_VERSION,
				"1.0");
		manifest.getMainAttributes().put(Attributes.Name.MAIN_CLASS,
				"breder.compiler.BrederCompiler");
		destFile.getParentFile().mkdirs();
		JarOutputStream output = new JarOutputStream(new FileOutputStream(
				destFile), manifest);
		try {
			for (File aux : srcDir.listFiles()) {
				if (!aux.isHidden()) {
					Deploy.add(srcDir, aux, output);
				}
			}
		} finally {
			output.close();
		}
	}

	public static void main(String[] args) throws Exception {
		new BrederExecuteCompilerDeploy(new BrederLanguageFile("bin")).start();
	}

}
