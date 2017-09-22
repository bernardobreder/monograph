<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p><dl><dt>
Interface que representa a leitura de um fluxo de conte�do. 
</dt></dl></p>

<p><h2>
    public String read (notnull Natural length) throws IOException  
</h2><dl><dt>Descri��o :</dt><dd>
    M�todo que realiza a leitura do fluxo de conte�do e retorna em forma de String.
</dd><dt>Parametro :</dt><dd>
    notnull Natural length - comprimento de caracteres que ser�o lidos.
</dd><dt>Retorno :</dt><dd>
    String - conte�do lido ou nulo para final de arquivo ou leitura.
</dd><dt>Exce��o :</dt><dd>
    IOException - Erro de entrada e sa�da.
</dd></dl></p>
