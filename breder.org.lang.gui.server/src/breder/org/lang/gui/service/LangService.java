package breder.org.lang.gui.service;

import breder.org.lang.gui.service.log.LogService;
import breder.org.lang.gui.service.user.ILogService;
import breder.org.lang.gui.service.user.IUserService;
import breder.org.lang.gui.service.user.UserService;
import breder.webservice.service.DbService;

public abstract class LangService extends DbService {

	public IUserService getUserService() {
		return new UserService(this.getTask());
	}

	public ILogService getLogservice() {
		return new LogService(this.getTask());
	}

}
