<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="pageTitle" value="Input Types examples" scope="application" />
<t:wrapper>
	<form class="col s12" method="post" action="/input-types">
		
		<div class="row">
			<div class="input-field col s12">
				<input type="date" name="date"> <label for="date">date</label>
			</div>
		</div>
		
		<div class="row">

			<div class="col s12">
				<label for="datetime-local">datetime-local</label> <input type="datetime-local" name="datetime-local">
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<input type="email" name="email"> <label for="email">email</label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<input type="number" name="number"> <label for="number">number</label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<input type="password" name="password"> <label for="password">password</label>
			</div>
		</div>


		<div class="row">
			<div class="input-field col s12">
				<input type="tel" name="tel"> <label for="tel">tel</label>
			</div>
		</div>

		<div class="row">
			<div class="col s12 input-field center-align">

				<button class="btn waves-effect waves-light" type="submit">
					<i class="material-icons left">save</i>send to server
				</button>
			</div>
		</div>
	</form>
</t:wrapper>
