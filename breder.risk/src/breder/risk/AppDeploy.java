package breder.risk;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.ZipInputStream;

import breder.deploy.Deploy;
import breder.util.io.HomeFile;

public class AppDeploy extends Deploy {

	private String mainClass = "breder.risk.Main";

	private File jarFile = new File(super.outDir, "BrederRisk.jar");

	private File exeFile = new File(super.outDir, "BrederRisk.exe");

	private File appFile = new File(super.outDir, "BrederRisk.app");

	private File appZipFile = new File(super.outDir, "BrederRisk.app.zip");

	private File exeZipFile = new File(super.outDir, "BrederRisk.exe.zip");

	public AppDeploy() throws IOException {
		try {
			this.buildJar(jarFile, mainClass, new File("../breder.risk/bin"),
					new File("../breder.util/bin"));
			this.buildApp(jarFile, mainClass, this.icnsfile, this.appFile);
			this.buildZip(appZipFile, this.appFile);
			this.extractZip(
					new ZipInputStream(new FileInputStream(appZipFile)),
					new File("/Applications/BLang"));
			new ProcessBuilder("chmod", "+x",
					"/Applications/BLang/BrederRisk.app/Contents/MacOS/JavaApplicationStub")
					.start();
			this.publish("../webapps/pub/" + jarFile.getName(), jarFile);
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
