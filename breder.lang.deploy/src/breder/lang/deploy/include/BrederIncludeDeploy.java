
package breder.lang.deploy.include;

import java.io.File;
import java.io.IOException;

import breder.deploy.Deploy;
import breder.lang.deploy.util.RunnableThread;
import breder.util.io.BrederLanguageFile;
import breder.util.util.FileUtil;

public class BrederIncludeDeploy extends RunnableThread {

	public BrederIncludeDeploy(File destDir) {
		super(destDir, true);
	}

	public void compile(File destDir, boolean is386) throws IOException {
		Deploy.copy(FileUtil.build("..", "blng.vm", "inc"), new BrederLanguageFile("inc"));
	}

	public static void main(String[] args) throws Exception {
		new BrederIncludeDeploy(new BrederLanguageFile("inc")).start();
	}

}
