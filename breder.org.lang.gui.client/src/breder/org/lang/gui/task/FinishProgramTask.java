package breder.org.lang.gui.task;

import breder.org.lang.gui.service.ServiceLocator;
import breder.util.task.RemoteTask;
import breder.webservice.IServerService;

public class FinishProgramTask extends RemoteTask {

	private final boolean logined;

	public FinishProgramTask(boolean logined) {
		super();
		this.logined = logined;
	}

	@Override
	public void perform() throws Throwable {
		if (this.logined) {
			ServiceLocator.getInstance().getUserService().logout();
		}
		IServerService.DEFAULT.finish();
	}

	@Override
	public void updateUI() {
		System.exit(0);
	}

}
