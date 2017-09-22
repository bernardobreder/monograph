<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p><dl><dt>
A classe ISqlConnection representa uma conex�o com um banco de dados.
Al�m disso, essa classe deve ser capaz de reconectar ao banco de dados, caso a comunica��o for perdida. 
Para isso, ser� necess�rio armazenar o usu�rio e senha para que a opera��o de <code>connect()</code> efetue a conex�o, caso seja perdido.
Caso queira finalizar uma conex�o, basta chamar a opera��o <code>close()</code>.
Caso queria iniciar uma transa��o, basta chamar a opera��o <code>begin()</code>.
</dt></dl></p>

<p><h2>
    public notnull ISqlTransaction begin () throws SqlException 
</h2><dl><dt>Descri��o :</dt><dd>
    M�todo utilizado para se criar uma transa��o com o banco.
</dd><dt>Parametro :</dt><dd>
</dd><dt>Retorno :</dt><dd>
    notnull ISqlTransaction - objeto que manipula a transa��o.
</dd><dt>Exce��o :</dt><dd>
    SqlException - Ocorrencia de um erro na cria��o de uma transa��o.
</dd></dl></p>

<p><h2>
    public void connect() throws SqlException 
</h2><dl><dt>Descri��o :</dt><dd>
    M�todo utilizado para se estabelecer uma conex�o com o servidor de banco de dados.
    Toda vez que for criar um objeto dessa interface, ser� necess�rio chamar a opera��o <code>connect()</code> para que possa efetuar a conex�o.
    Toda vez que a conex�o for finalizada ou interrumpida, poder� chamar essa opera��o para estabelecer a comunica��o.
    Caso a conex�o j� esteja estabelecida, essa opera��o ir� reconectar novamente.
</dd><dt>Parametro :</dt><dd>
</dd><dt>Retorno :</dt><dd>
</dd><dt>Exce��o :</dt><dd>
    SqlException - Ocorrencia de um erro na tentativa de conectar.
</dd></dl></p>

<p><h2>
    public void close() throws SqlException 
</h2><dl><dt>Descri��o :</dt><dd>
    M�todo utilizado para se finalizar a conex�o com o servidor de banco de dados.
    Caso a conex�o j� esteja finalizada, nada ir� acontecer.
</dd><dt>Parametro :</dt><dd>
</dd><dt>Retorno :</dt><dd>
</dd><dt>Exce��o :</dt><dd>
    SqlException - Ocorrencia de um erro na tentativa de finalizar a conex�o.
</dd></dl></p>