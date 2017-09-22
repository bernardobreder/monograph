<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p>
Para se criar uma objeto de uma classe, � preciso instanciar-lo. 
No momento da instancia��o, a Linguagem Breder sempre ir� chamar o construtor dela, mesmo que ela n�o existe.
</p>
<p>
O construtor tem o papel de inicializar o objeto para o seu funcionamento. 
� falha de programa��o a cria��o de m�todos de inicializa��o do objeto que n�o seja o pr�prio construtor. 
Portanto, depois que o construtor for executado, o objeto j� deve ter inicializado por completo. 
Com isso, todo construtor deve exigir tudo que for necess�rio para que o objeto possa ser inicializado.
Para se declarar uma construtor para uma classe na Linguagem Breder, basta declarar um m�todo sem retorno e que tenha o mesmo nome da classe.
</p>

<p>
No exemplo abaixo, ser� constru�do uma classe com 1 construtor que n�o recebe nenhum par�metro :
</p>
<pre>
public class Test {
  public Test () {}
}
</pre>

<p>
No exemplo abaixo, ser� constru�do uma classe com 1 construtor que recebe 1 par�metro do tipo <code>'breder.lang.Number'</code> :
</p>
<pre>
public class Test {
  public Test (Number x) {}
}
</pre>

<p>
No exemplo abaixo, ser� constru�do uma classe com 2 construtor :
</p>
<pre>
public class Test {
  public Test (Number x) {}
  public Test (Number x, Number y) {}
}
</pre>

<p>
No exemplo abaixo, ser� constru�do uma classe com 2 construtor e 2 campos :
</p>
<pre>
public class Test {
  private Number x;
  private Number y;
  public Test (Number x) {
    this.x = x;
    this.y = 0;
  }
  public Test (Number x, Number y) {
    this.x = x;
    this.y = y;
  }
}
</pre>

<p>
Na Linguagem Breder, uma interface pode define uma construtor padr�o para todas as implementa��es dela. 
Esse recurso torna-se interessante porque o usu�rio da API s� precisar� saber como funciona a interface, n�o precisando se preocupar como e com que construtor ser� implementado.
</p>

<p>
No exemplo abaixo, ser� constru�do uma interface com 1 construtor e uma classe concreta implementado o construtor :
</p>
<pre>
public interface ITest {
  public ITest ();
}
public class Test {
  public Test () {}
}
</pre>
<p>
No exemplo acima, a classe precisa definir um construtor declarado pela interface, mas nada impede que a classe possua al�m do construtor da classe, ter outras tamb�m.
</p>