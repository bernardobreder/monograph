package breder.ide.lang.c;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArProcess extends AbstractProcess {
	
	private List<String> sources = new ArrayList<String>();
	
	private String output;
	
	public ArProcess() {
		super();
	}
	
	@Override
	public void start() throws IOException {
		super.start();
	}
	
	@Override
	public String[] execute() throws IOException {
		List<String> commands = new ArrayList<String>();
		commands.add("ar");
		if (this.output != null) {
			commands.add("rcs");
			commands.add(this.output);
		}
		for (String source : this.getSources()) {
			commands.add(source);
		}
		return commands.toArray(new String[0]);
	}
	
	public void addSource(String string) {
		this.sources.add(new File(string).getAbsolutePath());
	}
	
	public List<String> getSources() {
		return Collections.unmodifiableList(sources);
	}
	
	public void setOutput(String output) {
		this.output = new File(output).getAbsolutePath();
	}
	
	@Override
	public String prompt() {
		return null;
	}
	
}
