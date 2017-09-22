import java.io.File;

import breder.deploy.TomcatDeploy;

public class ServerDeploy extends TomcatDeploy {

	private File inpaServerFile = new File("../breder.org.lang.gui.server");

	private File inpaClientFile = new File("../breder.org.lang.gui.client");

	private File brederUtilFile = new File("../breder.util");

	private File brederSqlFile = new File("../breder.sql");

	private File inpaSharedFile = new File("../breder.org.lang.gui.shared");

	private File brederWebserviceFile = new File("../breder.webservice");

	private File inpaTomcatFile = new File("../breder.org.lang.gui.tomcat");

	private File outputDir = new File("out");

	private File tempDir = new File("tmp");

	public void init() throws Exception {
		this.remove(outputDir);
		this.remove(tempDir);
		this.outputDir.mkdirs();
		this.buildJar(new File(outputDir, "client.jar"),
				"breder.org.lang.gui.Main", new File(inpaClientFile, "bin"),
				new File(brederUtilFile, "bin"),
				new File(inpaSharedFile, "bin"), new File(brederWebserviceFile,
						"bin"), new File(brederWebserviceFile,
						"lib/servlet-api.jar"));
		this.buildWar(new File(outputDir, "server.war"),
				this.getTomcatProject(), this.getServerDirs());
		this.publish(String.format("webapps/%s.war", this.getProjectName()),
				new File("out/server.war"));
		this.remove(outputDir);
	}

	public static void main(String[] args) throws Exception {
		new ServerDeploy().init();
	}

	@Override
	protected File getTomcatProject() {
		return inpaTomcatFile;
	}

	@Override
	protected File[] getServerDirs() {
		return new File[] { inpaServerFile, inpaSharedFile,
				brederWebserviceFile, brederSqlFile, brederUtilFile };
	}

	@Override
	protected String getProjectName() {
		return "langui";
	}

}
