package breder.ide.classloader;

import java.io.File;
import java.io.FileInputStream;

import breder.util.util.InputStreamUtil;

/**
 * Classloader de plugin
 * 
 * 
 * @author Bernardo Breder
 */
public class PluginClassLoader extends ClassLoader {

  /** Binario de plugin */
  private File binDir;

  /**
   * Construtor
   * 
   * @param parent
   * @param pluginDir
   */
  public PluginClassLoader(ClassLoader parent, File pluginDir) {
    super(parent);
    this.binDir = new File(pluginDir, "bin");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected synchronized Class<?> loadClass(String name, boolean resolve)
    throws ClassNotFoundException {
    try {
      return super.loadClass(name, resolve);
    }
    catch (ClassNotFoundException e) {
    }
    String filename = name.replace('.', File.separatorChar) + ".class";
    File binaryClass = new File(this.binDir, filename);
    if (binaryClass.exists()) {
      try {
        byte[] bytes =
          InputStreamUtil.getBytes(new FileInputStream(binaryClass));
        return this.defineClass(name, bytes, 0, bytes.length);
      }
      catch (Exception e) {
      }
    }
    throw new ClassNotFoundException(name);
  }

}
