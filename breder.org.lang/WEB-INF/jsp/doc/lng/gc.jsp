<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

Gargage Collection � uma t�cnica utilizada de coleta de objetos. 
Com ele, todos os objetos que n�o est�o sendo mais utilizado ser� eliminado de mem�ria.
Com isso, o usu�rio da linguagem n�o precisa se preocupar mais com o cicle de vida de um objeto, 
pois � de responsabilidade do Gargage Collection.
<br>
Gargage Collection utiliza a pilha de execu��o da Maquina Virtual para poder descobrir quais objetos est�o 
ainda sendo utilizado. Apartir das informa��es dos objetos utilizado, ser� selecionado tamb�m todos os objetos
dos campos deles. Apartir desse conjunto, todos os objetos respantes n�o est�o sendo utilizado, portanto ser�o 
coletados pelo Gargage Collection.  
