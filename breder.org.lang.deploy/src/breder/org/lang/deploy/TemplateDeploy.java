package breder.org.lang.deploy;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.JOptionPane;

import breder.org.disk.BFileSystem;
import breder.org.disk.IFile;
import breder.org.disk.IFolder;

public class TemplateDeploy extends Deploy {

	private static final File OUT_DIR = new File("out");

	private static final File TEMPLATE_DIR = new File("WEB-INF/template");

	public TemplateDeploy() throws IOException {
		String prepath = "/remote/lang/files/doc/";
		String path = JOptionPane
				.showInputDialog("Digite o caminho da publicação apartir de : "
						+ prepath);
		if (path == null || path.length() == 0) {
			return;
		}
		String fullpath = prepath + path;
		String folder = fullpath.substring(0, fullpath.lastIndexOf('/') + 1);
		String filename = fullpath.substring(fullpath.lastIndexOf('/') + 1);
		IFolder f = BFileSystem.getFolder(null, folder);
		if (f == null) {
			System.out.println("not found the folder : " + folder);
		}
		{
			IFile file = f.getFile(filename);
			if (file != null) {
				file.remove();
			}
		}
		File zip = new File(OUT_DIR, "template.zip");
		this.buildZip(zip, TEMPLATE_DIR.listFiles());
		f.newFile(filename, new FileInputStream(zip));
		this.remove(OUT_DIR);
	}

	public static void main(String[] args) throws Exception {
		new TemplateDeploy();
	}

}
