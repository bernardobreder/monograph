<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p>
Na Linguagem Breder, existe a estrutura de controle para desviar a execução do aplicativo em função de uma condição.
Portanto, dado uma condição, é possível atribuir a execução de um bloco de comandos. 
Caso a condição não seja satisfeita, será possível atribuir também outros blocos. 
</p>

<p>
No exemplo abaixo, será construído uma classe utilizando a estrutura de controle <code>If</code> para uma condição simples, utilizando o operador de igualdade.
</p>
<pre>
public class Test {
  public static void main(IList&lt;String&gt; args) {
    if (1 == 1) {
      Console.println("will execute");
    }
  }
}
</pre>

<p>
No exemplo abaixo, será construído uma classe utilizando a estrutura de controle <code>If</code> e <code>Else</code> para uma condição simples, utilizando o operador de igualdade.
</p>
<pre>
public class Test {
  public static void main(IList&lt;String&gt; args) {
    if (1 == 1) {
      Console.println("will execute");
    } else {
      Console.println("will not execute");
    }
  }
}
</pre>

<p>
No exemplo abaixo, será construído uma classe utilizando a estrutura de controle <code>If</code> e <code>Else</code> para uma condição simples, utilizando o operador de diferença.
</p>
<pre>
public class Test {
  public static void main(IList&lt;String&gt; args) {
    if (1 != 1) {
      Console.println("will not execute");
    } else {
      Console.println("will execute");
    }
  }
}
</pre>

<p>
No exemplo abaixo, será construído uma classe utilizando a estrutura de controle <code>If</code> e <code>Else</code> para uma condição, utilizando o operador de igualdade.
</p>
<pre>
public class Test {
  public static void main(IList&lt;String&gt; args) {
    Number i = 1;
    if (i == 1) {
      Console.println("will execute");
    } else {
      Console.println("will not execute");
    }
  }
}
</pre>

<p>
No exemplo abaixo, será construído uma classe utilizando a estrutura de controle <code>If</code>, <code>Else</code> e <code>Elseif</code> para uma condição, utilizando o operador de igualdade.
</p>
<pre>
public class Test {
  public static void main(IList&lt;String&gt; args) {
    Number i = 1;
    if (i == 0) {
      Console.println("will not execute");
    } elseif (i == 1) {
      Console.println("will execute");
    } else {
      Console.println("will not execute");
    }
  }
}
</pre>

<p>
No exemplo abaixo, será construído uma classe utilizando a estrutura de controle <code>If</code>, <code>Else</code> e <code>Elseif</code> para uma condição, utilizando o operador de igualdade.
</p>
<pre>
public class Test {
  public static void main(IList&lt;String&gt; args) {
    Number i = 1;
    if (i == -1) {
      Console.println("will not execute");
    } elseif (i == 0) {
      Console.println("will not execute");
    } elseif (i == 1) {
      Console.println("will execute");
    } else {
      Console.println("will not execute");
    }
  }
}
</pre>