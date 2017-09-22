package breder.installer.lang.task;

import java.io.File;

import javax.swing.text.JTextComponent;

import breder.installer.logic.IInstallTask;
import breder.installer.task.SetEnvironmentVariableTask;
import breder.installer.task.SetPathEnvironmentVariableTask;
import breder.util.io.BrederLanguageFile;
import breder.util.so.SoUtil;

public class InstallBrederLangTask implements IInstallTask {

	@Override
	public void perform(JTextComponent label) throws Exception {
		File binaryBrederHome = new BrederLanguageFile("bin");
		{
			new SetEnvironmentVariableTask("BREDER_HOME",
					new BrederLanguageFile().getAbsolutePath()).start();
		}
		{
			new SetPathEnvironmentVariableTask(
					binaryBrederHome.getAbsolutePath()).start();
		}
		if (SoUtil.isUnix()) {
			new ProcessBuilder("chmod", "+x", new File(binaryBrederHome,
					"breder").getAbsolutePath()).start().waitFor();
			new ProcessBuilder("chmod", "+x", new File(binaryBrederHome,
					"brederc").getAbsolutePath()).start().waitFor();
		}
	}

	@Override
	public String getName() {
		return "Installing the Breder Language";
	}

}
