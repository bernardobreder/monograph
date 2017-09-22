<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p>
Na Linguagem Breder, existe a estrutura de controle para realizar um loop de um bloco de comandos a partir de uma condi��o.
Portanto, dado uma condi��o, � poss�vel atribuir a execu��o de um bloco de comandos toda vez que a condi��o for satisfeita. 
Caso a condi��o n�o seja satisfeita, a estrutura de controle ser� finalizada. 
A diferen�a do <code>repeat</code> para o <code>while</code> � que o <code>while</code> primeiro testa a condi��o para depois executar o bloco. 
J� o <code>repeat</code> executa primeiro o bloco de comandos e depois faz o teste da condi��o.   
</p>

<p>
No exemplo abaixo, ser� constru�do uma classe utilizando a estrutura de controle <code>Repeat</code> para uma condi��o simples, utilizando o operador de igualdade. 
Nesse exemplo, o loop ser� executado infinitamente pois n�o se est� prevendo uma condi��o de sa�da.
</p>
<pre>
public class Test {
  public static void main(IList<String> args) {
    repeat {
      Console.println("will execute infinity");
    } while (1 == 1);
  }
}
</pre>

<p>
No exemplo abaixo, ser� constru�do uma classe utilizando a estrutura de controle <code>Repeat</code> para uma condi��o simples falsa, utilizando o operador de igualdade. 
Nesse exemplo, o loop n�o ser� executado devido a condi��o ser falsa.
</p>
<pre>
public class Test {
  public static void main(IList&lt;String&gt; args) {
    repeat {
      Console.println("will execute one time");
    } while (1 != 1);
  }
}
</pre>

<p>
No exemplo abaixo, ser� constru�do uma classe utilizando a estrutura de controle <code>Repeat</code> para uma condi��o falsa, utilizando o operador de igualdade. 
Nesse exemplo, o loop ser� executado 1 vez devido a condi��o ser falsa.
</p>
<pre>
public class Test {
  public static void main(IList&lt;String&gt; args) {
    Integer i = 1;
    repeat {
      Console.println("will execute one time");
    } while (i != 1);
  }
}
</pre>

<p>
No exemplo abaixo, ser� constru�do uma classe utilizando a estrutura de controle <code>While</code> para uma condi��o, utilizando o operador de igualdade. 
Nesse exemplo, o loop n�o ser� executado 1 vez.
</p>
<pre>
public class Test {
  public static void main(IList&lt;String&gt; args) {
    Integer i = 0;
    repeat {
      Console.println("will execute one time");
      i = i + 1;
    } while (i != 1);
  }
}
</pre>
