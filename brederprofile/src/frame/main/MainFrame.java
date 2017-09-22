package frame.main;

import javax.swing.JFrame;

import org.javabuilders.BuildResult;
import org.javabuilders.swing.SwingJavaBuilder;

public class MainFrame extends JFrame {

  /** Objeto do SwingJavaBulder */
  protected final BuildResult result = SwingJavaBuilder.build(this);

  public MainFrame() {
    this.setAlwaysOnTop(true);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.pack();
  }

}
