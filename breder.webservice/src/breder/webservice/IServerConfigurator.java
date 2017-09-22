package breder.webservice;

/**
 * Interface respons�vel por configurar o tomcat service
 * 
 * @author Bernardo
 */
public interface IServerConfigurator {

  /**
   * Retorna a url do tomcat. O default � : http://localhost:8080
   * 
   * @return url do tomcat
   */
  public abstract String getTomcatUrl();

  /**
   * Atribui uma url que localiza o tomcat
   * 
   * @param tomcatUrl
   */
  public abstract void setTomcatUrl(String tomcatUrl);

  /**
   * Retorna o caminho do servlet de servi�o na url do tomcat. O default � :
   * service
   * 
   * @return
   */
  public abstract String getServletUrl();

  /**
   * Atribui uma url que localiza o servlet na url do tomcat
   * 
   * @param servletUrl
   */
  public abstract void setServletUrl(String servletUrl);

  /**
   * Indica o timeout, em milisegundos, para as sess�es
   * 
   * @return
   */
  public long getSessionTimeout();

  /**
   * Atribui um timeout para as sess�es
   * 
   * @param sessionTimeout
   */
  public void setSessionTimeout(long sessionTimeout);

}