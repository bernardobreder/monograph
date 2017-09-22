<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p>
Na Linguagem Breder, a forma mais adequada para criar um conceito, � atrav�s da cria��o de uma classe.
A partir da cria��o de uma classe, ser� poss�vel atribuir v�rias caracter�sticas referente ao conceito em que deseja expressar.
</p>

<p>
Uma classe representa uma estrutura de dados capaz de estruturar e organizar um conceito.
Por exemplo, podemos estruturar o conceito "carro" como uma classe de nome "Carro".
Com isso, essa classe poder� ter caracter�sticas e opera��es que diz respeito a um carro, como por exemplo a a��o de acelerar, frear.
Al�m disso, como o conceito de carro est� definido na classe acima, se desejar criar um objeto utilizando o conceito da classe do carro, ser� criado uma inst�ncia dele.
Com isso, as inst�ncias ou objetos ter�o as mesmas caracter�sticas conceituais, por�m com valores possivelmente diferentes, gerando assim algum comportamento diferente em suas opera��es.
Por exemplo, se for definido que um carro possui uma cor, ser� poss�vel criar v�rios objetos diferente, com cores diferente, a onde cada um ir� se comportar de forma diferente de acordo com o valor de cor atribu�do. 
</p>

<p>
Para criar uma classe, ser� preciso criar um arquivo com o mesmo nome da classe, com a extens�o ".breder".
Por exemplo, se for criar uma classe de nome "Teste", ent�o o nome do arquivo dever� ser "Test.breder".
No exemplo abaixo, ser� constru�do uma classe sem nenhum campo e nenhuma opera��o.
</p>

<pre>
public class Test {}
</pre>

<p>
Uma classe representa uma estrutura de dados que armazenada informa��es de campos e m�todos referente ao conceito delegado a classe. 
No exemplo acima, o conceito foi a defini��o do que � ser um carro, mas n�o os seus valores poss�veis de atributos. 
</p>

<p>
Como comentado anteriormente, uma classe pode possuir diversos campos que definam as suas caracter�sticas.
Esses campos podem ser do conceito da classe ou do objeto.
</p>

<p>
Quando se tem um campo do objeto, cada um deles podem possuir valores diferentes.
Com isso, podemos criar uma cole��o de objetos que se comportam diferente de acordo com o seu contexto, de acordo com os valores dos seus campos.
Para definir um campo do objeto, basta n�o utilizar a palavra reservado 'static'.
Abaixo, ser� mostrado um exemplo de declara��o de um campo do objeto.
</p>

<pre>
public class Test {
  public Number x;
}
</pre>

<p>
Agora, quando se tem um campo que representa o conceito da classe, seu valor estar� amarrado a classe e n�o aos objetos.
Com isso, todos os objetos dessa classe, ter�o o valor desse campo compartilhado.
Para definir um campo da classe, basta utilizar a palavra reservada 'static'.
Abaixo, ser� mostrado um exemplo de declara��o de um campo da classe.
</p>

<pre>
public class Test {
  public static Number x;
}
</pre>

<p>
Al�m de campos, uma classe pode possuir opera��es associado a ela.
Essas opera��es descrevem um comportamento relativo aos campos que ele e outros dependentes possuem.
Da mesma maneira que um campo pode pertencer a um objeto ou a uma classe, uma opera��o ou m�todo tamb�m possui essa caracter�stica.
</p>

<p>
Quando se tem um m�todo do objeto, a sua implementa��o ser� referente aos campos do pr�prio objeto.
Para definir um m�todo do objeto, basta n�o utilizar a palavra reservado 'static'.
Abaixo, ser� mostrado um exemplo de declara��o de um m�todo do objeto.
</p>

<pre>
public class Test {
  public Number getX () { return 5; }
}
</pre>

<p>
Agora, quando se tem um m�todo de uma classe, o seu comportamento n�o faz referencia a um objeto especifico.
Normalmente, essas opera��es de classes podem ser usada para se implementar utilit�rios ou fazer reaproveitamento de c�digo.
Para definir um campo da classe, basta utilizar a palavra reservada 'static'.
Abaixo, ser� mostrado um exemplo de declara��o de um m�todo da classe.
</p>

<pre>
public class Test {
  public static Number getX () { return 5; }
}
</pre>

<p>
Al�m dos m�todos e campos, uma classe pode possuir uma hierarquia de outras classes. 
Essa hierarquia faz com que a classe adquiram funcionalidades daquela que herdou e possa tamb�m reimplementar algumas opera��es.
Por padr�o, se uma classe n�o herdar de nenhuma outra, ela ser� herdada implicitamente da classe <code>breder.lang.Object</code> que representa uma classe simples, sem grandes funcionalidades.
Caso queira que uma classe herde de algum outra classe, essa heran�a pode ser de um ou mais classes. 
Com isso, uma classe pode possuir hierarquia m�ltiplas, sendo muito usado para fazer reaproveitamento de c�digo.
Por�m, tome cuidado com o uso da hierarquia m�ltiplas, pois ela pode te levar a um diagrama de classe muito complexo.
A grande fun��o da hierarquia multipla � aumentar o reuso de c�digo, fazendo com que uma classe herde de outra para poder aproveitar as opera��es que a outra tem.
</p>

<p>
No exemplo abaixo, ser� constru�do uma classe sem nenhuma heran�a declarada, portanto implicitamente, herdar� do <code>breder.lang.Object</code> :
</p>
<pre>
public class Test {}
</pre>

<p>
No exemplo abaixo, ser� constru�do uma classe que possua heran�a de 1 classe :
</p>
<pre>
public class Test 
  extends Test1 {}
</pre>

<p>
No exemplo abaixo, ser� constru�do uma classe que possua heran�a de 2 classes :
</p>
<pre>
public class Test 
  extends Test1, Test2 {}
</pre>

<p>
Uma classe na Linguagem Breder pode tamb�m implementar alguma interface. Para isso, todos os m�todos declarado na interface, dever� ser implementado pela classe.
Da mesma forma da hierarquia de classes, uma classe pode tamb�m ter uma hierarquia de interfaces, da mesma maneira definida para a hierarquia de classe.
</p>

<p>
No exemplo abaixo, ser� constru�do uma classe que possua heran�a de 1 interface :
</p>
<pre>
public class Test 
  implements Test1 {}
</pre>

<p>
No exemplo abaixo, ser� constru�do uma classe que possua heran�a de 2 interfaces :
</p>
<pre>
public class Test 
  implements Test1, Test2 {}
</pre>

<p>
Como descrito acima, uma classe pode possuir hierarquia de classes e interface. 
No exemplo abaixo, ser� constru�do uma classe que possua heran�a de 2 classes e 2 interfaces :
</p>
<pre>
public class Test 
  extends Test1, Test2 
  implements ITest3, ITest4 {}
</pre>