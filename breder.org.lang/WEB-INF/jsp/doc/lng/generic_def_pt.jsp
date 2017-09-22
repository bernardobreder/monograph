<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p>
Na Linguagem Breder, � poss�vel usufruir do recurso de Gen�rico.
O objetivo do Gen�rico, � a redu��o do n�mero de <code>'casting'</code> em projetos da Linguagem Breder.
Dessa forma, o n�mero de erros da classe ClassCastRuntimeException ser� reduzido, porque atrav�s do Gen�rico, todo <code>'casting'</code> � feita de forma segura.
</p>

<p>
Com isso, toda classe declarada, poder� especificar um conjuntos de tipos Gen�ricos.
A partir disso, toda vez que for necess�rio instanciar um objetos dessa classe, ser� necess�rio parametrizar o tipo Gen�rico, personalizando assim a respostas e entradas dos m�todos que utilizam desse recurso.
</p>

<p>
Os tipos gen�ricos � parametrizado em tempo de compila��o.
Por�m, tamb�m � poss�vel que o gen�rico seja realizado em tempo de execu��o, para qual quando for necess�rio saber qual tipo gen�rico foi atribu�do, a Maquina Virtual saiba responder. 
</p>

<p>
No exemplo abaixo, ser� constru�do uma classe n�o que utiliza a estrutura de Gen�rico.
</p>
<pre>
public class Test {
  private Number i;
  public void set(Number i) {
    this.i = i;
  }
  public Number get() {
    return this.i;
  }
  public static void main(IList&lt;String&gt; args) {
    Test test = new Test();
    test.setI(1);
    Console.println(test.getI()); 
  }
}
</pre>

<p>
No exemplo abaixo, ser� constru�do uma classe que utiliza a estrutura de Gen�rico.
</p>
<pre>
public class Test&lt;E&gt; {
  private E i;
  public void set(E i) {
    this.i = i;
  }
  public E get() {
    return this.i;
  }
  public static void main(IList&lt;String&gt; args) {
    Test&lt;Number&gt; test = new Test&lt;Number&gt;();
    test.setI(1);
    Console.println(test.getI()); 
  }
}
</pre>

<p>
No exemplo abaixo, ser� constru�do uma classe que utiliza a estrutura de Gen�rico.
</p>
<pre>
public class Test&lt;E&gt; {
  private E i;
  public void set(E i) {
    this.i = i;
  }
  public E get() {
    return this.i;
  }
  public static void main(IList&lt;String&gt; args) {
    Test&lt;Number&gt; testA = new Test&lt;Number&gt;();
    testA.setI(1);
    Console.println(testA.getI());
    Test&lt;String&gt; testB = new Test&lt;String&gt;();
    testB.setI("test");
    Console.println(testB.getI()); 
  }
}
</pre>

<p>
No exemplo, acima, a mesma classe foi usado 2 tipos diferente no momento da instancia��o, fazendo com que se torne mais port�til a v�rios tipos. 
</p>