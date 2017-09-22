<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p>
Na Linguagem Breder, � poss�vel utilizar do recurso de <code>'cast'</code> para substituir um tipo de um objeto para outro.
O objetivo do <code>'cast'</code> � substituir o tipo de um objeto para outro, tendo a certeza de que o outro tipo seja compat�vel a hierarquia do tipo anterior.
Caso a incompatibilidade ocorra, ser� lan�ado um erro da classe <code>ClassCastRuntimeException</code>.
</p>

<p>
Por exemplo, � poss�vel, de forma segura, fazer um <code>cast</code> da classe <code>breder.lang.Number</code> para a classe <code>breder.lang.Object</code>.
Por�m � poss�vel, de forma insegura, fazer um <code>cast</code> da classe <code>breder.lang.Object</code> para a classe <code>breder.lang.Number</code>.
</p>

<p>
O <code>cast</code> em tempo de compila��o, somente determina se a opera��o � segura ou n�o.
Caso seja segura, n�o ser� necess�rio realizar o <code>cast</code> explicito.
Por�m, caso seja insegura, o <code>cast</code> deve ser posto de forma explicita, na qual dever� especificar a classe.
</p>

<p>
No exemplo abaixo, ser� constru�do uma classe que utiliza uma forma segura de <code>cast</code>.
</p>
<pre>
public class Test {
  public static void main(IList&lt;String&gt; args) {
    Number i = 1;
    Object o = i;
  }
}
</pre>

<p>
No exemplo abaixo, ser� constru�do uma classe que utiliza uma forma insegura de <code>cast</code>.
</p>
<pre>
public class Test {
  public static void main(IList&lt;String&gt; args) {
    Object i = 1;
    Number o = (Number)i;
  }
}
</pre>

<p>
No exemplo acima, em tempo de execu��o, caso o <code>cast</code> da vari�vel de nome <code>i</code> n�o perten�a a hierarquia de classes da classe Number, um erro da classe ClassCastRuntimeException ir� ocorrer.
</p>

<p>
No exemplo abaixo, ser� constru�do uma classe que utiliza uma forma insegura de <code>cast</code> em que ir� ser lan�ado um erro da classe ClassCastRuntimeException.
</p>
<pre>
public class Test {
  public static void main(IList&lt;String&gt; args) {
    Object i = "test";
    Number o = (Number)i;
  }
}
</pre>