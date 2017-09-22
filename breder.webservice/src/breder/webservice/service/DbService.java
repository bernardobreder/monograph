package breder.webservice.service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import breder.jsql.ITransaction;
import breder.webservice.IService;
import breder.webservice.jdbc.DbTask;

public class DbService extends AbstractService implements InvocationHandler {

	protected DbTask<Object> task;

	@Override
	public void dispose() {
	}

	@Override
	public void init() {
	}

	public ITransaction getTransaction() {
		return task.getMainTransaction();
	}

	@Override
	public Object invoke(Object proxy, final Method method, final Object[] args)
			throws Throwable {
		task = new DbTask<Object>() {

			@Override
			protected Object dbPerform() throws Throwable {
				return method.invoke(DbService.this, args);
			}

		};
		return task.start();
	}

	public <E extends IService> E getProxy(Class<E> c) {
		return HandlerService.getProxy(c, super.getProxy(c), this);
	}

	public DbTask<Object> getTask() {
		return task;
	}

}
