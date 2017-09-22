
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p>
	O compilador da Linguagem Breder tem a fun��o de transformar diversos c�digos fontes da Linguagem em um arquivo compilado "binary.b".
	Esse arquivo representa um arquivo execut�vel para a M�quina Virtual Breder, aonde todas as classes, m�todos, campos e instru��es forem posto num �nico arquivo.
</p>

<p>
	Como j� citado, o compilador cria uma arquivo execut�vel que representa a uni�o de v�rias classes e instru��es.
	Esse arquivo possui as seguintes informa��es :
</p>

<ul>
	<li>
	<b>Caracteres Chaves</b> - 
	Caracteres que descreve que o arquivo � um arquivo bin�rio para a M�quina Virtual Breder. 
	Com isso, foi colocado 2 caracteres que representam a letra 'B'.  
	</li>
	
	<li>
	<b>Classe de Inicializa��o</b> - 
	Informa��o do nome da classe que ser� executado o m�todo "public static void main(IList\<String> args)".
	Nessa classe dever� ter esse m�todo inicializador. 
	</li>
	
	<li>
	<b>Bibliotecas Nativas</b> - 
	Informa��o de quais bibliotecas nativas foram importada em tempo de compila��o.
	Essas bibliotecas ser�o consultadas para implementar os m�todos nativos. 
	</li>
	
	<li>
	<b>Classes</b> - 
	Informa��o de todos os nomes de todas as classes compiladas.
	</li>
	
	<li>
	<b>Hierarquia</b> - 
	Informa��o de todas as liga��es hierarquica entre classes e interfaces.
	</li>
	
	<li>
	<b>Campos</b> - 
	Informa��o de todos os campos que cada classe possui.
	</li>
	
	<li>
	<b>M�todos</b> - 
	Informa��o de todos os cabe�alhos de m�todo que cada classe possui e suas caracter�sticas.
	</li>
	
	<li>
	<b>Strings</b> - 
	Informa��o de as constantes de String utilizada no c�digo fonte.
	</li>
	
	<li>
	<b>Numbers</b> - 
	Informa��o de as constantes de Number utilizada no c�digo fonte.
	</li>
	
	<li>
	<b>Instru��es</b> - 
	Todas as instru��es de todas as classes. 
	As instru��es est�o organizado numa lista seq�encial, aonde os m�todos est�o sempre apontando para algum elemento da lista para executar.
	</li>
</ul>

<p>
A partir do cabe�alho do arquivo execut�vel da Linguagem Breder, a M�quina Virtual Breder ir� carregar o arquivo execut�vel.
Quando � carregado o arquivo, � criado as classes, m�todos, campos e outro de acordo com a especifica��o do execut�vel.
</p>

<p>
Depois de carregado toda a m�quina virtual a partir de um arquivo execut�vel, ser� chamado o m�todo inicializador "public static void main(IList\<String> args)" da classe definido no cabe�alho do execut�vel.
Nesse m�todo ser� executado todo o programa, at� que ele seja finalizado.
</p>

<p>
Ao m�todo inicializador ser terminado, a m�quina virtual ir� se finalizar para liberar a mem�ria dos objetos alocados em tempo de execu��o. 
</p>