package breder.plugin.wizard.build.bproject.template;

public class CameraTemplate extends SourceTemplate {

	@Override
	public String getTemplateFolderName() {
		return "camera";
	}

	@Override
	public String getTemplateName() {
		return "Camera";
	}

	@Override
	public String[] getTemplateSources() {
		return new String[] { "src/Main.breder", "src/bgame/BCamera.breder",
				"src/bgame/BWorld.breder", "texture.raw" };
	}

}
