
package breder.lang.deploy.shared;

import java.io.File;
import java.io.IOException;

import breder.deploy.BrederSharedCompiler;
import breder.deploy.Deploy;
import breder.lang.deploy.util.RunnableThread;
import breder.util.io.BrederLanguageFile;
import breder.util.util.FileUtil;

public class BrederSharedGuiSdlDeploy extends RunnableThread {

	public BrederSharedGuiSdlDeploy(File destDir, boolean is386) {
		super(destDir, is386);
	}

	public void compile(File destDir, boolean is386) throws IOException {
		String[] flags = new String[0];
		flags = new String[] { "-lSDL", "-lSDL_image" };
		File tempDir = FileUtil.buildTmp();
		try {
			File file = new BrederSharedCompiler().set386(is386).execute(tempDir, new File("../"),
					new File("../blng.gui.sdl"), new File("../blng.gui.sdl/nsrc/breder_gui_sdl"),
					new File("../blng.gui.sdl/nsrc/breder_gui_sdl"), "breder_gui_sdl", flags)[0];
			Deploy.copy(file, destDir);
		} finally {
			FileUtil.remove(tempDir);
		}
	}

	public static void main(String[] args) throws Exception {
		new BrederSharedGuiSdlDeploy(new BrederLanguageFile("nat"), false).start();
	}

}
