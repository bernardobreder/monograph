<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p>
A Maquina Virtual Breder possui diversas instruções que consideram a Orientação a Objeto, Coleta de Lixo, Exceções e outras funcionalidades. 
Abaixo será comentada cada instrução que a Maquina Virtual Breder implementa.
</p>

<p>
Todas as instruções são de 32 bits, aonde os 8 bits mais significativo é o opcode e o restante de 24 bits representa dados da instrução.
Caso a instrução não utilizar todos os 24 bits, o que for usado será considerado os bits menos significativos dos 24 bits.
Portanto para um instrução que usa 2 dados de 8 bits, ficará organizado : < opcode:8 >< empty:8 >< data1:8 >< data2:8 > 
</p>

<p>
Diversas instruções utilizam de uma pilha de objetos para fazer a manipulação e carga de dados.
A pilha é utilizada por diversas instruções da Maquina Virtual Breder para realizar operações que necessitam de parametros.
Além disso, todo o objetos que estiver na pilha, não será coletado pela Coleta de Lixo.
</p>

<ul>
<li>
	<b>const_nil</b>() - 
    Instrução que empilha um valor nulo no topo da pilha.
    Essa instrução incrementa o número de objetos na pilha em uma unidade.
</li>

<li>
	<b>const_nil2</b>() - 
    Instrução que empilha dois valor nulo no topo da pilha.
    Essa instrução incrementa o número de objetos na pilha em duas unidade.
</li>

<li>
	<b>const_nil3</b>() - 
    Instrução que empilha três valor nulo no topo da pilha.
    Essa instrução incrementa o número de objetos na pilha em três unidade.
</li>

<li>
	<b>const_nil4</b>() - 
    Instrução que empilha quatro valor nulo no topo da pilha.
    Essa instrução incrementa o número de objetos na pilha em quatro unidade.
</li>

<li>
	<b>const_nil5</b>() - 
    Instrução que empilha cinco valor nulo no topo da pilha.
    Essa instrução incrementa o número de objetos na pilha em cinco unidade.
</li>

<li>
<b>inc</b>(count:24) - 
    Instrução que empilha vários elementos nulos no topo da pilha. 
    A quantidade a ser empilhado consta como parâmetro. 
    Essa instrução incrementa o número de objetos na pilha em 'count' unidade.
</li>

<li>
<b>dec</b>(count:24) - 
    Instrução que desempilha vários elementos do topo da pilha. 
    A quantidade a ser desempilhado consta como parâmetro.
    Essa instrução decrementa o número de objetos na pilha em 'count' unidade. 
</li>

<li>
<b>const_number</b>(index:24) - 
    Instrução que empilha um valor numérico. 
    Seu valor é recuperado através do índice da instrução que busca nas constantes de número definido no cabeçalho do binário.
    Essa instrução incrementa o número de objetos na pilha em uma unidade. 
</li>

<li>
<b>const_string</b>(index:24) - 
    Instrução que empilha um valor texto. 
    Seu valor é recuperado através do índice da instrução que busca nas constantes de texto definido no cabeçalho do binário.
    Essa instrução incrementa o número de objetos na pilha em uma unidade. 
</li>

<li>
<b>const_boolean</b>(flag:1) - 
    Instrução que empilha um valor de lógica. 
    Caso o valor da flag for igual a zero, será empilhado o valor false.
    Caso o valor da flag for igual a um, será empilhado o valor true.
    Essa instrução incrementa o número de objetos na pilha em uma unidade. 
</li>

<li>
<b>load</b>(index:24) - 
    Instrução que empilha um objeto que está na pilha. 
    O índice do objeto que está na pilha será um índice+1 para elementos da ordem da pilha de cima para baixo.
    Portanto, o índice zero não será valido e o índice 1 será o elemento do topo da pilha.
    Essa instrução incrementa o número de objetos na pilha em uma unidade. 
</li>

<li>
<b>store</b>(index:24) - 
    Instrução que realiza um salvamente no valor de uma célula da pilha. 
    O índice representa qual celula a ser saldo e o objeto a ser salvo será o objeto do topo da pilha.
    Portanto, o índice zero não será valido, pois não faz sentido, e o índice 1 será o elemento abaixo do topo da pilha.
    Essa instrução decrementa o número de objetos na pilha em uma unidade.
</li>

<li>
<b>new</b>(index:24) - 
    Instrução que realiza uma alocação de um objeto em memória. 
    O índice representa o índice da classe a qual deseja alocar.
    Essa instrução incrementa o número de objetos na pilha em uma unidade.
</li>

<li>
<b>jump</b>(index:24) - 
    Instrução que atualiza o valor da próxima instrução. 
    O índice representa o índice da próxima instrução.
    Essa instrução não altera o número de objetos na pilha.
</li>

<li>
<b>jump_object</b>(index:24) - 
    Instrução que realiza uma chamada de método. 
    O índice representa o índice do método desconsiderando o polimorfismo, aonde será considerado em tempo de execução.
    O valor da próxima instrução será armazenado em uma pilha para que no retorno do método, a próxima instrução possa ser utilizada.
    Caso o objeto do topo da pilha for nulo, será lançado uma exceção.
    Essa instrução não altera o número de objetos na pilha.
</li>

<li>
<b>jump_static</b>(index:24) - 
    Instrução que realiza uma chamada de método estático. 
    O índice representa o índice do método estático.
    Essa instrução não altera o número de objetos na pilha.
</li>

<li>
<b>jump_class</b>(index:24) - 
    Instrução que realiza a atualização da próxima instrução, caso o objeto no topo da pilha for da classe de índice especificado. 
    O índice representa o índice da classe que deseja consultar.
    Essa instrução não altera o número de objetos na pilha.
</li>

<li>
<b>jump_true</b>(index:24) - 
    Instrução que atualiza o valor da próxima instrução caso o objeto do topo da pilha será um objeto boolean de valor true.
    O objeto do topo da pilha precisa ser um objeto boolean.
    O índice representa o índice da próxima instrução.
    Caso o objeto do topo da pilha for nulo, será lançado uma exceção.
    Essa instrução decrementa o número de objetos na pilha em uma unidade.
</li>

<li>
<b>jump_false</b>(index:24) - 
    Instrução que atualiza o valor da próxima instrução caso o objeto do topo da pilha será um objeto boolean de valor false.
    O objeto do topo da pilha precisa ser um objeto boolean.
    O índice representa o índice da próxima instrução.
    Caso o objeto do topo da pilha for nulo, será lançado uma exceção.
    Essa instrução decrementa o número de objetos na pilha em uma unidade.
</li>

<li>
<b>ret</b>(count:24) - 
    Instrução que realiza o retorno de um método.
    Será decrementado 'count' elementos do topo da pilha
    O índice representa o índice da próxima instrução.
    O valor da próxima instrução será atualizada para continuar o último método executado.
    Essa instrução decrementa o número de objetos na pilha em 'count' unidade.
</li>

<li>
<b>cast</b>(index:24) - 
    Instrução que realiza um cast do objeto do topo da pilha para uma classe.
    O índice representa o índice da classe que deseja fazer o cast.
    Caso o objeto do topo da pilha for nulo, será lançado uma exceção.
    Essa instrução não altera o número de objetos na pilha.
</li>

<li>
<b>notnull</b>() - 
    Instrução que realiza a verificação do objeto no topo da pilha ser nulo ou não.
    Caso seja nulo, será lançado uma exceção.
    Essa instrução não altera o número de objetos na pilha.
</li>

<li>
<b>not</b>() - 
    Instrução que empilha um novo objeto boolean com o valor contrario a do objeto boolean no topo da pilha.
    O objeto do topo da pilha precisa ser um objeto boolean.
    Caso o objeto do topo da pilha for nulo, será lançado uma exceção.
    Essa instrução incrementa o número de objetos na pilha em uma unidade.
</li>

<li>
<b>get_field</b>(index:24) - 
    Instrução que altera o topo da pilha com o campo relativo ao objeto do topo da pilha.
    O índice representa o índice do campo relativo a classe do objeto do topo da pilha, em tempo de execução.
    Caso o objeto do topo da pilha for nulo, será lançado uma exceção.
    Essa instrução não altera o número de objetos na pilha.
</li>

<li>
<b>set_field</b>(index:24) - 
    Instrução que realiza a escrita do valor do campo cujo objeto está no topo da pilha e o valor a ser escrito está abaixo do topo da pilha.
    O índice representa o índice do campo relativo a classe do objeto do topo da pilha, em tempo de execução.
    Caso o objeto do topo da pilha for nulo, será lançado uma exceção.
    Essa instrução decrementa o número de objetos na pilha em duas unidade.
</li>

<li>
<b>get_static_field</b>(index:24) - 
    Instrução que empilha o valor do campo estático de índice especificado no seu conteúdo.
    O índice representa o índice do campo estático relativo a classe do objeto.
    Essa instrução incrementa o número de objetos na pilha em uma unidade.
</li>

<li>
<b>set_static_field</b>(index:24) - 
    Instrução que realiza a escrita do valor do campo estático cujo objeto está no topo da pilha.
    O índice representa o índice do campo estático relativo a classe do objeto.
    Essa instrução decrementa o número de objetos na pilha em uma unidade.
</li>

<li>
<b>try</b>(index:24) - 
    Instrução que empilha todas as referências necessárias para restaurar o estado original, caso ocorra algum erro.
    O índice representa o quanto o PC deve ser incrementado para tratar o erro.
    Essa instrução não altera o número de objetos na pilha.
</li>

<li>
<b>throw</b>() - 
    Instrução que realiza o desvio de execução para um bloco especifico, utilizando o objeto no topo da pilha como tratamento de erro.
    Essa instrução decrementa o número de objetos na pilha em uma unidade.
</li>

<li>
<b>throw_true</b>() - 
    Instrução que indica que não ocorreu nenhum erro num bloco protegido.
    Essa instrução não altera o número de objetos na pilha.
</li>

<li>
<b>throw_false</b>() - 
    Instrução que indica que ocorreu um erro e já foi tratado, atualizando o PC.
    Essa instrução não altera o número de objetos na pilha.
</li>

<li>
<b>throw_store</b>() - 
    Instrução que armazena o objeto Throwable em um local especifico.
    Essa instrução não altera o número de objetos na pilha.
</li>

<li>
<b>or</b>() - 
    Instrução que realiza a operação de lógica de conjunção.
    Os objetos utilizados serão os objetos desempilhado do topo da pilha.
    Será criado um objeto do tipo Boolean com a resposta, empilhando no topo da pilha.
    Essa instrução incrementa o número de objetos na pilha em uma unidade.
</li>

<li>
<b>and</b>() - 
    Instrução que realiza a operação de lógica de disjunça.
    Os objetos utilizados serão os objetos desempilhado do topo da pilha.
    Será criado um objeto do tipo Boolean com a resposta, empilhando no topo da pilha.
    Essa instrução incrementa o número de objetos na pilha em uma unidade.
</li>

<li>
<b>equal_boolean</b>() - 
    Instrução que realiza a operação de comparação entre objetos da classe Boolean.
    Os objetos utilizados serão os objetos desempilhado do topo da pilha.
    Será criado um objeto do tipo Boolean com a resposta, empilhando no topo da pilha.
    Essa instrução incrementa o número de objetos na pilha em uma unidade.
</li>

<li>
<b>equal_number</b>() - 
    Instrução que realiza a operação de comparação entre objetos da classe Number.
    Os objetos utilizados serão os objetos desempilhado do topo da pilha.
    Será criado um objeto do tipo Boolean com a resposta, empilhando no topo da pilha.
    Essa instrução incrementa o número de objetos na pilha em uma unidade.
</li>

<li>
<b>equal_string</b>() - 
    Instrução que realiza a operação de comparação entre objetos da classe String.
    Os objetos utilizados serão os objetos desempilhado do topo da pilha.
    Será criado um objeto do tipo Boolean com a resposta, empilhando no topo da pilha.
    Essa instrução incrementa o número de objetos na pilha em uma unidade.
</li>

<li>
<b>not_equal_boolean</b>() - 
    Instrução que realiza a operação a desiqualdade entre objetos da classe Boolean.
    Os objetos utilizados serão os objetos desempilhado do topo da pilha.
    Será criado um objeto do tipo Boolean com a resposta, empilhando no topo da pilha.
    Essa instrução incrementa o número de objetos na pilha em uma unidade.
</li>

<li>
<b>not_equal_number</b>() - 
    Instrução que realiza a operação a desiqualdade entre objetos da classe Number.
    Os objetos utilizados serão os objetos desempilhado do topo da pilha.
    Será criado um objeto do tipo Boolean com a resposta, empilhando no topo da pilha.
    Essa instrução incrementa o número de objetos na pilha em uma unidade.
</li>

<li>
<b>not_equal_string</b>() - 
    Instrução que realiza a operação a desiqualdade entre objetos da classe String.
    Os objetos utilizados serão os objetos desempilhado do topo da pilha.
    Será criado um objeto do tipo Boolean com a resposta, empilhando no topo da pilha.
    Essa instrução incrementa o número de objetos na pilha em uma unidade.
</li>

<li>
<b>high_equal_number</b>() - 
    Instrução que realiza a operação a comparação de maior entre objetos da classe Number.
    Os objetos utilizados serão os objetos desempilhado do topo da pilha.
    Será criado um objeto do tipo Boolean com a resposta, empilhando no topo da pilha.
    Essa instrução incrementa o número de objetos na pilha em uma unidade.
</li>

<li>
<b>high_equal_string</b>() - 
    Instrução que realiza a operação a comparação de maior entre objetos da classe String.
    Os objetos utilizados serão os objetos desempilhado do topo da pilha.
    Será criado um objeto do tipo Boolean com a resposta, empilhando no topo da pilha.
    Essa instrução incrementa o número de objetos na pilha em uma unidade.
</li>

<li>
<b>high_number</b>() - 
    Instrução que realiza a operação a comparação de maior entre objetos da classe Number.
    Os objetos utilizados serão os objetos desempilhado do topo da pilha.
    Será criado um objeto do tipo Boolean com a resposta, empilhando no topo da pilha.
    Essa instrução incrementa o número de objetos na pilha em uma unidade.
</li>

<li>
<b>high_string</b>() - 
    Instrução que realiza a operação a comparação de maior entre objetos da classe String.
    Os objetos utilizados serão os objetos desempilhado do topo da pilha.
    Será criado um objeto do tipo Boolean com a resposta, empilhando no topo da pilha.
    Essa instrução incrementa o número de objetos na pilha em uma unidade.
</li>

<li>
<b>low_equal_number</b>() - 
    Instrução que realiza a operação a comparação de maior entre objetos da classe Number.
    Os objetos utilizados serão os objetos desempilhado do topo da pilha.
    Será criado um objeto do tipo Boolean com a resposta, empilhando no topo da pilha.
    Essa instrução incrementa o número de objetos na pilha em uma unidade.
</li>

<li>
<b>low_equal_string</b>() - 
    Instrução que realiza a operação a comparação de maior entre objetos da classe String.
    Os objetos utilizados serão os objetos desempilhado do topo da pilha.
    Será criado um objeto do tipo Boolean com a resposta, empilhando no topo da pilha.
    Essa instrução incrementa o número de objetos na pilha em uma unidade.
</li>

<li>
<b>low_number</b>() - 
    Instrução que realiza a operação a comparação de maior entre objetos da classe Number.
    Os objetos utilizados serão os objetos desempilhado do topo da pilha.
    Será criado um objeto do tipo Boolean com a resposta, empilhando no topo da pilha.
    Essa instrução incrementa o número de objetos na pilha em uma unidade.
</li>

<li>
<b>low_string</b>() - 
    Instrução que realiza a operação a comparação de maior entre objetos da classe String.
    Os objetos utilizados serão os objetos desempilhado do topo da pilha.
    Será criado um objeto do tipo Boolean com a resposta, empilhando no topo da pilha.
    Essa instrução incrementa o número de objetos na pilha em uma unidade.
</li>

<li>
<b>sum_number</b>() - 
    Instrução que realiza a operação de soma entre objetos da classe Number.
    Os objetos utilizados serão os objetos desempilhado do topo da pilha.
    Será criado um objeto do tipo Boolean com a resposta, empilhando no topo da pilha.
    Essa instrução incrementa o número de objetos na pilha em uma unidade.
</li>

<li>
<b>sum_string</b>() - 
    Instrução que realiza a operação de soma entre objetos da classe String.
    Os objetos utilizados serão os objetos desempilhado do topo da pilha.
    Será criado um objeto do tipo Boolean com a resposta, empilhando no topo da pilha.
    Essa instrução incrementa o número de objetos na pilha em uma unidade.
</li>

<li>
<b>sub_number</b>() - 
    Instrução que realiza a operação de subtração entre objetos da classe Number.
    Os objetos utilizados serão os objetos desempilhado do topo da pilha.
    Será criado um objeto do tipo Boolean com a resposta, empilhando no topo da pilha.
    Essa instrução incrementa o número de objetos na pilha em uma unidade.
</li>

<li>
<b>mul_number</b>() - 
    Instrução que realiza a operação de multiplicação entre objetos da classe Number.
    Os objetos utilizados serão os objetos desempilhado do topo da pilha.
    Será criado um objeto do tipo Boolean com a resposta, empilhando no topo da pilha.
    Essa instrução incrementa o número de objetos na pilha em uma unidade.
</li>

<li>
<b>div_number</b>() - 
    Instrução que realiza a operação de divisão entre objetos da classe Number.
    Os objetos utilizados serão os objetos desempilhado do topo da pilha.
    Será criado um objeto do tipo Boolean com a resposta, empilhando no topo da pilha.
    Essa instrução incrementa o número de objetos na pilha em uma unidade.
</li>

</ul>