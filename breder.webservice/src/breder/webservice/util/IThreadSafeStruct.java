package breder.webservice.util;

/**
 * Interface que garante que uma estrutura é capaz de proteger contra erros de
 * concorrencia tanto na execução de métodos, quanto na interação de alguma
 * estrutura interna.<br/>
 * Não é permitido realiza lock para leitura e depois para escrita, se não ter
 * tido liberado, primeiramente o lock da leitura.
 */
public interface IThreadSafeStruct {

  /**
   * Realiza um lock para leitura
   */
  public void lockRead();

  /**
   * Realiza um lock para escrita
   */
  public void lockWrite();

  /**
   * Finaliza o lock
   */
  public void unlock();

}
