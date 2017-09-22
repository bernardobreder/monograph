package breder.ide.lang;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import breder.ide.NodeUtil;
import breder.xml.ITag;
import breder.xml.XmlReader;

public class ProjectManager {

	private static final ProjectManager instance = new ProjectManager();

	private final Set<Project> projects = new HashSet<Project>();

	public Project getProject(String name) {
		for (Project project : this.projects) {
			if (project.getName().equals(name)) {
				return project;
			}
		}
		return null;
	}

	public void addProject(Project project) {
		this.projects.add(project);
	}

	public static ProjectManager getInstance() {
		return instance;
	}

	public Set<Project> getProjects() {
		return projects;
	}

	public <E extends Project> Set<E> getProjects(Class<E> c) {
		Set<E> set = new HashSet<E>();
		for (Project project : this.projects) {
			if (c.isInstance(project)) {
				set.add((E) project);
			}
		}
		return set;
	}

	public void start() {
		for (File file : new File("app").listFiles()) {
			if (file.isDirectory()) {
				if (NodeUtil.accept(file)) {
					Project project = this.buildProject(file);
					this.addProject(project);
				}
			}
		}
	}

	private Project buildProject(File dir) {
		try {
			XmlReader reader = new XmlReader(new FileInputStream(new File(dir,
					"project.xml")));
			reader.start();
			ITag projectTag = reader.getTag("project");
			String languagename = projectTag.check("language");
			ILanguage language = LanguageManager.getInstance()
					.get(languagename);
			return language.newProject(dir, projectTag);
		} catch (Exception e) {
			return null;
		}
	}

}
