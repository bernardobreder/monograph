import org.javabuilders.swing.SwingJavaBuilder;

import frame.main.AppConfigurator;
import frame.main.MainFrame;
import frame.main.MainMemoryTable;

public class Main {

  /**
   * @param args
   */
  public static void main(String[] args) {
    if (args.length > 0) {
      AppConfigurator.MEMORY_FILE = args[0];
    }
    SwingJavaBuilder.getConfig().addType(MainMemoryTable.class);
    new MainFrame().setVisible(true);
  }
}
