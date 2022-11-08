<%@ page contentType="text/html" pageEncoding="UTF-8" %> 
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:wrapper>


<div class="section no-pad-bot" id="index-banner">
	<div class="container">
		<h1>Edit tasks</h1>


		<div class="row">
			<form class="col s12">
				<div class="row">
					 <div class="input-field col s4">
						
  <select class="browser-default">
    <option value="" disabled selected>Choose your task</option>
    <option value="1">Going to shop</option>
    <option value="2">Taking out a trash</option>
    <option value="3">Prip a presentation</option>
  </select>
					 </div>
					<div class="input-field col s4">
						<label for="start">Start date:</label>

<input type="date" id="start" name="trip-start"
       value="2022-10-19"
       min="2022-10-19" max="2022-12-31">
					</div>
					<div class="input-field col s4">
						
  <select class="browser-default">
    <option value="" disabled selected>Choose participant</option>
    <option value="1">Anna</option>
    <option value="2">Elena</option>
    <option value="3">Anton</option>
  </select>
					 </div>
				</div>
				
			</form>
		</div>
		<div class="row">
			<div class="col s12 input-field center-align">
				<a class="btn waves-effect waves-light red" href="index.jsp"><i class="material-icons left">list</i>back to list</a> <a class="btn waves-effect waves-light green"
					href="#"><i class="material-icons left">save</i>Save</a>
			</div>
		</div>
	</div>
</div>

</t:wrapper>
