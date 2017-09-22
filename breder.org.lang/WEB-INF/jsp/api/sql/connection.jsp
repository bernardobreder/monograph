<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p><dl><dt>
A classe ISqlConnection representa uma conexão com um banco de dados.
Além disso, essa classe deve ser capaz de reconectar ao banco de dados, caso a comunicação for perdida. 
Para isso, será necessário armazenar o usuário e senha para que a operação de <code>connect()</code> efetue a conexão, caso seja perdido.
Caso queira finalizar uma conexão, basta chamar a operação <code>close()</code>.
Caso queria iniciar uma transação, basta chamar a operação <code>begin()</code>.
</dt></dl></p>

<p><h2>
    public notnull ISqlTransaction begin () throws SqlException 
</h2><dl><dt>Descrição :</dt><dd>
    Método utilizado para se criar uma transação com o banco.
</dd><dt>Parametro :</dt><dd>
</dd><dt>Retorno :</dt><dd>
    notnull ISqlTransaction - objeto que manipula a transação.
</dd><dt>Exceção :</dt><dd>
    SqlException - Ocorrencia de um erro na criação de uma transação.
</dd></dl></p>

<p><h2>
    public void connect() throws SqlException 
</h2><dl><dt>Descrição :</dt><dd>
    Método utilizado para se estabelecer uma conexão com o servidor de banco de dados.
    Toda vez que for criar um objeto dessa interface, será necessário chamar a operação <code>connect()</code> para que possa efetuar a conexão.
    Toda vez que a conexão for finalizada ou interrumpida, poderá chamar essa operação para estabelecer a comunicação.
    Caso a conexão já esteja estabelecida, essa operação irá reconectar novamente.
</dd><dt>Parametro :</dt><dd>
</dd><dt>Retorno :</dt><dd>
</dd><dt>Exceção :</dt><dd>
    SqlException - Ocorrencia de um erro na tentativa de conectar.
</dd></dl></p>

<p><h2>
    public void close() throws SqlException 
</h2><dl><dt>Descrição :</dt><dd>
    Método utilizado para se finalizar a conexão com o servidor de banco de dados.
    Caso a conexão já esteja finalizada, nada irá acontecer.
</dd><dt>Parametro :</dt><dd>
</dd><dt>Retorno :</dt><dd>
</dd><dt>Exceção :</dt><dd>
    SqlException - Ocorrencia de um erro na tentativa de finalizar a conexão.
</dd></dl></p>