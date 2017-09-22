package breder.ide.lang.c;
public class StringUtil {
	
	public static boolean startWithNumber(String text) {
		return text.length() > 0 && Character.isDigit(text.charAt(0));
	}
	
	public static Number getNumber(String text) {
		char[] chars = text.toCharArray();
		int n;
		boolean dot = false;
		for (n = 0; n < chars.length; n++) {
			if (chars[n] == '.' || chars[n] == ',') {
				dot = true;
			} else if (!Character.isDigit(chars[n]))
				break;
		}
		text = text.substring(0, n);
		if (dot) {
			return new Double(text);
		} else {
			return new Long(text);
		}
	}
	
}
