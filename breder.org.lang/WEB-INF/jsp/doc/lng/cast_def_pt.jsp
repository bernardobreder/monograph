<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p>
Na Linguagem Breder, é possível utilizar do recurso de <code>'cast'</code> para substituir um tipo de um objeto para outro.
O objetivo do <code>'cast'</code> é substituir o tipo de um objeto para outro, tendo a certeza de que o outro tipo seja compatível a hierarquia do tipo anterior.
Caso a incompatibilidade ocorra, será lançado um erro da classe <code>ClassCastRuntimeException</code>.
</p>

<p>
Por exemplo, é possível, de forma segura, fazer um <code>cast</code> da classe <code>breder.lang.Number</code> para a classe <code>breder.lang.Object</code>.
Porém é possível, de forma insegura, fazer um <code>cast</code> da classe <code>breder.lang.Object</code> para a classe <code>breder.lang.Number</code>.
</p>

<p>
O <code>cast</code> em tempo de compilação, somente determina se a operação é segura ou não.
Caso seja segura, não será necessário realizar o <code>cast</code> explicito.
Porém, caso seja insegura, o <code>cast</code> deve ser posto de forma explicita, na qual deverá especificar a classe.
</p>

<p>
No exemplo abaixo, será construído uma classe que utiliza uma forma segura de <code>cast</code>.
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
No exemplo abaixo, será construído uma classe que utiliza uma forma insegura de <code>cast</code>.
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
No exemplo acima, em tempo de execução, caso o <code>cast</code> da variável de nome <code>i</code> não pertença a hierarquia de classes da classe Number, um erro da classe ClassCastRuntimeException irá ocorrer.
</p>

<p>
No exemplo abaixo, será construído uma classe que utiliza uma forma insegura de <code>cast</code> em que irá ser lançado um erro da classe ClassCastRuntimeException.
</p>
<pre>
public class Test {
  public static void main(IList&lt;String&gt; args) {
    Object i = "test";
    Number o = (Number)i;
  }
}
</pre>