package ui.util;

public abstract class ReadableTableModel extends BasicTableModel {

  @Override
  public boolean isCellEditable(int rowIndex, int columnIndex) {
    return false;
  }

  @Override
  public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    throw new IllegalStateException("tabela não editável");
  }

}
