import breder.installer.lang.Installer;
import breder.installer.task.InstallerStarter;

public class Main {

	public static void main(String[] args) {
		new InstallerStarter(new Installer()).start();
	}

}
/**
 * Para linux, basta adicionar no arquivo ".profile" um valor
 * "export BREDER_HOME=..." Para macos, basta adicionar no arquivo
 * ".MacOSX/environment.plist" um valor : <plist version="1.0"> <dict>
 * <key>BREDER_HOME</key> <string>/Users/bernardobreder/breder/lang</string>
 * </dict> </plist> Para windows, basta executar um programa guardado no desktop
 * do aspire one
 */

