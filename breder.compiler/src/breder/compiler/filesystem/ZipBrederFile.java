package breder.compiler.filesystem;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

public class ZipBrederFile extends BrederFile {

	private InputStream input;

	public ZipBrederFile(File path, byte[] bytes) {
		super(path);
		this.input = new ByteArrayInputStream(bytes);
	}

	@Override
	public InputStream getInputStream() {
		return this.input;
	}

	@Override
	public boolean exist() {
		return true;
	}

}
