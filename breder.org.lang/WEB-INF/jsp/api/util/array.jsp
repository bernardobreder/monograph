<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p><dl><dt>
Classe é uma abstração de um array de comprimento fixo. 
A lista pode ser implementada de várias maneiras possivel, portanto a sua interface será generica o bastante. 
</dt></dl></p>

<p><h2>
	public E get (notnull Index index);
</h2><dl><dt>Descrição :</dt><dd>
    Método que retorna um elemento da lista através de um indice.
</dd><dt>Parametro :</dt><dd>
	notnull Index index - indice na qual deseja consultar.
</dd><dt>Retorno :</dt><dd>
    notnull E - Objeto consultado.
</dd><dt>Exceção :</dt><dd>
	IndexOutOfBoundsRuntimeException - Não foi encontrado um elemento com o indice especificado.
</dd></dl></p>

<p><h2>
	public void set (notnull Index index, E value);
</h2><dl><dt>Descrição :</dt><dd>
    Método que modifica um item da lista. O indice deve existir.
    O método não é usado para se inserir elemento na lista. 
</dd><dt>Parametro :</dt><dd>
    notnull Index index - Indice da lista.
</dd><dd>
    notnull E value - Valor a ser modificado.
</dd><dt>Retorno :</dt><dd>
</dd><dt>Exceção :</dt><dd>
	IndexOutOfBoundsRuntimeException - Não foi encontrado um elemento com o indice especificado.
</dd></dl></p>

<p><h2>
	public notnull Natural size ();
</h2><dl><dt>Descrição :</dt><dd>
    Método que retorna o tamanho da lista. 
</dd><dt>Parametro :</dt><dd>
</dd><dt>Retorno :</dt><dd>
    notnull Natural - Tamanho da lista.
</dd><dt>Exceção :</dt><dd>
</dd></dl></p>

<p><h2>
	public notnull Boolean contain (E value);
</h2><dl><dt>Descrição :</dt><dd>
    Método que retorna se o elemento contém na lista. 
</dd><dt>Parametro :</dt><dd>
    notnull E element - Valor a ser comparado.
</dd><dt>Retorno :</dt><dd>
    notnull Boolean - Resultado da consulta.
</dd><dt>Exceção :</dt><dd>
</dd></dl></p>

<p><h2>
    public Index indexOf (E value);  
</h2><dl><dt>Descrição :</dt><dd>
    Método que retorna o indice do objeto a ser consultado. 
</dd><dt>Parametro :</dt><dd>
    notnull E value - Objeto a ser consultado.
</dd><dt>Retorno :</dt><dd>
    Index - Resultado do indice ou nulo caso não achar.
</dd><dt>Exceção :</dt><dd>
</dd></dl></p>

<p><h2>
    public Index indexOf (E value, notnull Index startIndex);
</h2><dl><dt>Descrição :</dt><dd>
    Método que retorna o indice do objeto a ser consultado. 
</dd><dt>Parametro :</dt><dd>
    notnull E value - Objeto a ser consultado.
</dd><dd>
    notnull E value - Objeto a ser consultado.
</dd><dt>Retorno :</dt><dd>
    Index - Resultado do indice ou nulo caso não achar.
</dd><dt>Exceção :</dt><dd>
</dd></dl></p>