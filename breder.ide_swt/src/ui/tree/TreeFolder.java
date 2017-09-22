package ui.tree;

public abstract class TreeFolder extends TreeData {

  public abstract void addChild(TreeData child);

  public abstract void removeChild(TreeData child);

  public abstract TreeData[] getChildren();

  public boolean hasChildren() {
    return getChildren().length > 0;
  }

}
