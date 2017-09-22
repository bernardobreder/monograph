package breder.org.lang.gui.brederdoc;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BrederDoc {

	private List<File> classpaths = new ArrayList<File>();

	private File dirOutput;

	public static void main(String[] args) throws IOException, ParseException {
		BrederDoc doc = new BrederDoc();
		doc.addClasspath(new File("../breder.compiler/tst"));
		doc.setDirOutput(new File("doc"));
		doc.start();
	}

	public void start() throws IOException, ParseException {
		this.dirOutput.mkdirs();
		for (File classpath : this.classpaths) {
			this.start(classpath);
		}
	}

	private void start(File dir) throws IOException, ParseException {
		for (File file : dir.listFiles()) {
			if (file.isDirectory()) {
				this.start(file);
			} else if (file.getName().endsWith(".breder")) {
				FileBrederDoc fdoc = new FileBrederDoc(file, this.dirOutput);
				fdoc.start();
			}
		}
	}

	public void setDirOutput(File file) {
		if (file == null || (file.exists() && !file.isDirectory())) {
			throw new IllegalArgumentException();
		}
		this.dirOutput = file;
	}

	public void addClasspath(File file) {
		if (file == null || !file.isDirectory()) {
			throw new IllegalArgumentException();
		}
		this.classpaths.add(file);
	}

	public List<File> getClasspaths() {
		return Collections.unmodifiableList(this.classpaths);
	}

	public File getDirOutput() {
		return dirOutput;
	}

}
