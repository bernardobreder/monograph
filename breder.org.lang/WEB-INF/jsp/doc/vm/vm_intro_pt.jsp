<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p>
A maioria das Linguagens compiladas utilizam de um código fonte para poder gerar um executável compatível à máquina que foi compilada.
A partir disso, o executável não deve ser executada em outra máquina caso a compilação foi específica de um computador.
Para resolver isso, a compilação é feita em uma maquina 386 aonde todas as instruções do 386 constam nos computadores atuais.
Assim, ao compilar numa arquitetura 386, o executável gerado pode ser rodado em qualquer computador que seja superior ao 386.
</p>

<p>
Porém, mesmo que a Linguagem compilada tenha resolvido o problema da arquitetura que o executável foi gerado, não resolve o problema de qual Sistema Operacional foi compilado.
Portanto, para que um programa possa ser rodado em qualquer máquina, é necessário que o executável seja compatível a Arquitetura e ao Sistema Operacional.
Com isso, um programa compilado para 386 no Windows, não irá ser executado em uma máquina cujo Sistema Operacional seja do tipo Unix. 
</p>

<p>
Para resolver esse problema, foi elaborado uma abstração de uma Computador e de um Sistema Operacional, aonde qualquer programa deva executar em qualquer máquina, independente de sua Arquitetura e do seu Sistema Operacional.
Com isso, o conceito de Máquina Virtual foi elaborado para abstrair dessas especificações de Arquitetura e Sistema Operacional que cada computador possui.  
</p>

<p>
A Maquina Virtual Breder foi elaborada para executar instruções que consideram a Orientação a Objeto, Coleta de Lixo, Exceções e outras funcionalidades. 
Para isso, foi criado diversas instruções que possibilitam a execução dessas funcionalidades, além das instruções básicas.
</p>