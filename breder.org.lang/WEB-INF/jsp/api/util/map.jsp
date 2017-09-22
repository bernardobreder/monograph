<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p><dl><dt>
Classe é uma abstração das de mapa.
Essa classe é capaz de através de uma chave, a estrutura retorna um valor.
Mas para que ela funcione, será preciso cadastrar as entidades através do método 'set'. 
</dt></dl></p>

<p><h2>
    public void set (notnull K key, notnull V value)  
</h2><dl><dt>Descrição :</dt><dd>
    Método que adiciona ou modifica o valor de uma entidade do mapa.
</dd><dt>Parametro :</dt><dd>
	notnull K key - Chave da entidade.
</dd><dd>
	notnull V value - Valor da entidade.
</dd><dt>Retorno :</dt><dd>
</dd><dt>Exceção :</dt><dd>
</dd></dl></p>

<p><h2>
    public V get (notnull K key)
</h2><dl><dt>Descrição :</dt><dd>
    Método que retorna o valor de uma entidade do mapa.
</dd><dt>Parametro :</dt><dd>
	notnull K key - Chave da entidade.
</dd><dt>Retorno :</dt><dd>
    V - Valor da entidade ou nulo caso não ache.
</dd><dt>Exceção :</dt><dd>
</dd></dl></p>

<p><h2>
    public V remove (notnull K key)
</h2><dl><dt>Descrição :</dt><dd>
    Método que remove uma entidade do mapa a partir da sua chave.  
</dd><dt>Parametro :</dt><dd>
    notnull K key - Chave da entidade.
</dd><dt>Retorno :</dt><dd>
    V - Valor da entidade ou nulo caso não ache.
</dd><dt>Exceção :</dt><dd>
</dd></dl></p>

<p><h2>
    public notnull Natural size ()  
</h2><dl><dt>Descrição :</dt><dd>
    Método que retorna a quantidade de entidades cadastrada no mapa. 
</dd><dt>Parametro :</dt><dd>
</dd><dt>Retorno :</dt><dd>
    notnull Natural - Tamanho do mapa.
</dd><dt>Exceção :</dt><dd>
</dd></dl></p>
