<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p>
Na Linguagem Breder, é possível declarar blocos de variáveis para que possa organizar os escopos das variáveis. 
O uso de blocos irá ajudar a definir o escopo de cada variável, deixando claro o limite de cada um delas.
</p>

<p>
No exemplo abaixo, será construído uma classe com 1 bloco :
</p>
<pre>
public class Test {
  public static void main(IList&lt;String&gt; args) {
    {
      Number i = 1;
      i = 2;
    }
  }
}
</pre>

<p>
Abaixo será demonstrado uma forma de organizar as variáveis em forma de escopo, dando a vantagem de reutilizar o mesmo nome da variável.
No exemplo abaixo, será construído uma classe com 3 bloco duas variável :
</p>
<pre>
public class Test {
  public static void main(IList&lt;String&gt; args) {
    {
      Number i = 1;
      i = 3;
    }
    {
      Number u = 1;
      u = 3;
    }
    {
      Number i = 1;
      i = 3;
    }
  }
}
</pre>
