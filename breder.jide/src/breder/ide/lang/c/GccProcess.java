package breder.ide.lang.c;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GccProcess extends AbstractProcess {
	
	private List<String> sources = new ArrayList<String>();
	
	private List<String> includes = new ArrayList<String>();
	
	private List<String> links = new ArrayList<String>();
	
	private List<String> linkDirs = new ArrayList<String>();
	
	private List<String> flags = new ArrayList<String>();
	
	private String output;
	
	public GccProcess() {
		super();
	}
	
	@Override
	public void start() throws IOException {
		// this.addOther("-arch");
		// this.addOther("i386");
		super.start();
		String[] lines = this.readConsole(this.getErrorStream());
		lines = this.readConsole(this.getInputStream());
		for (String line : lines) {
			if (line.trim().startsWith("Undefined symbols")) {
				
			}
		}
	}
	
	@Override
	public String[] execute() throws IOException {
		List<String> commands = new ArrayList<String>();
		commands.add("gcc");
		for (String other : this.getOthers()) {
			commands.add(other);
		}
		if (this.output != null) {
			commands.add("-o");
			commands.add(this.output);
		}
		for (String include : this.getIncludes()) {
			commands.add("-I" + include);
		}
		for (String linkDir : this.getLinkDirs()) {
			commands.add("-L");
			commands.add(linkDir);
		}
		for (String link : this.getLinks()) {
			commands.add("-l" + link);
		}
		for (String source : this.getSources()) {
			commands.add(source);
		}
		return commands.toArray(new String[0]);
	}
	
	public void addOther(String string) {
		this.flags.add(string);
	}
	
	public void addSource(String string) {
		this.sources.add(new File(string).getAbsolutePath());
	}
	
	public void addInclude(String string) {
		this.includes.add(new File(string).getAbsolutePath());
	}
	
	public void addLinkDir(String linkDir) {
		this.linkDirs.add(new File(linkDir).getAbsolutePath());
	}
	
	public void addLink(String link) {
		this.links.add(link);
	}
	
	public List<String> getOthers() {
		return Collections.unmodifiableList(this.flags);
	}
	
	public List<String> getSources() {
		return Collections.unmodifiableList(sources);
	}
	
	public List<String> getIncludes() {
		return Collections.unmodifiableList(includes);
	}
	
	public List<String> getLinkDirs() {
		return Collections.unmodifiableList(linkDirs);
	}
	
	public List<String> getLinks() {
		return Collections.unmodifiableList(links);
	}
	
	public void setOutput(String output) {
		this.output = new File(output).getAbsolutePath();
	}
	
	@Override
	public String prompt() {
		return null;
	}
	
}
