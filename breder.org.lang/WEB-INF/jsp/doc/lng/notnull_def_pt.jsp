<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p>
Como visto no t�pico de declara��o de m�todos, � poss�vel na declara de um par�metro, dizer que seu valor poder� ser nulo ou n�o em tempo de compila��o.
O objetivo dessa funcionalidade, � especificar se um par�metro ou retorno poder� ser passado ou retornado nulo ou n�o.
</p>

<p>
No exemplo abaixo, ser� constru�do um m�todo na qual pode ser passado um par�metro nulo.
</p>
<pre>
public class Test {
  public static void test (Number x) {
    Console.println(x.toString());
  }
  public static void main(IList&lt;String&gt; args) {
    Test.test(null);
  }
}
</pre>

<p>
Por�m, a Linguagem Breder permite dizer que o par�metro obrigatoriamente deve ser n�o nulo, em tempo de compila��o.
Para isso, basta adicionar uma palavra chave <code>'notnull'</code> atr�s do tipo.
No exemplo abaixo, ser� constru�do uma m�todo (n�o necessariamente est�tico) na qual n�o se pode passar um par�metro nulo.
</p>
<pre>
public class Test {
  public static void test (notnull Number x) {
    Console.println(x.toString());
  }
  public static void main(IList&lt;String&gt; args) {
    Test.test(1);
  }
}
</pre>

<p>
No exemplo acima, caso o usu�rio tente passar nulo como par�metro, o Compilador Breder ir� dizer que n�o ser� poss�vel compilar porque foi passado um par�metro nulo em um campo n�o nulo.
</p>

<p>
Com isso, fica uma d�vida. Como o Compilador Breder sabe que uma vari�vel � nula ou n�o ?
A resposta � bem simples, o Compilador ir� buscar a ocorr�ncia da associa��o dessa vari�vel e verificar se h� possibilidade de ele ser nula ou n�o.
No exemplo abaixo, ser� constru�do uma m�todo que ir� testar a ocorr�ncia desse fato.
</p>
<pre>
public class Test {
  public static void main(IList&lt;String&gt; args) {
    // Vari�vel 'a' nunca ser� nula.
    Number a = 1;
    // Vari�vel 'b' ser� t�o n�o nulo quanto 'a'.
    // Portanto, n�o ser� nulo.
    Number b = a;
    // Vari�vel 'c' ser� nulo.
    Number c = null;
    // Vari�vel 'd' ser� t�o nulo quanto 'c'.
    // Portanto, ser� nulo.
    Number d = c;
    // Vari�vel 'e' poder� ser nulo.
    // Porque o retorno pode ser nulo.
    Number e = Test.a();
    // Vari�vel 'f' n�o poder� ser nulo.
    // Porque n�o retorna nulo.
    Number f = Test.b();
  }
  public static Number a () {
    return null;
  }
  public static notnull Number b () {
    return 1;
  }
}
</pre>

<p>
Como visto acima, tanto nos par�metros, quanto nos retornos, poder� especificar se a vari�vel ser�, possivelmente, nula ou n�o.
Para isso, deve-se usar a palavra chave 'notnull' somente na declara��o de m�todos e n�o na declara��o de vari�veis. 
</p>

<p>
Pode ser que esteja perguntando, porque o Compilador Breder n�o use o mesmo algoritimo para descobrir se os par�metro e os retornos s�o nulo ou n�o.
Na resposta seria porque na declara��o de m�todos de uma classe, esse algoritimo funcionalidade, mas para os m�todos nativos e m�todos de uma interfaces n�o existe uma implementa��o, n�o deixando com que o algoritimo trabalhar.
</p>

<p>
A grande vantagem de se trabalhar com um campo n�o nulo ou n�o, seria amarrar a especifica��o do m�todo, informando tamb�m se os par�metros e retornos podem ser nulos ou n�o.
</p>

<p>
Caso o usu�rio possua uma vari�vel que foi computada como possivelmente nula, o Compilador n�o ir� permitir a chamada de m�todo em cima dessa vari�vel, porque poder�, possivelmente, ocorrer um erro de NullPointerRuntimeException caso o valor da vari�vel seja realmente nula em tempo de execu��o.
</p>

<p>
Para contornar essa situa��o, o Compilador obrigado que seja tratado a ocorr�ncia de uma valor nulo ou n�o.
No exemplo abaixo, ser� constru�do uma m�todo que ir� tratar a ocorr�ncia de nulo.
</p>

<pre>
public class Test {
  public static void main(IList&lt;String&gt; args) {
    Number n = Test.a();
    if (n != null) {
      Console.println("will not execute");
    } else {
      Console.println("will execute");
    }
    n = Test.b();
    if (n != null) {
      Console.println("will execute");
    } else {
      Console.println("will not execute");
    }
  }
  public static Number a () {
    return null;
  }
  public notnull Number b () {
    return 1;
  }
}
</pre>

<p>
Dessa forma, ser� muito dif�cil que algum erro de NullPointerRuntimeException ocorra.
</p>