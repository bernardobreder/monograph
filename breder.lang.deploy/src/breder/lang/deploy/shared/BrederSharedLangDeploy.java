
package breder.lang.deploy.shared;

import java.io.File;
import java.io.IOException;

import breder.deploy.BrederSharedCompiler;
import breder.deploy.Deploy;
import breder.lang.deploy.util.RunnableThread;
import breder.util.io.BrederLanguageFile;
import breder.util.util.FileUtil;

public class BrederSharedLangDeploy extends RunnableThread {

	public BrederSharedLangDeploy(File destDir, boolean is386) {
		super(destDir, is386);
	}

	public void compile(File destDir, boolean is386) throws IOException {
		File tempDir = FileUtil.buildTmp();
		try {
			File file = new BrederSharedCompiler().set386(is386).execute(tempDir, new File("../"),
					new File("../blng.lang"), new File("../blng.lang/nsrc/breder_lang"),
					new File("../blng.lang/nsrc/breder_lang"), "breder_lang")[0];
			Deploy.copy(file, destDir);
		} finally {
			FileUtil.remove(tempDir);
		}
	}

	public static void main(String[] args) throws Exception {
		new BrederSharedLangDeploy(new BrederLanguageFile("nat"), false).start();
	}

}
