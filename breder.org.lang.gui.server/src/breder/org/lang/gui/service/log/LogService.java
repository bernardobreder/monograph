
package breder.org.lang.gui.service.log;

import java.util.ArrayList;
import java.util.List;

import breder.org.lang.gui.service.LangService;
import breder.org.lang.gui.service.user.ILogService;
import breder.org.lang.gui.table.LogTable;
import breder.org.lang.gui.user.User;
import breder.util.util.BDate;
import breder.webservice.jdbc.DbTask;

public class LogService extends LangService implements ILogService {

	public LogService() {
		this(null);
	}

	public LogService(DbTask<Object> task) {
		this.task = task;
	}

	@Override
	public void printError(String msg, Object... objects) {
		this.print("[error] : " + msg, objects);
	}

	@Override
	public void printInfo(String msg, Object... objects) {
		this.print("[info] : " + msg, objects);
	}

	@Override
	public void printWarning(String msg, Object... objects) {
		this.print("[warning] : " + msg, objects);
	}

	private void print(String msg, Object... objects) {
		User user = this.getUserService().getUser();
		Number userId = null;
		if (user != null) {
			userId = user.getId();
		}
		List<Object> list = new ArrayList<Object>();
		list.add(new BDate());
		for (Object object : objects) {
			list.add(object);
		}
		String message = String.format("[%s]" + msg, list.toArray());
		this.getTransaction().insert(LogTable.class).add(new LogTable(userId, message)).execute();
	}

}
