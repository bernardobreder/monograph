<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p><dl><dt>
Interface que representa um arquivo. 
</dt></dl></p>
    
<p><h2>
    public notnull IInputStream getInputStream () throws IOException 
</h2><dl><dt>Descrição :</dt><dd>
    Método que retorna a stream de leitura do arquivo.
</dd><dt>Parametro :</dt><dd>
</dd><dt>Retorno :</dt><dd>
    notnull IInputStream - stream de leitura do arquivo.
</dd><dt>Exceção :</dt><dd>
</dd></dl></p>

<p><h2>
    public notnull IOutputStream getOutputStream () throws IOException 
</h2><dl><dt>Descrição :</dt><dd>
    Método que retorna a stream de escrita do arquivo.
    Caso o arquivo já existe, ele será substituido.
</dd><dt>Parametro :</dt><dd>
</dd><dt>Retorno :</dt><dd>
    notnull IOutputStream - stream de escrita do arquivo.
</dd><dt>Exceção :</dt><dd>
</dd></dl></p>
