package breder.webservice;

import java.io.IOException;

import breder.webservice.exception.AuthenticationException;
import breder.webservice.service.IAuthenticationService;
import breder.webservice.service.ServerService;

/**
 * Estrutura respons�vel por registrar uma usu�rio a um servidor. <br/>
 * Esse registro � rec�proco, tanto o cliente se registra no servidor, quando o
 * servidor se registra no cliente.<br/>
 */
public interface IServerService {
	
	/**
	 * Implementação default
	 */
	public static final IServerService DEFAULT = ServerService.getInstance();
	
	/**
	 * Inicializa o registro no servidor e o servidor no cliente. Essa opera��o
	 * pode ser chamada quantas vezes quiser, que depois da primeira vez, a sua
	 * execu��o � ignorada.
	 * 
	 * @throws IOException
	 */
	public void init(String username, char[] password) throws AuthenticationException;
	
	public void init() throws AuthenticationException;
	
	/**
	 * Busca por um servi�o que o servidor implemente. Essa busca � instantanea
	 * e retorna um proxy que gerencia a chamada ao servidor e a sua resposta.
	 * 
	 * @param <E>
	 *            classe do servi�o
	 * @param clazz
	 *            classe do servi�o que deseja processar
	 * @return retorna o proxy que implementa o servi�o
	 */
	public abstract <E extends IService> E lookup(Class<E> clazz);
	
	public void setAuthenticationServiceClass(
			Class<? extends IAuthenticationService> authenticationServiceClass);
	
	public void setLoginFrameClass(Class<? extends ILoginFrame> loginFrameClass);
	
	/**
	 * Retorna as configura��es do framework.
	 */
	public abstract IServerConfigurator getConfig();
	
	/**
	 * Finaliza a comunica��o com o servidor.
	 */
	public void finish();
	
}
