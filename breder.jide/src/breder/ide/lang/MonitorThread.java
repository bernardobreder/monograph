package breder.ide.lang;

import breder.ide.MainFrame;

public class MonitorThread extends Thread {
	
	private Integer index;
	
	private InputThread[] inputs;
	
	public MonitorThread(Integer index, InputThread... inputs) {
		this.index = index;
		this.inputs = inputs;
	}
	
	public void run() {
		for (InputThread input : this.inputs) {
			try {
				input.join();
			} catch (InterruptedException e) {
			}
		}
		MainFrame.getInstance().removeConsole(index);
	}
}
