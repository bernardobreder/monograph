<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p><dl><dt>
A classe ISqlTransaction representa uma transação com um banco de dados.
</dt></dl></p>

<p><h2>
    public notnull IList&lt;IList&lt;ISqlPrimitive&gt;&gt; query (notnull String sql, IList&lt;ISqlPrimitive&gt; arguments) throws SqlException  
</h2><dl><dt>Descrição :</dt><dd>
    Método utilizado para se fazer uma query. 
    Essa query conterá o comando sql no primeiro parâmetro com os valores atribuido com o caracter '?'.
    Os argumentos escondidos pelo caracter '?', serão substituidos internamente pelos par%ametros da lista passado pelo segundo argumento.
    Caso não tenha nenhum parâmetro para ser passado para a query, o segundo argumento poderá ser nulo.
    Caso a query retorne algum ou nenhum valor, será sempre criado uma lista de tuplas que representa a resposta da query.
</dd><dt>Parametro :</dt><dd>
    notnull String sql - Query em SQL
</dd><dd>
    IList&lt;ISqlPrimitive&gt; arguments - Argumentos da query a ser passado como parametro.
</dd><dt>Retorno :</dt><dd>
    notnull IList&lt;IList&lt;ISqlPrimitive&gt;&gt; - Lista de tuplas que representa a resposta da query.
</dd><dt>Exceção :</dt><dd>
    SqlException - Ocorrencia de um erro.
</dd></dl></p>

<p><h2>
    public void commit () throw SqlException
</h2><dl><dt>Descrição :</dt><dd>
    Método utilizado para efetuar o commit de todas as mudanças ocorridas na transação.
    A partir desse método, nenhuma operação pode ser feita nesse objeto. 
</dd><dt>Parametro :</dt><dd>
</dd><dt>Retorno :</dt><dd>
</dd><dt>Exceção :</dt><dd>
    SqlException - Ocorrencia de um erro.
</dd></dl></p>

<p><h2>
    public void rollback ()  
</h2><dl><dt>Descrição :</dt><dd>
    Método utilizado para desfazer todas as mudanças ocorridas na transação.
    A partir desse método, nenhuma operação pode ser feita nesse objeto. 
</dd><dt>Parametro :</dt><dd>
</dd><dt>Retorno :</dt><dd>
</dd><dt>Exceção :</dt><dd>
    SqlException - Ocorrencia de um erro.
</dd></dl></p>

