<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:wrapper>


	<h1>Task list</h1>

	<table class="highlight">
		<thead>
			<tr>
				<th>&#8470</th>
				<th>Task Name</th>
				<th>Patricipant Name</th>
				<th>Date</th>


			</tr>

		</thead>
		<tbody>
			<tr>
				<td>1</td>
				<td>Going to shop</td>
				<td>Anna</td>
				<td>19.10.2022</td>
				<td><a
					class="btn-small btn-floating waves-effect waves-light blue"
					title="Редактировать" href="edit.jsp"><i
						class="material-icons">edit</i></a></td>
				<td><a
					class="btn-small btn-floating waves-effect waves-light blue"
					title="Удалить" href="#"><i class="material-icons">delete</i></a></td>
			</tr>
			<tr>
				<td>2</td>
				<td>Taking out a trash</td>
				<td>Elena</td>
				<td>20.10.2022</td>
				<td><a
					class="btn-small btn-floating waves-effect waves-light blue"
					title="Редактировать" href="edit.jsp"><i
						class="material-icons">edit</i></a></td>
				<td><a
					class="btn-small btn-floating waves-effect waves-light blue"
					title="Удалить" href="#"><i class="material-icons">delete</i></a></td>
			</tr>
			<tr>
				<td>3</td>
				<td>Prip a presentation</td>
				<td>Anton</td>
				<td>22.10.2022</td>
				<td><a
					class="btn-small btn-floating waves-effect waves-light blue"
					title="Редактировать" href="edit.jsp"><i
						class="material-icons">edit</i></a></td>
				<td><a
					class="btn-small btn-floating waves-effect waves-light blue"
					title="Удалить" href="#"><i class="material-icons">delete</i></a></td>
			</tr>
		</tbody>
	</table>
	<div class="row"></div>
	<div class="row">
		<div class="col s12">
			<div class="center-align">
				<a class="btn-floating btn-large waves-effect waves-light blue"
					href="edit.jsp"><i class="material-icons">add</i></a>
			</div>
		</div>
	</div>


</t:wrapper>

