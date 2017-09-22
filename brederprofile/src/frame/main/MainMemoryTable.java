package frame.main;

import javax.swing.JTable;

public class MainMemoryTable extends JTable {

  public MainMemoryTable() {
    super(new MainMemoryTableModel());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public MainMemoryTableModel getModel() {
    return (MainMemoryTableModel) super.getModel();
  }
}
