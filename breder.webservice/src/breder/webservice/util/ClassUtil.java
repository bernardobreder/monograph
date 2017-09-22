package breder.webservice.util;

public class ClassUtil {

  public static boolean isInstanceof(Class<?> c1, Class<?> c2) {
    try {
      return c1.asSubclass(c2) != null;
    }
    catch (ClassCastException e) {
      return false;
    }
  }

}
