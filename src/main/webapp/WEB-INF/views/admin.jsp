<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>Dashboard Template for Bootstrap</title>

<!-- Bootstrap core CSS -->
<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet">

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<link href="../../assets/css/ie10-viewport-bug-workaround.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="<c:url value="/resources/css/dashboard.css" />"
	rel="stylesheet">

<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
<script src="../../assets/js/ie-emulation-modes-warning.js"></script>

<script src="<c:url value="/resources/js/sockjs-0.3.4.js" />"></script>
<script src="<c:url value="/resources/js/stomp.js" />"></script>
<script src="<c:url value="/resources/js/stompClient.js" />"></script>

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
<script>

	window.onload = function() {
		var stompClient = null;
		connect();
	}

	function connect() {
	    var socket = new SockJS("<c:url value='/hello'/>");
	    stompClient = Stomp.over(socket);
	    stompClient.connect({}, function(frame) {
	        console.log('Connected: ' + frame);
	        stompClient.subscribe('/topic/showValues', showValues);
	        stompClient.subscribe('/topic/showUsersAfterDelete', showUsersAfterDelete);
	    });
	}
</script>
</head>

<body>

	<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">This page is for ROLE_ADMIN only!</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-right">

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

					<form name='userForm' action="javascript:formSubmit()" method='GET'>
						<input name="submit" class="buttonStyleRed" type="submit"
							value="Log out" />
					</form>
				</c:if>

			</ul>
		</div>
	</div>
	</nav>

	<div class="container-fluid">
		<h2 class="sub-header">Users :</h2>
		<div class="table-responsive">
			 <table id="example" class="table table-striped table-bordered" cellspacing="0" width="100%">
				<thead>
					<tr>
						<th>ID</th>
						<th>Username</th>
						<th>Role</th>
						<th>Delete</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${userRoleList}" var="i">
						<tr>
							<td><c:out value="${i.getUserRoleId()}" /></td>
							<td><c:out value="${i.getUser().getUsername()}" /></td>
							<td><c:out value="${i.getRole()}" /></td>
							<td><button type="button" class="btn btn-danger"onclick="deleteUser('${i.getUserRoleId()}');">Delete</button></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

		</div>
	</div>

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script>
		window.jQuery
				|| document
						.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')
	</script>
	<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	<!-- Just to make our placeholder images work. Don't actually copy the next line! -->
	<script src="../../assets/js/vendor/holder.min.js"></script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>

	<script src="<c:url value="/resources/js/jquery.dataTables.min.js" />"></script>
    <script src="<c:url value="/resources/js/dataTables.bootstrap.min.js" />"></script>
    <script type="text/javascript">
	    $(document).ready(function() {
	        $('#example').dataTable();
	    } );
    </script>
</body>
</html>
