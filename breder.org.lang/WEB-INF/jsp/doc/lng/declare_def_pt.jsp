<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p>
Na Linguagem Breder, � poss�vel declarar vari�veis em um bloco de comandos. 
Al�m disso, essa vari�vel existir� enquanto o bloco declarado estiver existindo ainda.
</p>

<p>
No exemplo abaixo, ser� constru�do uma classe com 1 m�todo declarando uma vari�vel :
</p>
<pre>
public class Test {
  public static void main(IList&lt;String&gt; args) {
    Number i = 1;
  }
}
</pre>

<p>
No exemplo abaixo, ser� constru�do uma classe com 1 m�todo declarando duas vari�vel :
</p>
<pre>
public class Test {
  public static void main(IList&lt;String&gt; args) {
    Number i, Number u = 1, 2;
  }
}
</pre>

<p>
No exemplo abaixo, ser� constru�do uma classe com 1 m�todo declarando tr�s vari�vel :
</p>
<pre>
public class Test {
  public static void main(IList&lt;String&gt; args) {
    Number i, String s = 1, "test";
  }
}
</pre>

<p>
No exemplo abaixo, ser� constru�do uma classe com 1 m�todo declarando duas vari�vel, a partir do retorno de uma m�todo :
</p>
<pre>
public class Test {
  public static Number, Number test () {
    return 1, 2;
  }
  public static void main(IList&lt;String&gt; args) {
    Number i, Number u = Test.a();
  }
}
</pre>
