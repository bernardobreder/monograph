package breder.ide.plugin.explorer;

import java.awt.Component;

import javax.swing.JScrollPane;

import breder.ide.plugin.IView;

/**
 * Painel de Explorer
 * 
 * 
 * @author Bernardo Breder
 */
public class ExplorerPanel implements IView {

  /**
   * {@inheritDoc}
   */
  @Override
  public Component build() {
    return new JScrollPane(new ExplorerTree());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getName() {
    return "Explorer";
  }

}
