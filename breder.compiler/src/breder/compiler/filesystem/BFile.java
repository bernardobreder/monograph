package breder.compiler.filesystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import breder.compiler.exception.FileNotFoundException;

public class BFile extends Resource implements IFile {

	public BFile(File path) {
		super(path);
	}

	@Override
	public InputStream getInputStream() throws FileNotFoundException {
		try {
			return new FileInputStream(this.toFile());
		} catch (java.io.FileNotFoundException e) {
			throw new FileNotFoundException(this.toFile().getAbsolutePath());
		}
	}

	@Override
	public OutputStream getOutputStream() throws FileNotFoundException {
		try {
			return new FileOutputStream(this.toFile());
		} catch (java.io.FileNotFoundException e) {
			throw new FileNotFoundException(this.toFile().getAbsolutePath());
		}
	}

}
