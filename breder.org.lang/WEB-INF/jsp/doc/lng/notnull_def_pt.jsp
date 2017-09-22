<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p>
Como visto no tópico de declaração de métodos, é possível na declara de um parâmetro, dizer que seu valor poderá ser nulo ou não em tempo de compilação.
O objetivo dessa funcionalidade, é especificar se um parâmetro ou retorno poderá ser passado ou retornado nulo ou não.
</p>

<p>
No exemplo abaixo, será construído um método na qual pode ser passado um parâmetro nulo.
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
Porém, a Linguagem Breder permite dizer que o parâmetro obrigatoriamente deve ser não nulo, em tempo de compilação.
Para isso, basta adicionar uma palavra chave <code>'notnull'</code> atrás do tipo.
No exemplo abaixo, será construído uma método (não necessariamente estático) na qual não se pode passar um parâmetro nulo.
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
No exemplo acima, caso o usuário tente passar nulo como parâmetro, o Compilador Breder irá dizer que não será possível compilar porque foi passado um parâmetro nulo em um campo não nulo.
</p>

<p>
Com isso, fica uma dúvida. Como o Compilador Breder sabe que uma variável é nula ou não ?
A resposta é bem simples, o Compilador irá buscar a ocorrência da associação dessa variável e verificar se há possibilidade de ele ser nula ou não.
No exemplo abaixo, será construído uma método que irá testar a ocorrência desse fato.
</p>
<pre>
public class Test {
  public static void main(IList&lt;String&gt; args) {
    // Variável 'a' nunca será nula.
    Number a = 1;
    // Variável 'b' será tão não nulo quanto 'a'.
    // Portanto, não será nulo.
    Number b = a;
    // Variável 'c' será nulo.
    Number c = null;
    // Variável 'd' será tão nulo quanto 'c'.
    // Portanto, será nulo.
    Number d = c;
    // Variável 'e' poderá ser nulo.
    // Porque o retorno pode ser nulo.
    Number e = Test.a();
    // Variável 'f' não poderá ser nulo.
    // Porque não retorna nulo.
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
Como visto acima, tanto nos parâmetros, quanto nos retornos, poderá especificar se a variável será, possivelmente, nula ou não.
Para isso, deve-se usar a palavra chave 'notnull' somente na declaração de métodos e não na declaração de variáveis. 
</p>

<p>
Pode ser que esteja perguntando, porque o Compilador Breder não use o mesmo algoritimo para descobrir se os parâmetro e os retornos são nulo ou não.
Na resposta seria porque na declaração de métodos de uma classe, esse algoritimo funcionalidade, mas para os métodos nativos e métodos de uma interfaces não existe uma implementação, não deixando com que o algoritimo trabalhar.
</p>

<p>
A grande vantagem de se trabalhar com um campo não nulo ou não, seria amarrar a especificação do método, informando também se os parâmetros e retornos podem ser nulos ou não.
</p>

<p>
Caso o usuário possua uma variável que foi computada como possivelmente nula, o Compilador não irá permitir a chamada de método em cima dessa variável, porque poderá, possivelmente, ocorrer um erro de NullPointerRuntimeException caso o valor da variável seja realmente nula em tempo de execução.
</p>

<p>
Para contornar essa situação, o Compilador obrigado que seja tratado a ocorrência de uma valor nulo ou não.
No exemplo abaixo, será construído uma método que irá tratar a ocorrência de nulo.
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
Dessa forma, será muito difícil que algum erro de NullPointerRuntimeException ocorra.
</p>