<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p>
Na Linguagem Breder, existe a estrutura de controle para repeti��o em fun��o do incremento ou decremento de uma vari�vel.
Al�m disso, essa estrutura pode ser usado de outra forma que ser� demonstrado em alguns exemplos.
Portanto, at� que a condi��o seja satisfeita, a repeti��o ir� acontecer.
Caso a condi��o n�o seja satisfeita, a repeti��o ser� finalizada. 
</p>

<p>
Ser� constru�do um exemplo em que ser� inicializado uma vari�vel com valor inicial 1, ser� incrementado de 1 o seu valor a cada repeti��o e a condi��o de sa�da � o valor ser maior que 10. 
No exemplo abaixo, ser� constru�do uma classe utilizando a estrutura de controle <code>For</code>.
</p>
<pre>
public class Test {
  public static void main(IList&lt;String&gt; args) {
    for (Number i = 1; i <= 10 ; i += 1) {
      Console.println("will execute 10 times");
    }
  }
}
</pre>

<p>
Ser� constru�do um exemplo em que ser� inicializado uma vari�vel com valor inicial 10, ser� decrementado de 1 o seu valor a cada repeti��o e a condi��o de sa�da � o valor ser menor que 0. 
No exemplo abaixo, ser� constru�do uma classe utilizando a estrutura de controle <code>For</code>.
</p>
<pre>
public class Test {
  public static void main(IList&lt;String&gt; args) {
    for (Number i = 10; i >= 0 ; i -= 1) {
      Console.println("will execute 10 times");
    }
  }
}
</pre>
