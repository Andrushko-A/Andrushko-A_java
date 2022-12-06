<%@ page contentType="text/html" pageEncoding="UTF-8" %> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<c:set var="pageTitle" value="Participants" scope="application"/>
<c:set var="pageUrl" value="/participant" scope="page" />
<t:wrapper>


		<h1>Participants</h1>
		
		<table>
			<thead>
				<tr>
					<th><mytaglib:sort-link pageUrl="${pageUrl}" column="id">Id</mytaglib:sort-link></th>
					<th><mytaglib:sort-link pageUrl="${pageUrl}" column="name">Name</mytaglib:sort-link></th>
				</tr>
			</thead>
			
           <tbody>
			<c:forEach var="entity" items="${list}" varStatus="loopCounter">
				<tr>
					<td><c:out value="${entity.id}" /></td>
					<td><c:out value="${entity.name}" /></td>
					<td><a class="btn-small btn-floating waves-effect waves-light blue" title="редактировать" href="/participant?view=edit&id=${entity.id}"><i
							class="material-icons">edit</i></a><a class="btn-small btn-floating waves-effect waves-light red" title="удалить" onclick="sendHTTPDelete('/participant?id=${entity.id}')"><i class="material-icons">delete</i></a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="row">
		<div class="col s12">
			<div class="center-align">
				<a class="btn-floating btn-large waves-effect waves-light" href="/participant?view=edit"><i class="material-icons">add</i></a>
			</div>
		</div>
	</div>
<t:paging />
</t:wrapper>