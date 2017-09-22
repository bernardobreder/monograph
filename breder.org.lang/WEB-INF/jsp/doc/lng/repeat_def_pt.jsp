<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p>
Na Linguagem Breder, existe a estrutura de controle para realizar um loop de um bloco de comandos a partir de uma condição.
Portanto, dado uma condição, é possível atribuir a execução de um bloco de comandos toda vez que a condição for satisfeita. 
Caso a condição não seja satisfeita, a estrutura de controle será finalizada. 
A diferença do <code>repeat</code> para o <code>while</code> é que o <code>while</code> primeiro testa a condição para depois executar o bloco. 
Já o <code>repeat</code> executa primeiro o bloco de comandos e depois faz o teste da condição.   
</p>

<p>
No exemplo abaixo, será construído uma classe utilizando a estrutura de controle <code>Repeat</code> para uma condição simples, utilizando o operador de igualdade. 
Nesse exemplo, o loop será executado infinitamente pois não se está prevendo uma condição de saída.
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
No exemplo abaixo, será construído uma classe utilizando a estrutura de controle <code>Repeat</code> para uma condição simples falsa, utilizando o operador de igualdade. 
Nesse exemplo, o loop não será executado devido a condição ser falsa.
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
No exemplo abaixo, será construído uma classe utilizando a estrutura de controle <code>Repeat</code> para uma condição falsa, utilizando o operador de igualdade. 
Nesse exemplo, o loop será executado 1 vez devido a condição ser falsa.
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
No exemplo abaixo, será construído uma classe utilizando a estrutura de controle <code>While</code> para uma condição, utilizando o operador de igualdade. 
Nesse exemplo, o loop não será executado 1 vez.
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
