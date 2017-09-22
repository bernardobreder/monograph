package breder.installer.lang;
import java.io.File;
import java.io.IOException;

import breder.deploy.Deploy;
import breder.util.io.HomeFile;

public class AppDeploy extends Deploy {

	private String mainClass = "Main";

	private File jarFile = new File(super.outDir, "blanginstall.jar");

	private File exeFile = new File(super.outDir, "blanginstall.exe");

	private File appFile = new File(super.outDir, "BLangInstall.app");

	private File appZipFile = new File(super.outDir, "blanginstall.app.zip");

	private File exeZipFile = new File(super.outDir, "blanginstall.exe.zip");

	public AppDeploy() throws IOException {
		try {
			this.buildJar(jarFile, mainClass, new File(
					"../breder.installer.lang/bin"), new File(
					"../breder.util/bin"), new File("../breder.io/bin"),
					new File("../breder.xml/bin"), new File(
							"../breder.installer/bin"));
			this.publish("../webapps/pub/" + jarFile.getName(), jarFile);
			this.buildApp(jarFile, mainClass, this.icnsfile, this.appFile);
			this.buildZip(appZipFile, this.appFile);
			this.publish("../webapps/pub/" + appZipFile.getName(), appZipFile);
			this.buildExe(jarFile, iconfile, false, new File(new HomeFile(),
					"launch4j/launch4j"), this.exeFile);
			this.buildZip(exeZipFile, this.exeFile);
			this.publish("../webapps/pub/" + exeZipFile.getName(), exeZipFile);
		} finally {
			this.finish();
		}
	}

	public static void main(String[] args) throws IOException {
		new AppDeploy();
	}

}
