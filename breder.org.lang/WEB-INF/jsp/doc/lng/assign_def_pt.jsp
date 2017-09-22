<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p>
Na Linguagem Breder, é possível fazer associação de um valor a uma variável já declarada. 
</p>

<p>
No exemplo abaixo, será construído uma classe com 1 método associando uma variável :
</p>
<pre>
public class Test {
  public static void main(IList&lt;String&gt; args) {
    Number i = 1;
    i = 2;
  }
}
</pre>

<p>
No exemplo abaixo, será construído uma classe com 1 método associando duas variável :
</p>
<pre>
public class Test {
  public static void main(IList&lt;String&gt; args) {
    Number i, Number u = 1, 2;
    i, u = 3, 4;
  }
}
</pre>

<p>
No exemplo abaixo, será construído uma classe com 1 método associando três variável :
</p>
<pre>
public class Test {
  public static void main(IList&lt;String&gt; args) {
    Number i, String s = 1, "test";
    i, s = 3, "other";
  }
}
</pre>

<p>
No exemplo abaixo, será construído uma classe com 1 método associando duas variável, a partir do retorno de uma método :
</p>
<pre>
public class Test {
  public static Number, Number test () {
    return 1, 2;
  }
  public static void main(IList&lt;String&gt; args) {
    Number i, Number u = Test.a();
    i, u = Test.a();
  }
}
</pre>
