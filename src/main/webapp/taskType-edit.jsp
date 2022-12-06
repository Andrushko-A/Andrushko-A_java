<%@ page contentType="text/html" pageEncoding="UTF-8" %> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<c:set var="pageTitle" value="Edit" scope="application"/>
<t:wrapper>
<c:choose>
		<c:when test="${empty dto.id}">
			<h1>Create taskType</h1>
		</c:when>
		<c:otherwise>
			<h1>Edit taskType #${dto.id}</h1>
		</c:otherwise>
	</c:choose>
	<form class="col s12" method="post" action="/taskType">
		<div class="row">
			<input type="hidden" name="id" value="${dto.id}" />
			<div class="row">
             <div class="input-field col s6">
					<input type="text" name="name" value="${dto.name}"> <label for="name">Name</label>
				</div>
				</div>
				
				<div class="row">
             <div class="input-field col s6">
					<input type="text" name="dateOfCorrection" value="${dto.dateOfCorrection}"> <label for="dateOfCorrection">dateOfCorrection</label>
				</div>
				</div>
				
		</div>
		<div class="row">
			<div class="col s12 input-field center-align">
				<a class="btn waves-effect waves-light red" href="/taskType"><i class="material-icons left">list</i>back</a>&nbsp;
				<button class="btn waves-effect waves-light" type="submit">
					<i class="material-icons left">save</i>save
				</button>
			</div>
		</div>
	</form>
</t:wrapper>
