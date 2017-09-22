package breder.webservice.util;

/**
 * Interface que garante que uma estrutura � capaz de proteger contra erros de
 * concorrencia tanto na execu��o de m�todos, quanto na intera��o de alguma
 * estrutura interna.<br/>
 * N�o � permitido realiza lock para leitura e depois para escrita, se n�o ter
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
