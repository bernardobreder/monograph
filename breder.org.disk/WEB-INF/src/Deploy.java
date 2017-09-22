import java.io.File;

import breder.deploy.TomcatDeploy;

public class Deploy extends TomcatDeploy {

	@Override
	protected String getProjectName() {
		return "disk";
	}

	@Override
	protected File[] getServerDirs() {
		return new File[] { new File("../breder.util"),
				new File("../breder.sql"), new File("../breder.disk") };
	}

	@Override
	protected File getTomcatProject() {
		return new File("../breder.org.disk");
	}

	public static void main(String[] args) throws Exception {
		new Deploy().init();
	}

}
