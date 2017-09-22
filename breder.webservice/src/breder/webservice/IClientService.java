package breder.webservice;

import breder.webservice.service.ClientService;
import breder.webservice.service.IFeedBack;

/**
 * Classe responsável por acessar as informações do cliente que está processando
 * um serviço no servidor. Essa classe só deve ser usada quando se está
 * processando um serviço no ambiente do servidor.<br/>
 * Com essa estrutura é possível localizar todos os serviços de um usuário,
 * recuperar o código do registro do usuário que solicitou o chamada ao serviço
 * que está sendo processada ou até mesmo informar todos os registros
 * cadastrado.
 * 
 * @author Bernardo
 */
public interface IClientService {

  /**
   * Implementação default
   */
  public static final IClientService DEFAULT = ClientService.getInstance();

  /**
   * Retorna o registro do usuario que está utilizando o serviço.
   * 
   * @return código do usuário
   */
  public abstract String getCode();

  /**
   * Retorna todos os registros cadastrado.
   * 
   * @return códigos de todos os usuários que estão registrado no sistema
   */
  public abstract String[] getAllCodes();

  /**
   * Retorna um serviço do usuário, quando um serviço do servidor estiver sendo
   * processado. Essas operação só pode ser feita caso esteja processando alguma
   * serviço no servidor e não no cliente. Além disso, a operação será realizado
   * em outra thread, significando que não irá interromper a thread corrente
   * para poder processar. Caso o parametro do feedback for nulo, significa que
   * o método não irá informar o seu retorno.
   * 
   * @param <E> classe do serviço
   * @param clazz classe do serviço
   * @return proxy do serviço
   */
  public <E extends IService> E getUserService(Class<E> clazz, String code,
    IFeedBack feedback);

  /**
   * O mesmo do {@link IClientService.getUserService(Class<E>, String,
   * IFeedBack<?>)} so que será executado na própria thread corrente
   * 
   * @param <E> Classe do serviço
   * @param clazz Classe do serviço
   * @return proxy do serviço
   */
  public <E extends IService> E getUserService(Class<E> clazz, String code);

  /**
   * O mesmo do {@link IClientService.getUserService(Class<E>, String,
   * IFeedBack<?>)} so que o usuário é que fez a requisição para o servidor.
   * 
   * @param <E> Classe do serviço
   * @param clazz Classe do serviço
   * @return proxy do serviço
   */
  public <E extends IService> E getUserService(Class<E> clazz);

  /**
   * O mesmo do {@link IClientService.getUserService(Class<E>, String,
   * IFeedBack<?>)} so que o usuário é que fez a requisição para o servidor e
   * será realizado o processamento em outra Thread.
   * 
   * @param <E> Classe do serviço
   * @param clazz Classe do serviço
   * @return proxy do serviço
   */
  public <E extends IService> E getUserService(Class<E> clazz,
    IFeedBack feedback);

}