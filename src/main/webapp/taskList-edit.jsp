<%@ page contentType="text/html" pageEncoding="UTF-8" %> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<c:set var="pageTitle" value="Edit" scope="application"/>
<t:wrapper>
<c:choose>
		<c:when test="${empty dto.id}">
			<h1>Create task</h1>
		</c:when>
		<c:otherwise>
			<h1>Edit task #${dto.id}</h1>
		</c:otherwise>
	</c:choose>
	<form class="col s12" method="post" action="/taskList">
		<div class="row">
			<input type="hidden" name="id" value="${dto.id}" />
			<div class="row">
             <div class="input-field col s6">
					<label><input type="checkbox" name="status" ${dto.status ? 'checked' : ''} required value="true" /> <span>Status</span>
					</label>
				</div>
				</div>
			<div class="row">
				<div class="col s6">
					<label for="taskId">Task ID</label> 
					<select name="taskId" class="browser-default" required>
						<option value="">--select task--</option>
						<c:forEach items="${allTasks}" var="task">
							<option value="${task.id}" <c:if test="${task.id eq dto.taskId}">selected="selected"</c:if>>${task.name}</option>
						</c:forEach>
					</select>
				</div>
				<div class="col s6">
					<label for="participantId">Participant ID</label> 
					<select name="participantId" class="browser-default" required>
						<option value="">--select participant--</option>
						<c:forEach items="${allParticipants}" var="participant">
							<option value="${participant.id}" <c:if test="${participant.id eq dto.participantId}">selected="selected"</c:if>>${participant.name}</option>
						</c:forEach>
					</select>
				</div>
				<div class="input-field col s6">
					<input type="text" name="teamId"  required value="${dto.teamId}"> <label for="teamId">Team ID</label>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col s12 input-field center-align">
				<a class="btn waves-effect waves-light red" href="/taskList"><i class="material-icons left">list</i>back</a>&nbsp;
				<button class="btn waves-effect waves-light" type="submit">
					<i class="material-icons left">save</i>save
				</button>
			</div>
		</div>
	</form>
</t:wrapper>
