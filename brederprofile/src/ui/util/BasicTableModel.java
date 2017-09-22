package ui.util;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public abstract class BasicTableModel implements TableModel {

  private final List<TableModelListener> listeners =
    new ArrayList<TableModelListener>();

  @Override
  public void addTableModelListener(TableModelListener l) {
    this.listeners.add(l);
  }

  @Override
  public void removeTableModelListener(TableModelListener l) {
    this.listeners.remove(l);
  }

}
