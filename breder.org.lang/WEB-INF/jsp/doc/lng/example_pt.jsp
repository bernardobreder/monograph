<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p>
Abaixo ser� constru�do um c�digo fonte de exemplo para testar se o compilador foi bem instalado e est� funcionando. 
Com isso, deve ser criado um arquivo de nome "HelloWorld.breder" e ser� executado um comando para compilar o programa e outro comando para executar o aplicativo compilado.
</p>
<pre>
public class HelloWorld {
  public static void main(IList&lt;String&gt; args) {
    Console.println("Hello World!");
  }
}
</pre>

<p>
Todos os arquivos e comandos apresentados aqui, ser�o postos e executados numa pasta especifica para o teste.
Esse c�digo fonte foi salvo com o nome <code>"HelloWorld.breder"</code> na pasta <code>"test"</code>. 
Utilizando o diret�rio corrente por exemplo "/home/breder/test" ou "c:\test", existir� o arquivo "/home/breder/test/HelloWorld.breder" ou "c:\test\HelloWorld.breder".
</p>

<p>
Agora, na pasta "/home/breder/test" ou "c:\test", basta compilar o c�digo fonte com o seguinte comando: 
</p>

<pre>
brederc HelloWorld
</pre>

<p>
Execute, na pasta "/home/breder/test" ou "c:\test", a maquina virtual a partir do arquivo que foi gerado pelo compilador com o seguinte comando : 
</p>

<pre>
breder binary.b
</pre>