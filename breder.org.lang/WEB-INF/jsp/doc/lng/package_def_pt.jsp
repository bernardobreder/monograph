<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p>
Na Linguagem Breder, toda estrutura do tipo classe ou interface deve possuir um pacote associado.
O pacote serve para poder determinar aonde est� localizado a classe ou interface declarada.
Com isso, depois que for criado v�rias classes e interfaces, poder� perceber que os pacotes servem tamb�m para poder organizar suas estruturas de forma a classific�-la. 
</p>

<p>
O Compilador Breder utiliza do nome do pacote para poder procurar a estrutura nos <code>classpaths</code> (diret�rios aonde iram buscar todas as classes e interfaces).
Al�m disso, caso queira utilizar alguma estrutura, ser� necess�rio informar aonde est� localizado ela para que o compilador Breder possa referenci�-lo de forma correta. 
Com isso, ser� necess�rio usar a palavra chave <code>import</code> para importar classes referente a um pacote.
</p>

<p>
No c�digo abaixo ir� mostrar a declara��o de uma classe com o nome do pacote <code>test</code> :
</p>
<pre>
package test;
public class Test {
} 
</pre>

<p>
No c�digo abaixo ir� mostrar a declara��o de uma classe com o nome do pacote <code>test1</code> :
</p>
<pre>
package test1;
public class Test1 {
} 
</pre>

<p>
No c�digo abaixo ir� mostrar a declara��o de uma classe que utiliza a classe Test e Test1 :
</p>
<pre>
package test.test2;
import test.*;
import test1.*;
public class Test2 extends Test, Test1 {
} 
</pre>

<p>
As classes declarada acima, deveram estar localizado em seus diret�rios correspondente ao nome do pacote.
Portanto, nesse exemplo, em Unix o diret�rio da classe Test deve ser <code>./test</code>, para o diret�rio da classe Test1 deve ser <code>./test1</code> e o diret�rio da classe Test2 deve ser <code>./test/test2</code>.
Por�m, em Windows, o diret�rio da classe Test deve ser <code>.\test</code>, para o diret�rio da classe Test1 deve ser <code>.\test1</code> e o diret�rio da classe Test2 deve ser <code>.\test\test2</code>.
</p>

<p>
No c�digo abaixo ir� mostrar a declara��o de uma classe que utiliza a classe Test e Test1 de outra maneira :
</p>
<pre>
package test.test2;
import test.Test;
import test1.Test1;
public class Test2 extends Test, Test1 {
} 
</pre>

<p>
No c�digo abaixo ir� mostrar a declara��o de uma classe que utiliza a classe Test e Test1, por�m o pacote dessa classe ser� o mesmo a da classe Test1, n�o precisando importar as classes do pacote <code>test1</code> pois ele j� pertence a esse pacote :
</p>
<pre>
package test1;
import test.*;
public class Test2 extends Test, Test1 {
} 
</pre>

<p>
Para as classes e interfaces declaradas sem pacotes, ela n�o poder� ser importada por nenhuma classe. 
Esse caso s� � interessante para a classe inicializadora do programa que estar� criando atrav�s da Linguagem Breder.
</p>
<pre>
package test1;
import test.*;
public class Test2 extends Test, Test1 {
} 
</pre>

