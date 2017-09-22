<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p>
Atualmente, existem muitas linguagens que atender a diversos tipos de projetos. 
Podem ter projetos que necessitam de grande performance. 
Podem ter outros que precisem de um estruturas de dados complexas. 
Ou, podem ter projetos requerem muita seguran�a tanto no c�digo quanto na execu��o.
</p>

<p>
A seguran�a mencionada acima �, normalmente, inversamente proporcional a efici�ncia do c�digo.
A id�ia de um c�digo seguro seria ter um c�digo na qual o desenvolvedor se assegura que aquele c�digo n�o ir� falhar ou gerar um bug.
Para que a seguran�a de execu��o ocorra, normalmente � usado ferramentas j� implementada que torna o problema mais f�cil.
Ent�o, o objeto � tornar uma problema complicado, em um problema f�cil, a partir do uso de ferramentas j� implementada que se enquadra no contexto.
Por�m, o uso dessa ferramenta, pode tornar o resultado final menos eficiente porque foi usado uma estrutura que foi adaptada ao problema.
</p>

<p>
Quando se deseja desenvolver um aplicativo que necessita de muita performance, normalmente � necess�rio uma Linguagem de Programa��o compilada.
Isso porque as Linguagens compiladas transformam o c�digo fonte em instru��es de m�quina, tornando a sua execu��o muito eficiente.
Por�m, como as instru��es s�o de baixo n�vel, a seguran�a da execu��o do c�digo � comprometida. 
Al�m disso, as Linguagens compiladas, normalmente, n�o oferecem muitos recursos para cria��o de estrutura de dados complexas.
</p>

<p>
Quando se deseja desenvolver um aplicativo que necessita de uma grande organiza��o estrutural, normalmente � utilizado uma Linguagem compilada de alto n�vel ou uma Linguagens Interpretadas.
Dependendo da analise do projeto, a Linguagem compilada e interpretada � escolhida em fun��o de manter a grande performance ou a seguran�a de c�digo.
Caso seja mais importante a performance, � mais adequado escolher a Linguagem compilada.
Por�m, caso a seguran�a de execu��o for um fator primordial, a Linguagem interpretada ser� mais adequado. 
</p>

<p>
Com a evolu��o dos aplicativos e o aumento da complexidade deles, a Linguagem de Programa��o de alto n�vel tem mais se adequado a projetos atuais que necessitam de muita estrutura de dados.
Mesmo assim, diversos projetos que necessitam de recursos em baixo n�vel tem sendo desenvolvido com a finalidade de servir como biblioteca para projetos de alto n�vel.
</p>

<p>
A partir disso, foi elaborado uma Linguagem de Programa��o que organiza esse quadro, aonde o desenvolvimento de alto n�vel utilizasse as fun��es desenvolvida em baixo e alto n�vel.
Com isso, foi elaborado diversas especifica��es de forma a manter um bom desenvolvimento nos dois ambientes de programa��o.
</p>

<p>
Para que tenha um desenvolvimento em alto n�vel seguro, � necess�rio que a Linguagem de Programa��o seja interpretada.
Al�m disso, para manter o grande desempenho no ambiente baixo n�vel, ser� necess�rio que se programe em uma Linguagem compilada, para que a Linguagem de alto n�vel o utilize.
</p>

<p>
Portanto, a Linguagem de Programa proposta precise ter uma boa comunica��o entre os dois ambiente, n�o havendo nenhum custo de processamento.
Al�m disso, como a Linguagem ser� de algo n�vel, podemos utilizar a Orientado a Objeto.
</p>

<p>
O objetivo da Linguagem � fornecer ferramentas aos usu�rios para a obten��o de um c�digo claro, simples e de alto n�vel. 
Al�m disso, a Linguagem Breder possui aspectos semelhantes a da Linguagem Java, Lua e C++, trazendo qualidades destas.
A Linguagem Breder � fortemente tipada, permitindo encontrar diversos erros sint�ticos e sem�nticos em tempo de compila��o, facilitando o desenvolvimento. 
</p>

<p>
A Linguagem Breder foi desenvolvida para ambientes Windows, Linux e MacOS, permitindo o uso do mesmo c�digo fonte nesses ambientes. 
Al�m dessas, diversos dispositivos embarcados foram testados de forma a calcular o grau do custo que a Linguagem Breder est� gerando.
</p>

<p>
Al�m disso, foi desenvolvido uma M�quina Virtual Breder que abstrai todos os recursos espec�ficos do Hardware e do Sistema Operacional, mantendo assim uma homogeneidade na execu��o do aplicativo, mesmo sendo para dispositivos embarcados ou n�o, para que todos trabalhem de forma homog�nea.
</p>

<p>
Al�m disso, a Linguagem Breder permite compilar c�digos fontes, gerando somente um arquivo execut�vel para a M�quina Virtual Breder. 
Assim, esse arquivo execut�vel poder� rodar em qualquer computador, cuja M�quina Virtual Breder esteja instalada.
</p>

<p>
A Linguagem Breder possui caracter�sticas interessante que torna o desenvolvimento mais produtivo e confi�vel.
Tendo como principais caracter�sticas a Orienta��o a Objeto e a utiliza��o da Coleta de Lixo, que libera os objetos da mem�ria automaticamente.
Assim, todo projeto ser� dividido em ambiente alto e baixo n�vel, na maneira que no ambiente de baixo n�vel servisse como biblioteca para o ambiente alto n�vel. 
Al�m disso, a Linguagem Breder possibilita um melhor encapsulamento e prote��o para campos nulos ou n�o.
</p>

<p>
<b>O objetivo principal da Linguagem Breder � atender projetos que necessitam de processamentos intensos em ambientes baixo n�vel e processamentos estruturados em ambiente alto n�vel.</b>
Ent�o, toda a programa��o baixo n�vel ser� desenvolvida em ambiente compilado e todo desenvolvimento alto n�vel ser� produzido na Linguagem Breder.
Para isso, foi criado diversos aspectos internos que asseguram a grande performance na comunica��o entre a Linguagem Breder e outras Linguagens Compiladas.
Ao longo da documenta��o ser� observado que quase todos os m�todos da API b�sica dispon�veis est�o implementados nativamente na linguagem C ANSI, pois ser� o papel da Linguagem baixo n�vel fornecer fun��es b�sicas para a Linguagem Breder, com grande performance, devido as fun��es implementadas compiladamente serem mais r�pidas do que as interpretadas.
</p>
