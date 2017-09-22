
package breder.compiler.util;

import java.util.List;

public class BStringBuilder {

	private int len;

	private List<String> values = new LightArrayList<String>();

	public void append(String text) {
		this.len += text.length();
		this.values.add(text);
	}

	public String toString() {
		char[] chars = new char[this.len];
		for (int n = 0, m = 0; n < values.size(); n++) {
			String value = this.values.get(n);
			value.getChars(0, value.length(), chars, m);
			m += value.length();
		}
		return new String(chars);
	}

	public int length() {
		return this.len;
	}

}
