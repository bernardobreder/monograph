package util;

import javax.swing.SwingUtilities;

public abstract class Task extends Thread {

  public abstract void action() throws Throwable;

  @Override
  public void run() {
    try {
      action();
      SwingUtilities.invokeLater(new Runnable() {

        @Override
        public void run() {
          updateUI();
        }

      });
    }
    catch (Throwable e) {
      handler(e);
    }finally{
    	doFinally();
    }
  }

  public void updateUI() {
  }

  public void handler(Throwable e) {
    e.printStackTrace();
  }
  
  public void doFinally() {
  }

}
