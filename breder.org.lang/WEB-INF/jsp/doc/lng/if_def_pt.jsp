<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p>
Na Linguagem Breder, existe a estrutura de controle para desviar a execu��o do aplicativo em fun��o de uma condi��o.
Portanto, dado uma condi��o, � poss�vel atribuir a execu��o de um bloco de comandos. 
Caso a condi��o n�o seja satisfeita, ser� poss�vel atribuir tamb�m outros blocos. 
</p>

<p>
No exemplo abaixo, ser� constru�do uma classe utilizando a estrutura de controle <code>If</code> para uma condi��o simples, utilizando o operador de igualdade.
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
No exemplo abaixo, ser� constru�do uma classe utilizando a estrutura de controle <code>If</code> e <code>Else</code> para uma condi��o simples, utilizando o operador de igualdade.
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
No exemplo abaixo, ser� constru�do uma classe utilizando a estrutura de controle <code>If</code> e <code>Else</code> para uma condi��o simples, utilizando o operador de diferen�a.
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
No exemplo abaixo, ser� constru�do uma classe utilizando a estrutura de controle <code>If</code> e <code>Else</code> para uma condi��o, utilizando o operador de igualdade.
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
No exemplo abaixo, ser� constru�do uma classe utilizando a estrutura de controle <code>If</code>, <code>Else</code> e <code>Elseif</code> para uma condi��o, utilizando o operador de igualdade.
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
No exemplo abaixo, ser� constru�do uma classe utilizando a estrutura de controle <code>If</code>, <code>Else</code> e <code>Elseif</code> para uma condi��o, utilizando o operador de igualdade.
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