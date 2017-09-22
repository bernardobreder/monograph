<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

Gargage Collection é uma técnica utilizada de coleta de objetos. 
Com ele, todos os objetos que não estão sendo mais utilizado será eliminado de memória.
Com isso, o usuário da linguagem não precisa se preocupar mais com o cicle de vida de um objeto, 
pois é de responsabilidade do Gargage Collection.
<br>
Gargage Collection utiliza a pilha de execução da Maquina Virtual para poder descobrir quais objetos estão 
ainda sendo utilizado. Apartir das informações dos objetos utilizado, será selecionado também todos os objetos
dos campos deles. Apartir desse conjunto, todos os objetos respantes não estão sendo utilizado, portanto serão 
coletados pelo Gargage Collection.  
