<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/header.js"></script>

<link rel="icon" href="images/logo.png" />
<link rel="stylesheet" type="text/css" href="css/reset-style.css" />
<link rel="stylesheet" type="text/css" href="css/header-style.css" />

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
			<ul class="dropdown-menu" role="menu" aria-labelledby="dLabel"
				style="margin-left: 162px;">
				<li><a class="links">My Profile</a></li>
				<!-- <li><a class="links" id="changePassword" href="#">Change
						Password</a></li> -->
				<li><a class="links" href="./logout.html">Logout</a></li>
			</ul>
		</div>


<!-- 
		Modal 1
		<div id="changePasswordModal" class="modal">
			<div class="modal-dialog">
				Modal content
				<div class="modal-content"">
					<div class="modal-header">
						<button type="button" class="close"
							id="closechangePasswordModalId">&times;</button>
						<h4 class="modal-title">Change Password</h4>
					</div>
					<div class="modal-body">
						<p id="changePasswordModalFormMessageId" style="color: red"></p>
						<form id="changePasswordForm">
							<table>
								<tr>
									<td>Old Password :</td>
									<td><input type="password" class="form-control"
										name="oldPassword" id="oldPassword"
										placeholder="Enter Old Password " required></td>
								</tr>
								<tr>
									<td>New Password :</td>
									<td><input type="password" class="form-control"
										name="newPassword1" id="newPassword1"
										placeholder="Enter New Password " required></td>
								</tr>
								<tr>
									<td>ReType New Password :</td>
									<td><input type="password" class="form-control"
										name="newPassword2" id="newPassword2"
										placeholder="Enter New Password " required></td>
								</tr>
								<tr>
									<td colspan="2" align="right"><button type="submit"
											id="submitButtonOfChangePassword" class="btn btn-primary">Submit</button></td>
								</tr>
							</table>

						</form>
					</div>
				</div>
			</div>
		</div>
		/Modal 1

 -->




	</div>
</div>

<!--Header END -->
