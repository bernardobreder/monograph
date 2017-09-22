package workspace;

import view.View;
import editor.Editor;

public abstract class Workspace {

  /** Nome do workspace */
  protected String name;
  /** Editores cadastrados */
  protected Editor editor;
  /** Visões cadastradas */
  protected View[] views;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Editor getEditor() {
    return editor;
  }

  public void setEditor(Editor editors) {
    this.editor = editors;
  }

  public View[] getViews() {
    return views;
  }

  public void setViews(View[] views) {
    this.views = views;
  }

  public void close() {
    for (View view : this.getViews()) {
      view.close();
    }
    this.getEditor().close();
  }

  public void open() {
    for (View view : this.getViews()) {
      view.open();
    }
    this.getEditor().open();
  }

}
