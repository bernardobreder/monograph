<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p>
Na Linguagem Breder, é possível declarar variáveis em um bloco de comandos. 
Além disso, essa variável existirá enquanto o bloco declarado estiver existindo ainda.
</p>

<p>
No exemplo abaixo, será construído uma classe com 1 método declarando uma variável :
</p>
<pre>
public class Test {
  public static void main(IList&lt;String&gt; args) {
    Number i = 1;
  }
}
</pre>

<p>
No exemplo abaixo, será construído uma classe com 1 método declarando duas variável :
</p>
<pre>
public class Test {
  public static void main(IList&lt;String&gt; args) {
    Number i, Number u = 1, 2;
  }
}
</pre>

<p>
No exemplo abaixo, será construído uma classe com 1 método declarando três variável :
</p>
<pre>
public class Test {
  public static void main(IList&lt;String&gt; args) {
    Number i, String s = 1, "test";
  }
}
</pre>

<p>
No exemplo abaixo, será construído uma classe com 1 método declarando duas variável, a partir do retorno de uma método :
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
