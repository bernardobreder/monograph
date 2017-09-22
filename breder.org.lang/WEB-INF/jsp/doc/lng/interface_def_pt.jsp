<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p>
Quando se deseja elaborar uma especifica��o de uma funcionalidade, a boa pr�tica na Linguagem Breder � especificar primeiro as interfaces necess�rias para essas funcionalidades.
Nessas interfaces, ser�o definidas todas as opera��es necess�rias para que a funcionalidade possa ser implementada de forma correta e clara.
</p>

<p>
Na constru��o das interfaces, � super importante as opera��es n�o devam considerar a poss�vel implementa��o.
Isso porque, as interfaces servem para organizar as id�ias das funcionalidades e n�o para organizar a id�ia de uma implementa��o poss�vel para a funcionalidade.
</p>

<p>
A id�ia, � que a interface sirva como uma camada que implementa uma funcionalidade.
Caso essa camada necessite me mudar a implementa��o, � importante que a interface da camada permane�a a mesma, mudando somente a forma que foi implementada.
</p>

<p>
Com essa pol�tica, a necessidade de se mudar a forma como ser� implementado n�o importa no primeiro momento.
Isso porque todos os objetos externos estar�o sempre fazendo referencia a objetos das interfaces das implementa��es espec�ficas.
</p>

<p>
Por exemplo, caso queira implementar a funcionalidade de acelerar um carro, ser� necess�rio primeiro especificar essa opera��o.
Depois de ter especificado, ser� feito a primeira implementa��o dessa interface, sendo necess�rio implementar a opera��o de acelerar.
Caso futuramente deseja mudar a forma de implementar a mesma funcionalidade, basta somente mudar a implementa��o da classe que implementou a interface, fazendo com que n�o seja necess�rio mudar o c�digo externo que faz refer�ncia a ele, pois eles est�o considerando somente o conceito geral e n�o um implementa��o especifica.
</p>

<p>
Na Linguagem Breder � poss�vel declarar uma interface que define caracter�sticas de uma API.
O objetivo da interface � definir uma API ideal para uma estrutura de forma que algu�m implemente.
Nessa Linguagem, � considerado como boa pr�tica, sempre criar uma API para um recurso ou funcionalidade, mesmo que ela s� possua uma implementa��o poss�vel.
Essa boa pr�tica ser� melhor comentada o seu motivo quando for implementado a funcionalidade de hierarquia de pacotes. 
</p>

<p>
Uma interface representa um estrutura com um conjunto de m�todos n�o implementado. 
O objetivo dela � somente armazenar as declara��es das opera��es nela atribu�da.
Caso uma classe tente implementar uma interface, ser� necess�rio implementar todas as opera��es da interface.
</p>

<p>
Deve refor�ar que toda interface deve ser criada com as funcionalidades <b>ideais</b> e <b>gen�ricas</b>, mesmo que esse ideal for dif�cil de se alcan�ar.
Isso porque toda interface deve ser de alto n�vel, escondendo toda a complexidade de implementa��o que foi necess�rio para se implementar as funcionalidades.
Al�m disso, a API deve ser o mais gen�rico poss�vel porque essa interface pode possuir diversas implementa��es, n�o podendo considerar particularidades de alguma implementa��o. 
</p>

<p>
Como padr�o da Linguagem Breder, toda interface deve come�ar o nome com a letra 'I', indicando que se trata de uma interface, para n�o atrapalhar o nome da classe de implementa��o padr�o.
Com isso, toda vez que for criar uma estrutura, ser� criado uma interface com o nome come�ando com a letra 'I' e ser� criado uma classe de implementa��o padr�o com o mesmo nome, sem a letra 'I'.
</p>

<p>
No exemplo abaixo, ser� constru�do uma interface :
</p>
<pre>
public interface ITest {}
</pre>

<p>
No exemplo abaixo, ser� constru�do uma interface com um m�todo :
</p>
<pre>
public interface ITest {
  public void testA () ;
  public Number testB (Number x, Number y);
}
</pre>

<p>
No exemplo abaixo, ser� constru�do um exemplo de interface de carro :
</p>
<pre>
public interface ICar {
  public void acelerar () ;
}
</pre>

<p>
No exemplo abaixo, ser� constru�do um exemplo de implementa��o de uma interface de carro :
</p>
<pre>
public class Car implements ICar {
  private Number speed;
  public void acelerar () {
    this.speed = this.speed + 5;
  }
}
</pre>

<p>
No exemplo abaixo, ser� constru�do um exemplo de implementa��o futura de uma interface de carro. 
Esse exemplo demonstra uma necessidade futura de mudar a implementa��o da opera��o de acelerar.
Repara que n�o foi mudado em nada o cabe�alho do m�todo, somente a sua implementa��o.
Isso porque o cabe�alho est� amarrado a interface que est� implementando. 
</p>
<pre>
public class Car implements ICar {
  private Number speed;
  public void acelerar () {
    this.speed = this.speed + 10;
  }
}
</pre>

<p>
No exemplo abaixo, ser� constru�do uma interface e uma implementa��o padr�o :
</p>
<pre>
public interface ITest {}
public class Test 
  implements ITest {}
</pre>

<p>
Mesmo para as classes da Linguagem Breder, as interfaces tamb�m tem uma hierarquia de interfaces. 
Com isso, uma interface pode ter uma ou mais interfaces na hierarquia.
</p>

<p>
No exemplo abaixo, ser� constru�do uma interface que possua heran�a de 1 interface :
</p>
<pre>
public interface ITest
  extends ITest1 {}
</pre>

<p>
No exemplo abaixo, ser� constru�do uma interface que possua heran�a de 2 interfaces :
</p>
<pre>
public interface ITest 
  extends ITest1, ITest2 {}
</pre>
