import java.io.File;

import breder.util.deploy.TomcatDeploy;

public class Deploy extends TomcatDeploy {

	@Override
	protected String getProjectName() {
		return "mobileme";
	}

	@Override
	protected File[] getServerDirs() {
		return new File[] { new File("../breder.util"), new File("../breder.sql") };
	}

	@Override
	protected File getTomcatProject() {
		return new File("../breder.org.mobileme");
	}

	public static void main(String[] args) throws Exception {
		new Deploy().init();
	}

}
