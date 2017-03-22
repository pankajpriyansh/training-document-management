
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:400,600,600i,700,800"
	rel="stylesheet" type="text/css">
<link rel="icon" href="images/logo.png" />
<link rel="stylesheet" type="text/css" href="css/trainee-body-style.css" />
<link rel="stylesheet" type="text/css" href="css/font-awesome.min.css">
<script src="js/jquery-3.1.1.min.js"></script>

<!-- <link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> -->
<script src="js/pdf.js"></script>
<script src="js/pdf.worker.js"></script>
<script src="js/trainee/trainee.js"></script>

<link rel="stylesheet" href="css/jquery-ui.css">
<script src="js/jquery.js"></script>
<script src="js/jquery-ui.js"></script>

<link href="css/video-js.min.css" rel="stylesheet">
<script src="js/video.min.js"></script>


<!--Description and Sidebar START -->
<div class="container-fluid grey description-sidebar-container">




	<div class="container col-md-12">
		<div class="row ">


			<!--DESCRIPTION -->
			<div class="col-md-12 white description">

				<div class="col-md-2 description">
					<h3>Description</h3>
				</div>
				<div class="col-md-10">

					<p></p>

				</div>


			</div>


			<div align="right">
				<input type="button" value="Raise Request" id="raiseRequest">
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
						<th class=" table_sno ">S.No</th>
						<th class=" table_width">Name</th>
						<th class=" table_width ">Status</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${listOfDocuments}" var="document"
						varStatus="status">
						<tr class="">
							<td class=" table_sno ">${status.index + 1}</td>
							<td class=" table_width "><a class="links DocumentBox"
								documentDescription="${document.getDescription()}"
								documentId="${document.getId()}"
								documentPath="${document.getFilePath()}" href="#">
									${document.getName()} </a></td>
							<td class=" table_width "><span id="${document.getId()}"
								class="glyphicon glyphicon-time green"></span></td>
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
						<div class="pdf" id="page" align="center">
							<canvas id="canvas"></canvas>
							<video id="my-player" class="video-js" controls preload="auto"
								width="1000px" height="400px" poster="images/videoimg.jpg"
								data-setup='{}' style="display: none">
							</video>
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

<!-- Modal1 Raise Request -->

<div id="RaiseRequestModal" class="modal">
	<div class="modal-dialog">
		<!-- Modal content -->
		<div class="modal-content"">
			<div class="modal-header">
				<button type="button" class="close" id="closeRaiseRequestModelId">&times;</button>
				<h4 class="modal-title">Request For Document</h4>
			</div>
			<div class="modal-body">
				<p id="raiseRequestFormMessageId" style="color: red"></p>
				<form id="raiseRequestForm">
					<table>
						<tr>
							<td>To</td>
							<td><select name="receiver" id="trainersEmailSelectBox"
								required>
									<option disabled selected></option>
									<c:forEach items="${trainers}" var="trainer">
										<option value="${trainer.id}">${trainer.email}</option>
									</c:forEach>
							</select></td>
						</tr>
						<!-- <tr>
						
							<td><input type="hidden" class="form-control" name="receiver"
								id="receiver" required></td>
						</tr> -->
						<tr>
							<td>Reason</td>
							<td><input type="text" class="form-control" name="subject"
								id="reasonOfRequest" required></td>
						</tr>
						<tr>
							<td>Select Document</td>
							<td><select name="body" id="documentsSelectBox"
								multiple="multiple" required>
									<option disabled selected></option>
									<c:forEach items="${documents}" var="document">
										<option value="${document.id}">${document.name}</option>
									</c:forEach>
							</select></td>
						</tr>

						<tr>
							<td colspan="2" align="right"><button type="submit"
									id="submitButtonForRaiseRequest" class="btn btn-success">Send</button></td>
						</tr>
					</table>

				</form>
			</div>
		</div>
	</div>
</div>

<!-- Modal1 Raise Request End -->

