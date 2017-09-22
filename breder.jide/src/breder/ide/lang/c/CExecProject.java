package breder.ide.lang.c;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import breder.ide.SO;
import breder.ide.debug.DebugManager;
import breder.ide.lang.IDebug;
import breder.ide.lang.IRun;
import breder.ide.lang.ProjectManager;

public class CExecProject extends CProject implements IRun, IDebug {

	public CExecProject(File dir) throws ParseException, IOException {
		super(dir);
	}

	@Override
	public void debug() throws IOException {
		File binaryFile = new File(this.getTargets().get(0));
		GdbProcess process = new GdbProcess(binaryFile.getAbsolutePath());
		process.setCurrentDir(this.getDir());
		for (String argument : this.getArguments()) {
			process.addArgument(argument);
		}
		process.start();
		process.setBreakLine("main");
		process.run();
		DebugManager.getInstance().addProcess(process);
	}

	@Override
	public void run(int id) throws IOException {
		File binaryFile = new File(this.getTargets().get(0));
		CProcess process = new CProcess();
		process.setOutput(binaryFile.getAbsolutePath());
		for (String argument : this.getArguments()) {
			for (String split : argument.trim().split(" |\r|\n|\t")) {
				if (split.length() > 0) {
					process.addArgument(split);
				}
			}
		}
		process.setCurrentDir(this.getDir());
		process.start();
	}

	@Override
	public void build() throws IOException {
		this.buildDepend();
		new File(this.getTargets().get(0)).getParentFile().mkdirs();
		File binaryFile = new File(this.getTargets().get(0));
		if (SO.getInstance().isWindows()) {
			binaryFile = new File(binaryFile.toString() + ".exe");
		}
		GccProcess process = new GccProcess();
		process.setOutput(binaryFile.getAbsolutePath());
		process.addOther("-g");
		for (File include : this.getIncludes()) {
			process.addInclude(include.getAbsolutePath());
		}
		for (File source : this.getSources()) {
			this.buildSource(process, source);
		}
		// for (File linkDir : this.getLinkDirs()) {
		// process.addLinkDir(linkDir.getAbsolutePath());
		// }
		// for (String link : this.getLinks()) {
		// process.addLink(link);
		// }
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
			if (!file.getName().startsWith(".")) {
				if (file.isFile()) {
					if (file.getName().endsWith(".c")) {
						process.addSource(file.getAbsolutePath());
					}
				} else {
					this.buildSource(process, file);
				}
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

	public boolean canRun() {
		return true;
	}

	public boolean canDebug() {
		return true;
	}

	public boolean canBuild() {
		return true;
	}

}
