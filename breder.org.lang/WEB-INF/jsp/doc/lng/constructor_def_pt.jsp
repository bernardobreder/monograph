<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p>
Para se criar uma objeto de uma classe, é preciso instanciar-lo. 
No momento da instanciação, a Linguagem Breder sempre irá chamar o construtor dela, mesmo que ela não existe.
</p>
<p>
O construtor tem o papel de inicializar o objeto para o seu funcionamento. 
É falha de programação a criação de métodos de inicialização do objeto que não seja o próprio construtor. 
Portanto, depois que o construtor for executado, o objeto já deve ter inicializado por completo. 
Com isso, todo construtor deve exigir tudo que for necessário para que o objeto possa ser inicializado.
Para se declarar uma construtor para uma classe na Linguagem Breder, basta declarar um método sem retorno e que tenha o mesmo nome da classe.
</p>

<p>
No exemplo abaixo, será construído uma classe com 1 construtor que não recebe nenhum parâmetro :
</p>
<pre>
public class Test {
  public Test () {}
}
</pre>

<p>
No exemplo abaixo, será construído uma classe com 1 construtor que recebe 1 parâmetro do tipo <code>'breder.lang.Number'</code> :
</p>
<pre>
public class Test {
  public Test (Number x) {}
}
</pre>

<p>
No exemplo abaixo, será construído uma classe com 2 construtor :
</p>
<pre>
public class Test {
  public Test (Number x) {}
  public Test (Number x, Number y) {}
}
</pre>

<p>
No exemplo abaixo, será construído uma classe com 2 construtor e 2 campos :
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
Na Linguagem Breder, uma interface pode define uma construtor padrão para todas as implementações dela. 
Esse recurso torna-se interessante porque o usuário da API só precisará saber como funciona a interface, não precisando se preocupar como e com que construtor será implementado.
</p>

<p>
No exemplo abaixo, será construído uma interface com 1 construtor e uma classe concreta implementado o construtor :
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
No exemplo acima, a classe precisa definir um construtor declarado pela interface, mas nada impede que a classe possua além do construtor da classe, ter outras também.
</p>