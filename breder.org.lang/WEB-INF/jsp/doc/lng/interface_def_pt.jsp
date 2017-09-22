<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p>
Quando se deseja elaborar uma especificação de uma funcionalidade, a boa prática na Linguagem Breder é especificar primeiro as interfaces necessárias para essas funcionalidades.
Nessas interfaces, serão definidas todas as operações necessárias para que a funcionalidade possa ser implementada de forma correta e clara.
</p>

<p>
Na construção das interfaces, é super importante as operações não devam considerar a possível implementação.
Isso porque, as interfaces servem para organizar as idéias das funcionalidades e não para organizar a idéia de uma implementação possível para a funcionalidade.
</p>

<p>
A idéia, é que a interface sirva como uma camada que implementa uma funcionalidade.
Caso essa camada necessite me mudar a implementação, é importante que a interface da camada permaneça a mesma, mudando somente a forma que foi implementada.
</p>

<p>
Com essa política, a necessidade de se mudar a forma como será implementado não importa no primeiro momento.
Isso porque todos os objetos externos estarão sempre fazendo referencia a objetos das interfaces das implementações específicas.
</p>

<p>
Por exemplo, caso queira implementar a funcionalidade de acelerar um carro, será necessário primeiro especificar essa operação.
Depois de ter especificado, será feito a primeira implementação dessa interface, sendo necessário implementar a operação de acelerar.
Caso futuramente deseja mudar a forma de implementar a mesma funcionalidade, basta somente mudar a implementação da classe que implementou a interface, fazendo com que não seja necessário mudar o código externo que faz referência a ele, pois eles estão considerando somente o conceito geral e não um implementação especifica.
</p>

<p>
Na Linguagem Breder é possível declarar uma interface que define características de uma API.
O objetivo da interface é definir uma API ideal para uma estrutura de forma que alguém implemente.
Nessa Linguagem, é considerado como boa prática, sempre criar uma API para um recurso ou funcionalidade, mesmo que ela só possua uma implementação possível.
Essa boa prática será melhor comentada o seu motivo quando for implementado a funcionalidade de hierarquia de pacotes. 
</p>

<p>
Uma interface representa um estrutura com um conjunto de métodos não implementado. 
O objetivo dela é somente armazenar as declarações das operações nela atribuída.
Caso uma classe tente implementar uma interface, será necessário implementar todas as operações da interface.
</p>

<p>
Deve reforçar que toda interface deve ser criada com as funcionalidades <b>ideais</b> e <b>genéricas</b>, mesmo que esse ideal for difícil de se alcançar.
Isso porque toda interface deve ser de alto nível, escondendo toda a complexidade de implementação que foi necessário para se implementar as funcionalidades.
Além disso, a API deve ser o mais genérico possível porque essa interface pode possuir diversas implementações, não podendo considerar particularidades de alguma implementação. 
</p>

<p>
Como padrão da Linguagem Breder, toda interface deve começar o nome com a letra 'I', indicando que se trata de uma interface, para não atrapalhar o nome da classe de implementação padrão.
Com isso, toda vez que for criar uma estrutura, será criado uma interface com o nome começando com a letra 'I' e será criado uma classe de implementação padrão com o mesmo nome, sem a letra 'I'.
</p>

<p>
No exemplo abaixo, será construído uma interface :
</p>
<pre>
public interface ITest {}
</pre>

<p>
No exemplo abaixo, será construído uma interface com um método :
</p>
<pre>
public interface ITest {
  public void testA () ;
  public Number testB (Number x, Number y);
}
</pre>

<p>
No exemplo abaixo, será construído um exemplo de interface de carro :
</p>
<pre>
public interface ICar {
  public void acelerar () ;
}
</pre>

<p>
No exemplo abaixo, será construído um exemplo de implementação de uma interface de carro :
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
No exemplo abaixo, será construído um exemplo de implementação futura de uma interface de carro. 
Esse exemplo demonstra uma necessidade futura de mudar a implementação da operação de acelerar.
Repara que não foi mudado em nada o cabeçalho do método, somente a sua implementação.
Isso porque o cabeçalho está amarrado a interface que está implementando. 
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
No exemplo abaixo, será construído uma interface e uma implementação padrão :
</p>
<pre>
public interface ITest {}
public class Test 
  implements ITest {}
</pre>

<p>
Mesmo para as classes da Linguagem Breder, as interfaces também tem uma hierarquia de interfaces. 
Com isso, uma interface pode ter uma ou mais interfaces na hierarquia.
</p>

<p>
No exemplo abaixo, será construído uma interface que possua herança de 1 interface :
</p>
<pre>
public interface ITest
  extends ITest1 {}
</pre>

<p>
No exemplo abaixo, será construído uma interface que possua herança de 2 interfaces :
</p>
<pre>
public interface ITest 
  extends ITest1, ITest2 {}
</pre>
