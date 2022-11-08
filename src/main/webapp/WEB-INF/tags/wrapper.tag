<%@tag description="Page template" pageEncoding="UTF-8"%>
<html>

<head>
<title>Task List</title>

<!-- Compiled and minified CSS -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
</head>


<nav class="light-blue lighten-1" role="navigation">
	<div class="nav-wrapper container">
		<ul class="right hide-on-med-and-down">
		<li><a href="index.jsp">Task list</a></li>
			<li><a href="tasks.jsp">Tasks</a></li>
			<li><a href="participants.jsp">Participants</a></li>
			<li><a href="#">Settings</a></li>
			<li><a href='#'><% java.util.Date date = new java.util.Date(); out.print(date.toString()); %>
			</a></li>
			<li><a class="btn-small btn-floating waves-effect waves-light blue" title="Ð²ÑÐ¹ÑÐ¸" href="#"><i class="material-icons">input</i></a></li>
		</ul>
	</div>
	
</nav>


<div class="section no-pad-bot" id="index-banner">
	<div class="container">
		 <jsp:doBody/> <!-- Page body will be here -->
	</div>
</div>
<!-- Compiled and minified JavaScript -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>

</body>
</html>
