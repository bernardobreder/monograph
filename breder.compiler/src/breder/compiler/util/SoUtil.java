
package breder.compiler.util;

import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JOptionPane;

public class SoUtil {

	public static final int CTRL_MASK = isMacOs() ? KeyEvent.META_MASK : KeyEvent.CTRL_MASK;

	public static final int SHIFT_MASK = KeyEvent.SHIFT_MASK;

	public static boolean isMacOs() {
		return isUnix() && new File("/Applications").exists();
	}

	public static boolean isWindow() {
		return !isUnix() && new File("C:\\").exists();
	}

	public static boolean isLinux() {
		return isUnix() && !isMacOs() && !isWindow();
	}

	public static boolean isUnix() {
		return File.listRoots().length == 1 && File.listRoots()[0].getAbsolutePath().equals("/")
				&& new File("/usr").exists();
	}

	public static boolean is64Bit() {
		String x86 = System.getenv("PROCESSOR_ARCHITECTURE");
		if (x86 != null && x86.equals("x86")) {
			return false;
		}
		try {
			Process process = new ProcessBuilder("java", "-version").start();
			process.waitFor();
			String lines = new String(InputStreamUtil.getBytes(process.getErrorStream()));
			for (String line : lines.split("\n")) {
				if (line.toLowerCase().contains("64-bit")) {
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			return JOptionPane.showConfirmDialog(null, "Is your machine is 64-Bit ?", "Is 64-Bit",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
		}
	}

	public static String getExtension() {
		if (isWindow()) {
			return ".exe";
		} else {
			return "";
		}
	}

	public static String getLibraryExtension() {
		if (isWindow()) {
			return ".dll";
		} else if (isMacOs()) {
			return ".dylib";
		} else {
			return ".so";
		}
	}

	public static String getName() {
		if (isWindow()) {
			return "windows";
		} else if (isLinux()) {
			return "linux";
		} else if (isMacOs()) {
			return "mac";
		} else
			return null;
	}

}
