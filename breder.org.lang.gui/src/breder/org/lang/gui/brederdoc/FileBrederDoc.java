package breder.org.lang.gui.brederdoc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;

public class FileBrederDoc {

	private final File file;

	private final File dirOutput;

	public FileBrederDoc(File file, File dirOutput) {
		this.file = file;
		this.dirOutput = dirOutput;
	}

	public void start() throws IOException, ParseException {
		BrederDocParser parser = new BrederDocParser(new FileInputStream(this.file));
		parser.start();
	}

	public File getFile() {
		return file;
	}

	public File getDirOutput() {
		return dirOutput;
	}

}
