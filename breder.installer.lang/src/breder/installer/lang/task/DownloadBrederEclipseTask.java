package breder.installer.lang.task;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipInputStream;

import javax.swing.SwingUtilities;
import javax.swing.text.JTextComponent;

import breder.installer.logic.IInstallTask;
import breder.util.exception.SOSupportedException;
import breder.util.io.BrederLanguageFile;
import breder.util.net.HttpUrlConnection;
import breder.util.so.SoUtil;
import breder.util.util.FileUtil;
import breder.util.util.InputStreamUtil;

public class DownloadBrederEclipseTask implements IInstallTask {

	@Override
	public void perform(final JTextComponent label) throws Exception {
		String path = null;
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
		path = String.format("http://www.breder.org/pub/beclipse_%s.zip", so);
		HttpUrlConnection c = new HttpUrlConnection(path);
		final File dir = new BrederLanguageFile();
		File eclipseFile = new File("beclipse.zip");
		OutputStream output = new FileOutputStream(eclipseFile);
		InputStream input = c.getInputStream();
		double reader = 0;
		byte[] bytes = new byte[1024];
		while (true) {
			int n = input.read(bytes);
			if (n == -1)
				break;
			reader += n;
			output.write(bytes, 0, n);
			final double bmreader = reader / 1024 / 1024;
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					label.setText(String
							.format("Downloading the Breder Eclipse (%s). \n(%.2f MBytes) with around 160MBytes",
									new File(dir, "beclipse").getAbsolutePath(),
									bmreader));
				}
			});
		}
		ZipInputStream zipinput = new ZipInputStream(new FileInputStream(
				eclipseFile));
		InputStreamUtil.extractZip(zipinput, dir);
		if (SoUtil.isLinux()) {
			File eFile = new File(new File(dir, "beclipse"), "eclipse");
			new ProcessBuilder("chmod", "+x", eFile.getAbsolutePath()).start()
					.waitFor();
		} else if (SoUtil.isMacOs()) {
			File eFile = FileUtil.build(dir.toString(), "beclipse",
					"Eclipse.app", "Contents", "MacOS", "eclipse");
			new ProcessBuilder("chmod", "+x", eFile.getAbsolutePath()).start()
					.waitFor();
			FileUtil.remove(FileUtil.build(dir.toString(), "__MACOSX"));
		}
		c.close();
		eclipseFile.delete();
	}

	@Override
	public String getName() {
		return "Downloading the Breder Eclipse.";
	}

}
