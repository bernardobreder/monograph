<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p>
Na Linguagem Breder, � poss�vel fazer associa��o de um valor a uma vari�vel j� declarada. 
</p>

<p>
No exemplo abaixo, ser� constru�do uma classe com 1 m�todo associando uma vari�vel :
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
No exemplo abaixo, ser� constru�do uma classe com 1 m�todo associando duas vari�vel :
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
No exemplo abaixo, ser� constru�do uma classe com 1 m�todo associando tr�s vari�vel :
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
No exemplo abaixo, ser� constru�do uma classe com 1 m�todo associando duas vari�vel, a partir do retorno de uma m�todo :
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
