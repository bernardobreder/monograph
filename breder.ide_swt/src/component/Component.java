package component;


public abstract class Component {

  public abstract ComponentUI getUi();

  public void close() {
    this.getUi().close();
  }

  public void open() {
    this.getUi().open();
  }

}
