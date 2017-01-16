<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link rel="icon" href="images/logo.png" />
<link rel="stylesheet" type="text/css" href="css/reset-style.css" />
<link rel="stylesheet" type="text/css" href="css/trainee-body-style.css" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="js/pdf.js"></script>
<script src="js/pdf.worker.js"></script>
<script src="js/trainee.js"></script>
<!--Description and Sidebar START -->
<div class="container-fluid grey description-sidebar-container">
	<div class="container col-md-12">
		<div class="row ">

			<!-- SIDEBAR -->
			<div class="col-md-3 sidebar-container" id="table-wrapper">
				<div class="sidebar" id="table-scroll">
					<table class="col-md-12">
						<thead>
							<tr class="">
								<th class="col-md-6 text-center">S. No</th>
								<th class="col-md-6 text-center">Name</th>
								<th class="col-md-6 text-center">Status</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${listOfDocuments}" var="document"
								varStatus="status">
								<tr class="">
									<td class="col-md-6 text-center">${status.index + 1}</td>
									<td class="col-md-6 text-center"><a
										class="links DocumentBox"
										documentDescription="${document.getDescription()}"
										documentPath="${document.getFilePath()}" href="#">
											${document.getName()} </a></td>
									<td class="col-md-6 text-center"><span
										class="glyphicon glyphicon-ok green"></span></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>


			<!--SIDEBAR END -->

			<!--DESCRIPTION -->

			<div class="description col-md-9">
				<h3>Description</h3>
				<p></p>
			</div>

			<!--DESCRIPTION END-->

		</div>
	</div>
</div>

<!--Display Area-->
<div class="container-fluid grey">
	<div class="container">
		<div class="row">

			<div col="col-md-3"></div>

			<div class="col-md-9 display-section-container">
				<div id="display-section">
					<!-- <iframe class="embed-responsive-item"
						src="http://www.w3schools.com/bootstrap/bootstrap_grid_examples.asp"></iframe> -->
					<div id="page" align="center">
						<button id="previouspage" style="float: center">Previous
							page</button>
						<button id="nextpage" style="float: center">NextPage</button>
						<button id="zoom" style="float: rigth">Zoom ( +/- )</button>
						<br>
						<canvas class="iframe" id="canvas"></canvas>
					</div>
				</div>
			</div>

		</div>
	</div>
</div>

<!--Display Area END-->