package util;

import frame.GenericShell;

/**
 * Realiza uma tarefa em Thread separada, podendo atualizar a interface gr�fica
 * apartir do m�todo updateUI.
 * 
 * 
 * @author Tecgraf
 */
public abstract class Task extends Thread {

  /** Hierarquia de tarefa */
  protected final Task parent;
  /** Flag de interrup��o */
  private boolean interrupt;

  /**
   * Construtor padr�o
   */
  public Task() {
    this(null);
  }

  /**
   * Construtor com uma hierarquia
   * 
   * @param parent
   */
  public Task(Task parent) {
    super();
    this.parent = parent;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void run() {
    try {
      this.action();
      GenericShell.getDisplay().asyncExec(new Runnable() {
        @Override
        public void run() {
          if (isInterrupted())
            return;
          try {
            updateUI();
          }
          catch (Throwable e) {
            e.printStackTrace();
          }
        }
      });
    }
    catch (Throwable e) {
      this.handler(e);
    }
  }

  public abstract void action() throws Throwable;

  public void updateUI() {
  }

  public void handler(Throwable e) {
    e.printStackTrace();
  }

  @Override
  public void interrupt() {
    interrupt = true;
  }

  @Override
  public boolean isInterrupted() {
    return interrupt;
  }

  public Task getParent() {
    return parent;
  }

}
