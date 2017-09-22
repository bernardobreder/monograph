
package breder.lang.deploy.source;

import java.io.File;

import breder.lang.deploy.util.RunnableThread;
import breder.util.io.BrederLanguageFile;
import breder.util.util.FileUtil;

public class BrederSource extends Thread {

	private String projectDir;

	public BrederSource(String projectDir) {
		super(RunnableThread.GROUP, projectDir);
		this.projectDir = projectDir;
	}

	@Override
	public void run() {
		try {
			File project = new File(this.projectDir);
			File srcDir = new File(project, "src");
			File destDir = new BrederLanguageFile("src");
			FileUtil.copyDir(srcDir, destDir);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
