package breder.ide.lang.breder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import breder.ide.IdeEditorPane;
import breder.ide.MainFrame;
import breder.ide.lang.IBuilder;
import breder.ide.lang.ILanguage;
import breder.ide.lang.IRun;
import breder.ide.lang.InputThread;
import breder.ide.lang.LanguageManager;
import breder.ide.lang.Project;
import breder.ide.lang.ProjectManager;
import breder.xml.ITag;
import breder.xml.XmlReader;

public class BrederProject extends Project implements IBuilder, IRun {

	private final List<String> projects = new ArrayList<String>();

	public BrederProject(File dir) throws ParseException, IOException {
		super(dir);
		this.init();
	}

	private void init() throws ParseException, IOException {
		File file = new File(this.getDir(), "project.xml");
		XmlReader reader = new XmlReader(new FileInputStream(file));
		reader.start();
		ITag projectTag = reader.getTag("project");
		for (ITag tag : projectTag.getTags("source")) {
			this.addSource(new File(this.getDir(), tag.check("src")));
		}
		for (ITag tag : projectTag.getTags("target")) {
			File dir = new File(this.getDir(), tag.check("src"));
			this.addTarget(dir.toString());
		}
		for (ITag tag : projectTag.getTags("link")) {
			String project = tag.check("project");
			this.addLink(project);
		}
	}

	private void addLink(String project) {
		this.projects.add(project);
	}

	@Override
	public void run(int id) throws IOException {
		if (this.getTargets().size() > 0) {
			String output = new File(new File(this.getTargets().get(0)),
					"binary.b").toString();
			BrederRunProcess process = new BrederRunProcess(output);
			for (String projectname : this.projects) {
				Project project = ProjectManager.getInstance().getProject(
						projectname);
				String path = new File(project.getTargets().get(0))
						.getParentFile().getAbsolutePath();
				process.addLinkers(path);
			}
			process.start();
			new InputThread(id, process.getInputStream()).start();
		}
	}

	@Override
	public void build() throws IOException {
		IdeEditorPane editor = MainFrame.getInstance().getTextField();
		if (editor == null
				|| editor.getProjectNode().getProject().getClass() != this
						.getClass()) {
			return;
		}
		if (this.getTargets().size() == 0) {
			return;
		}
		File file = editor.getIndex().getFile();
		String classname = null;
		for (File source : editor.getProjectNode().getProject().getSources()) {
			if (file.getAbsolutePath().startsWith(source.getAbsolutePath())) {
				classname = file.getAbsolutePath().substring(
						source.getAbsolutePath().length() + 1);
				classname = classname.replace(File.separatorChar, '.');
				classname = classname.substring(0, classname.length()
						- ".breder".length());
				break;
			}
		}
		if (classname != null) {
			BrederCompilerProcess process = new BrederCompilerProcess(classname);
			for (File sourceFile : this.getSources()) {
				process.addSource(sourceFile.toString());
			}
			process.setOutput(new File(this.getTargets().get(0), "binary.b")
					.toString());
			for (String project : this.projects) {
				process.addLinkers(project);
			}
			process.start();
		}
	}

	@Override
	public ILanguage getLanguage() {
		return LanguageManager.getInstance().get("breder");
	}

}
