package breder.org.lang.gui.task;

import breder.org.lang.gui.service.ServiceLocator;
import breder.util.task.RemoteTask;
import breder.webservice.IServerService;

public class LogoutBasicTask extends RemoteTask {

	@Override
	public void perform() throws Throwable {
		ServiceLocator.getInstance().getUserService().logout();
		IServerService.DEFAULT.finish();
	}

	@Override
	public void handler(Throwable t) {
	}

	@Override
	public void updateUI() {
	}

}
