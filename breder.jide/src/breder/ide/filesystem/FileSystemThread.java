package breder.ide.filesystem;

import java.util.List;
import java.util.Vector;

public class FileSystemThread extends Thread {
	
	private static final FileSystemThread instance = new FileSystemThread();
	
	public final List<Runnable> listeners = new Vector<Runnable>();
	
	private FileSystemThread() {
		super("FileSystem");
	}
	
	public void run() {
		while (true) {
			try {
				for (Runnable r : this.listeners) {
					r.run();
				}
			} catch (Exception e) {
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		}
	}
	
	public void addListener(Runnable r) {
		this.listeners.add(r);
	}
	
	public static FileSystemThread getInstance() {
		return instance;
	}
	
}
