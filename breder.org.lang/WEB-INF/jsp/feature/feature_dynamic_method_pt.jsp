<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p>
</p>

<pre>
public class Test {
  public static void a () {}
  public static void main(IList&lt;String&gt; args) {
    Method&lt;&gt;&lt;&gt; method = Test.a&lt;&gt;;
    method();
  }
}
</pre>

<p>
</p>

<pre>
public class Test {
  public static void a (notnull Number i) {}
  public static void main(IList&lt;String&gt; args) {
    Method&lt;&gt;&lt;Number&gt; method = Test.a&lt;Number&gt;;
    method(5);
  }
}
</pre>

<p>
</p>

<pre>
public class Test {
  public static void a (notnull Number i, Integer u) {}
  public static void main(IList&lt;String&gt; args) {
    Method&lt;&gt;&lt;Number, Integer&gt; method = Test.a&lt;Number, Integer&gt;;
    method(5, 10);
  }
}
</pre>

<p>
</p>

<pre>
public class Test {
  public static notnull Number a (Number i, Integer u) {}
  public static void main(IList&lt;String&gt; args) {
    Method&lt;Number&gt;&lt;Number, Integer&gt; method = Test.a&lt;Number, Integer&gt;;
    Console.println(method(5, 10));
  }
}
</pre>

<p>
</p>

<pre>
public class Test {
  public static Number, String a (Number i, Integer u) {}
  public static void main(IList&lt;String&gt; args) {
    Method&lt;Number, String&gt;&lt;Number, Integer&gt; method = Test.a&lt;Number, Integer&gt;;
    Number a, String b = method(5, 10);
  }
}
</pre>

<p>
</p>

<pre>
public class Test {
  public static Number, String a (Number i, Integer u) {}
  public static void b(Method&lt;Number, String&gt;&lt;Number, Integer&gt; m) {
  	m(5, 10);
  }
  public static void main(IList&lt;String&gt; args) {
    Method&lt;Number, String&gt;&lt;Number, Integer&gt; method = Test.a&lt;Number, Integer&gt;;
    Test.b(method);
  }
}
</pre>

<p>
</p>

<pre>
public class Test {
  public Number, String a (Number i, Integer u) {}
  public void b(Method&lt;Number, String&gt;&lt;Number, Integer&gt; m) {
  	m(5, 10);
  }
  public static void main(IList&lt;String&gt; args) {
    Test t = new Test();
    Method&lt;Number, String&gt;&lt;Number, Integer&gt; method = t.a&lt;Number, Integer&gt;;
    t.b(method);
  }
}
</pre>

<p>
</p>

<pre>
public class Test {
  public Number, String a (Number i, Integer u) {}
  public void b(Method&lt;Number, String&gt;&lt;Number, Integer&gt; m) {
  	m(5, 10);
  }
  public static void main(IList&lt;String&gt; args) {
    Test t = new Test();
    Method&lt;Number, String&gt;&lt;Number, Integer&gt; method = function&lt;Number, String&gt;&lt;Number a, Integer b&gt; {
      return 5, "test";
    }
    t.b(method);
  }
}
</pre>

<p>
</p>
