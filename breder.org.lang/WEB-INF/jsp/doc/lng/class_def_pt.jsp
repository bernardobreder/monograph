<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p>
Na Linguagem Breder, a forma mais adequada para criar um conceito, é através da criação de uma classe.
A partir da criação de uma classe, será possível atribuir várias características referente ao conceito em que deseja expressar.
</p>

<p>
Uma classe representa uma estrutura de dados capaz de estruturar e organizar um conceito.
Por exemplo, podemos estruturar o conceito "carro" como uma classe de nome "Carro".
Com isso, essa classe poderá ter características e operações que diz respeito a um carro, como por exemplo a ação de acelerar, frear.
Além disso, como o conceito de carro está definido na classe acima, se desejar criar um objeto utilizando o conceito da classe do carro, será criado uma instância dele.
Com isso, as instâncias ou objetos terão as mesmas características conceituais, porém com valores possivelmente diferentes, gerando assim algum comportamento diferente em suas operações.
Por exemplo, se for definido que um carro possui uma cor, será possível criar vários objetos diferente, com cores diferente, a onde cada um irá se comportar de forma diferente de acordo com o valor de cor atribuído. 
</p>

<p>
Para criar uma classe, será preciso criar um arquivo com o mesmo nome da classe, com a extensão ".breder".
Por exemplo, se for criar uma classe de nome "Teste", então o nome do arquivo deverá ser "Test.breder".
No exemplo abaixo, será construído uma classe sem nenhum campo e nenhuma operação.
</p>

<pre>
public class Test {}
</pre>

<p>
Uma classe representa uma estrutura de dados que armazenada informações de campos e métodos referente ao conceito delegado a classe. 
No exemplo acima, o conceito foi a definição do que é ser um carro, mas não os seus valores possíveis de atributos. 
</p>

<p>
Como comentado anteriormente, uma classe pode possuir diversos campos que definam as suas características.
Esses campos podem ser do conceito da classe ou do objeto.
</p>

<p>
Quando se tem um campo do objeto, cada um deles podem possuir valores diferentes.
Com isso, podemos criar uma coleção de objetos que se comportam diferente de acordo com o seu contexto, de acordo com os valores dos seus campos.
Para definir um campo do objeto, basta não utilizar a palavra reservado 'static'.
Abaixo, será mostrado um exemplo de declaração de um campo do objeto.
</p>

<pre>
public class Test {
  public Number x;
}
</pre>

<p>
Agora, quando se tem um campo que representa o conceito da classe, seu valor estará amarrado a classe e não aos objetos.
Com isso, todos os objetos dessa classe, terão o valor desse campo compartilhado.
Para definir um campo da classe, basta utilizar a palavra reservada 'static'.
Abaixo, será mostrado um exemplo de declaração de um campo da classe.
</p>

<pre>
public class Test {
  public static Number x;
}
</pre>

<p>
Além de campos, uma classe pode possuir operações associado a ela.
Essas operações descrevem um comportamento relativo aos campos que ele e outros dependentes possuem.
Da mesma maneira que um campo pode pertencer a um objeto ou a uma classe, uma operação ou método também possui essa característica.
</p>

<p>
Quando se tem um método do objeto, a sua implementação será referente aos campos do próprio objeto.
Para definir um método do objeto, basta não utilizar a palavra reservado 'static'.
Abaixo, será mostrado um exemplo de declaração de um método do objeto.
</p>

<pre>
public class Test {
  public Number getX () { return 5; }
}
</pre>

<p>
Agora, quando se tem um método de uma classe, o seu comportamento não faz referencia a um objeto especifico.
Normalmente, essas operações de classes podem ser usada para se implementar utilitários ou fazer reaproveitamento de código.
Para definir um campo da classe, basta utilizar a palavra reservada 'static'.
Abaixo, será mostrado um exemplo de declaração de um método da classe.
</p>

<pre>
public class Test {
  public static Number getX () { return 5; }
}
</pre>

<p>
Além dos métodos e campos, uma classe pode possuir uma hierarquia de outras classes. 
Essa hierarquia faz com que a classe adquiram funcionalidades daquela que herdou e possa também reimplementar algumas operações.
Por padrão, se uma classe não herdar de nenhuma outra, ela será herdada implicitamente da classe <code>breder.lang.Object</code> que representa uma classe simples, sem grandes funcionalidades.
Caso queira que uma classe herde de algum outra classe, essa herança pode ser de um ou mais classes. 
Com isso, uma classe pode possuir hierarquia múltiplas, sendo muito usado para fazer reaproveitamento de código.
Porém, tome cuidado com o uso da hierarquia múltiplas, pois ela pode te levar a um diagrama de classe muito complexo.
A grande função da hierarquia multipla é aumentar o reuso de código, fazendo com que uma classe herde de outra para poder aproveitar as operações que a outra tem.
</p>

<p>
No exemplo abaixo, será construído uma classe sem nenhuma herança declarada, portanto implicitamente, herdará do <code>breder.lang.Object</code> :
</p>
<pre>
public class Test {}
</pre>

<p>
No exemplo abaixo, será construído uma classe que possua herança de 1 classe :
</p>
<pre>
public class Test 
  extends Test1 {}
</pre>

<p>
No exemplo abaixo, será construído uma classe que possua herança de 2 classes :
</p>
<pre>
public class Test 
  extends Test1, Test2 {}
</pre>

<p>
Uma classe na Linguagem Breder pode também implementar alguma interface. Para isso, todos os métodos declarado na interface, deverá ser implementado pela classe.
Da mesma forma da hierarquia de classes, uma classe pode também ter uma hierarquia de interfaces, da mesma maneira definida para a hierarquia de classe.
</p>

<p>
No exemplo abaixo, será construído uma classe que possua herança de 1 interface :
</p>
<pre>
public class Test 
  implements Test1 {}
</pre>

<p>
No exemplo abaixo, será construído uma classe que possua herança de 2 interfaces :
</p>
<pre>
public class Test 
  implements Test1, Test2 {}
</pre>

<p>
Como descrito acima, uma classe pode possuir hierarquia de classes e interface. 
No exemplo abaixo, será construído uma classe que possua herança de 2 classes e 2 interfaces :
</p>
<pre>
public class Test 
  extends Test1, Test2 
  implements ITest3, ITest4 {}
</pre>