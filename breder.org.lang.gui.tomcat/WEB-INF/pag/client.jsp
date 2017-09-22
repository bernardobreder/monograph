<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="header/header.jsp"></c:import>

<h3>
	Tabela de Animais<br>
	<table>
		<tr>
			<td>Nome</td>
		</tr>
		<c:forEach var="animal" items="${animals}">
			<tr>
				<td>${animal.name}</td>
			</tr>
		</c:forEach>
	</table>
	
</h3>

<c:import url="header/tail.jsp"></c:import>
