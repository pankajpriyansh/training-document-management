<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:400,600,600i,700,800"
	rel="stylesheet" type="text/css">
<link rel="icon" href="images/logo.png" />
<link rel="stylesheet" type="text/css"
	href="css/trainee-body-style-css.css" />
<link rel="stylesheet" type="text/css" href="css/font-awesome.min.css">
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
	<div class="col-md-3 " id="logo">

		<!-- SIDEBAR -->
		<div class="col-md-3 sidebar-container" id="table-wrapper">
			<div class="sidebar" id="table-scroll">
				<table class="col-md-12">
					<thead>
						<tr class="">
							<th class=" table_sno text-center">S.No</th>
							<th class=" table_width text-center">Name</th>
							<th class=" table_width text-center">Status</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${listOfDocuments}" var="document"
							varStatus="status">
							<tr class="">
								<td class=" table_sno text-center">${status.index + 1}</td>
								<td class=" table_width "><a class="links DocumentBox"
									documentDescription="${document.getDescription()}"
									documentId="${document.getId()}"
									documentPath="${document.getFilePath()}" href="#">
										${document.getName()} </a></td>
								<td class=" table_width text-center"><span
									id="${document.getId()}" class="glyphicon glyphicon-time green"></span></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>


		<!--SIDEBAR END -->
	</div>
	<div class="container col-md-9">
		<div class="row ">


			<!--DESCRIPTION -->
			<div class="col-md-12 white">
				<h3>Description</h3>
			</div>
			<div class=" col-md-12 description ">
				<p></p>
			</div>

			<!--DESCRIPTION END-->

		</div>
	</div>


	<!--Display Area-->
	<div class="container-fluid grey ">
		<div class="container">
			<div class="row ">



				<div class="col-md-9 display-pdf ">
					<div class="display-section">
						<!-- <iframe class="embed-responsive-item"
						src="http://www.w3schools.com/bootstrap/bootstrap_grid_examples.asp"></iframe> -->
						<div class="col-md-12 pdf-nav">



							<button type="button" id="previouspage" class="btn1 btn-default ">
								<span class="glyphicon glyphicon-circle-arrow-left"></span>
							</button>
							<button class="btn" id="zoom">
								<span class="glyphicon glyphicon-zoom-out"></span>
							</button>


							<button type="button" id="nextpage" class="btn2 btn-default ">
								<span class="glyphicon glyphicon-circle-arrow-right"></span>
							</button>
						</div>
						<div class="pdf" id="page" align="center">

							<br>
							<canvas id="canvas"></canvas>


						</div>
					</div>
				</div>

			</div>
		</div>
	</div>
</div>

<!--Display Area END-->
 --%>




<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:400,600,600i,700,800"
	rel="stylesheet" type="text/css">
<link rel="icon" href="images/logo.png" />
<link rel="stylesheet" type="text/css" href="css/trainee-body-style.css" />
<link rel="stylesheet" type="text/css" href="css/font-awesome.min.css">
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


			<!--DESCRIPTION -->
			<div class="col-md-12 white description">

				<div class="col-md-2">
					<h3>Description</h3>
				</div>
				<div class="col-md-10">

					<p></p>

				</div>
			</div>

			<!--DESCRIPTION END-->

		</div>
	</div>




	<!-- SIDEBAR -->
	<div class="col-md-3 sidebar-container" id="table-wrapper">
		<div class="sidebar" id="table-scroll">
			<table class="col-md-12">
				<thead>
					<tr class="">
						<th class=" table_sno text-center">S.No</th>
						<th class=" table_width text-center">Name</th>
						<th class=" table_width text-center">Status</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${listOfDocuments}" var="document"
						varStatus="status">
						<tr class="">
							<td class=" table_sno text-center">${status.index + 1}</td>
							<td class=" table_width "><a class="links DocumentBox"
								documentDescription="${document.getDescription()}"
								documentId="${document.getId()}"
								documentPath="${document.getFilePath()}" href="#">
									${document.getName()} </a></td>
							<td class=" table_width text-center"><span
								id="${document.getId()}" class="glyphicon glyphicon-time green"></span></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>


	<!--SIDEBAR END -->






	<!--Display Area-->
	<div class="container-fluid grey ">
		<div class="container">
			<div class="row ">



				<div class="col-md-9 display-pdf ">
					<div class="display-section">
						<!-- <iframe class="embed-responsive-item"
						src="http://www.w3schools.com/bootstrap/bootstrap_grid_examples.asp"></iframe> -->

						<div class="pdf" id="page" align="center">

							<canvas id="canvas"></canvas>


						</div>
					</div>
				</div>

			</div>
		</div>
	</div>

	<div class="col-md-11 pdf-nav">



		<button type="button" id="previouspage" class="btn1 btn-default ">
			<span class="glyphicon glyphicon-circle-arrow-left gray">Previous</span>
		</button>
		<button class="btn" id="zoom">
			<span class="glyphicon glyphicon-zoom-out gray"></span>
		</button>


		<button type="button" id="nextpage" class="btn2 btn-default ">
			Next<span class="glyphicon glyphicon-circle-arrow-right gray"></span>
		</button>
	</div>

</div>
<!--Display Area END-->
