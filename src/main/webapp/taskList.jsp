<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="pageTitle" value="taskList" scope="application"/>
<t:wrapper>


	<h1>Task list</h1>

	<table>
		<thead>
			<tr>
				<th>id</th>
				<th>task</th>
				<th>participant</th>
				<th>deadline</th>
				<th>dateOfCorrection</th>
				<th>status</th>
				<th>team</th>


			</tr>

		</thead>
		<tbody>
			<c:forEach var="entity" items="${list}" varStatus="loopCounter">
				<tr>
					<td><c:out value="${entity.id}" /></td>
					<td><c:out value="${entity.taskName}" /></td>
					<td><c:out value="${entity.participantName}" /></td>
					<td><fmt:formatDate pattern="yyyy-MM-dd" value="${entity.deadline}" /></td>
					<td><fmt:formatDate pattern="yyyy-MM-dd" value="${entity.dateOfCorrection}" /></td>
					<td><c:out value="${entity.status}" /></td>
					<td><c:out value="${entity.teamName}" /></td>
					<td><a class="btn-small btn-floating waves-effect waves-light blue" title="редактировать" href="/taskList?view=edit&id=${entity.id}"><i
							class="material-icons">edit</i></a><a class="btn-small btn-floating waves-effect waves-light red" title="удалить" onclick="sendHTTPDelete('/taskList?id=${entity.id}')"><i class="material-icons">delete</i></a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="row">
		<div class="col s12">
			<div class="center-align">
				<a class="btn-floating btn-large waves-effect waves-light" href="/taskList?view=edit"><i class="material-icons">add</i></a>
			</div>
		</div>
	</div>


</t:wrapper>

