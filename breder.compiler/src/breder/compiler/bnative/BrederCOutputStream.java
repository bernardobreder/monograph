package breder.compiler.bnative;

import java.io.IOException;
import java.io.OutputStream;

public class BrederCOutputStream {

	private int tab;
	private OutputStream output;

	public BrederCOutputStream(OutputStream output) {
		this.tab = 0;
		this.output = output;
	}

	public void print(String line) throws IOException {
		StringBuilder sb = new StringBuilder(line.length() + this.tab + 2);
		for (int n = 0; n < this.tab; n++) {
			sb.append("\t");
		}
		sb.append(line);
		this.output.write(sb.toString().getBytes());
	}

	public void println(String line) throws IOException {
		this.print(line);
		this.println();
	}

	public void println() throws IOException {
		this.output.write("\r\n".getBytes());
	}

	public void incTab() {
		this.tab++;
	}

	public void decTab() {
		this.tab--;
	}

	public void close() throws IOException {
		this.output.close();
	}

}
