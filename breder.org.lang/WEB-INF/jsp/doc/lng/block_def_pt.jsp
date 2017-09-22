<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p>
Na Linguagem Breder, � poss�vel declarar blocos de vari�veis para que possa organizar os escopos das vari�veis. 
O uso de blocos ir� ajudar a definir o escopo de cada vari�vel, deixando claro o limite de cada um delas.
</p>

<p>
No exemplo abaixo, ser� constru�do uma classe com 1 bloco :
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
Abaixo ser� demonstrado uma forma de organizar as vari�veis em forma de escopo, dando a vantagem de reutilizar o mesmo nome da vari�vel.
No exemplo abaixo, ser� constru�do uma classe com 3 bloco duas vari�vel :
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
