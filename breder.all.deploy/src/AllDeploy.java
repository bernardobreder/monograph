public class AllDeploy {

	public static void main(String[] args) throws Exception {
		breder.installer.lang.AppDeploy.main(null);
		breder.lang.deploy.BrederDeploy.main(null);
		breder.lang.deploy.EclipsePluginDeploy.main(null);
		breder.org.lang.deploy.Deploy.main(null);
		breder.risk.AppDeploy.main(null);
		breder.test.AppDeploy.main(null);
	}

}
