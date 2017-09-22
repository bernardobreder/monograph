<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="header/header.jsp"></c:import>

<h3>
	<form method="POST" action="login?path=${path}">
		Usuário : <input name="username"/><br>
		Senha : <input name="password"/><br>
		<input type="submit"/>
	</form>
</h3>

<c:import url="header/tail.jsp"></c:import>
