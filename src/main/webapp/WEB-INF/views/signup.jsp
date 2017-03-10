<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>SignUp Page</title>
<link rel="stylesheet" href="<c:url value="/resources/css/loginstyle.css" />" type="text/css" >
</head>
<body>

	<div id="login-box">

		<h2 class="text">SignUp</h2>

		<form name='signupForm' action='/Monopoly/signup' method='POST'>
		  <table>
			<tr>
				<td>User:</td>
				<td><input type='text' name='username' value=''></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type='password' name='password' /></td>
			</tr>
			<tr>
				<td>Email:</td>
				<td><input type='email' name='email' /></td>
			</tr>
			<tr>
				<td colspan='2'><input name="submit" type="submit" class="buttonStyleLogin" value="Sign up" /></td>
			</tr>
		  </table>

		  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

		</form>
	</div>

</body>
</html>