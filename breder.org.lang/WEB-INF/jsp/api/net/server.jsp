<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p><dl><dt>
Interface que representa um servidor. 
</dt></dl></p>

<p><h2>
    public notnull IClientSocket accept () throws IOException  
</h2><dl><dt>Descri��o :</dt><dd>
    M�todo que espera algum cliente se conectar com o pr�prio servidor.
    Nessa m�todo, a controle da thread ficar� dormindo at� que algum cliente solicite uma conex�o.
</dd><dt>Parametro :</dt><dd>
</dd><dt>Retorno :</dt><dd>
    notnull IClientSocket - Socket de comunica��o.
</dd><dt>Exce��o :</dt><dd>
</dd></dl></p>

