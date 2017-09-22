
package breder.compiler.filesystem;

import java.io.File;

public class Resource implements IResource {

	protected final File file;

	public Resource(File path) {
		this.file = path;
	}

	@Override
	public boolean exist() {
		return file.exists() && existSameName();
	}

	private boolean existSameName() {
		File aux = null;
		for (File f : file.getParentFile().listFiles()) {
			if (f.getName().equals(file.getName())) {
				aux = f;
				break;
			}
		}
		return aux != null;
	}

	@Override
	public File toFile() {
		return file;
	}

}
