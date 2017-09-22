package breder.compiler.filesystem;

import java.io.File;

public interface IResource {

	public boolean exist();

	public File toFile();

}
