<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p><dl><dt>
Interface que representa um servidor. 
</dt></dl></p>

<p><h2>
    public notnull IClientSocket accept () throws IOException  
</h2><dl><dt>Descrição :</dt><dd>
    Método que espera algum cliente se conectar com o próprio servidor.
    Nessa método, a controle da thread ficará dormindo até que algum cliente solicite uma conexão.
</dd><dt>Parametro :</dt><dd>
</dd><dt>Retorno :</dt><dd>
    notnull IClientSocket - Socket de comunicação.
</dd><dt>Exceção :</dt><dd>
</dd></dl></p>

