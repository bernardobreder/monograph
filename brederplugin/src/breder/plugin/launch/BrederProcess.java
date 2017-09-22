package breder.plugin.launch;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;

import breder.plugin.BrederProjectConstant;
import breder.plugin.util.BrederLanguageFile;
import breder.plugin.util.SoUtil;

public class BrederProcess {

	private String output = "./binary.b";

	private List<String> flags = new ArrayList<String>();

	private Process process;

	private final IProject project;

	public BrederProcess(IProject project) {
		super();
		this.project = project;
	}

	public Process start(File dir) throws IOException, InterruptedException {
		List<String> list = new ArrayList<String>();
		String brederFile = new BrederLanguageFile("bin", "breder"
				+ SoUtil.getExtension()).toString();
		if (SoUtil.isUnix()) {
			new ProcessBuilder("chmod", "+x", brederFile.toString()).start()
					.waitFor();
		}
		list.add(brederFile);
		list.add(output);
		list.add("-no_breder_home");
		list.add("-lp");
		list.add(this.project.getFolder(BrederProjectConstant.BINARY_FOLDER)
				.getLocation().toFile().toString());
		list.add("-lp");
		list.add(new File(new BrederLanguageFile(), "nat").toString());
		for (String flag : flags) {
			list.add(flag);
		}
		this.process = new ProcessBuilder(list.toArray(new String[0]))
				.directory(dir).start();
		return this.process;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public void addLinkerPath(String string) {
		this.flags.add("-lp");
		this.flags.add(string);
	}

	public Process getProcess() {
		return this.process;
	}

}
