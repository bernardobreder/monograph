package breder.compiler.filesystem;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileCacheInputStream extends CacheInputStream {

	private java.io.File file;

	private long timestamp;

	public FileCacheInputStream(java.io.File file) throws FileNotFoundException {
		super(new FileInputStream(file));
		this.file = file;
		this.timestamp = file.lastModified();
	}

	@Override
	public void reset() throws IOException {
		if (this.timestamp != file.lastModified()) {
			this.timestamp = file.lastModified();
			super.cleanCache(new FileInputStream(file));
		}
	}

}
