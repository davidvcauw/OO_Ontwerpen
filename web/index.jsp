<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<jsp:include page="head.jsp">
	<jsp:param name="title" value="Home" />
</jsp:include>
<body>
	<jsp:include page="header.jsp">
		<jsp:param name="title" value="Home" />
	</jsp:include>
	<main>
		<c:if test="${errors.size()>0 }">
			<div class="danger">
				<ul>
					<c:forEach var="error" items="${errors }">
						<li>${error }</li>
					</c:forEach>
				</ul>
			</div>
		</c:if>
		<c:choose>
			<c:when test="${user!=null}">
				<p>Welcome ${user.getFirstName()}!</p>
				<form method="post" action="Controller?action=LogOut">
					<p>
						<input type="submit" id="logoutbutton" value="Log Out">
					</p>
				</form>
			</c:when>
			<c:otherwise>
				<form method="post" action="Controller?action=LogIn">
					<p>
						<label for="email">Your email </label>
						<input type="text" id="email" name="email" value="jan@ucll.be">
					</p>
					<p>
						<label for="password">Your password</label>
						<input type="password" id="password" name="password" value="t">
					</p>
					<p>
						<input type="submit" id="loginbutton" value="Log in">
					</p>
				</form>
			</c:otherwise>
		</c:choose>
		<h3>Onderwerp 1</h3>
		<div id="feedback1"></div>
		<label for="naam1">Naam: </label>
		<input type="text" id="naam1" /><br>
		<label for="topic1">feedback:</label>
		<input type="text" id="topic1" /><br>
		<label for="topic1score">Score: </label>
		<input type="number" id="topic1score" min="1" max="10">
		<button id="button1">submit</button>

		<h3>Onderwerp 2</h3>
		<div id="feedback2"></div>
		<label for="naam2">Naam: </label>
		<input type="text" id="naam2" /><br>
		<label for="topic2">feedback: </label>
		<input type="text" id="topic2" /><br>
		<label for="topic2score">Score: </label>
		<input type="number" id="topic2score" min="1" max="10">
		<button  id="button2">submit</button>

		<h3>Onderwerp 3</h3>
		<div id="feedback3"></div>
		<label for="naam3">Naam: </label>
		<input type="text" id="naam3" /><br>
		<label for="topic3">feedback: </label>
		<input type="text" id="topic3" /><br>
		<label for="topic3score">Score: </label>
		<input type="number" id="topic3score" min="1" max="10">
		<button id="button3">submit</button>

		<h3>Onderwerp 4</h3>
		<div id="feedback4"></div>
		<label for="naam4">Naam: </label>
		<input type="text" id="naam4" /><br>
		<label for="topic4">feedback: </label>
		<input type="text" id="topic4" /><br>
		<label for="topic4score">Score: </label>
		<input type="number" id="topic4score" min="1" max="10">
		<button id="button4">submit</button>

		<h3>Onderwerp 5</h3>
		<div id="feedback5"></div>
		<label for="naam5">Naam: </label>
		<input type="text" id="naam5" /><br>
		<label for="topic5">feedback: </label>
		<input type="text" id="topic5" /><br>
		<label for="topic5score">Score: </label>
		<input type="number" id="topic5score" min="1" max="10">
		<button id="button5">submit</button>

		<div id="messages"></div>

		<script type="text/javascript" src="js/opdracht2.js"></script>
	</main>

	<jsp:include page="footer.jsp">
		<jsp:param name="title" value="Home" />
	</jsp:include>
</body>


</html>