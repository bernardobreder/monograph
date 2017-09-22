package builder;

import java.io.FileNotFoundException;

import util.InputStreamUtil;
import util.Task;
import breder.io.BDirectory;
import breder.io.BResource;
import breder.io.BResourceManager;

public class ProjectBuilderTask extends Task {
	
	private BDirectory projectDir;
	
	public ProjectBuilderTask(BDirectory projectDir) {
		this.projectDir = projectDir;
	}
	
	@Override
	public void action() throws Throwable {
		BDirectory binDir = this.projectDir.getDir("bin");
		if (!binDir.exist()) {
			binDir = binDir.newDirectory();
		}
		BDirectory srcDir = this.projectDir.getDir("src");
		String arg = String.format("javac -cp %s -d %s %s/a/*.java", srcDir.getAbsoluteName(),
				binDir.getAbsoluteName(), srcDir.getAbsoluteName());
		ProcessBuilder builder = new ProcessBuilder("java", arg);
		Process process = builder.start();
		System.out.println(new String(InputStreamUtil.getBytes(process.getInputStream())));
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		BResource resource = BResourceManager.getInstance().get("../../prj/breder.test");
		if (resource.isDirectory()) {
			BDirectory dir = (BDirectory) resource;
			new ProjectBuilderTask(dir).run();
		} else
			throw new FileNotFoundException(resource.getAbsoluteName());
		
	}
	
}
