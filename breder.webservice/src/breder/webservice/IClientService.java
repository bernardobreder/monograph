package breder.webservice;

import breder.webservice.service.ClientService;
import breder.webservice.service.IFeedBack;

/**
 * Classe respons�vel por acessar as informa��es do cliente que est� processando
 * um servi�o no servidor. Essa classe s� deve ser usada quando se est�
 * processando um servi�o no ambiente do servidor.<br/>
 * Com essa estrutura � poss�vel localizar todos os servi�os de um usu�rio,
 * recuperar o c�digo do registro do usu�rio que solicitou o chamada ao servi�o
 * que est� sendo processada ou at� mesmo informar todos os registros
 * cadastrado.
 * 
 * @author Bernardo
 */
public interface IClientService {

  /**
   * Implementa��o default
   */
  public static final IClientService DEFAULT = ClientService.getInstance();

  /**
   * Retorna o registro do usuario que est� utilizando o servi�o.
   * 
   * @return c�digo do usu�rio
   */
  public abstract String getCode();

  /**
   * Retorna todos os registros cadastrado.
   * 
   * @return c�digos de todos os usu�rios que est�o registrado no sistema
   */
  public abstract String[] getAllCodes();

  /**
   * Retorna um servi�o do usu�rio, quando um servi�o do servidor estiver sendo
   * processado. Essas opera��o s� pode ser feita caso esteja processando alguma
   * servi�o no servidor e n�o no cliente. Al�m disso, a opera��o ser� realizado
   * em outra thread, significando que n�o ir� interromper a thread corrente
   * para poder processar. Caso o parametro do feedback for nulo, significa que
   * o m�todo n�o ir� informar o seu retorno.
   * 
   * @param <E> classe do servi�o
   * @param clazz classe do servi�o
   * @return proxy do servi�o
   */
  public <E extends IService> E getUserService(Class<E> clazz, String code,
    IFeedBack feedback);

  /**
   * O mesmo do {@link IClientService.getUserService(Class<E>, String,
   * IFeedBack<?>)} so que ser� executado na pr�pria thread corrente
   * 
   * @param <E> Classe do servi�o
   * @param clazz Classe do servi�o
   * @return proxy do servi�o
   */
  public <E extends IService> E getUserService(Class<E> clazz, String code);

  /**
   * O mesmo do {@link IClientService.getUserService(Class<E>, String,
   * IFeedBack<?>)} so que o usu�rio � que fez a requisi��o para o servidor.
   * 
   * @param <E> Classe do servi�o
   * @param clazz Classe do servi�o
   * @return proxy do servi�o
   */
  public <E extends IService> E getUserService(Class<E> clazz);

  /**
   * O mesmo do {@link IClientService.getUserService(Class<E>, String,
   * IFeedBack<?>)} so que o usu�rio � que fez a requisi��o para o servidor e
   * ser� realizado o processamento em outra Thread.
   * 
   * @param <E> Classe do servi�o
   * @param clazz Classe do servi�o
   * @return proxy do servi�o
   */
  public <E extends IService> E getUserService(Class<E> clazz,
    IFeedBack feedback);

}