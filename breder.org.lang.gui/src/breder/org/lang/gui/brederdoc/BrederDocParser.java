package breder.org.lang.gui.brederdoc;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;

import breder.util.util.InputStreamUtil;

public class BrederDocParser {

	private final char[] bytes;

	private int current;

	public BrederDocParser(InputStream input) throws IOException {
		super();
		this.bytes = new String(InputStreamUtil.getBytes(input)).toCharArray();
	}

	public void start() throws ParseException {
		this.readPackage();
		this.readImports();
		this.canDoc();
		this.readStruct();
	}

	private void readPackage() {
		if (this.canToken("package")) {
			this.canIds();
			this.canToken(";");
		}
	}

	private void readImports() throws ParseException {
		while (this.canToken("import")) {
			this.canId();
			while (this.canToken(".")) {
				if (this.canId() != null || this.canToken("*"))
					;
			}
			this.doToken(";");
		}
	}

	private void readStruct() throws ParseException {
		this.canToken("public");
		{
			this.canToken("abstract");
			this.canToken("final");
		}
		if (this.canToken("class")) {
			String name = this.doId();
			if (this.canToken("extends")) {

			}
		} else if (this.canToken("interface")) {

		} else {
			throw new ParseException("", 0);
		}
	}

	public String doId() throws ParseException {
		String id = this.canId();
		if (id == null) {
			throw new ParseException("", 0);
		}
		return id;
	}

	public String canIds() {
		int current = this.current;
		String id = this.canId();
		if (id == null) {
			this.current = current;
			return null;
		}
		while (this.canToken(".")) {
			String aux = this.canId();
			if (aux == null) {
				this.current = current;
				return null;
			}
			id += "." + aux;
		}
		return id;
	}

	public String canId() {
		String id = this.isId();
		if (id == null) {
			return null;
		}
		this.current += id.length();
		return id;
	}

	public String isId() {
		if (this.canSpace()) {
			return null;
		}
		int n = this.current;
		while (!this.isEOF() && Character.isJavaIdentifierPart(this.bytes[n])) {
			n++;
		}
		if (n == this.current) {
			return null;
		} else {
			return new String(this.bytes, this.current, n - this.current);
		}
	}

	public void doToken(String token) throws ParseException {
		if (!this.canToken(token)) {
			throw new ParseException("", 0);
		}
	}

	public boolean canToken(String token) {
		boolean flag = this.isToken(token);
		if (flag) {
			this.current += token.length();
			return flag;
		}
		return flag;
	}

	public boolean isToken(String token) {
		if (this.canSpace()) {
			return false;
		}
		int length = token.length();
		for (int n = 0; n < length; n++) {
			if (this.bytes[n + this.current] != token.charAt(n)) {
				return false;
			}
		}
		return true;
	}

	private boolean canSpace() {
		while (!this.isEOF() && Character.isWhitespace(this.bytes[this.current])) {
			this.current++;
		}
		return this.isEOF();
	}

	/**
	 * 
	 * 
	 * @return
	 */
	public String canDoc() {
		int begin = this.current;
		if (this.canToken("/*")) {
			int index = this.current;
			while (this.bytes[index] != '*' || this.bytes[index + 1] != '/') {
				index++;
			}
			begin+=2;
			while (this.bytes[begin] == '*') {
				begin++;
			}
			while (this.bytes[index] == '*') {
				index--;
			}
			StringBuilder text = new StringBuilder(new String(this.bytes, begin, index - begin).trim());
			this.current += index + 2;
			System.out.println(text);
		}
		return null;
	}

	public boolean isEOF() {
		return this.current == this.bytes.length;
	}

}
