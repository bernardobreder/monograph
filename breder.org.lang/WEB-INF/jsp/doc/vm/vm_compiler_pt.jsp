
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p>
	O compilador da Linguagem Breder tem a função de transformar diversos códigos fontes da Linguagem em um arquivo compilado "binary.b".
	Esse arquivo representa um arquivo executável para a Máquina Virtual Breder, aonde todas as classes, métodos, campos e instruções forem posto num único arquivo.
</p>

<p>
	Como já citado, o compilador cria uma arquivo executável que representa a união de várias classes e instruções.
	Esse arquivo possui as seguintes informações :
</p>

<ul>
	<li>
	<b>Caracteres Chaves</b> - 
	Caracteres que descreve que o arquivo é um arquivo binário para a Máquina Virtual Breder. 
	Com isso, foi colocado 2 caracteres que representam a letra 'B'.  
	</li>
	
	<li>
	<b>Classe de Inicialização</b> - 
	Informação do nome da classe que será executado o método "public static void main(IList\<String> args)".
	Nessa classe deverá ter esse método inicializador. 
	</li>
	
	<li>
	<b>Bibliotecas Nativas</b> - 
	Informação de quais bibliotecas nativas foram importada em tempo de compilação.
	Essas bibliotecas serão consultadas para implementar os métodos nativos. 
	</li>
	
	<li>
	<b>Classes</b> - 
	Informação de todos os nomes de todas as classes compiladas.
	</li>
	
	<li>
	<b>Hierarquia</b> - 
	Informação de todas as ligações hierarquica entre classes e interfaces.
	</li>
	
	<li>
	<b>Campos</b> - 
	Informação de todos os campos que cada classe possui.
	</li>
	
	<li>
	<b>Métodos</b> - 
	Informação de todos os cabeçalhos de método que cada classe possui e suas características.
	</li>
	
	<li>
	<b>Strings</b> - 
	Informação de as constantes de String utilizada no código fonte.
	</li>
	
	<li>
	<b>Numbers</b> - 
	Informação de as constantes de Number utilizada no código fonte.
	</li>
	
	<li>
	<b>Instruções</b> - 
	Todas as instruções de todas as classes. 
	As instruções estão organizado numa lista seqüencial, aonde os métodos estão sempre apontando para algum elemento da lista para executar.
	</li>
</ul>

<p>
A partir do cabeçalho do arquivo executável da Linguagem Breder, a Máquina Virtual Breder irá carregar o arquivo executável.
Quando é carregado o arquivo, é criado as classes, métodos, campos e outro de acordo com a especificação do executável.
</p>

<p>
Depois de carregado toda a máquina virtual a partir de um arquivo executável, será chamado o método inicializador "public static void main(IList\<String> args)" da classe definido no cabeçalho do executável.
Nesse método será executado todo o programa, até que ele seja finalizado.
</p>

<p>
Ao método inicializador ser terminado, a máquina virtual irá se finalizar para liberar a memória dos objetos alocados em tempo de execução. 
</p>