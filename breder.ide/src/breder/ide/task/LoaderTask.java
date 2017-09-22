package breder.ide.task;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

import breder.ide.classloader.PluginClassLoader;
import breder.ide.gui.IdeFrame;
import breder.ide.gui.IdeLoaderFrame;
import breder.ide.plugin.IMenuItem;
import breder.ide.plugin.IPlugin;
import breder.ide.plugin.IView;
import breder.ide.view.ViewManager;
import breder.util.task.EventTask;
import breder.util.task.RemoteTask;

/**
 * Thread que carrega os plugins
 * 
 * 
 * @author Bernardo Breder
 */
public class LoaderTask extends Thread {

  /**
   * {@inheritDoc}
   */
  @Override
  public void run() {
    File pluginDir = new File("plugin");
    pluginDir.mkdirs();
    File[] plugins = pluginDir.listFiles();
    Arrays.sort(plugins, new Comparator<File>() {

      @Override
      public int compare(File o1, File o2) {
        return o1.getName().compareTo(o2.getName());
      }
    });
    for (File plugin : plugins) {
      if (!plugin.isHidden() && plugin.isDirectory()) {
        LoaderAppTask task = new LoaderAppTask(plugin);
        task.start();
        task.join();
      }
    }
    EventTask.invokeLater(new Runnable() {
      @Override
      public void run() {
        IdeLoaderFrame.getInstance().close();
        IdeFrame.getInstance().setVisible(true);
      }
    });
  }

  /**
   * Carrega um Plugin
   * 
   * 
   * @author Bernardo Breder
   */
  public class LoaderAppTask extends RemoteTask {

    /** Classloader */
    private PluginClassLoader classloader;
    /** Plugin */
    private IPlugin plugin;

    /**
     * Construtor
     * 
     * @param pluginDir
     */
    public LoaderAppTask(File pluginDir) {
      this.classloader =
        new PluginClassLoader(this.getClass().getClassLoader(), pluginDir);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void perform() throws Throwable {
      @SuppressWarnings("unchecked")
      Class<? extends IPlugin> pluginClass =
        (Class<? extends IPlugin>) this.classloader.loadClass("Plugin");
      this.plugin = pluginClass.newInstance();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handler(Throwable t) {
      t.printStackTrace();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateUI() {
      this.addMenu();
      this.addView();
    }

    /**
     * Adiciona um menu
     */
    private void addMenu() {
      IdeFrame frame = IdeFrame.getInstance();
      IMenuItem[] menuItens = this.plugin.getMenuItens();
      if (menuItens != null) {
        for (IMenuItem item : menuItens) {
          frame.addMenuItem(item);
        }
        frame.setJMenuBar(frame.getJMenuBar());
      }
    }

    /**
     * Adiciona uma visão
     */
    private void addView() {
      IView[] views = this.plugin.getViews();
      if (views != null) {
        for (IView view : views) {
          ViewManager.getDefault().put(view.getName(), view);
        }
      }
    }

  }

}
