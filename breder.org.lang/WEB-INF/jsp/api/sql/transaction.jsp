<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p><dl><dt>
A classe ISqlTransaction representa uma transa��o com um banco de dados.
</dt></dl></p>

<p><h2>
    public notnull IList&lt;IList&lt;ISqlPrimitive&gt;&gt; query (notnull String sql, IList&lt;ISqlPrimitive&gt; arguments) throws SqlException  
</h2><dl><dt>Descri��o :</dt><dd>
    M�todo utilizado para se fazer uma query. 
    Essa query conter� o comando sql no primeiro par�metro com os valores atribuido com o caracter '?'.
    Os argumentos escondidos pelo caracter '?', ser�o substituidos internamente pelos par%ametros da lista passado pelo segundo argumento.
    Caso n�o tenha nenhum par�metro para ser passado para a query, o segundo argumento poder� ser nulo.
    Caso a query retorne algum ou nenhum valor, ser� sempre criado uma lista de tuplas que representa a resposta da query.
</dd><dt>Parametro :</dt><dd>
    notnull String sql - Query em SQL
</dd><dd>
    IList&lt;ISqlPrimitive&gt; arguments - Argumentos da query a ser passado como parametro.
</dd><dt>Retorno :</dt><dd>
    notnull IList&lt;IList&lt;ISqlPrimitive&gt;&gt; - Lista de tuplas que representa a resposta da query.
</dd><dt>Exce��o :</dt><dd>
    SqlException - Ocorrencia de um erro.
</dd></dl></p>

<p><h2>
    public void commit () throw SqlException
</h2><dl><dt>Descri��o :</dt><dd>
    M�todo utilizado para efetuar o commit de todas as mudan�as ocorridas na transa��o.
    A partir desse m�todo, nenhuma opera��o pode ser feita nesse objeto. 
</dd><dt>Parametro :</dt><dd>
</dd><dt>Retorno :</dt><dd>
</dd><dt>Exce��o :</dt><dd>
    SqlException - Ocorrencia de um erro.
</dd></dl></p>

<p><h2>
    public void rollback ()  
</h2><dl><dt>Descri��o :</dt><dd>
    M�todo utilizado para desfazer todas as mudan�as ocorridas na transa��o.
    A partir desse m�todo, nenhuma opera��o pode ser feita nesse objeto. 
</dd><dt>Parametro :</dt><dd>
</dd><dt>Retorno :</dt><dd>
</dd><dt>Exce��o :</dt><dd>
    SqlException - Ocorrencia de um erro.
</dd></dl></p>

