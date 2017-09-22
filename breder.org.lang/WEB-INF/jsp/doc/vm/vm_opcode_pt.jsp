<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p>
A Maquina Virtual Breder possui diversas instru��es que consideram a Orienta��o a Objeto, Coleta de Lixo, Exce��es e outras funcionalidades. 
Abaixo ser� comentada cada instru��o que a Maquina Virtual Breder implementa.
</p>

<p>
Todas as instru��es s�o de 32 bits, aonde os 8 bits mais significativo � o opcode e o restante de 24 bits representa dados da instru��o.
Caso a instru��o n�o utilizar todos os 24 bits, o que for usado ser� considerado os bits menos significativos dos 24 bits.
Portanto para um instru��o que usa 2 dados de 8 bits, ficar� organizado : < opcode:8 >< empty:8 >< data1:8 >< data2:8 > 
</p>

<p>
Diversas instru��es utilizam de uma pilha de objetos para fazer a manipula��o e carga de dados.
A pilha � utilizada por diversas instru��es da Maquina Virtual Breder para realizar opera��es que necessitam de parametros.
Al�m disso, todo o objetos que estiver na pilha, n�o ser� coletado pela Coleta de Lixo.
</p>

<ul>
<li>
	<b>const_nil</b>() - 
    Instru��o que empilha um valor nulo no topo da pilha.
    Essa instru��o incrementa o n�mero de objetos na pilha em uma unidade.
</li>

<li>
	<b>const_nil2</b>() - 
    Instru��o que empilha dois valor nulo no topo da pilha.
    Essa instru��o incrementa o n�mero de objetos na pilha em duas unidade.
</li>

<li>
	<b>const_nil3</b>() - 
    Instru��o que empilha tr�s valor nulo no topo da pilha.
    Essa instru��o incrementa o n�mero de objetos na pilha em tr�s unidade.
</li>

<li>
	<b>const_nil4</b>() - 
    Instru��o que empilha quatro valor nulo no topo da pilha.
    Essa instru��o incrementa o n�mero de objetos na pilha em quatro unidade.
</li>

<li>
	<b>const_nil5</b>() - 
    Instru��o que empilha cinco valor nulo no topo da pilha.
    Essa instru��o incrementa o n�mero de objetos na pilha em cinco unidade.
</li>

<li>
<b>inc</b>(count:24) - 
    Instru��o que empilha v�rios elementos nulos no topo da pilha. 
    A quantidade a ser empilhado consta como par�metro. 
    Essa instru��o incrementa o n�mero de objetos na pilha em 'count' unidade.
</li>

<li>
<b>dec</b>(count:24) - 
    Instru��o que desempilha v�rios elementos do topo da pilha. 
    A quantidade a ser desempilhado consta como par�metro.
    Essa instru��o decrementa o n�mero de objetos na pilha em 'count' unidade. 
</li>

<li>
<b>const_number</b>(index:24) - 
    Instru��o que empilha um valor num�rico. 
    Seu valor � recuperado atrav�s do �ndice da instru��o que busca nas constantes de n�mero definido no cabe�alho do bin�rio.
    Essa instru��o incrementa o n�mero de objetos na pilha em uma unidade. 
</li>

<li>
<b>const_string</b>(index:24) - 
    Instru��o que empilha um valor texto. 
    Seu valor � recuperado atrav�s do �ndice da instru��o que busca nas constantes de texto definido no cabe�alho do bin�rio.
    Essa instru��o incrementa o n�mero de objetos na pilha em uma unidade. 
</li>

<li>
<b>const_boolean</b>(flag:1) - 
    Instru��o que empilha um valor de l�gica. 
    Caso o valor da flag for igual a zero, ser� empilhado o valor false.
    Caso o valor da flag for igual a um, ser� empilhado o valor true.
    Essa instru��o incrementa o n�mero de objetos na pilha em uma unidade. 
</li>

<li>
<b>load</b>(index:24) - 
    Instru��o que empilha um objeto que est� na pilha. 
    O �ndice do objeto que est� na pilha ser� um �ndice+1 para elementos da ordem da pilha de cima para baixo.
    Portanto, o �ndice zero n�o ser� valido e o �ndice 1 ser� o elemento do topo da pilha.
    Essa instru��o incrementa o n�mero de objetos na pilha em uma unidade. 
</li>

<li>
<b>store</b>(index:24) - 
    Instru��o que realiza um salvamente no valor de uma c�lula da pilha. 
    O �ndice representa qual celula a ser saldo e o objeto a ser salvo ser� o objeto do topo da pilha.
    Portanto, o �ndice zero n�o ser� valido, pois n�o faz sentido, e o �ndice 1 ser� o elemento abaixo do topo da pilha.
    Essa instru��o decrementa o n�mero de objetos na pilha em uma unidade.
</li>

<li>
<b>new</b>(index:24) - 
    Instru��o que realiza uma aloca��o de um objeto em mem�ria. 
    O �ndice representa o �ndice da classe a qual deseja alocar.
    Essa instru��o incrementa o n�mero de objetos na pilha em uma unidade.
</li>

<li>
<b>jump</b>(index:24) - 
    Instru��o que atualiza o valor da pr�xima instru��o. 
    O �ndice representa o �ndice da pr�xima instru��o.
    Essa instru��o n�o altera o n�mero de objetos na pilha.
</li>

<li>
<b>jump_object</b>(index:24) - 
    Instru��o que realiza uma chamada de m�todo. 
    O �ndice representa o �ndice do m�todo desconsiderando o polimorfismo, aonde ser� considerado em tempo de execu��o.
    O valor da pr�xima instru��o ser� armazenado em uma pilha para que no retorno do m�todo, a pr�xima instru��o possa ser utilizada.
    Caso o objeto do topo da pilha for nulo, ser� lan�ado uma exce��o.
    Essa instru��o n�o altera o n�mero de objetos na pilha.
</li>

<li>
<b>jump_static</b>(index:24) - 
    Instru��o que realiza uma chamada de m�todo est�tico. 
    O �ndice representa o �ndice do m�todo est�tico.
    Essa instru��o n�o altera o n�mero de objetos na pilha.
</li>

<li>
<b>jump_class</b>(index:24) - 
    Instru��o que realiza a atualiza��o da pr�xima instru��o, caso o objeto no topo da pilha for da classe de �ndice especificado. 
    O �ndice representa o �ndice da classe que deseja consultar.
    Essa instru��o n�o altera o n�mero de objetos na pilha.
</li>

<li>
<b>jump_true</b>(index:24) - 
    Instru��o que atualiza o valor da pr�xima instru��o caso o objeto do topo da pilha ser� um objeto boolean de valor true.
    O objeto do topo da pilha precisa ser um objeto boolean.
    O �ndice representa o �ndice da pr�xima instru��o.
    Caso o objeto do topo da pilha for nulo, ser� lan�ado uma exce��o.
    Essa instru��o decrementa o n�mero de objetos na pilha em uma unidade.
</li>

<li>
<b>jump_false</b>(index:24) - 
    Instru��o que atualiza o valor da pr�xima instru��o caso o objeto do topo da pilha ser� um objeto boolean de valor false.
    O objeto do topo da pilha precisa ser um objeto boolean.
    O �ndice representa o �ndice da pr�xima instru��o.
    Caso o objeto do topo da pilha for nulo, ser� lan�ado uma exce��o.
    Essa instru��o decrementa o n�mero de objetos na pilha em uma unidade.
</li>

<li>
<b>ret</b>(count:24) - 
    Instru��o que realiza o retorno de um m�todo.
    Ser� decrementado 'count' elementos do topo da pilha
    O �ndice representa o �ndice da pr�xima instru��o.
    O valor da pr�xima instru��o ser� atualizada para continuar o �ltimo m�todo executado.
    Essa instru��o decrementa o n�mero de objetos na pilha em 'count' unidade.
</li>

<li>
<b>cast</b>(index:24) - 
    Instru��o que realiza um cast do objeto do topo da pilha para uma classe.
    O �ndice representa o �ndice da classe que deseja fazer o cast.
    Caso o objeto do topo da pilha for nulo, ser� lan�ado uma exce��o.
    Essa instru��o n�o altera o n�mero de objetos na pilha.
</li>

<li>
<b>notnull</b>() - 
    Instru��o que realiza a verifica��o do objeto no topo da pilha ser nulo ou n�o.
    Caso seja nulo, ser� lan�ado uma exce��o.
    Essa instru��o n�o altera o n�mero de objetos na pilha.
</li>

<li>
<b>not</b>() - 
    Instru��o que empilha um novo objeto boolean com o valor contrario a do objeto boolean no topo da pilha.
    O objeto do topo da pilha precisa ser um objeto boolean.
    Caso o objeto do topo da pilha for nulo, ser� lan�ado uma exce��o.
    Essa instru��o incrementa o n�mero de objetos na pilha em uma unidade.
</li>

<li>
<b>get_field</b>(index:24) - 
    Instru��o que altera o topo da pilha com o campo relativo ao objeto do topo da pilha.
    O �ndice representa o �ndice do campo relativo a classe do objeto do topo da pilha, em tempo de execu��o.
    Caso o objeto do topo da pilha for nulo, ser� lan�ado uma exce��o.
    Essa instru��o n�o altera o n�mero de objetos na pilha.
</li>

<li>
<b>set_field</b>(index:24) - 
    Instru��o que realiza a escrita do valor do campo cujo objeto est� no topo da pilha e o valor a ser escrito est� abaixo do topo da pilha.
    O �ndice representa o �ndice do campo relativo a classe do objeto do topo da pilha, em tempo de execu��o.
    Caso o objeto do topo da pilha for nulo, ser� lan�ado uma exce��o.
    Essa instru��o decrementa o n�mero de objetos na pilha em duas unidade.
</li>

<li>
<b>get_static_field</b>(index:24) - 
    Instru��o que empilha o valor do campo est�tico de �ndice especificado no seu conte�do.
    O �ndice representa o �ndice do campo est�tico relativo a classe do objeto.
    Essa instru��o incrementa o n�mero de objetos na pilha em uma unidade.
</li>

<li>
<b>set_static_field</b>(index:24) - 
    Instru��o que realiza a escrita do valor do campo est�tico cujo objeto est� no topo da pilha.
    O �ndice representa o �ndice do campo est�tico relativo a classe do objeto.
    Essa instru��o decrementa o n�mero de objetos na pilha em uma unidade.
</li>

<li>
<b>try</b>(index:24) - 
    Instru��o que empilha todas as refer�ncias necess�rias para restaurar o estado original, caso ocorra algum erro.
    O �ndice representa o quanto o PC deve ser incrementado para tratar o erro.
    Essa instru��o n�o altera o n�mero de objetos na pilha.
</li>

<li>
<b>throw</b>() - 
    Instru��o que realiza o desvio de execu��o para um bloco especifico, utilizando o objeto no topo da pilha como tratamento de erro.
    Essa instru��o decrementa o n�mero de objetos na pilha em uma unidade.
</li>

<li>
<b>throw_true</b>() - 
    Instru��o que indica que n�o ocorreu nenhum erro num bloco protegido.
    Essa instru��o n�o altera o n�mero de objetos na pilha.
</li>

<li>
<b>throw_false</b>() - 
    Instru��o que indica que ocorreu um erro e j� foi tratado, atualizando o PC.
    Essa instru��o n�o altera o n�mero de objetos na pilha.
</li>

<li>
<b>throw_store</b>() - 
    Instru��o que armazena o objeto Throwable em um local especifico.
    Essa instru��o n�o altera o n�mero de objetos na pilha.
</li>

<li>
<b>or</b>() - 
    Instru��o que realiza a opera��o de l�gica de conjun��o.
    Os objetos utilizados ser�o os objetos desempilhado do topo da pilha.
    Ser� criado um objeto do tipo Boolean com a resposta, empilhando no topo da pilha.
    Essa instru��o incrementa o n�mero de objetos na pilha em uma unidade.
</li>

<li>
<b>and</b>() - 
    Instru��o que realiza a opera��o de l�gica de disjun�a.
    Os objetos utilizados ser�o os objetos desempilhado do topo da pilha.
    Ser� criado um objeto do tipo Boolean com a resposta, empilhando no topo da pilha.
    Essa instru��o incrementa o n�mero de objetos na pilha em uma unidade.
</li>

<li>
<b>equal_boolean</b>() - 
    Instru��o que realiza a opera��o de compara��o entre objetos da classe Boolean.
    Os objetos utilizados ser�o os objetos desempilhado do topo da pilha.
    Ser� criado um objeto do tipo Boolean com a resposta, empilhando no topo da pilha.
    Essa instru��o incrementa o n�mero de objetos na pilha em uma unidade.
</li>

<li>
<b>equal_number</b>() - 
    Instru��o que realiza a opera��o de compara��o entre objetos da classe Number.
    Os objetos utilizados ser�o os objetos desempilhado do topo da pilha.
    Ser� criado um objeto do tipo Boolean com a resposta, empilhando no topo da pilha.
    Essa instru��o incrementa o n�mero de objetos na pilha em uma unidade.
</li>

<li>
<b>equal_string</b>() - 
    Instru��o que realiza a opera��o de compara��o entre objetos da classe String.
    Os objetos utilizados ser�o os objetos desempilhado do topo da pilha.
    Ser� criado um objeto do tipo Boolean com a resposta, empilhando no topo da pilha.
    Essa instru��o incrementa o n�mero de objetos na pilha em uma unidade.
</li>

<li>
<b>not_equal_boolean</b>() - 
    Instru��o que realiza a opera��o a desiqualdade entre objetos da classe Boolean.
    Os objetos utilizados ser�o os objetos desempilhado do topo da pilha.
    Ser� criado um objeto do tipo Boolean com a resposta, empilhando no topo da pilha.
    Essa instru��o incrementa o n�mero de objetos na pilha em uma unidade.
</li>

<li>
<b>not_equal_number</b>() - 
    Instru��o que realiza a opera��o a desiqualdade entre objetos da classe Number.
    Os objetos utilizados ser�o os objetos desempilhado do topo da pilha.
    Ser� criado um objeto do tipo Boolean com a resposta, empilhando no topo da pilha.
    Essa instru��o incrementa o n�mero de objetos na pilha em uma unidade.
</li>

<li>
<b>not_equal_string</b>() - 
    Instru��o que realiza a opera��o a desiqualdade entre objetos da classe String.
    Os objetos utilizados ser�o os objetos desempilhado do topo da pilha.
    Ser� criado um objeto do tipo Boolean com a resposta, empilhando no topo da pilha.
    Essa instru��o incrementa o n�mero de objetos na pilha em uma unidade.
</li>

<li>
<b>high_equal_number</b>() - 
    Instru��o que realiza a opera��o a compara��o de maior entre objetos da classe Number.
    Os objetos utilizados ser�o os objetos desempilhado do topo da pilha.
    Ser� criado um objeto do tipo Boolean com a resposta, empilhando no topo da pilha.
    Essa instru��o incrementa o n�mero de objetos na pilha em uma unidade.
</li>

<li>
<b>high_equal_string</b>() - 
    Instru��o que realiza a opera��o a compara��o de maior entre objetos da classe String.
    Os objetos utilizados ser�o os objetos desempilhado do topo da pilha.
    Ser� criado um objeto do tipo Boolean com a resposta, empilhando no topo da pilha.
    Essa instru��o incrementa o n�mero de objetos na pilha em uma unidade.
</li>

<li>
<b>high_number</b>() - 
    Instru��o que realiza a opera��o a compara��o de maior entre objetos da classe Number.
    Os objetos utilizados ser�o os objetos desempilhado do topo da pilha.
    Ser� criado um objeto do tipo Boolean com a resposta, empilhando no topo da pilha.
    Essa instru��o incrementa o n�mero de objetos na pilha em uma unidade.
</li>

<li>
<b>high_string</b>() - 
    Instru��o que realiza a opera��o a compara��o de maior entre objetos da classe String.
    Os objetos utilizados ser�o os objetos desempilhado do topo da pilha.
    Ser� criado um objeto do tipo Boolean com a resposta, empilhando no topo da pilha.
    Essa instru��o incrementa o n�mero de objetos na pilha em uma unidade.
</li>

<li>
<b>low_equal_number</b>() - 
    Instru��o que realiza a opera��o a compara��o de maior entre objetos da classe Number.
    Os objetos utilizados ser�o os objetos desempilhado do topo da pilha.
    Ser� criado um objeto do tipo Boolean com a resposta, empilhando no topo da pilha.
    Essa instru��o incrementa o n�mero de objetos na pilha em uma unidade.
</li>

<li>
<b>low_equal_string</b>() - 
    Instru��o que realiza a opera��o a compara��o de maior entre objetos da classe String.
    Os objetos utilizados ser�o os objetos desempilhado do topo da pilha.
    Ser� criado um objeto do tipo Boolean com a resposta, empilhando no topo da pilha.
    Essa instru��o incrementa o n�mero de objetos na pilha em uma unidade.
</li>

<li>
<b>low_number</b>() - 
    Instru��o que realiza a opera��o a compara��o de maior entre objetos da classe Number.
    Os objetos utilizados ser�o os objetos desempilhado do topo da pilha.
    Ser� criado um objeto do tipo Boolean com a resposta, empilhando no topo da pilha.
    Essa instru��o incrementa o n�mero de objetos na pilha em uma unidade.
</li>

<li>
<b>low_string</b>() - 
    Instru��o que realiza a opera��o a compara��o de maior entre objetos da classe String.
    Os objetos utilizados ser�o os objetos desempilhado do topo da pilha.
    Ser� criado um objeto do tipo Boolean com a resposta, empilhando no topo da pilha.
    Essa instru��o incrementa o n�mero de objetos na pilha em uma unidade.
</li>

<li>
<b>sum_number</b>() - 
    Instru��o que realiza a opera��o de soma entre objetos da classe Number.
    Os objetos utilizados ser�o os objetos desempilhado do topo da pilha.
    Ser� criado um objeto do tipo Boolean com a resposta, empilhando no topo da pilha.
    Essa instru��o incrementa o n�mero de objetos na pilha em uma unidade.
</li>

<li>
<b>sum_string</b>() - 
    Instru��o que realiza a opera��o de soma entre objetos da classe String.
    Os objetos utilizados ser�o os objetos desempilhado do topo da pilha.
    Ser� criado um objeto do tipo Boolean com a resposta, empilhando no topo da pilha.
    Essa instru��o incrementa o n�mero de objetos na pilha em uma unidade.
</li>

<li>
<b>sub_number</b>() - 
    Instru��o que realiza a opera��o de subtra��o entre objetos da classe Number.
    Os objetos utilizados ser�o os objetos desempilhado do topo da pilha.
    Ser� criado um objeto do tipo Boolean com a resposta, empilhando no topo da pilha.
    Essa instru��o incrementa o n�mero de objetos na pilha em uma unidade.
</li>

<li>
<b>mul_number</b>() - 
    Instru��o que realiza a opera��o de multiplica��o entre objetos da classe Number.
    Os objetos utilizados ser�o os objetos desempilhado do topo da pilha.
    Ser� criado um objeto do tipo Boolean com a resposta, empilhando no topo da pilha.
    Essa instru��o incrementa o n�mero de objetos na pilha em uma unidade.
</li>

<li>
<b>div_number</b>() - 
    Instru��o que realiza a opera��o de divis�o entre objetos da classe Number.
    Os objetos utilizados ser�o os objetos desempilhado do topo da pilha.
    Ser� criado um objeto do tipo Boolean com a resposta, empilhando no topo da pilha.
    Essa instru��o incrementa o n�mero de objetos na pilha em uma unidade.
</li>

</ul>