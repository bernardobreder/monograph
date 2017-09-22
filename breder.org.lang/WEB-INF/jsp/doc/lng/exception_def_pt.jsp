<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p>
Na Linguagem Breder, existe a estrutura de tratamento e de gera��o de erro. 
Essa estrutura � definida na declara��o de qualquer m�todo e na sua implementa��o com o auxilio do compilador, caso n�o houver um tratamento explicito necess�rio. 
</p>

<p>
No exemplo abaixo, ser� constru�do uma classe que possua 1 m�todo que pode gerar um erro do tipo <code>breder.lang.Exception</code>.
</p>
<pre>
public class Test {
  public void test () throws Exception {
  }
}
</pre>

<p>
No exemplo abaixo, ser� constru�do uma classe que possua 1 m�todo que pode gerar um erro do tipo <code>breder.lang.Exception</code> e <code>breder.lang.RuntimeException</code>.
</p>
<pre>
public class Test {
  public void test () 
    throws Exception, RuntimeException {
  }
}
</pre>

<p>
Abaixo ocorrer� um erro caso o par�metro passado por nulo. Com isso, para se lan�ar um erro, basta utilizar da palavra chave <code>throw</code> e indicar um objeto para ser lan�ado.
No exemplo abaixo, ser� constru�do uma classe que possua 1 m�todo que pode gerar um erro do tipo <code>breder.lang.Exception</code> e <code>breder.lang.RuntimeException</code>.
</p>
<pre>
public class Test {
  public void test (Number i) 
    throws Exception, RuntimeException {
    if (i == null) {
      throw new Exception();
    }
  }
}
</pre>

<p>
O mesmo exemplo modificando para algo mais real, aonde o par�metro � testado primeiro e depois � feito os c�lculos de retorno.
No exemplo abaixo, ser� constru�do uma classe que possua 1 m�todo que pode gerar um erro do tipo <code>breder.lang.Exception</code> e <code>breder.lang.RuntimeException</code>.
</p>
<pre>
public class Test {
  public Number test (Number i) 
    throws Exception, RuntimeException {
    if (i == null) {
      throw new Exception();
    }
    return i;
  }
}
</pre>

<p>
Para se chamar um m�todo que pode ocorrer um erro, � preciso est� sempre tratando, caso ocorra. 
Portanto, sempre que for chamar um m�todo que pode ocorrer algum poss�vel erro, ser� precisa definir um escopo de tratamento de erro utilizando a palavra chave <code>try</code> aonde ser� definido o tratamento do erro especifico atrav�s da palavra chave <code>catch</code>.
No exemplo abaixo, ser� constru�do uma classe que possua 1 m�todo que pode gerar um erro do tipo <code>breder.lang.Exception</code> e <code>breder.lang.RuntimeException</code>.
</p>
<pre>
public class Test {
  public static Number test (Number i) 
    throws Exception, RuntimeException {
    if (i == null) {
      throw new Exception();
    }
    return i;
  }
  public static void main(IList&lt;String&gt; args) {
    try {
      Test.test(null);
    } catch (Exception e) {
      Console.println("will execute");
    } catch (RuntimeException e) {
      Console.println("will not execute");
    }
  }
}
</pre>

<p>
O mesmo c�digo com o par�metro n�o nulo. 
Cabe comentar que todo erro vindo da classe <code>breder.lang.RuntimeException</code>, n�o precisa tratar explicitamente.
Portanto, no exemplo abaixo n�o ser� tratado o erro de <code>breder.lang.RuntimeException</code>.
</p>
<pre>
public class Test {
  public static Number test (Number i) 
    throws Exception, RuntimeException {
    if (i == null) {
      throw new Exception();
    }
    return i;
  }
  public static void main(IList&lt;String&gt; args) {
    try {
      Test.test(1);
    } catch (Exception e) {
      Console.println("will not execute");
    }
  }
}
</pre>

<p>
Caso quando for chamar o m�todo n�o queira tratar o erro no momento que estiver, ser� poss�vel delegar o tratamento para quem o chamou anteriormente.
No exemplo abaixo, ser� constru�do uma classe que possua 1 m�todo que pode gerar um erro do tipo <code>breder.lang.Exception</code>.
</p>
<pre>
public class Test {
  public static Number test (Number i) 
    throws Exception {
    if (i == null) {
      throw new Exception();
    }
    return i;
  }
  public static void test2 () throws Exception {
    Test.test(null);
  }
  public static void main(IList&lt;String&gt; args) {
    try {
      Test.test2();
    } catch (Exception e) {
      Console.println("will execute");
    }
  }
}
</pre>

