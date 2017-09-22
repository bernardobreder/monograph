
package breder.compiler.util;

import java.util.List;

public class CollectionsUtil {

	public static String toString(List<? extends Object> c) {
		BStringBuilder sb = new BStringBuilder();
		sb.append("(");
		int size = c.size();
		for (int n = 0; n < size; n++) {
			sb.append(c.get(n).toString());
			if (n != size - 1) {
				sb.append(",");
			}
		}
		sb.append(")");
		return sb.toString();
	}

}
