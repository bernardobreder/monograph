package breder.ide.lang.breder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import breder.ide.lang.ILanguage;
import breder.ide.lang.LanguageManager;
import breder.ide.lang.c.AbstractProcess;
import breder.util.util.ArrayUtil;

public class BrederCompilerProcess extends AbstractProcess {

	private List<String> sources = new ArrayList<String>();

	private List<String> linkers = new ArrayList<String>();

	private String output;

	private String classname;

	public BrederCompilerProcess(String classname) {
		super();
		this.classname = classname;
	}

	@Override
	public void start() throws IOException {
		super.start();
	}

	@Override
	public String[] execute() throws IOException {
		ILanguage lang = LanguageManager.getInstance().get(BrederLanguage.NAME);
		List<String> commands = new ArrayList<String>();
		commands.add(this.classname);
		if (this.output != null) {
			commands.add("-o");
			commands.add(this.output);
		}
		for (String source : this.getSources()) {
			commands.add("-classpath");
			commands.add(source);
		}
		for (String linker : this.getLinkers()) {
			commands.add("-l");
			commands.add(linker);
		}
		String args = ArrayUtil.toString(commands, " ");
		return String.format(lang.build(), args).split(" |\r|\n|\t");
	}

	public void addSource(String string) {
		this.sources.add(new File(string).getAbsolutePath());
	}

	public List<String> getSources() {
		return Collections.unmodifiableList(sources);
	}

	public void addLinkers(String project) {
		this.linkers.add(project);
	}

	public List<String> getLinkers() {
		return Collections.unmodifiableList(linkers);
	}

	public void setOutput(String output) {
		this.output = new File(output).getAbsolutePath();
	}

	@Override
	public String prompt() {
		return null;
	}

}
