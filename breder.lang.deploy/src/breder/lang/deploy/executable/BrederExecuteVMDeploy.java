package breder.lang.deploy.executable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import breder.deploy.BrederVMCompiler;
import breder.deploy.Deploy;
import breder.lang.deploy.util.RunnableThread;
import breder.util.io.BrederLanguageFile;
import breder.util.util.FileUtil;

public class BrederExecuteVMDeploy extends RunnableThread {

	public BrederExecuteVMDeploy(File destDir, boolean is386) {
		super(destDir, is386);
	}

	public void compile(File destDir, boolean is386) throws IOException {
		File tempDir = FileUtil.buildTmp();
		try {
			File file = new BrederVMCompiler().set386(is386).execute(tempDir,
					new File("../"))[0];
			if (!file.exists()) {
				throw new FileNotFoundException(file.getAbsolutePath());
			}
			Deploy.copy(file, destDir);
		} finally {
			FileUtil.remove(tempDir);
		}
	}

	public static void main(String[] args) throws Exception {
		new BrederExecuteVMDeploy(new BrederLanguageFile("bin"), false).start();
	}

}
