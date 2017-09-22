package frame.main.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JFileChooser;

import util.Task;
import frame.main.AppConfigurator;
import frame.main.MainMemoryTableModel;
import frame.main.MainMemoryTableModelItem;

public class MainMemoryTableRefreshTask extends Task {

  private static long lastModified;

  private final File file;

  private final MainMemoryTableModel model;

  private List<MainMemoryTableModelItem> list;

  public MainMemoryTableRefreshTask(MainMemoryTableModel model) {
    this.model = model;
    if (AppConfigurator.MEMORY_FILE == null) {
      JFileChooser chooser = new JFileChooser();
      while (chooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
      }
      file = chooser.getSelectedFile();
    }
    else {
      file = new File(AppConfigurator.MEMORY_FILE);
    }
    this.lastModified = Long.MAX_VALUE;
  }

  @Override
  public synchronized void action() throws Throwable {
    if (file.lastModified() + 1000 > this.lastModified)
      return;
    this.lastModified = file.lastModified();
    list = new ArrayList<MainMemoryTableModelItem>();
    BufferedReader input = null;
    try {
      input = new BufferedReader(new FileReader(file));
      while (true) {
        String line = input.readLine();
        if (line == null)
          break;
        StringTokenizer tokens = new StringTokenizer(line);
        try {
          String type = tokens.nextToken();
          if (type.equals("alloc")) {
            alloc(list, tokens);
          }
          else if (type.equals("free")) {
            free(list, tokens);
          }
        }
        catch (NoSuchElementException e) {
        }
      }
    }
    finally {
      if (input != null)
        input.close();
    }
  }

  @Override
  public void handler(Throwable e) {
  }

  private void alloc(List<MainMemoryTableModelItem> list, StringTokenizer tokens) {
    String address = tokens.nextToken();
    Integer size = 0;
    try {
      size = Integer.parseInt(tokens.nextToken());
    }
    catch (Exception e) {
    }
    Integer len = 0;
    try {
      len = Integer.parseInt(tokens.nextToken());
    }
    catch (Exception e) {
    }
    Integer count = 0;
    try {
      count = Integer.parseInt(tokens.nextToken());
    }
    catch (Exception e) {
    }
    String describer = null;
    try {
      describer = tokens.nextToken();
    }
    catch (Exception e) {
    }
    list
      .add(new MainMemoryTableModelItem(address, size, len, count, describer));
  }

  private void free(List<MainMemoryTableModelItem> list, StringTokenizer tokens) {
    String address = tokens.nextToken();
    for (int n = 0; n < list.size(); n++) {
      MainMemoryTableModelItem item = list.get(n);
      if (item.getAddress().hashCode() == address.hashCode()
        && item.getAddress().equals(address)) {
        list.remove(n);
      }
    }
  }

  @Override
  public synchronized void updateUI() {
    if (list != null) {
      Vector<MainMemoryTableModelItem> vector = this.model.getDataVector();
      int size = 0, len = 0, count = 0;
      vector.clear();
      for (MainMemoryTableModelItem item : list) {
        vector.add(0, item);
        size += item.getSize() * item.getLength();
        len += item.getLength();
        count = Math.max(count, item.getCount());
      }
      vector.add(0, new MainMemoryTableModelItem("Total", size, len, 0, null));
      this.model.fireTableDataChanged();
    }
  }

  @Override
  public void doFinally() {
    try {
      Thread.sleep(100);
    }
    catch (InterruptedException e) {
    }
    new MainMemoryTableRefreshTask(model).start();
  }

}
