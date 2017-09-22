<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p>
Nesse tópico, iremos abordar como implementar um método da Linguagem Breder nativamente. 
As principais vantagens de se criar um método nativo são : 
</p>

<p>
Criar um método com objetivo de ter uma grande performance.
Isso porque os métodos implementados nativamente são processados com instruções diretamente da máquina, não havendo overhead de processamento da Linguagem Breder.
Porém, a segurança de execução pode ser comprometida, se o programador não saber usar a Linguagem de baixo nível adequadamente.
</p>

<p>
Um outro critério é quando os programadores precisam reutilizar códigos que já estão implementados nativamente em outros projetos.
Por exemplo, caso queira usufruir de uma biblioteca gráfica que está implementado na Linguagem C ANSI, será necessário criar algumas operações nativas na qual a implementação será somente chamar as funções da biblioteca gráfica.
</p>

<p>
A API básica da Linguagem Breder foi toda implementada nativamente, com o objetivo de ter grande performance em cima dela.
Portanto, todas as funções que a Linguagem Breder já disponibiliza, são funções com grande grau de eficiência, sem agredir a segurança da execução.
</p>

<p>
A seguir, será construído um exemplo simples, demonstrando como criar operações nativas.
Na demonstração, será criado um diretório próprio para o teste.
O nome do diretório de teste é '/home/breder/test' em Unix ou 'c:\test' em Windows. 
</p>

<p>
Para se construir uma operação nativa, será necessário primeiro, construir uma classe com uma operação nativa.
Abaixo será demonstrado a criação de uma classe de nome 'Hello'.
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
Nessa arquivo, tem uma classe declarada com um método definido nativamente através da palavra chave 'native'.
</p>

<p>
Agora, será feito a compilação do código fonte criado através do comando <code>'brederc'</code>.
Mas, para isso, iremos primeiro avisar que iremos usar um arquivo compilado nativo com o nome 'myhello'.
Na diretório '/home/breder/test' no Unix ou 'c:\test' no Windows, iremos compilar da seguinte maneira :
</p>

<code>
brederc hello.Hello -l myhello
</code>

<p>
Caso não retorne nenhum aviso, significa que o código fonte foi compilado com sucesso.
Iremos primeiro tentar executar o executável na Maquina Virtual para ver qual erro que irá ocorrer.
Na diretório '/home/breder/test' no Unix ou 'c:\test' no Windows, executar o seguinte comando :
</p>

<code>
breder binary.b -lp .
</code>

<p>
O erro 'Cannot open the library 'myhello'' foi gerado ao tentar executar o executável na Maquina Virtual.
Isso porque não foi criado o arquivo compilado nativamente ainda.
</p>

<p>
Agora, será criado uma arquivo fonte da Linguagem C ANSI com o seguinte código fonte no diretório '/home/breder/test/hello.c' no Unix ou 'c:\test\hello.c'.
</p>

<pre>
#include "b_bni.h"
b_bni_state_t hello_Hello$hi(b_vm_t* vm) {
  printf("Hello World!\n");
}
</pre>

<p>
Para compilar o arquivo fonte 'hello.c' basta executar o seguinte comando :
Na diretório '/home/breder/test' no Unix ou 'c:\test' no Windows, executar o seguinte comando :
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
Depois de ter executado o comando para compilar o código fonte 'hello.c', será gerado o arquivo 'myhello.dylib' no MacOS ou 'myhello.so' no Linux ou 'myhello.dll' no Windows.
Esse arquivo, representa o código fonte 'hello.c' compilado na forma que a Linguagem Breder entenda.
</p>

<p>
Agora que já foi criado a biblioteca nativa, o erro gerado na tentativa de chamar o executável não irá acontecer com o seguinte comando.
Na diretório '/home/breder/test' no Unix ou 'c:\test' no Windows, executar o seguinte comando :
</p>

<code>
breder binary.b -lp .
</code>

<p>
Se tudo deu certo, a seguinte resposta deve ser imprimido : 'Hello World!'. 
</p>