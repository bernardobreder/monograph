<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p>
Um método ou uma operação é uma ação que um objeto pode fazer, a partir de suas características e seus campos.
Em um método, pode ser passado diversos parâmetros de diversas classes.
Além disso, um método pode retorna também diversos objetos. 
</p>

<p>
O objetivo de um método é gerar uma mudança de estado do objeto ou simplesmente fazer um processamento e retornar alguns objetos.
</p>

<p>
Um método pode estar sendo implementada de duas forma possível. 
A forma mais segura e produtiva é implementar o método utilizando a Linguagem Breder.
</p>

<p>
Porém, a outra forma de se implementar um método é sendo nativamente, utilizando-se de uma Linguagem Compilada.
Os métodos implementados nativamente, são implementados em uma Linguagem do tipo C Ansi na qual é representado em um arquivo de biblioteca dinâmica.
A grande vantagem de se implementar nativamente é o ganho com a performance, que é muito superior, porém perde muito na segurança de execução.
</p>

<p>
No exemplo abaixo, será construído uma classe com 1 método de nome <code>'test'</code> que não retorna nenhum objeto e não possui nenhum parâmetro :
</p>
<pre>
public class Test {
  public void test () {
  }
}
</pre>

<p>
No exemplo abaixo, será construído uma classe com 1 método de nome <code>'test'</code> que não retorna nenhum objeto e recebe 1 parâmetro da classe <code>'breder.lang.Number'</code> :
</p>
<pre>
public class Test {
  public void test (Number x) {
  }
}
</pre>

<p>
No exemplo abaixo, será construído uma classe com 1 método de nome <code>'test'</code> que retorna 1 objeto da classe <code>'breder.lang.Number'</code> e recebe 1 parâmetro da classe <code>'breder.lang.Number'</code> :
</p>
<pre>
public class Test {
  public Number test (Number x) {
    return x;
  }
}
</pre>

<p>
No exemplo abaixo, será construído uma classe com 1 método de nome <code>'test'</code> que retorna 2 objetos da classe <code>'breder.lang.Number'</code> e recebe 1 parâmetro da classe <code>'breder.lang.Number'</code> :
</p>
<pre>
public class Test {
  public Number, Number test (Number x) {
    return x, x;
  }
}
</pre>

<p>
No exemplo abaixo, será construído uma classe com 1 método de nome <code>'test'</code> que retorna 2 objetos da classe <code>'breder.lang.Number'</code> e recebe 2 parâmetro da classe <code>'breder.lang.Number'</code> :
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
A Linguagem Breder utiliza do polimorfismo para escolher qual método será executado através de uma rotina de chamada de método.
Para o exemplo acima, se a chamada conter somente um parâmetro, o primeiro método será chamado.
Caso for passado dois parâmetros, o segundo método será executado.
Com isso, em uma classe, pode-se ter diversos métodos com o mesmo nome, na qual se diferenciam somente no número e tipo de argumento.
</p>

<p>
No exemplo abaixo, será construído uma classe com 2 método de nome <code>'getX'</code> e <code>'setX'</code> que tem a função de guardar um valor e retornar o que foi guardado :
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
Na Linguagem Breder, é possível declarar um método estático. Esse método será um método da classe e não do objeto, fazendo com que não seja necessário de um objeto para chamar o método, e sim da classe que o pertença.
No exemplo abaixo, será construído uma classe com 1 método de nome <code>'test'</code> que retorna 2 objetos da classe <code>'breder.lang.Number'</code>, recebe 1 parâmetro da classe <code>'breder.lang.Number'</code> e que será estático :
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
No exemplo abaixo, será demonstrado o uso do polimorfismo :
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