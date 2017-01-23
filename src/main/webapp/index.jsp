<!DOCTYPE html>

<head>
<title>YTDMS</title>
<link rel="icon" href="images/logo.png" />

<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:400,600,600i,700,800"
	rel="stylesheet" type="text/css">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/reset.css">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/font-awesome.min.css">

<!-- jQuery library -->
<script src="js/jquery-3.1.1.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/jquery.validation/1.15.1/jquery.validate.min.js"></script>
<!-- Latest compiled JavaScript -->
<script src="js/bootstrap.min.js"></script>
<script src="js/index.js"></script>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script>
	$(document).ready(function() {
		$("#expand").click(function() {
			$("li").toggle(1000);
			$("#expand").css("display", "list-item")

		});
	});
	$(document).ready(function() {
		$("#expand1").click(function() {
			$("li").toggle(1000);

			$("#expand1").css("display", "list-item")
		});
	});
	$(document).ready(function() {
		$("#scolltop").click(function() {
			$("html, body").animate({
				scrollTop : 0
			}, "slow");
			return false;
		});

	});
</script>
</head>

<body>
	<div id="top"></div>

	<!-- HEADER -->
	<div class="container ">
		<header>
			<div class="col-sm-4 col-md-4 col-xs-12">
				<img id="icon" src="images/logo.png" alt="logo">
			</div>
			<div class="col-sm-8 col-md-8 col-xs-12">

				<h1 id="logo " class="text-left ">
					<button type="submit" id="registrationButton"
						class="btn btn-primary text-right contact">Register</button>
					<button type="button" id="loginButton"
						class="btn btn1 btn-primary text-right contact">Login</button>
				</h1>


			</div>

		</header>
		<!-- END OF HEADER -->
	</div>

	<!-- Carousel -->



	<div id="myCarousel" class="carousel slide" data-ride="carousel">
		<!-- Indicators -->
		<ol class="carousel-indicators">
			<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
			<li data-target="#myCarousel" data-slide-to="1"></li>
			<li data-target="#myCarousel" data-slide-to="2"></li>
			<li data-target="#myCarousel" data-slide-to="3"></li>
		</ol>

		<!-- Wrapper for slides -->
		<div class="carousel-inner" role="listbox">

			<div class="item active">
				<img src="images/Carausel-image-1.jpg" alt="Chania"> <span
					class="galary-text">Just because something doesnot do what
					you planned it to do doesnot mean its useless. - Thomas Edison </span>

			</div>

			<div class="item">
				<img src="images/Carausel-image-2.jpg" alt="Chania"> <span
					class="galary-text">Life is 10% what happens to you and 90%
					how you react to it. - Charles R. Swindoll</span>

			</div>

			<div class="item">
				<img src="images/Carausel-image-3.jpeg" alt="Flower"> <span
					class="galary-text">Good, better, best. Never let it rest.
					'Til your good is better and your better is best. - St. Jerome </span>


			</div>

			<div class="item">
				<img src="images/tech_banner.jpg" alt="Flower"> <span
					class="galary-text">Life is 10% what happens to you and 90%
					how you react to it. - Charles R. Swindoll</span>

			</div>



			<!-- Left and right controls -->
			<a class="left carousel-control" href="#myCarousel" role="button"
				data-slide="prev"> <span
				class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
				<span class="sr-only">Previous</span>
			</a> <a class="right carousel-control" href="#myCarousel" role="button"
				data-slide="next"> <span
				class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
				<span class="sr-only">Next</span>
			</a>
		</div>
	</div>

	<!-- END OF IMAGE GALARY  -->
	<!-- NAVIGATION -->
	<nav class="navbar navbar-inverse" data-spy="affix"
		data-offset-top="595">
		<div class="container-fluid ">
			<div class="container ">
				<ul class="nav navbar-nav cl-effect-14 ">
					<li><a id="expand1" href="javascript:void(0);"
						onclick="Function()">MENU </a></li>
					<li class="active"><a href="#">Home</a></li>
					<li><a href="#Updates">Updates</a></li>
					<!-- <li><a href="#OUR_SERVICES">OUR SERVICES</a></li> -->
					<!-- <li><a href="#WE_OFFER">WE OFFER</a></li> -->
					<li><a href="#BLOG">BLOG</a></li>
					<!-- <li><a href="#CONTACT">CONTACT</a></li> -->
				</ul>
				<a id="expand" href="javascript:void(0);"
					style="font-size: 25px text-decoration:none" onclick="Function()">☰</a>
			</div>
		</div>
	</nav>
	<!-- END OF NAVIGATION -->
	<!-- Updates -->

	<div id="Updates"></div>

	<div class="container">

		<h4>Updates</h4>

		<div class="products">

			<div class="product col-sm-6 col-md-6 col-xs-12">

				<img class="img" src="images/spring-logo.png" alt=" ">
				<div class="product-text">
					<p>Lorem Ipsum is simply dummy text of the printing and
						typesetting industry. Lorem Ipsum has been the industry's standard
						dummy text ever since the 1500s,</p>
				</div>
				<div class="product-price"></div>

				<a href="http://www.yash.com/news-room/press-releases/"
					class="read_more_button_update hvr-rectangle-out "
					title="Read More">Read More</a>
			</div>

			<div class="product2 col-sm-6 col-md-6 col-xs-12">

				<img class="img" src="images/spring-logo.png" alt=" ">
				<div class="product-text">
					<p>Lorem Ipsum is simply dummy text of the printing and
						typesetting industry. Lorem Ipsum has been the industry's standard
						dummy text since the 1500s,</p>
				</div>

				<div class="product-price"></div>

				<a href="http://www.yash.com/news-room/press-releases/"
					class="read_more_button_update hvr-rectangle-out" title="Read More">Read
					More</a>

			</div>


		</div>

	</div>

	<!-- END of OUR products -->
	<div id="BLOG"></div>
	<!-- WE OFFER -->
	<div id="WE_OFFER"></div>
	<div class="container-fluid cream">
		<div class="container">
			<div class="text-center">
				<h4>BLOG</h4>
			</div>
			<div id="card">
				<div class=" card col-sm-4 col-md-6  col-xs-12  hovereffect ">

					<img class="card-img-top " src="images/spring-logo.png"
						alt="Card image cap"> <a
						href="http://www.yash.com/news-room/press-releases/"
						class="read_more_button hvr-rectangle-out" title="Read More">Read
						More</a>
					<h4></h4>
					<div class="card-block">
						<h3 class="card-text">Some quick example text to build on the
							card title and make up the bulk of the card's content.</h3>

					</div>
				</div>

				<div class="card  col-sm-4 col-md-6 col-xs-12 hovereffect">
					<img class="card-img-top" src="images/hibernate_logo.svg"
						alt="Card image cap"> <a
						href="http://www.yash.com/news-room/press-releases/"
						class="read_more_button hvr-rectangle-out" title="Read More">Read
						More</a>
					<h4></h4>
					<div class="card-block">
						<h3 class="card-text">Some quick example text to build on the
							card title and make up the bulk of the card's content.</h3>
					</div>
				</div>

				<div class="card  col-sm-4 col-md-6 col-xs-12 hovereffect">
					<img class="card-img-top" src="images/AngularJS-logo.png"
						alt="Card image cap"> <a
						href="http://www.yash.com/news-room/press-releases/"
						class="read_more_button hvr-rectangle-out" title="Read More">Read
						More</a>
					<h4></h4>
					<div class="card-block">
						<h3 class="card-text">Some quick example text to build on the
							card title and make up the bulk of the card's content.</h3>
					</div>
				</div>

				<div class="card col-sm-4 col-md-6 col-xs-12 hovereffect">
					<img class="card-img-top" src="images/struts-logo.png"
						alt="Card image cap"> <a
						href="http://www.yash.com/news-room/press-releases/"
						class="read_more_button hvr-rectangle-out" title="Read More">Read
						More</a>
					<h4></h4>
					<div class="card-block">
						<h3 class="card-text">Some quick example text to build on the
							card title and make up the bulk of the card's content.</h3>
					</div>
				</div>

				<div class="card  col-sm-4 col-md-6 col-xs-12 hovereffect">
					<img class="card-img-top" src="images/Java_logo.jpg"
						alt="Card image cap"> <a
						href="http://www.yash.com/news-room/press-releases/"
						class="read_more_button hvr-rectangle-out" title="Read More">Read
						More</a>
					<h4></h4>
					<div class="card-block">
						<h3 class="card-text">Some quick example text to build on the
							card title and make up the bulk of the card's content.</h3>
					</div>
				</div>

				<div class="card  col-sm-4 col-md-6 col-xs-12 hovereffect">
					<img class="card-img-top" src="images/Junit-logo.jpg"
						alt="Card image cap"> <a
						href="http://www.yash.com/news-room/press-releases/"
						class="read_more_button hvr-rectangle-out" title="Read More">Read
						More</a>
					<h4></h4>
					<div class="card-block">
						<h3 class="card-text">Some quick example text to build on the
							card title and make up the bulk of the card's content.</h3>
					</div>
				</div>

			</div>
		</div>

	</div>
	<!--END OF WE OFFER -->

	<!--BOTTOM -->


	<!--END OF BOTTOM -->
	<!--END OF WE OFFER -->
	<div id="CONTACT"></div>

	<!-- Modal For Login -->
	<div id="loginModal" class="modal">
		<!-- Modal content -->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" id="closeLoginModelId">&times;</button>
				<h3>Login</h3>
			</div>
			<div align="center">
				<p id="loginFormMessageId" style="color: red"></p>
				<form id="loginForm">
					<table>
						<tr>
							<td>Email</td>
							<td><input type="text" class="form-control" name="email"
								placeholder="Enter Email " required></td>
						</tr>
						<tr>
							<td>Password</td>
							<td><input type="password" class="form-control"
								name="password" placeholder="Enter Password " required></td>
						</tr>
						<tr>
							<td colspan="2"><input type="checkbox" name="rememberMe"
								checked="checked"> Remember me</td>
						</tr>
						<tr>
							<td colspan="2" align="right"><button type="submit"
									id="submitButtonOfLogin" class="btn btn-success">Submit</button></td>
						</tr>
						<tr>
							<td colspan="2" align="left">Forgot <a href="#">password?</a></td>
						</tr>
					</table>

				</form>
			</div>
		</div>
	</div>
	<!-- Login Modal Ends -->


	<!-- Modal For Registration -->
	<div id="registrationModal" class="modal">
		<!-- Modal content -->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" id="closeRegistrationModelId">&times;</button>
				<h3>Registration</h3>
			</div>
			<div align="center">
				<p id="registrationFormMessageId" style="color: red"></p>
				<form id="registrationForm">
					<table>
						<tr>
							<td>First Name</td>
							<td><input type="text" class="form-control" name="firstname"
								placeholder="Enter First Name " required></td>
						</tr>
						<tr>
							<td>Last Name</td>
							<td><input type="text" class="form-control" name="lastname"
								placeholder="Enter Last Name " required></td>
						</tr>
						<tr>
							<td>Contact No.</td>
							<td><input type="text" class="form-control" name="contact"
								placeholder="Enter Contact No. " required></td>
						</tr>
						<tr>
							<td>Email</td>
							<td><input type="text" class="form-control" name="email"
								placeholder="Enter Email " required></td>
						</tr>
						<tr>
							<td>Password</td>
							<td><input type="password" class="form-control"
								name="password" placeholder="Enter Password " required></td>
						</tr>
						<tr>
							<td colspan="2" align="right"><button type="submit"
									id="submitButtonOfRegistration" class="btn btn-success">Submit</button></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
	<!-- Registration Modal Ends -->


	<footer>

		<div class="container">
			<div class=" about-us col-sm-6 col-md-6 col-xs-12">
				<div class="text-center">
					<h3>ABOUT US</h3>
				</div>
				<p>Lorem Ipsum is simply dummy text of the printing and
					typesetting industry. Lorem Ipsum has been the industry's standard
					dummy text ever since the 1500s, when an unknown printer took a
					galley of type and scrambled it to make a type specimen book.</p>
				<br> <a class="read two" href="#">Read More</a>
			</div>


			<div class="get-in-touch col-sm-6 col-md-6 col-xs-12">
				<div class="text-center">
					<h3>GET IN TOUCH</h3>
				</div>
				<p>Lorem Ipsum is simply dummy text of the printing and
					typesetting industry. Lorem Ipsum has been the industry's standard
					dummy text ever since</p>

				<br>

				<form class="form-inline">
					<div class="form-group">

						<input type="email" class="form-control" id="email"
							placeholder="Enter your email id...">
					</div>

					<button type="submit" class="btn btn-default">SUBSCRIBE</button>
				</form>

			</div>
		</div>


		<div class="text-center">
			<p>All rights are reserved by | Yash Technologies pvt.ltd</p>
		</div>
	</footer>
</body>
</html>