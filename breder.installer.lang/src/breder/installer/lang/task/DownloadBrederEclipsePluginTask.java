
package breder.installer.lang.task;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import javax.swing.text.JTextComponent;

import breder.installer.logic.IInstallTask;
import breder.util.io.BrederLanguageFile;
import breder.util.net.HttpUrlConnection;
import breder.util.util.InputStreamUtil;

public class DownloadBrederEclipsePluginTask implements IInstallTask {

	@Override
	public void perform(final JTextComponent label) throws Exception {
		String path = "http://www.breder.org/pub/breder_plugin.jar";
		HttpUrlConnection c = new HttpUrlConnection(path);
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		InputStream input = c.getInputStream();
		double reader = 0;
		byte[] bytes = new byte[1024];
		while (true) {
			int n = input.read(bytes);
			if (n == -1)
				break;
			reader += n;
			output.write(bytes, 0, n);
		}
		File eclipseDir = new BrederLanguageFile("beclipse");
		eclipseDir.mkdirs();
		File pluginDir = new File(eclipseDir, "plugins");
		pluginDir.mkdirs();
		FileOutputStream foutput = new FileOutputStream(new File(pluginDir, "brederplugin_1.0.0.jar"));
		InputStreamUtil.copyStream(new ByteArrayInputStream(output.toByteArray()), foutput);
		foutput.close();
	}

	@Override
	public String getName() {
		return "Downloading the Breder Eclipse Plugin.";
	}

}
