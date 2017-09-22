import breder.ide.plugin.IMenu;
import breder.ide.plugin.IMenuItem;
import breder.ide.plugin.IPlugin;
import breder.ide.plugin.IView;
import breder.ide.plugin.explorer.ExplorerPanel;
import breder.ide.plugin.impl.Menu;
import breder.ide.plugin.impl.MenuItem;

/**
 * Estrutura de Plugin
 * 
 * 
 * @author Bernardo Breder
 */
public class Plugin implements IPlugin {

  /** Menus */
  private IMenuItem[] menus;

  /**
   * Construtor
   */
  public Plugin() {
    IMenu menu = new Menu("Tool");
    IMenuItem item = new MenuItem(menu, "Calc");
    this.menus = new IMenuItem[] { item };
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IMenuItem[] getMenuItens() {
    return menus;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IView[] getViews() {
    return new IView[] { new ExplorerPanel() };
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getName() {
    return "Explorer";
  }

}
