<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p>
Na Linguagem Breder, existe a estrutura de tratamento e de geração de erro. 
Essa estrutura é definida na declaração de qualquer método e na sua implementação com o auxilio do compilador, caso não houver um tratamento explicito necessário. 
</p>

<p>
No exemplo abaixo, será construído uma classe que possua 1 método que pode gerar um erro do tipo <code>breder.lang.Exception</code>.
</p>
<pre>
public class Test {
  public void test () throws Exception {
  }
}
</pre>

<p>
No exemplo abaixo, será construído uma classe que possua 1 método que pode gerar um erro do tipo <code>breder.lang.Exception</code> e <code>breder.lang.RuntimeException</code>.
</p>
<pre>
public class Test {
  public void test () 
    throws Exception, RuntimeException {
  }
}
</pre>

<p>
Abaixo ocorrerá um erro caso o parâmetro passado por nulo. Com isso, para se lançar um erro, basta utilizar da palavra chave <code>throw</code> e indicar um objeto para ser lançado.
No exemplo abaixo, será construído uma classe que possua 1 método que pode gerar um erro do tipo <code>breder.lang.Exception</code> e <code>breder.lang.RuntimeException</code>.
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
O mesmo exemplo modificando para algo mais real, aonde o parâmetro é testado primeiro e depois é feito os cálculos de retorno.
No exemplo abaixo, será construído uma classe que possua 1 método que pode gerar um erro do tipo <code>breder.lang.Exception</code> e <code>breder.lang.RuntimeException</code>.
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
Para se chamar um método que pode ocorrer um erro, é preciso está sempre tratando, caso ocorra. 
Portanto, sempre que for chamar um método que pode ocorrer algum possível erro, será precisa definir um escopo de tratamento de erro utilizando a palavra chave <code>try</code> aonde será definido o tratamento do erro especifico através da palavra chave <code>catch</code>.
No exemplo abaixo, será construído uma classe que possua 1 método que pode gerar um erro do tipo <code>breder.lang.Exception</code> e <code>breder.lang.RuntimeException</code>.
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
O mesmo código com o parâmetro não nulo. 
Cabe comentar que todo erro vindo da classe <code>breder.lang.RuntimeException</code>, não precisa tratar explicitamente.
Portanto, no exemplo abaixo não será tratado o erro de <code>breder.lang.RuntimeException</code>.
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
Caso quando for chamar o método não queira tratar o erro no momento que estiver, será possível delegar o tratamento para quem o chamou anteriormente.
No exemplo abaixo, será construído uma classe que possua 1 método que pode gerar um erro do tipo <code>breder.lang.Exception</code>.
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

