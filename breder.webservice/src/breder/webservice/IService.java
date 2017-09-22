package breder.webservice;

import java.io.Serializable;

/**
 * Interface b�sica de um servi�o. Todo servi�o deve implementar a interface.
 * 
 * @author Tecgraf
 */
public interface IService extends Serializable {
	
	public void init();
	
	public void dispose();
	
	public <E extends IService> E getProxy(Class<E> c);
	
}
