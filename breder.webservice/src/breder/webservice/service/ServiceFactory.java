package breder.webservice.service;

import java.lang.reflect.Proxy;

import breder.webservice.IService;
import breder.webservice.remote.ClientServiceProxy;
import breder.webservice.remote.ClientServiceRetryProxy;
import breder.webservice.remote.ServerServiceProxy;

/**
 * @author bbreder
 */
public final class ServiceFactory {
	
	private static final ServiceFactory instance = new ServiceFactory();
	
	private ServiceFactory() {
	}
	
	/**
	 * Cria uma requisi��o de um cliente, que ser� processada no servidor.
	 * 
	 * @param <E>
	 * @param c
	 * @param code
	 * @return
	 */
	public <E extends IService> E newClientInstance(Class<E> c, String code) {
		E handler = this.newClientInstanceSimple(c, code);
		return c.cast(Proxy.newProxyInstance(c.getClassLoader(), new Class[] { c },
				new ClientServiceRetryProxy<E>(handler)));
	}
	
	public <E extends IService> E newClientInstanceSimple(Class<E> c, String code) {
		ClientServiceProxy proxy = new ClientServiceProxy(c);
		return c.cast(Proxy.newProxyInstance(c.getClassLoader(), new Class[] { c }, proxy));
	}
	
	/**
	 * Cria uma requisi��o no servidor que ser� processada no cliente. A thread
	 * corrente ir� processar a requisi��o.
	 * 
	 * @param <E>
	 * @param c
	 * @param code
	 * @return
	 */
	public <E extends IService> E newServerInstance(Class<E> c, String code) {
		ServerServiceProxy proxy = new ServerServiceProxy(c, code);
		return c.cast(Proxy.newProxyInstance(c.getClassLoader(), new Class[] { c }, proxy));
	}
	
	/**
	 * Cria uma requisi��o no servidor que ser� processada no cliente. O
	 * processamente ser� feito por outra thread. Caso o feedback seja nulo, o
	 * retorno da requisi��o ser� ignorado.
	 * 
	 * @param <E>
	 * @param c
	 * @param code
	 * @return
	 */
	public <E extends IService> E newServerInstance(Class<E> c, String code, IFeedBack feedback) {
		ServerServiceProxy proxy = new ServerServiceProxy(c, code, true, feedback);
		return c.cast(Proxy.newProxyInstance(c.getClassLoader(), new Class[] { c }, proxy));
	}
	
	public static ServiceFactory getInstance() {
		return instance;
	}
	
}
