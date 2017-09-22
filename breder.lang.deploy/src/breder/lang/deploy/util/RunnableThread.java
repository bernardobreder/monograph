package breder.lang.deploy.util;

import java.io.File;

public abstract class RunnableThread extends Thread {

	protected File destDir;

	protected boolean is386;

	public static final ThreadGroup GROUP = new ThreadGroup(
			RunnableThread.class.getSimpleName());

	public RunnableThread(File destDir, boolean is386) {
		super(GROUP, RunnableThread.class.getSimpleName());
		this.setName(this.getClass().getSimpleName());
		this.destDir = destDir;
		this.is386 = is386;
	}

	public abstract void compile(File destDir, boolean is386) throws Exception;

	@Override
	public void run() {
		try {
			this.compile(destDir, is386);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
