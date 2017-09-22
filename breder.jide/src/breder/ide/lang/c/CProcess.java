package breder.ide.lang.c;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CProcess extends AbstractProcess {
	
	private final List<String> arguments = new ArrayList<String>();
	
	private String output;
	
	public CProcess() {
		super();
	}
	
	@Override
	public void start() throws IOException {
		super.start();
	}
	
	@Override
	public String[] execute() throws IOException {
		List<String> commands = new ArrayList<String>();
		commands.add(this.getOutput());
		for (String argument : this.arguments) {
			commands.add(argument);
		}
		return commands.toArray(new String[0]);
	}
	
	public void addArgument(String argument) {
		this.arguments.add(argument);
	}
	
	public void setOutput(String output) {
		this.output = new File(output).getAbsolutePath();
	}
	
	public String prompt() {
		return null;
	}
	
	public String getOutput() {
		return output;
	}
	
}
