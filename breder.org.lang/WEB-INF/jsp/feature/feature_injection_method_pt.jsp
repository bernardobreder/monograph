<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p>
</p>

<pre>
public class Test {
  public static void main(IList&lt;String&gt; args) {
    Number i = 10;
    Console.println(i.toString());
  }
}
</pre>

<p>
</p>

<pre>
public plugin class Number {
  public notnull Number toNegative() {
    return this * -1;
  }
}
public class Test {
  public static void main(IList&lt;String&gt; args) {
    Number i = 10;
    Console.println(i.toNegative().toString());
  }
}
</pre>
