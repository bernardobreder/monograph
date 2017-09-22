<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p><dl><dt>
Interface que representa a leitura de um fluxo de conteúdo. 
</dt></dl></p>

<p><h2>
    public String read (notnull Natural length) throws IOException  
</h2><dl><dt>Descrição :</dt><dd>
    Método que realiza a leitura do fluxo de conteúdo e retorna em forma de String.
</dd><dt>Parametro :</dt><dd>
    notnull Natural length - comprimento de caracteres que serão lidos.
</dd><dt>Retorno :</dt><dd>
    String - conteúdo lido ou nulo para final de arquivo ou leitura.
</dd><dt>Exceção :</dt><dd>
    IOException - Erro de entrada e saída.
</dd></dl></p>
