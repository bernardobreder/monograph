<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p>
O processo de instalação consiste em atribuir uma variável de ambiente BREDER_HOME e acrescentar a frente do valor da variável de ambiente PATH um caminho especifico, dependendo do sistema operacional em que se esteja trabalhando.
</p>

<p>
<b>Importante</b> : O compilador foi construido na Linguagem Java. 
Portanto para que o compilador da Linguagem Breder funcione, é preciso que a Máquina Virtual Java esteja instalada. 
</p>

<p>
No Windows, para adicionar uma variável de ambiente, basta ir no <code>"Painel de Controle"</code> -> <code>"Sistema"</code>.
Escolha a Aba <code>"Avançado"</code> e clique no botão <code>"Variável de Ambiente"</code>. 
Nessa janela, terão 2 listas, uma para variáveis de usuário e outra de sistema.
Com isso, basta adicionar uma nova variável <code>BREDER_HOME</code> com o valor do caminho aonde foi extraido o conteúdo do download, na lista do sistema.
Por exemplo, suponha que foi feito o download do arquivo zip na pasta <code>"c:\"</code>. 
Deve-se então, extrair o conteúdo do zip para uma pasta <code>"breder"</code>, ficando <code>"c:\breder"</code>.
Com isso, basta atribuir a variável de ambiente <code>BREDER_HOME</code> com o valor "c:\breder".
Feito isso, o valor da variável de ambiente <code>PATH</code> contido na lista de sistema será modificado, acrescentando no final do valor : <code>;%BREDER_HOME%\bin</code>
</p>
<p>
No Windows, existe passo a passo de como configurar : <b><a href="http://www.breder.org/disk/lang/files/install_win_xp.pdf">clique aqui</a></b>
</p>

<p>
No Linux, MacOs ou qualquer outra distribuição Unix, primeiro deve-se abaixar a Linguagem na sessão de Download, extraindo o seu conteúdo para uma pasta, por exemplo <code>"/usr/local/breder"</code>.
Em seguida, basta criar dois links apontando para os executáveis localizado na pasta <code>"/usr/local/breder/bin"</code>. 
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
