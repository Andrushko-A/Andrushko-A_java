<%@ page contentType="text/html" pageEncoding="UTF-8" %> 
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:wrapper>


		<h1>Tasks</h1>
		
		<table>
			<thead>
				<tr>
					<th>&#8470</th>
					<th>Task Name</th>
					
					
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>1</td>
					<td>Going to shop</td>
					
					<td><a class="btn-small btn-floating waves-effect waves-light blue" title="Удалить" href="#"><i class="material-icons">delete</i></a></td>
				</tr>
				<tr>
					<td>2</td>
					<td>Taking out a trash</td>
					
					<td><a class="btn-small btn-floating waves-effect waves-light blue" title="Удалить" href="#"><i class="material-icons">delete</i></a></td>
				</tr>
				<tr>
					<td>3</td>
					<td>Prip a presentation</td>
					
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
