package breder.lang.deploy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import breder.util.io.HomeFile;
import breder.util.net.BrederFtp;
import breder.util.util.FileUtil;
import breder.util.util.InputStreamUtil;

public class EclipsePluginDeploy {

	public static void main(String[] args) throws Exception {
		File dir = FileUtil.build(new HomeFile().toString(), "breder",
				"eclipse", "plugins");
		if (!dir.exists())
			throw new FileNotFoundException(dir.getAbsolutePath());
		File file = FileUtil.listContain(dir, "brederplugin")[0];
		BrederFtp ftp = new BrederFtp("../webapps/pub/breder_plugin.jar");
		InputStreamUtil.copyStream(new FileInputStream(file),
				ftp.getOutputStream());
		ftp.getOutputStream().close();
	}

}
