<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p>
A maioria das Linguagens compiladas utilizam de um c�digo fonte para poder gerar um execut�vel compat�vel � m�quina que foi compilada.
A partir disso, o execut�vel n�o deve ser executada em outra m�quina caso a compila��o foi espec�fica de um computador.
Para resolver isso, a compila��o � feita em uma maquina 386 aonde todas as instru��es do 386 constam nos computadores atuais.
Assim, ao compilar numa arquitetura 386, o execut�vel gerado pode ser rodado em qualquer computador que seja superior ao 386.
</p>

<p>
Por�m, mesmo que a Linguagem compilada tenha resolvido o problema da arquitetura que o execut�vel foi gerado, n�o resolve o problema de qual Sistema Operacional foi compilado.
Portanto, para que um programa possa ser rodado em qualquer m�quina, � necess�rio que o execut�vel seja compat�vel a Arquitetura e ao Sistema Operacional.
Com isso, um programa compilado para 386 no Windows, n�o ir� ser executado em uma m�quina cujo Sistema Operacional seja do tipo Unix. 
</p>

<p>
Para resolver esse problema, foi elaborado uma abstra��o de uma Computador e de um Sistema Operacional, aonde qualquer programa deva executar em qualquer m�quina, independente de sua Arquitetura e do seu Sistema Operacional.
Com isso, o conceito de M�quina Virtual foi elaborado para abstrair dessas especifica��es de Arquitetura e Sistema Operacional que cada computador possui.  
</p>

<p>
A Maquina Virtual Breder foi elaborada para executar instru��es que consideram a Orienta��o a Objeto, Coleta de Lixo, Exce��es e outras funcionalidades. 
Para isso, foi criado diversas instru��es que possibilitam a execu��o dessas funcionalidades, al�m das instru��es b�sicas.
</p>