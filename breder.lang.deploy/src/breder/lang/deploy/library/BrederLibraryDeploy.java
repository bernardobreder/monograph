package breder.lang.deploy.library;

import java.io.File;
import java.io.IOException;

import breder.deploy.BrederLibraryCompiler;
import breder.deploy.Deploy;
import breder.lang.deploy.util.RunnableThread;
import breder.util.io.BrederLanguageFile;
import breder.util.util.FileUtil;

public class BrederLibraryDeploy extends RunnableThread {

	public BrederLibraryDeploy(File destDir, boolean is386) {
		super(destDir, is386);
	}

	public void compile(File destDir, boolean is386) throws IOException {
		File tempDir = FileUtil.buildTmp();
		try {
			File file = new BrederLibraryCompiler().set386(is386).execute(
					tempDir, new File("../"))[0];
			Deploy.copy(file, destDir);
		} finally {
			FileUtil.remove(tempDir);
		}
	}

	public static void main(String[] args) throws Exception {
		new BrederLibraryDeploy(new BrederLanguageFile("lib"), false).start();
	}

}
