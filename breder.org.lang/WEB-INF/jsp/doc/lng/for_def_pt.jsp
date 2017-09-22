<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p>
Na Linguagem Breder, existe a estrutura de controle para repetição em função do incremento ou decremento de uma variável.
Além disso, essa estrutura pode ser usado de outra forma que será demonstrado em alguns exemplos.
Portanto, até que a condição seja satisfeita, a repetição irá acontecer.
Caso a condição não seja satisfeita, a repetição será finalizada. 
</p>

<p>
Será construído um exemplo em que será inicializado uma variável com valor inicial 1, será incrementado de 1 o seu valor a cada repetição e a condição de saída é o valor ser maior que 10. 
No exemplo abaixo, será construído uma classe utilizando a estrutura de controle <code>For</code>.
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
Será construído um exemplo em que será inicializado uma variável com valor inicial 10, será decrementado de 1 o seu valor a cada repetição e a condição de saída é o valor ser menor que 0. 
No exemplo abaixo, será construído uma classe utilizando a estrutura de controle <code>For</code>.
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
