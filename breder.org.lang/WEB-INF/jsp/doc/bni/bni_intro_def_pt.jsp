<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p>
Nesse t�pico, iremos abordar como implementar um m�todo da Linguagem Breder nativamente. 
As principais vantagens de se criar um m�todo nativo s�o : 
</p>

<p>
Criar um m�todo com objetivo de ter uma grande performance.
Isso porque os m�todos implementados nativamente s�o processados com instru��es diretamente da m�quina, n�o havendo overhead de processamento da Linguagem Breder.
Por�m, a seguran�a de execu��o pode ser comprometida, se o programador n�o saber usar a Linguagem de baixo n�vel adequadamente.
</p>

<p>
Um outro crit�rio � quando os programadores precisam reutilizar c�digos que j� est�o implementados nativamente em outros projetos.
Por exemplo, caso queira usufruir de uma biblioteca gr�fica que est� implementado na Linguagem C ANSI, ser� necess�rio criar algumas opera��es nativas na qual a implementa��o ser� somente chamar as fun��es da biblioteca gr�fica.
</p>

<p>
A API b�sica da Linguagem Breder foi toda implementada nativamente, com o objetivo de ter grande performance em cima dela.
Portanto, todas as fun��es que a Linguagem Breder j� disponibiliza, s�o fun��es com grande grau de efici�ncia, sem agredir a seguran�a da execu��o.
</p>

<p>
A seguir, ser� constru�do um exemplo simples, demonstrando como criar opera��es nativas.
Na demonstra��o, ser� criado um diret�rio pr�prio para o teste.
O nome do diret�rio de teste � '/home/breder/test' em Unix ou 'c:\test' em Windows. 
</p>

<p>
Para se construir uma opera��o nativa, ser� necess�rio primeiro, construir uma classe com uma opera��o nativa.
Abaixo ser� demonstrado a cria��o de uma classe de nome 'Hello'.
</p>

<pre>
package hello;
public class Hello {
  public static native void hi () ;
  public static void main(IList&lt;String&gt; args) {
    Hello.hi();
  }
}
</pre>

<p>
O arquivo acima foi criado com o caminho '/home/breder/test/hello/Hello.breder' no Unix ou 'c:\breder\test\hello\Hello.breder' no Windows.
Nessa arquivo, tem uma classe declarada com um m�todo definido nativamente atrav�s da palavra chave 'native'.
</p>

<p>
Agora, ser� feito a compila��o do c�digo fonte criado atrav�s do comando <code>'brederc'</code>.
Mas, para isso, iremos primeiro avisar que iremos usar um arquivo compilado nativo com o nome 'myhello'.
Na diret�rio '/home/breder/test' no Unix ou 'c:\test' no Windows, iremos compilar da seguinte maneira :
</p>

<code>
brederc hello.Hello -l myhello
</code>

<p>
Caso n�o retorne nenhum aviso, significa que o c�digo fonte foi compilado com sucesso.
Iremos primeiro tentar executar o execut�vel na Maquina Virtual para ver qual erro que ir� ocorrer.
Na diret�rio '/home/breder/test' no Unix ou 'c:\test' no Windows, executar o seguinte comando :
</p>

<code>
breder binary.b -lp .
</code>

<p>
O erro 'Cannot open the library 'myhello'' foi gerado ao tentar executar o execut�vel na Maquina Virtual.
Isso porque n�o foi criado o arquivo compilado nativamente ainda.
</p>

<p>
Agora, ser� criado uma arquivo fonte da Linguagem C ANSI com o seguinte c�digo fonte no diret�rio '/home/breder/test/hello.c' no Unix ou 'c:\test\hello.c'.
</p>

<pre>
#include "b_bni.h"
b_bni_state_t hello_Hello$hi(b_vm_t* vm) {
  printf("Hello World!\n");
}
</pre>

<p>
Para compilar o arquivo fonte 'hello.c' basta executar o seguinte comando :
Na diret�rio '/home/breder/test' no Unix ou 'c:\test' no Windows, executar o seguinte comando :
</p>

<p>
No MacOS : gcc -shared -o myhello.dylib hello.c -I$BREDER_HOME/inc
</p>

<p>
No Linux : gcc -shared -o myhello.so hello.c -I$BREDER_HOME/inc
</p>

<p>
No Windows : gcc -shared -o myhello.dll hello.c -I%BREDER_HOME%/inc
</p>

<p>
Depois de ter executado o comando para compilar o c�digo fonte 'hello.c', ser� gerado o arquivo 'myhello.dylib' no MacOS ou 'myhello.so' no Linux ou 'myhello.dll' no Windows.
Esse arquivo, representa o c�digo fonte 'hello.c' compilado na forma que a Linguagem Breder entenda.
</p>

<p>
Agora que j� foi criado a biblioteca nativa, o erro gerado na tentativa de chamar o execut�vel n�o ir� acontecer com o seguinte comando.
Na diret�rio '/home/breder/test' no Unix ou 'c:\test' no Windows, executar o seguinte comando :
</p>

<code>
breder binary.b -lp .
</code>

<p>
Se tudo deu certo, a seguinte resposta deve ser imprimido : 'Hello World!'. 
</p>