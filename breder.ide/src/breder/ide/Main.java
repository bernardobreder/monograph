package breder.ide;

import java.io.IOException;
import java.net.MalformedURLException;

import breder.ide.gui.IdeLoaderFrame;
import breder.ide.task.LoaderTask;
import breder.util.lookandfeel.LookAndFeel;
import breder.util.task.EventTask;

/**
 * Inicializa a IDE
 * 
 * 
 * @author Bernardo Breder
 */
public class Main {

  /**
   * Inicializador
   * 
   * @param args
   * @throws IOException
   * @throws MalformedURLException
   * @throws IllegalStateException
   * @throws NullPointerException
   */
  public static void main(String[] args) throws NullPointerException,
    IllegalStateException, MalformedURLException, IOException {
    EventTask.invokeLater(new Runnable() {
      @Override
      public void run() {
        IdeLoaderFrame.getInstance().setVisible(true);
      }
    });
    EventTask.invokeLater(new Runnable() {
      @Override
      public void run() {
        LookAndFeel.getInstance().installMetal();
        new LoaderTask().start();
      }
    });
  }
}
