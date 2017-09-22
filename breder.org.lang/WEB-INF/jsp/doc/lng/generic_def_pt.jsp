<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p>
Na Linguagem Breder, é possível usufruir do recurso de Genérico.
O objetivo do Genérico, é a redução do número de <code>'casting'</code> em projetos da Linguagem Breder.
Dessa forma, o número de erros da classe ClassCastRuntimeException será reduzido, porque através do Genérico, todo <code>'casting'</code> é feita de forma segura.
</p>

<p>
Com isso, toda classe declarada, poderá especificar um conjuntos de tipos Genéricos.
A partir disso, toda vez que for necessário instanciar um objetos dessa classe, será necessário parametrizar o tipo Genérico, personalizando assim a respostas e entradas dos métodos que utilizam desse recurso.
</p>

<p>
Os tipos genéricos é parametrizado em tempo de compilação.
Porém, também é possível que o genérico seja realizado em tempo de execução, para qual quando for necessário saber qual tipo genérico foi atribuído, a Maquina Virtual saiba responder. 
</p>

<p>
No exemplo abaixo, será construído uma classe não que utiliza a estrutura de Genérico.
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
No exemplo abaixo, será construído uma classe que utiliza a estrutura de Genérico.
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
No exemplo abaixo, será construído uma classe que utiliza a estrutura de Genérico.
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
No exemplo, acima, a mesma classe foi usado 2 tipos diferente no momento da instanciação, fazendo com que se torne mais portátil a vários tipos. 
</p>