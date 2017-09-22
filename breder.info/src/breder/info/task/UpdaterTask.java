package breder.info.task;

import breder.util.task.RemoteTask;

public abstract class UpdaterTask extends RemoteTask {

	private boolean updated;

	@Override
	public void handler(Throwable t) {
	}

	@Override
	public void updateUI() throws Throwable {

	}

	public boolean isUpdated() {
		return updated;
	}

	public void setUpdated(boolean updated) {
		this.updated = updated;
	}

}
