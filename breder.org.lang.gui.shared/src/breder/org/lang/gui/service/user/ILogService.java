package breder.org.lang.gui.service.user;

import breder.webservice.IService;

public interface ILogService extends IService {

	public void printInfo(String msg, Object... objects);

	public void printWarning(String msg, Object... objects);

	public void printError(String msg, Object... objects);

}
