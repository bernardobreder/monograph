
package breder.lang.deploy.shared;

import java.io.File;
import java.io.IOException;

import breder.deploy.BrederSharedCompiler;
import breder.deploy.Deploy;
import breder.lang.deploy.util.RunnableThread;
import breder.util.io.BrederLanguageFile;
import breder.util.so.SoUtil;
import breder.util.util.FileUtil;

public class BrederSharedGuiOpenglDeploy extends RunnableThread {

	public BrederSharedGuiOpenglDeploy(File destDir, boolean is386) {
		super(destDir, is386);
	}

	public void compile(File destDir, boolean is386) throws IOException {
		String[] flags = new String[0];
		if (SoUtil.isWindow()) {
			flags = new String[] { "-L../blng.gui.opengl/lib/windows", "-I../blng.gui.opengl/lib/windows",
					"-lopengl32", "-lglut32", "-lglu32" };
		} else if (SoUtil.isLinux()) {
			flags = new String[] { "-l", "X11", "-l", "Xi", "-l", "Xmu", "-l", "glut", "-l", "GL", "-l", "GLU" };
		} else if (SoUtil.isMacOs()) {
			flags = new String[] { "-framework", "OpenGL", "-framework", "GLUT" };
		}
		File tempDir = FileUtil.buildTmp();
		try {
			File file = new BrederSharedCompiler().set386(is386).execute(tempDir, new File("../"),
					new File("../blng.gui.opengl"), new File("../blng.gui.opengl/nsrc/breder_gui_opengl"),
					new File("../blng.gui.opengl/nsrc/breder_gui_opengl"), "breder_gui_opengl", flags)[0];
			Deploy.copy(file, destDir);
		} finally {
			FileUtil.remove(tempDir);
		}
	}

	public static void main(String[] args) throws Exception {
		new BrederSharedGuiOpenglDeploy(new BrederLanguageFile("nat"), false).start();
	}

}
