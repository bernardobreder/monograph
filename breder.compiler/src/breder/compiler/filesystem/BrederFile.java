package breder.compiler.filesystem;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import breder.compiler.exception.FileNotFoundException;

public class BrederFile extends BFile implements IBrederFile {

	public BrederFile(File path) {
		super(path);
	}

	@Override
	public InputStream getInputStream() throws FileNotFoundException {
		try {
			InputStream input = BrederFileManager.getInstance().CACHE.get(this
					.toFile().getAbsolutePath());
			if (input == null) {
				input = new FileCacheInputStream(this.toFile());
				BrederFileManager.getInstance().CACHE.put(this.toFile()
						.getAbsolutePath(), input);
			} else {
				try {
					input.reset();
				} catch (IOException e) {
				}
			}
			return input;
		} catch (java.io.FileNotFoundException e) {
			throw new FileNotFoundException(this.toFile().getAbsolutePath());
		}
	}

}
