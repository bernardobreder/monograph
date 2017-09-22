<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="header.jsp"></c:import>

<h2> Forums </h2>
<table>
	<tr>
		<td>Name</td>
	</tr>
	<c:forEach var="folder_name" items="${folder_names}" varStatus="n">  
		<tr>
			<td>${folder_name}</td>
		</tr>
	</c:forEach>
</table>

<h2> Threads </h2>
<table>
	<tr>
		<td>Name</td>
	</tr>
	<c:forEach var="thread_name" items="${thread_names}" varStatus="n">  
		<tr>
			<td>${thread_name}</td>
		</tr>
	</c:forEach>
</table>

<c:import url="tail.jsp"></c:import>