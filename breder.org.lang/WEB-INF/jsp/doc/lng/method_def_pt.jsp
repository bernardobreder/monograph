<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p>
Um m�todo ou uma opera��o � uma a��o que um objeto pode fazer, a partir de suas caracter�sticas e seus campos.
Em um m�todo, pode ser passado diversos par�metros de diversas classes.
Al�m disso, um m�todo pode retorna tamb�m diversos objetos. 
</p>

<p>
O objetivo de um m�todo � gerar uma mudan�a de estado do objeto ou simplesmente fazer um processamento e retornar alguns objetos.
</p>

<p>
Um m�todo pode estar sendo implementada de duas forma poss�vel. 
A forma mais segura e produtiva � implementar o m�todo utilizando a Linguagem Breder.
</p>

<p>
Por�m, a outra forma de se implementar um m�todo � sendo nativamente, utilizando-se de uma Linguagem Compilada.
Os m�todos implementados nativamente, s�o implementados em uma Linguagem do tipo C Ansi na qual � representado em um arquivo de biblioteca din�mica.
A grande vantagem de se implementar nativamente � o ganho com a performance, que � muito superior, por�m perde muito na seguran�a de execu��o.
</p>

<p>
No exemplo abaixo, ser� constru�do uma classe com 1 m�todo de nome <code>'test'</code> que n�o retorna nenhum objeto e n�o possui nenhum par�metro :
</p>
<pre>
public class Test {
  public void test () {
  }
}
</pre>

<p>
No exemplo abaixo, ser� constru�do uma classe com 1 m�todo de nome <code>'test'</code> que n�o retorna nenhum objeto e recebe 1 par�metro da classe <code>'breder.lang.Number'</code> :
</p>
<pre>
public class Test {
  public void test (Number x) {
  }
}
</pre>

<p>
No exemplo abaixo, ser� constru�do uma classe com 1 m�todo de nome <code>'test'</code> que retorna 1 objeto da classe <code>'breder.lang.Number'</code> e recebe 1 par�metro da classe <code>'breder.lang.Number'</code> :
</p>
<pre>
public class Test {
  public Number test (Number x) {
    return x;
  }
}
</pre>

<p>
No exemplo abaixo, ser� constru�do uma classe com 1 m�todo de nome <code>'test'</code> que retorna 2 objetos da classe <code>'breder.lang.Number'</code> e recebe 1 par�metro da classe <code>'breder.lang.Number'</code> :
</p>
<pre>
public class Test {
  public Number, Number test (Number x) {
    return x, x;
  }
}
</pre>

<p>
No exemplo abaixo, ser� constru�do uma classe com 1 m�todo de nome <code>'test'</code> que retorna 2 objetos da classe <code>'breder.lang.Number'</code> e recebe 2 par�metro da classe <code>'breder.lang.Number'</code> :
</p>
<pre>
public class Test {
  public Number, Number test (Number x) {
    return x, x;
  }
  public Number, Number test (Number x, Number y) {
    return x, y;
  }
}
</pre>

<p>
A Linguagem Breder utiliza do polimorfismo para escolher qual m�todo ser� executado atrav�s de uma rotina de chamada de m�todo.
Para o exemplo acima, se a chamada conter somente um par�metro, o primeiro m�todo ser� chamado.
Caso for passado dois par�metros, o segundo m�todo ser� executado.
Com isso, em uma classe, pode-se ter diversos m�todos com o mesmo nome, na qual se diferenciam somente no n�mero e tipo de argumento.
</p>

<p>
No exemplo abaixo, ser� constru�do uma classe com 2 m�todo de nome <code>'getX'</code> e <code>'setX'</code> que tem a fun��o de guardar um valor e retornar o que foi guardado :
</p>
<pre>
public class Test {
  private Number x;
  public void setX (Number x) {
    this.x = x;
  }
  public Number getX () {
    return this.x;
  }
}
</pre>

<p>
Na Linguagem Breder, � poss�vel declarar um m�todo est�tico. Esse m�todo ser� um m�todo da classe e n�o do objeto, fazendo com que n�o seja necess�rio de um objeto para chamar o m�todo, e sim da classe que o perten�a.
No exemplo abaixo, ser� constru�do uma classe com 1 m�todo de nome <code>'test'</code> que retorna 2 objetos da classe <code>'breder.lang.Number'</code>, recebe 1 par�metro da classe <code>'breder.lang.Number'</code> e que ser� est�tico :
</p>
<pre>
public class Test {
  public static Number, Number test (Number x) {
    return x, x;
  }
  public static void main(IList&lt;String&gt; args) {
    Test.test(1);
  }
}
</pre>

<p>
No exemplo abaixo, ser� demonstrado o uso do polimorfismo :
</p>
<pre>
public class Test {
  public static void test (Number x) {
  	Console.println("A");
  }
  public static void test (Number x, Number y) {
    Console.println("B");
  }
  public static void main(IList&lt;String&gt; args) {
    Test.test(1);
    Test.test(1,2);
  }
}
</pre>