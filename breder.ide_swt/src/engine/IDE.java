package engine;

import workspace.Workspace;
import frame.desktop.Desktop;

public class IDE {

  private static final IDE instance = new IDE();

  private Desktop desktop;

  private Workspace workspace;

  private IDE() {
  }

  public Workspace getWorkspace() {
    return workspace;
  }

  public void setWorkspace(Workspace workspace) {
    if (this.workspace != null) {
      this.workspace.close();
    }
    this.workspace = workspace;
    this.workspace.open();
  }

  public Desktop getDesktop() {
    return desktop;
  }

  public void setDesktop(Desktop desktop) {
    this.desktop = desktop;
  }

  public static IDE getInstance() {
    return instance;
  }

}
