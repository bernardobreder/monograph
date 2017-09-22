<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p><dl><dt>
Classe é uma abstração das operações em uma String. 
</dt></dl></p>

<p><h2>
    public void append (notnull String text)
</h2><dl><dt>Descrição :</dt><dd>
    Método que adiciona no final, uma String.
</dd><dt>Parametro :</dt><dd>
	notnull String text - String a ser adicionado
</dd><dt>Retorno :</dt><dd>
</dd><dt>Exceção :</dt><dd>
</dd></dl></p>

<p><h2>
    public void append (notnull String text, notnull Number offset)
</h2><dl><dt>Descrição :</dt><dd>
    Método que adiciona no final, uma String, a partir de uma posição inicial.
</dd><dt>Parametro :</dt><dd>
notnull String text - String a ser adicionado.
</dd><dd>
notnull Number offset - Posição inicial.
</dd><dt>Retorno :</dt><dd>
</dd><dt>Exceção :</dt><dd>
</dd></dl></p>

<p><h2>
    public void append (notnull String text, notnull Number offset, notnull Number length)
</h2><dl><dt>Descrição :</dt><dd>
    Método que adiciona no final, uma String, a partir de uma posição inicial, copiando um comprimento especifico.
</dd><dt>Parametro :</dt><dd>
    notnull String text - String a ser adicionado.
</dd><dd>
    notnull Number offset - Posição inicial.
</dd><dd>
    notnull Number length - Quantidade de caracteres a ser copiado.
</dd><dt>Retorno :</dt><dd>
</dd><dt>Exceção :</dt><dd>
</dd></dl></p>

<p><h2>
    public notnull Number length ()
</h2><dl><dt>Descrição :</dt><dd>
    Método que retorna o comprimento da String atual. 
</dd><dt>Parametro :</dt><dd>
</dd><dt>Retorno :</dt><dd>
    notnull Number - Compimento da String.
</dd><dt>Exceção :</dt><dd>
</dd></dl></p>

<p><h2>
    public void clear ()
</h2><dl><dt>Descrição :</dt><dd>
    Método que retorna remove todos os caracteres cadastrados. 
</dd><dt>Parametro :</dt><dd>
</dd><dt>Retorno :</dt><dd>
</dd><dt>Exceção :</dt><dd>
</dd></dl></p>

<p><h2>
    public notnull String toString () 
</h2><dl><dt>Descrição :</dt><dd>
    Método que retorna a String atual. 
</dd><dt>Parametro :</dt><dd>
</dd><dt>Retorno :</dt><dd>
    notnull String - String construida atualmente.
</dd><dt>Exceção :</dt><dd>
</dd></dl></p>
