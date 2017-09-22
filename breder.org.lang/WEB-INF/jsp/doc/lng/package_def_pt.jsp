<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p>
Na Linguagem Breder, toda estrutura do tipo classe ou interface deve possuir um pacote associado.
O pacote serve para poder determinar aonde está localizado a classe ou interface declarada.
Com isso, depois que for criado várias classes e interfaces, poderá perceber que os pacotes servem também para poder organizar suas estruturas de forma a classificá-la. 
</p>

<p>
O Compilador Breder utiliza do nome do pacote para poder procurar a estrutura nos <code>classpaths</code> (diretórios aonde iram buscar todas as classes e interfaces).
Além disso, caso queira utilizar alguma estrutura, será necessário informar aonde está localizado ela para que o compilador Breder possa referenciá-lo de forma correta. 
Com isso, será necessário usar a palavra chave <code>import</code> para importar classes referente a um pacote.
</p>

<p>
No código abaixo irá mostrar a declaração de uma classe com o nome do pacote <code>test</code> :
</p>
<pre>
package test;
public class Test {
} 
</pre>

<p>
No código abaixo irá mostrar a declaração de uma classe com o nome do pacote <code>test1</code> :
</p>
<pre>
package test1;
public class Test1 {
} 
</pre>

<p>
No código abaixo irá mostrar a declaração de uma classe que utiliza a classe Test e Test1 :
</p>
<pre>
package test.test2;
import test.*;
import test1.*;
public class Test2 extends Test, Test1 {
} 
</pre>

<p>
As classes declarada acima, deveram estar localizado em seus diretórios correspondente ao nome do pacote.
Portanto, nesse exemplo, em Unix o diretório da classe Test deve ser <code>./test</code>, para o diretório da classe Test1 deve ser <code>./test1</code> e o diretório da classe Test2 deve ser <code>./test/test2</code>.
Porém, em Windows, o diretório da classe Test deve ser <code>.\test</code>, para o diretório da classe Test1 deve ser <code>.\test1</code> e o diretório da classe Test2 deve ser <code>.\test\test2</code>.
</p>

<p>
No código abaixo irá mostrar a declaração de uma classe que utiliza a classe Test e Test1 de outra maneira :
</p>
<pre>
package test.test2;
import test.Test;
import test1.Test1;
public class Test2 extends Test, Test1 {
} 
</pre>

<p>
No código abaixo irá mostrar a declaração de uma classe que utiliza a classe Test e Test1, porém o pacote dessa classe será o mesmo a da classe Test1, não precisando importar as classes do pacote <code>test1</code> pois ele já pertence a esse pacote :
</p>
<pre>
package test1;
import test.*;
public class Test2 extends Test, Test1 {
} 
</pre>

<p>
Para as classes e interfaces declaradas sem pacotes, ela não poderá ser importada por nenhuma classe. 
Esse caso só é interessante para a classe inicializadora do programa que estará criando através da Linguagem Breder.
</p>
<pre>
package test1;
import test.*;
public class Test2 extends Test, Test1 {
} 
</pre>

