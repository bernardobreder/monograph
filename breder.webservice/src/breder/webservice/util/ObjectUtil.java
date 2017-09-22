/**
 * 
 */

package breder.webservice.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 * @author bbreder
 */
public class ObjectUtil {

  public static <E> E readBytes(Class<E> c, byte[] bytes) throws IOException,
    ClassNotFoundException {
    return ObjectUtil.readBytes(c, new ByteArrayInputStream(bytes));
  }

  public static <E> E readBytes(Class<E> c, InputStream input)
    throws IOException, ClassNotFoundException {
    ByteArrayInputStream in;
    if (false) {
      ByteArrayOutputStream o = new ByteArrayOutputStream();
      byte[] bytes = new byte[1024];
      while (true) {
        int n = input.read(bytes);
        if (n == -1)
          break;
        o.write(bytes, 0, n);
        System.out.print(new String(bytes, 0, n));
      }
      in = new ByteArrayInputStream(o.toByteArray());
    }
    ObjectInputStream ois = new ObjectInputStream(input);
    return c.cast(ois.readObject());
  }

  public static void write(Object instance, OutputStream output)
    throws IOException {
    ObjectOutputStream oos = new ObjectOutputStream(output);
    oos.writeObject(instance);
  }

  public static ByteArrayOutputStream writeToBytes(Object instance)
    throws IOException {
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    ObjectOutputStream oos = new ObjectOutputStream(output);
    oos.writeObject(instance);
    return output;
  }

}
