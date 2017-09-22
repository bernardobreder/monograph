package breder.ide.lang;

import java.io.InputStream;

import breder.ide.MainFrame;
import breder.util.task.LocalTask;
import breder.util.task.RemoteTask;

public class ConsoleInputTask extends RemoteTask {
	
	private final InputStream stream;
	
	public ConsoleInputTask(InputStream stream) {
		super();
		this.stream = stream;
	}
	
	@Override
	public void perform() throws Throwable {
		while (true) {
			int c = stream.read();
			if (c == -1)
				break;
			new Task((char) c).start();
		}
	}
	
	@Override
	public void updateUI() throws Throwable {
	}
	
	private static class Task extends LocalTask {
		
		private final Character n;
		
		public Task(Character n) {
			super();
			this.n = n;
		}
		
		@Override
		public void updateUI() throws Throwable {
			MainFrame.getInstance().printConsole(n.toString());
		}
		
	}
	
}
