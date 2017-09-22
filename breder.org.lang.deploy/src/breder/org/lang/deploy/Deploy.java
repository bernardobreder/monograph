package breder.org.lang.deploy;
import java.io.File;

import breder.deploy.TomcatDeploy;

public class Deploy extends TomcatDeploy {

	@Override
	protected String getProjectName() {
		return "lang";
	}

	@Override
	protected File[] getServerDirs() {
		return new File[] { new File("../breder.xml") };
	}

	@Override
	protected File getTomcatProject() {
		return new File("../breder.org.lang");
	}

	public static void main(String[] args) throws Exception {
		new Deploy().init();
	}

}
