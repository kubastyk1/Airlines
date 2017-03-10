<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>Cover Template for Bootstrap</title>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.9.0/moment-with-locales.js"></script>
<script
	src="http://cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/a549aa8780dbda16f6cff545aeabc3d71073911e/src/js/bootstrap-datetimepicker.js"></script>

<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
<link href="<c:url value="/resources/css/cover.css" />" rel="stylesheet">
<link
	href="http://cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/a549aa8780dbda16f6cff545aeabc3d71073911e/build/css/bootstrap-datetimepicker.css"
	rel="stylesheet" />

<script>
function changeFromDescription(change) {
	var x = document.getElementById("fromButton");
	x.innerHTML = change;
}
function changeToDescription(change) {
	var x = document.getElementById("toButton");
	x.innerHTML = change;
}
</script>

</head>

<body>

	<!-- Spring Security -->
	<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>

	<c:url value="/j_spring_security_logout" var="logoutUrl" />

	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>





	<div class="site-wrapper">
		<div class="site-wrapper-inner">
			<div class="cover-container">
				<div class="masthead clearfix">
					<div class="inner">
						<h3 class="masthead-brand">Airlines</h3>
						<nav>
							<ul class="nav masthead-nav">

								<li class="active"><a href="#">Home</a></li>
								<!--  Jezeli nie jest zalogowany  -->
								<c:if test="${pageContext.request.userPrincipal.name == null}">
									<form name='signupForm' action='/airlines/signup' method='GET'>
										<input name="submit" class="buttonStyle" type="submit"
											value="Sign up" />
									</form>
									<form name='loginForm' action='/airlines/login' method='GET'>
										<input name="submit" class="buttonStyle2" type="submit"
											value="Log in" />
									</form>
								</c:if>

								<!--  Jezeli jest zalogowany  -->
								<c:if test="${pageContext.request.userPrincipal.name != null}">
									<form name='logoutForm' method='GET'>
										<input name="submit" class="buttonStyleRed" type="submit"
											value="${pageContext.request.userPrincipal.name}" />
									</form>

									<form name='userForm' action="javascript:formSubmit()"
										method='GET'>
										<input name="submit" class="buttonStyleRed" type="submit"
											value="Log out" />
									</form>
								</c:if>

							</ul>
						</nav>
					</div>
				</div>


				<div class="inner cover">
					<h1 class="cover-heading">Search flights:</h1>

					<div class="dropdown my-dropdown">
						<button class="btn btn-default btn-primary dropdown-toggle"
							type="button" data-toggle="dropdown" id="fromButton">
							From: <span class="caret"></span>
						</button>
						<ul class="dropdown-menu">
							<li><a href="javascript:changeFromDescription('Gdansk');">Gdansk</a></li>
							<li><a href="javascript:changeFromDescription('Warszawa');">Warszawa</a></li>
							<li><a href="javascript:changeFromDescription('Lodz');">Lodz</a></li>
						</ul>
					</div>

					<div class="container calendarText ">
						<div class="row">
							<div class='col-sm-6'>
								<div class="form-group">
									<div class='input-group date' id='datetimepicker2'>
										<input type='text' class="form-control" /> <span
											class="input-group-addon"> <span
											class="glyphicon glyphicon-calendar"></span>
										</span>
									</div>
								</div>
							</div>
							<script type="text/javascript">
								$(function() {
									$('#datetimepicker2').datetimepicker({
										locale : 'pl'
									});
								});
							</script>
						</div>
					</div>

					<div class="dropdown my-dropdown">
						<button class="btn btn-default btn-primary dropdown-toggle"
							type="button" data-toggle="dropdown" id="toButton">
							To: <span class="caret"></span>
						</button>
						<ul class="dropdown-menu">
							<li><a href="javascript:changeToDescription('Gdansk');">Gdansk</a></li>
							<li><a href="javascript:changeToDescription('Warszawa');">Warszawa</a></li>
							<li><a href="javascript:changeToDescription('Lodz');">Lodz</a></li>
						</ul>
					</div>

					<div class="container calendarText ">
						<div class="row">
							<div class='col-sm-6'>
								<div class="form-group">
									<div class='input-group date' id='datetimepicker3'>
										<input type='text' class="form-control" /> <span
											class="input-group-addon"> <span
											class="glyphicon glyphicon-calendar"></span>
										</span>
									</div>
								</div>
							</div>
 							<script type="text/javascript">
								$(function() {
									$('#datetimepicker3').datetimepicker({
										locale : 'pl'
									});
								});
							</script>
						</div>
					</div>

					<p class="lead">
						<a href="#" class="btn btn-lg btn-default">Search!</a>
					</p>
				</div>

			</div>

		</div>

	</div>

</body>
</html>

