package loader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

import resource.IDEConfigurator;

public class ApplicationClassLoader extends ClassLoader {

  protected final File root;

  protected Map<String, List<String>> libs;

  public ApplicationClassLoader(ClassLoader parent, File root) {
    super(parent);
    this.root = root;
    this.libs = new HashMap<String, List<String>>();
    {
      File libDir = new File(root, IDEConfigurator.LIB);
      if (libDir.exists()) {
        for (File jarFile : libDir.listFiles()) {
          if (jarFile.getName().endsWith(".jar")) {
            List<String> list = new ArrayList<String>();
            try {
              JarInputStream input =
                new JarInputStream(new FileInputStream(jarFile));
              JarEntry entry;
              while ((entry = input.getNextJarEntry()) != null) {
                if (!entry.isDirectory()) {
                  String classname = entry.getName();
                  classname =
                    classname.substring(0, classname.length()
                      - ".class".length());
                  classname = classname.replaceAll("/", ".");
                  list.add(classname);
                }
              }
            }
            catch (IOException e) {
            }
            this.libs.put(jarFile.getAbsolutePath(), list);
          }
        }
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected synchronized Class<?> loadClass(String name, boolean resolve)
    throws ClassNotFoundException {
    {
      File binDir = new File(root, IDEConfigurator.BIN);
      if (binDir.exists()) {
        File classFile =
          new File(binDir, name.replace('.', File.separatorChar) + ".class");
        if (classFile.exists()) {
          try {
            return readInputStream(name, new FileInputStream(classFile));
          }
          catch (IOException e1) {
          }
        }
      }
    }
    {
      String found = null;
      for (String key : this.libs.keySet()) {
        List<String> list = this.libs.get(key);
        for (String classname : list) {
          if (classname.equals(name)) {
            found = key;
            break;
          }
        }
        if (key != null)
          break;
      }
      if (found != null) {
        File jarFile = new File(found);
        try {
          JarInputStream input =
            new JarInputStream(new FileInputStream(jarFile));
          JarEntry entry;
          while ((entry = input.getNextJarEntry()) != null) {
            if (!entry.isDirectory()) {
              String classname = entry.getName();
              classname =
                classname.substring(0, classname.length() - ".class".length());
              classname = classname.replaceAll("/", ".");
              if (name.equals(classname)) {
                return readInputStream(classname, input);
              }
            }
          }
        }
        catch (IOException e) {
        }
      }
    }
    return super.getParent().loadClass(name);
  }

  public Class<?> readInputStream(String name, InputStream input)
    throws ClassNotFoundException {
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    byte[] bytes = new byte[8 * 1024];
    while (true) {
      int n;
      try {
        n = input.read(bytes);
      }
      catch (IOException e) {
        throw new ClassNotFoundException(e.getMessage());
      }
      if (n == -1)
        break;
      output.write(bytes, 0, n);
    }
    bytes = output.toByteArray();
    return super.defineClass(name, bytes, 0, bytes.length);
  }

  public File getRoot() {
    return root;
  }

}
