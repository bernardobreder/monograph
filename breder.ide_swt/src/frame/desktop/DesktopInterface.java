package frame.desktop;

import org.eclipse.swt.widgets.ToolBar;

import frame.GenericFrameInterface;

public abstract class DesktopInterface extends GenericFrameInterface<Desktop> {

  protected ToolBar toolBar;

  public abstract void onNewFileAction();

  public abstract void onQuitAction();

  public abstract void onInitWindowAction();

  public ToolBar getToolBar() {
    return toolBar;
  }

  public void setToolBar(ToolBar toolbar) {
    this.toolBar = toolbar;
  }

}
