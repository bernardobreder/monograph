/**
 * 
 */

package breder.webservice.util;

import java.lang.reflect.InvocationTargetException;

/**
 * @author bbreder
 */
public class ExceptionUtil {

  /**
   * /** Constr�i as pilhas que descrevem a exce��o e suas causas, no caso de
   * haver outras exce��es aninhadas.
   * 
   * @param throwable a exce��o principal
   * @return o texto que descreve todas as exce��es aninhadas.
   */
  public static String getContentOfStackTrace(Throwable throwable) {
    String separator = "";
    String tab = "";
    String text = "";
    Throwable curr;
    Throwable prox = throwable;
    do {
      curr = prox;
      text += separator;
      text += tab + "Classe: " + curr.getClass().getName() + "\n";
      text += tab + "Mensagem: " + curr.getLocalizedMessage() + "\n";
      text += tab + "Pilha:\n";
      StackTraceElement[] stackTrace = curr.getStackTrace();
      for (int i = 0; i < stackTrace.length; i++) {
        text += tab + stackTrace[i] + "\n";
      }
      separator = "\nCausada pela exce��o:\n";
      tab += "  ";
      if (curr instanceof InvocationTargetException) {
        InvocationTargetException ite = (InvocationTargetException) curr;
        prox = ite.getTargetException();
      }
      else {
        prox = curr.getCause();
      }
    } while (prox != null && curr != prox);
    return text;
  }

}
