package breder.compiler.filesystem;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class CacheInputStream extends InputStream {

	private ByteArrayInputStream cache;

	private ByteArrayOutputStream output;

	private InputStream input;

	public CacheInputStream(InputStream input) {
		super();
		this.cleanCache(input);
	}

	@Override
	public int read() throws IOException {
		if (cache == null) {
			int c = input.read();
			if (c == -1) {
				input.close();
				cache = new ByteArrayInputStream(output.toByteArray());
				output = null;
			} else {
				output.write(c);
			}
			return c;
		} else {
			return cache.read();
		}
	}

	public void reset() throws IOException {
		cache.reset();
	}

	protected void cleanCache(InputStream input) {
		this.cache = null;
		this.output = new ByteArrayOutputStream();
		this.input = input;
	}

	public void close() throws IOException {
		if (cache == null) {
			input.close();
			cache = new ByteArrayInputStream(output.toByteArray());
			output = null;
		} else {
			this.cache.reset();
		}
	}

}
