package breder.ide;

import java.io.File;

public class NodeUtil {
	
	public static boolean accept(File file) {
		return !(file.isHidden() || file.getName().startsWith("."));
	}
	
}
