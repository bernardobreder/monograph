package breder.ide.gui;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JFrame;

import breder.util.swing.BImage;

/**
 * Dialogo de carga da IDE
 * 
 * 
 * @author Bernardo Breder
 */
public class IdeLoaderFrame extends JDialog {

  /** Instancia default */
  private static IdeLoaderFrame instance = new IdeLoaderFrame();

  /**
   * Construtor
   */
  private IdeLoaderFrame() {
    try {
      this.add(new BImage(ImageIO.read(this.getClass().getResourceAsStream(
        "/breder/ide/resource/loader.jpg"))));
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    this.setUndecorated(true);
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.setSize(320, 240);
    this.setLocationRelativeTo(null);
    this.setVisible(true);
  }

  /**
   * Fecha a janela
   */
  public void close() {
    this.dispose();
    instance = null;
  }

  /**
   * Retorna
   * 
   * @return instance
   */
  public static IdeLoaderFrame getInstance() {
    return instance;
  }

}
