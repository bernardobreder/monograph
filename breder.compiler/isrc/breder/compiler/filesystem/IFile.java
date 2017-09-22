package breder.compiler.filesystem;

import java.io.InputStream;
import java.io.OutputStream;

import breder.compiler.exception.FileNotFoundException;

public interface IFile {

	public InputStream getInputStream() throws FileNotFoundException;

	public OutputStream getOutputStream() throws FileNotFoundException;

}
