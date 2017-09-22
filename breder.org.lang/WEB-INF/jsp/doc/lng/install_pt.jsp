<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p>
O processo de instala��o consiste em atribuir uma vari�vel de ambiente BREDER_HOME e acrescentar a frente do valor da vari�vel de ambiente PATH um caminho especifico, dependendo do sistema operacional em que se esteja trabalhando.
</p>

<p>
<b>Importante</b> : O compilador foi construido na Linguagem Java. 
Portanto para que o compilador da Linguagem Breder funcione, � preciso que a M�quina Virtual Java esteja instalada. 
</p>

<p>
No Windows, para adicionar uma vari�vel de ambiente, basta ir no <code>"Painel de Controle"</code> -> <code>"Sistema"</code>.
Escolha a Aba <code>"Avan�ado"</code> e clique no bot�o <code>"Vari�vel de Ambiente"</code>. 
Nessa janela, ter�o 2 listas, uma para vari�veis de usu�rio e outra de sistema.
Com isso, basta adicionar uma nova vari�vel <code>BREDER_HOME</code> com o valor do caminho aonde foi extraido o conte�do do download, na lista do sistema.
Por exemplo, suponha que foi feito o download do arquivo zip na pasta <code>"c:\"</code>. 
Deve-se ent�o, extrair o conte�do do zip para uma pasta <code>"breder"</code>, ficando <code>"c:\breder"</code>.
Com isso, basta atribuir a vari�vel de ambiente <code>BREDER_HOME</code> com o valor "c:\breder".
Feito isso, o valor da vari�vel de ambiente <code>PATH</code> contido na lista de sistema ser� modificado, acrescentando no final do valor : <code>;%BREDER_HOME%\bin</code>
</p>
<p>
No Windows, existe passo a passo de como configurar : <b><a href="http://www.breder.org/disk/lang/files/install_win_xp.pdf">clique aqui</a></b>
</p>

<p>
No Linux, MacOs ou qualquer outra distribui��o Unix, primeiro deve-se abaixar a Linguagem na sess�o de Download, extraindo o seu conte�do para uma pasta, por exemplo <code>"/usr/local/breder"</code>.
Em seguida, basta criar dois links apontando para os execut�veis localizado na pasta <code>"/usr/local/breder/bin"</code>. 
Para criar os links, basta executar o seguinte comando seguindo o exemplo citado acima :
</p>

<p>
sudo ln -s /usr/local/breder/bin/breder /usr/local/bin/breder
</p>
<p>
sudo ln -s /usr/local/breder/bin/brederc /usr/local/bin/brederc
</p>
<p>
sudo chmod +x /usr/local/bin/breder
</p>
<p>
sudo chmod +x /usr/local/bin/brederc
</p>
