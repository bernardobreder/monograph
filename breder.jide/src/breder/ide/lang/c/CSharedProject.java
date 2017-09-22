package breder.ide.lang.c;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import breder.ide.lang.ProjectManager;
import breder.util.util.FileUtil;

public class CSharedProject extends CProject {

	public CSharedProject(File dir) throws ParseException, IOException {
		super(dir);
	}

	@Override
	public void build() throws IOException {
		this.buildDepend();
		File binaryFile = new File(this.getTargets().get(0));
		File binaryDir = binaryFile.getParentFile();
		FileUtil.remove(binaryDir);
		binaryDir.mkdirs();
		GccProcess process = new GccProcess();
		process.addOther("-shared");
		process.setOutput(binaryFile.getAbsolutePath() + ".so");
		for (File include : this.getIncludes()) {
			process.addInclude(include.getAbsolutePath());
		}
		for (File source : this.getSources()) {
			this.buildSource(process, source);
		}
		for (File linkDir : this.getLinkDirs()) {
			process.addLinkDir(linkDir.getAbsolutePath());
		}
		for (String link : this.getLinks()) {
			process.addLink(link);
		}
		for (String flag : this.getFlags()) {
			for (String split : flag.split(" |\r|\n|\t")) {
				if (split.length() > 0) {
					process.addOther(split);
				}
			}
		}
		for (String depend : this.getDependencys()) {
			for (CProject project : ProjectManager.getInstance().getProjects(
					CProject.class)) {
				if (project.getName().equals(depend)) {
					if (project.getTargets().size() > 0) {
						File tdir = new File(project.getTargets().get(0));
						this.buildObject(process, tdir);
					}
				}
			}
		}
		process.start();
	}

	private void buildSource(GccProcess process, File source) {
		for (File file : source.listFiles()) {
			if (file.isFile()) {
				if (file.getName().endsWith(".c")) {
					process.addSource(file.getAbsolutePath());
				}
			} else {
				this.buildSource(process, file);
			}
		}
	}

	private void buildObject(GccProcess process, File source) {
		for (File file : source.listFiles()) {
			if (file.isFile()) {
				if (file.getName().endsWith(".o")) {
					process.addSource(file.getAbsolutePath());
				}
			} else {
				this.buildObject(process, file);
			}
		}
	}

}
