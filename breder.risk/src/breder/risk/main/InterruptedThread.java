package breder.risk.main;

public class InterruptedThread extends Thread {

	private Thread thread;
	private long miliseg;

	public InterruptedThread(long miliseg) {
		this.thread = Thread.currentThread();
		this.miliseg = miliseg;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(this.miliseg);
		} catch (InterruptedException e) {
			return;
		}
		this.thread.interrupt();
	}

}
