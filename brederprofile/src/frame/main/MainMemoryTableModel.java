package frame.main;

import java.text.NumberFormat;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import frame.main.action.MainMemoryTableRefreshTask;

public class MainMemoryTableModel extends DefaultTableModel {

  private String[] NAME =
    new String[] { "Address", "Size", "Len", "Count", "Descrição" };

  private Class[] CLASS =
    new Class[] { String.class, String.class, String.class, String.class,
        String.class };

  private final Vector<MainMemoryTableModelItem> itens =
    new Vector<MainMemoryTableModelItem>();

  public MainMemoryTableModel() {
    new MainMemoryTableRefreshTask(this).start();
  }

  @Override
  public Class<?> getColumnClass(int columnIndex) {
    return CLASS[columnIndex];
  }

  @Override
  public int getColumnCount() {
    return CLASS.length;
  }

  @Override
  public String getColumnName(int columnIndex) {
    return NAME[columnIndex];
  }

  @Override
  public boolean isCellEditable(int row, int column) {
    return false;
  }

  @Override
  public Object getValueAt(int row, int column) {
    MainMemoryTableModelItem item =
      (MainMemoryTableModelItem) super.getDataVector().get(row);
    if (column == 0) {
      return item.getAddress();
    }
    else if (column == 1) {
      return NumberFormat.getInstance().format(item.getSize());
    }
    else if (column == 2) {
      return NumberFormat.getInstance().format(item.getLength());
    }
    else if (column == 3) {
      return item.getCount();
    }
    else if (column == 4) {
      return item.getDescriber();
    }
    else
      return null;
  }
}
