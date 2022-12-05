<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<c:set var="pageTitle" value="taskList" scope="application"/>
<c:set var="pageUrl" value="/taskList" scope="page" />
<t:wrapper>


	<h1>Task list</h1>

	<table>
		<thead>
			<tr>
                <th><mytaglib:sort-link pageUrl="${pageUrl}" column="id">Id</mytaglib:sort-link></th>
                <th>task</th>
				<th>participant</th>
				<th><mytaglib:sort-link pageUrl="${pageUrl}" column="deadline">Deadline</mytaglib:sort-link></th>
				<th><mytaglib:sort-link pageUrl="${pageUrl}" column="date_of_correction">Correction date</mytaglib:sort-link></th>
				<th><mytaglib:sort-link pageUrl="${pageUrl}" column="status">Status</mytaglib:sort-link></th>
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
							class="material-icons">edit</i></a>
							<a class="btn-small btn-floating waves-effect waves-light blue" title="участники" href="/taskList?view=look=${entity.id}"><i
							class="material-icons">group</i></a>
							<a class="btn-small btn-floating waves-effect waves-light red" title="удалить" onclick="sendHTTPDelete('/taskList?id=${entity.id}')"><i class="material-icons">delete</i></a></td>
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

<t:paging />
</t:wrapper>

