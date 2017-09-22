package breder.webservice.remote;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import breder.util.task.RemoteTask;
import breder.webservice.ILoginFrame;
import breder.webservice.IServerService;
import breder.webservice.exception.PermissionException;
import breder.webservice.service.ServerService;

public class ClientServiceRetryProxy<E> implements InvocationHandler,
		Serializable {

	private final E proxy;

	public ClientServiceRetryProxy(E proxy) {
		super();
		this.proxy = proxy;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		String code = ServerService.getInstance().getCode();
		while (true) {
			try {
				Object result = method.invoke(this.proxy, args);
				if (!ServerService.getInstance().getCode().equals(code)) {
					ServerService.getInstance().setCode(code);
				}
				return result;
			} catch (Throwable e) {
				if (e.getCause() instanceof PermissionException) {
					synchronized (IServerService.DEFAULT) {
						new RemoteTask() {
							private ILoginFrame frame;

							@Override
							public void perform() throws Throwable {
								this.frame = ServerService.getInstance()
										.getLoginFrameClass().newInstance();
							}

							@Override
							public void updateUI() {
								frame.reset();
								frame.open();
							}

						}.start();
						IServerService.DEFAULT.wait();
					}
				} else {
					e.printStackTrace();
					ServerService.getInstance().finish();
					ServerService.getInstance().init();
				}
			}
		}
	}
}
