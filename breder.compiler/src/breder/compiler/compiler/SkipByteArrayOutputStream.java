package breder.compiler.compiler;

import java.io.ByteArrayOutputStream;

public class SkipByteArrayOutputStream extends ByteArrayOutputStream {

	public byte[] sub(int begin, int length) {
		byte[] bytes = new byte[length];
		System.arraycopy(this.buf, begin, bytes, 0, length);
		return bytes;
	}

}
