w<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p><dl><dt>
Classe que representa o tipo primitivo de texto. 
Apartir dessa classe, todas as opera��es de modifica��o, ser� sempre retornado uma nova String com ela. 
</dt></dl></p>

<p><h2>
    public notnull Natural length()
</h2><dl><dt>Descri��o :</dt><dd>
    M�todo que retorna o comprimento de uma String. 
</dd><dt>Parametro :</dt><dd>
</dd><dt>Retorno :</dt><dd>
    notnull Natural - Quantidade de caracteres que a String possui.
</dd><dt>Exce��o :</dt><dd>
</dd></dl></p>

<p><h2>
    public notnull Boolean operatorEqual (notnull Object other)
</h2><dl><dt>Descri��o :</dt><dd>
    M�todo que informa a igualdade entre 2 objetos.   
</dd><dt>Parametro :</dt><dd>
    notnull Object other - Objeto a ser comparado
</dd><dt>Retorno :</dt><dd>
    notnull Boolean - Indica se o objeto a ser comparado � igual a pr�pria instancia.
</dd><dt>Exce��o :</dt><dd>
</dd></dl></p>

<p><h2>
    public notnull Integer hashcode ()
</h2><dl><dt>Descri��o :</dt><dd>
    M�todo que retorna o hash da String.   
</dd><dt>Parametro :</dt><dd>
</dd><dt>Retorno :</dt><dd>
    notnull Integer - Inteiro hash.
</dd><dt>Exce��o :</dt><dd>
</dd></dl></p>

<p><h2>
    public notnull Boolean startWith (notnull String other)
</h2><dl><dt>Descri��o :</dt><dd>
    M�todo que indica se uma String come�a com outra, aonde o teste � feito no conte�do de toda String passada como par�metro.
</dd><dt>Parametro :</dt><dd>
    notnull String other - String a ser testado se est� no inicio.
</dd><dt>Retorno :</dt><dd>
    notnull Boolean - Responde se a String come�a com a String do par�metro.
</dd><dt>Exce��o :</dt><dd>
</dd></dl></p>

<p><h2>
    public notnull Boolean endWith (notnull String other)
</h2><dl><dt>Descri��o :</dt><dd>
    M�todo que indica se uma String termina com outra, aonde o teste � feito no conte�do de toda String passada como par�metro.
</dd><dt>Parametro :</dt><dd>
    notnull String other - String a ser testado se est� no final.
</dd><dt>Retorno :</dt><dd>
    notnull Boolean - Responde se a String termina com a String do par�metro.
</dd><dt>Exce��o :</dt><dd>
</dd></dl></p>

<p><h2>
    public notnull String trim ()
</h2><dl><dt>Descri��o :</dt><dd>
    M�todo que retorna uma nova String sem os caracteres de espa�o no inicio e no fim.
</dd><dt>Parametro :</dt><dd>
</dd><dt>Retorno :</dt><dd>
    notnull String - Retorna uma nova String sem os caracteres de espa�o no inicio e no fim.
</dd><dt>Exce��o :</dt><dd>
</dd></dl></p>
