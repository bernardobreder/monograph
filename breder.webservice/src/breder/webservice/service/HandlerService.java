package breder.webservice.service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import breder.webservice.IService;

public class HandlerService<E> implements InvocationHandler {
	
	private E parent;
	
	private InvocationHandler next;
	
	public HandlerService(E parent, InvocationHandler next) {
		this.parent = parent;
		this.next = next;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if (parent != null) {
			method.invoke(parent, args);
		}
		return next.invoke(next, method, args);
	}
	
	public static <E extends IService> E getProxy(Class<E> c, E parent, InvocationHandler next) {
		return (E) Proxy.newProxyInstance(c.getClassLoader(), new Class[] { c },
				new HandlerService<E>(parent, next));
	}
	
}
