package breder.ide.lang;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Project {
	
	private File dir;
	
	private final List<String> dependencys = new ArrayList<String>();
	
	private final List<String> arguments = new ArrayList<String>();
	
	private final List<String> targets = new ArrayList<String>();
	
	private final List<File> sources = new ArrayList<File>();
	
	public Project(File dir) {
		super();
		this.dir = dir;
	}
	
	protected void buildDepend() throws IOException {
		for (String depend : this.dependencys) {
			for (Project project : ProjectManager.getInstance().getProjects()) {
				if (project instanceof IBuilder) {
					if (project.getName().equals(depend)) {
						((IBuilder) project).build();
					}
				}
			}
		}
	}
	
	public abstract ILanguage getLanguage();
	
	public void addSource(File file) {
		this.sources.add(file);
	}
	
	public void addTarget(String target) {
		this.targets.add(target);
	}
	
	public void addDependency(String project) {
		this.dependencys.add(project);
	}
	
	public void addArgument(String argument) {
		this.arguments.add(argument);
	}
	
	public List<String> getDependencys() {
		return Collections.unmodifiableList(this.dependencys);
	}
	
	public List<String> getTargets() {
		return Collections.unmodifiableList(this.targets);
	}
	
	public List<String> getArguments() {
		return Collections.unmodifiableList(this.arguments);
	}
	
	public List<File> getSources() {
		return Collections.unmodifiableList(this.sources);
	}
	
	public String getName() {
		return dir.getName();
	}
	
	public File getDir() {
		return dir;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.getName() == null) ? 0 : this.getName().hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Project other = (Project) obj;
		if (this.getName() == null) {
			if (other.getName() != null)
				return false;
		} else if (!this.getName().equals(other.getName()))
			return false;
		return true;
	}
	
}
