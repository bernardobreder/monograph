<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p>
</p>

<pre>
public class Test {
  public static Number a () {
    Console.println("processing...")
    return 1 + 2;
  }
  public static void main(IList&lt;String&gt; args) {
    Console.println(Test.a());
    Console.println(Test.a());
  }
}
</pre>

<p>Output :</p>
<pre>
processing...
3
processing...
3
</pre>

<p>
</p>

<pre>
public class Test {
  public cache static Number a () {
    Console.println("processing...")
    return 1 + 2;
  }
  public static void main(IList&lt;String&gt; args) {
    Console.println(Test.a());
    Console.println(Test.a());
  }
}
</pre>

<p>Output :</p>
<pre>
processing...
3
3
</pre>

<p>
</p>

<pre>
public class Test {
  public static Number i;
  public static Number a () {
    Console.println("processing...")
    return 1 + Test.i;
  }
  public static void main(IList&lt;String&gt; args) {
    Test.i = 2;
    Console.println(Test.a());
    Console.println(Test.a());
  }
}
</pre>

<p>Output :</p>
<pre>
processing...
3
processing...
3
</pre>

<p>
</p>

<pre>
public class Test {
  public static Number i;
  public cache static Number a () {
    Console.println("processing...")
    return 1 + Test.i;
  }
  public static void main(IList&lt;String&gt; args) {
    Test.i = 2;
    Console.println(Test.a());
    Console.println(Test.a());
  }
}
</pre>

<p>Output :</p>
<pre>
processing...
3
3
</pre>

<p>
</p>

<pre>
public class Test {
  public static Number i;
  public cache static Number a () {
    Console.println("processing...")
    return 1 + Test.i;
  }
  public static void main(IList&lt;String&gt; args) {
    Test.i = 2;
    Console.println(Test.a());
    Test.i = 2;
    Console.println(Test.a());
  }
}
</pre>

<p>Output :</p>
<pre>
processing...
3
processing...
3
</pre>

<p>
</p>

<pre>
public class Test {
  public static Number i;
  public static Number u;
  public cache static Number a () {
    Console.println("processing...")
    return 1 + Test.i + Test.u;
  }
  public static void main(IList&lt;String&gt; args) {
    Test.i = 2;
    Test.u = 3;
    Console.println(Test.a());
    Test.u = 3;
    Console.println(Test.a());
    Console.println(Test.a());
  }
}
</pre>

<p>Output :</p>
<pre>
processing...
6
processing...
6
6
</pre>

<p>
</p>

<pre>
public class Test {
  public static Number i;
  public static Number u;
  public cache static Number a (notnull Number b) {
    Console.println("processing...")
    return 1 + Test.i + Test.u + b;
  }
  public static void main(IList&lt;String&gt; args) {
    Test.i = 2;
    Test.u = 3;
    Console.println(Test.a(1));
    Test.u = 3;
    Console.println(Test.a(2));
    Console.println(Test.a(3));
  }
}
</pre>

<p>Output :</p>
<pre>
processing...
6
processing...
7
processing...
8
</pre>

<p>
</p>

<pre>
public class Test {
  public static Number i;
  public static Number u;
  public static Number b () {
    return 1;
  }
  public cache static Number a () {
    Console.println("processing...")
    return 1 + Test.i + Test.u + Test.b();
  }
  public static void main(IList&lt;String&gt; args) {
    Test.i = 2;
    Test.u = 3;
    Console.println(Test.a());
    Test.u = 3;
    Console.println(Test.a());
    Console.println(Test.a());
  }
}
</pre>

<p>Output :</p>
<pre>
processing...
7
processing...
7
processing...
7
</pre>

<p>
</p>

<pre>
public class Test {
  public static Number i;
  public static Number u;
  public cache static Number b () {
    return 1;
  }
  public cache static Number a () {
    Console.println("processing...")
    return 1 + Test.i + Test.u + Test.b();
  }
  public static void main(IList&lt;String&gt; args) {
    Test.i = 2;
    Test.u = 3;
    Console.println(Test.a());
    Test.u = 3;
    Console.println(Test.a());
    Console.println(Test.a());
  }
}
</pre>

<p>Output :</p>
<pre>
processing...
7
processing...
7
7
</pre>

<p>
</p>

<pre>
public class Test {
  public static Number i;
  public static Number u;
  public cache static Number b (notnull Number p) {
    return p;
  }
  public cache static Number a () {
    Console.println("processing...")
    return 1 + Test.i + Test.u + Test.b(1);
  }
  public static void main(IList&lt;String&gt; args) {
    Test.i = 2;
    Test.u = 3;
    Console.println(Test.a());
    Test.u = 3;
    Console.println(Test.a());
    Console.println(Test.a());
  }
}
</pre>

<p>Output :</p>
<pre>
processing...
7
processing...
7
processing...
7
</pre>

<p>
</p>

<pre>
public class Test {
  public static Number i;
  public static Number u;
  public cache static Number b (notnull Number p) {
    return p;
  }
  public cache static Number a () {
    Console.println("processing...")
    return 1 + Test.i + Test.u + Test.b(Test.i);
  }
  public static void main(IList&lt;String&gt; args) {
    Test.i = 2;
    Test.u = 3;
    Console.println(Test.a());
    Test.u = 3;
    Console.println(Test.a());
    Console.println(Test.a());
  }
}
</pre>

<p>Output :</p>
<pre>
processing...
8
processing...
8
8
</pre>

<p>
</p>
