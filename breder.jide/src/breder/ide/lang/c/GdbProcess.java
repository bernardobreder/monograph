package breder.ide.lang.c;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GdbProcess extends AbstractProcess {
	
	private String inputStr;
	
	private File file;
	
	private Integer line;
	
	private final Map<String, String> variables = new HashMap<String, String>();
	
	private final List<String> arguments = new ArrayList<String>();
	
	private static final String EXC_BAD_ACCESS = "Program received signal EXC_BAD_ACCESS";
	
	public GdbProcess(String output) throws IOException {
		super();
		this.setInputStr(output);
	}
	
	@Override
	public String prompt() {
		return "(gdb)";
	}
	
	@Override
	public void start() throws IOException {
		super.start();
		this.readConsole(this.getInputStream());
	}
	
	public void addArgument(String argument) {
		this.arguments.add(argument);
	}
	
	public List<String> getArguments() {
		return Collections.unmodifiableList(this.arguments);
	}
	
	public Map<String, String> getVariables() {
		return Collections.unmodifiableMap(this.variables);
	}
	
	public Map<String, String> getVariables(String variable) throws IOException {
		Map<String, String> map = new HashMap<String, String>();
		{
			String[] lines = this.println(String.format("p *%s", variable));
			for (int n = 1; n < lines.length - 1; n++) {
				String line = lines[n];
				int index = line.indexOf('=');
				String key = line.substring(0, index).trim();
				String value = line.substring(index + 1).trim();
				if (value.endsWith(",")) {
					value = value.substring(0, value.length() - 1);
				}
				map.put(key, value);
			}
		}
		return map;
	}
	
	@Override
	public String[] execute() throws IOException {
		List<String> commands = new ArrayList<String>();
		commands.add("gdb");
		commands.add(inputStr);
		return commands.toArray(new String[0]);
	}
	
	public void setInputStr(String output) {
		this.inputStr = new File(output).getAbsolutePath();
	}
	
	private void refreshVariable() throws IOException {
		this.variables.clear();
		{
			String[] lines = this.println("info locals");
			String line0 = lines[0].trim();
			if (line0.indexOf('=') != -1) {
				for (String line : lines) {
					int index = line.indexOf('=');
					String key = line.substring(0, index).trim();
					String value = line.substring(index + 1).trim();
					this.variables.put(key, value);
				}
			}
		}
		{
			String[] lines = this.println("info args");
			String line0 = lines[0].trim();
			if (line0.indexOf('=') != -1) {
				for (String line : lines) {
					int index = line.indexOf('=');
					String key = line.substring(0, index).trim();
					String value = line.substring(index + 1).trim();
					this.variables.put(key, value);
				}
			}
		}
	}
	
	public File getFile() {
		return file;
	}
	
	public Integer getLine() {
		return line;
	}
	
	public void setBreakLine(int line) throws IOException {
		this.println(String.format("b %d", line));
	}
	
	public void setBreakLine(String method) throws IOException {
		this.println(String.format("b %s", method));
	}
	
	public void run() throws IOException {
		StringBuilder sb = new StringBuilder("r");
		for (String argument : this.getArguments()) {
			for (String split : argument.trim().split(" |\r|\n|\t")) {
				if (split.length() > 0) {
					sb.append(" " + split);
				}
			}
		}
		String[] lines = this.println(sb.toString());
		for (String line : lines) {
			if (line.startsWith("Breakpoint") && line.indexOf('/') != -1) {
				int index = line.indexOf('/');
				int index2 = line.indexOf(':');
				String filename = line.substring(index, index2).trim();
				this.file = new File(filename);
				String number = line.substring(index2 + 1).trim();
				try {
					this.line = new Integer(number);
				} catch (NumberFormatException e) {
				}
			} else if (StringUtil.startWithNumber(line)) {
				this.line = Integer.parseInt(StringUtil.getNumber(line).toString());
			}
		}
		this.refreshVariable();
	}
	
	public void next() throws IOException {
		this.genericNext("n");
	}
	
	public void nextIn() throws IOException {
		this.genericNext("s");
	}
	
	public void genericNext(String cmd) throws IOException {
		String[] lines = this.println(cmd);
		for (String line : lines) {
			if (line.indexOf(" at ") != -1) {
				int index0 = line.indexOf(" at ");
				int index = line.indexOf('/', index0);
				int index2 = line.indexOf(':', index);
				if (index0 != -1 && index != -1 && index2 != -1) {
					String filename = line.substring(index, index2).trim();
					this.file = new File(filename);
					this.line = Integer.parseInt(StringUtil.getNumber(line.substring(index2 + 1))
							.toString());
				}
			} else if (StringUtil.startWithNumber(line)) {
				this.line = Integer.parseInt(StringUtil.getNumber(line).toString());
			}
		}
		this.refreshVariable();
	}
	
	public void exit() throws IOException, InterruptedException {
		this.println("q");
		this.waitFor();
	}
	
}
