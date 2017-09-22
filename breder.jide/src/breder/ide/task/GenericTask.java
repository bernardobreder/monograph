package breder.ide.task;

import breder.util.task.RemoteTask;

public abstract class GenericTask extends RemoteTask {

	public GenericTask(String message) {
		super(message);
	}

	@Override
	public void start() {
		super.start(this.getClass().getSuperclass());
	}

}
