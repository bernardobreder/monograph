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

public class BrederRunProcess extends AbstractProcess {

	private String output;

	private List<String> linkers = new ArrayList<String>();

	public BrederRunProcess(String output) {
		super();
		this.output = output;
	}

	@Override
	public void start() throws IOException {
		super.start();
	}

	@Override
	public String[] execute() throws IOException {
		ILanguage lang = LanguageManager.getInstance().get(BrederLanguage.NAME);
		List<String> commands = new ArrayList<String>();
		if (this.output != null) {
			commands.add(new File(this.output).getAbsolutePath());
		}
		for (String linker : this.getLinkers()) {
			commands.add("-lp");
			commands.add(linker);
		}
		String args = ArrayUtil.toString(commands, " ");
		return String.format(lang.run(), args).split(" |\r|\n|\t");
	}

	public void setOutput(String output) {
		this.output = new File(output).getAbsolutePath();
	}

	public void addLinkers(String project) {
		this.linkers.add(project);
	}

	public List<String> getLinkers() {
		return Collections.unmodifiableList(linkers);
	}

	@Override
	public String prompt() {
		return null;
	}

}
