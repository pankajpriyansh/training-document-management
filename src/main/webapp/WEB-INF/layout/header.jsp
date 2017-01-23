<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link rel="icon" href="images/logo.png" />
<link rel="stylesheet" type="text/css" href="css/reset-style.css" />
<link rel="stylesheet" type="text/css" href="css/header-style.css" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<!-- Header START -->

<div class="container-fluid header">
	<div class="row">

		<div class="col-md-3">
			<img id="logo" src="images/logo.png" alt="YTMS" height="60"
				width="60">
		</div>
		<div class="col-md-6 text-center">
			<h3 id="h3">Welcome ${loggedInUser.firstname}
				${loggedInUser.lastname}</h3>
		</div>

		<div class="col-md-3 text-right logout dropdown">
			<button class="btn log-btn dropdown-toggle" type="button"
				data-toggle="dropdown">
				<a class="links">${loggedInUser.firstname}
					${loggedInUser.lastname}<span class="glyphicon glyphicon-user"></span>
				</a>
			</button>
			<ul id="dropdown-menu-id" class="dropdown-menu"
				style="margin-left: 162px;">
				<li><a class="links" href="#">My Profile</a></li>
				<li><a class="links" href="#">Account Settings</a></li>
				<li><a class="links" href="./logout.html">Logout</a></li>
			</ul>
		</div>

	</div>
</div>

<!--Header END -->
