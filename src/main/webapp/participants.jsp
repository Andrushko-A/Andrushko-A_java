<%@ page contentType="text/html" pageEncoding="UTF-8" %> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<c:set var="pageTitle" value="Participant" scope="application"/>
<t:wrapper>



		<h1>Participants</h1>
		
		<table>
			<thead>
				<tr>
					<th>&#8470</th>
					<th>Participant Name</th>
					
					
					
				</tr>
			</thead>
			
			<tbody >
			
				<tr>
					<td>1</td>
					<td>Anna</td>
					<td><a class="btn-small btn-floating waves-effect waves-light blue" title="Удалить" href="#"><i class="material-icons">delete</i></a></td>
					
				</tr>
				
				<tr>
					<td>2</td>
					<td>Elena</td>
					
					
					<td><a class="btn-small btn-floating waves-effect waves-light blue" title="Удалить" href="#"><i class="material-icons">delete</i></a></td>
				</tr>
				<tr>
					<td>3</td>
					<td>Anton</td>
					
					
					<td><a class="btn-small btn-floating waves-effect waves-light blue" title="Удалить" href="#"><i class="material-icons">delete</i></a></td>
				</tr>
			</tbody>
			
		</table>
<div class="row"></div>
<div class="row">
			<div class="col s12">
				<div class="center-align">
					<a class="btn-floating btn-large waves-effect waves-light blue" href="edit.jsp"><i class="material-icons">add</i></a>
				</div>
			</div>
		</div>


</t:wrapper>
