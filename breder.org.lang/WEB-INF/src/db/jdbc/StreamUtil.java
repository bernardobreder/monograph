package db.jdbc;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class StreamUtil {

	public static String toString(InputStream input) throws IOException {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		byte[] bytes = new byte[1024];
		while (true) {
			int n = input.read(bytes);
			if (n == -1)
				break;
			output.write(bytes, 0, n);
		}
		return new String(output.toByteArray());
	}

}
