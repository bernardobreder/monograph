<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p>
Atualmente, existem muitas linguagens que atender a diversos tipos de projetos. 
Podem ter projetos que necessitam de grande performance. 
Podem ter outros que precisem de um estruturas de dados complexas. 
Ou, podem ter projetos requerem muita segurança tanto no código quanto na execução.
</p>

<p>
A segurança mencionada acima é, normalmente, inversamente proporcional a eficiência do código.
A idéia de um código seguro seria ter um código na qual o desenvolvedor se assegura que aquele código não irá falhar ou gerar um bug.
Para que a segurança de execução ocorra, normalmente é usado ferramentas já implementada que torna o problema mais fácil.
Então, o objeto é tornar uma problema complicado, em um problema fácil, a partir do uso de ferramentas já implementada que se enquadra no contexto.
Porém, o uso dessa ferramenta, pode tornar o resultado final menos eficiente porque foi usado uma estrutura que foi adaptada ao problema.
</p>

<p>
Quando se deseja desenvolver um aplicativo que necessita de muita performance, normalmente é necessário uma Linguagem de Programação compilada.
Isso porque as Linguagens compiladas transformam o código fonte em instruções de máquina, tornando a sua execução muito eficiente.
Porém, como as instruções são de baixo nível, a segurança da execução do código é comprometida. 
Além disso, as Linguagens compiladas, normalmente, não oferecem muitos recursos para criação de estrutura de dados complexas.
</p>

<p>
Quando se deseja desenvolver um aplicativo que necessita de uma grande organização estrutural, normalmente é utilizado uma Linguagem compilada de alto nível ou uma Linguagens Interpretadas.
Dependendo da analise do projeto, a Linguagem compilada e interpretada é escolhida em função de manter a grande performance ou a segurança de código.
Caso seja mais importante a performance, é mais adequado escolher a Linguagem compilada.
Porém, caso a segurança de execução for um fator primordial, a Linguagem interpretada será mais adequado. 
</p>

<p>
Com a evolução dos aplicativos e o aumento da complexidade deles, a Linguagem de Programação de alto nível tem mais se adequado a projetos atuais que necessitam de muita estrutura de dados.
Mesmo assim, diversos projetos que necessitam de recursos em baixo nível tem sendo desenvolvido com a finalidade de servir como biblioteca para projetos de alto nível.
</p>

<p>
A partir disso, foi elaborado uma Linguagem de Programação que organiza esse quadro, aonde o desenvolvimento de alto nível utilizasse as funções desenvolvida em baixo e alto nível.
Com isso, foi elaborado diversas especificações de forma a manter um bom desenvolvimento nos dois ambientes de programação.
</p>

<p>
Para que tenha um desenvolvimento em alto nível seguro, é necessário que a Linguagem de Programação seja interpretada.
Além disso, para manter o grande desempenho no ambiente baixo nível, será necessário que se programe em uma Linguagem compilada, para que a Linguagem de alto nível o utilize.
</p>

<p>
Portanto, a Linguagem de Programa proposta precise ter uma boa comunicação entre os dois ambiente, não havendo nenhum custo de processamento.
Além disso, como a Linguagem será de algo nível, podemos utilizar a Orientado a Objeto.
</p>

<p>
O objetivo da Linguagem é fornecer ferramentas aos usuários para a obtenção de um código claro, simples e de alto nível. 
Além disso, a Linguagem Breder possui aspectos semelhantes a da Linguagem Java, Lua e C++, trazendo qualidades destas.
A Linguagem Breder é fortemente tipada, permitindo encontrar diversos erros sintáticos e semânticos em tempo de compilação, facilitando o desenvolvimento. 
</p>

<p>
A Linguagem Breder foi desenvolvida para ambientes Windows, Linux e MacOS, permitindo o uso do mesmo código fonte nesses ambientes. 
Além dessas, diversos dispositivos embarcados foram testados de forma a calcular o grau do custo que a Linguagem Breder está gerando.
</p>

<p>
Além disso, foi desenvolvido uma Máquina Virtual Breder que abstrai todos os recursos específicos do Hardware e do Sistema Operacional, mantendo assim uma homogeneidade na execução do aplicativo, mesmo sendo para dispositivos embarcados ou não, para que todos trabalhem de forma homogênea.
</p>

<p>
Além disso, a Linguagem Breder permite compilar códigos fontes, gerando somente um arquivo executável para a Máquina Virtual Breder. 
Assim, esse arquivo executável poderá rodar em qualquer computador, cuja Máquina Virtual Breder esteja instalada.
</p>

<p>
A Linguagem Breder possui características interessante que torna o desenvolvimento mais produtivo e confiável.
Tendo como principais características a Orientação a Objeto e a utilização da Coleta de Lixo, que libera os objetos da memória automaticamente.
Assim, todo projeto será dividido em ambiente alto e baixo nível, na maneira que no ambiente de baixo nível servisse como biblioteca para o ambiente alto nível. 
Além disso, a Linguagem Breder possibilita um melhor encapsulamento e proteção para campos nulos ou não.
</p>

<p>
<b>O objetivo principal da Linguagem Breder é atender projetos que necessitam de processamentos intensos em ambientes baixo nível e processamentos estruturados em ambiente alto nível.</b>
Então, toda a programação baixo nível será desenvolvida em ambiente compilado e todo desenvolvimento alto nível será produzido na Linguagem Breder.
Para isso, foi criado diversos aspectos internos que asseguram a grande performance na comunicação entre a Linguagem Breder e outras Linguagens Compiladas.
Ao longo da documentação será observado que quase todos os métodos da API básica disponíveis estão implementados nativamente na linguagem C ANSI, pois será o papel da Linguagem baixo nível fornecer funções básicas para a Linguagem Breder, com grande performance, devido as funções implementadas compiladamente serem mais rápidas do que as interpretadas.
</p>
