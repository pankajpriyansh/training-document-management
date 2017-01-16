<link rel="icon" href="D:/html assigments/traineer/logo.png" />
<link rel="stylesheet" type="text/css" href="css/reset-style.css" />
<link rel="stylesheet" type="text/css" href="css/trainer-body-style.css" />

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="js/trainer.js"></script>

<!-- <a href="./createDocumentForm.html"><b>Create Document</b></a>
<br>
Back to
<a href="./index.html"><b>Home</b></a>
Page -->

<div class="container-fluid text-center">
	<div class="row content ">
		<div class="col-sm-2 sidenav">
			<h3>Welcome</h3>

			<table class="col-md-12">
				<thead>
					<tr class="">
						<th class="col-md-6 text-center">Action</th>
					</tr>
				</thead>

				<tbody>
					<tr class="">
						<td class="col-md-6 text-center"><a class="links"
							href="#display-section"> Document Operations </a></td>
					</tr>

					<tr class="">
						<td class="col-md-6 text-center"><a class="links"
							href="#display-section"> Reports </a></td>
					</tr>

				</tbody>
			</table>

		</div>

		<div class="col-sm-10 text-left" id="display-section">

			<div class="col-sm-3 text-left" id="section">
				<h3>SECTION</h3>
				<hr>
				<h4>TOTAL</h4>
				<div id="totalSectionId">${totalSections}</div>
			</div>

			<div class="col-sm-3 text-left" id="section">
				<h3>CATAGORY</h3>
				<hr>
				<h4>TOTAL</h4>
				<div id="totalCategoryId">${totalCategories}</div>

			</div>
			<div class="col-sm-3 text-left" id="section">
				<h3>DOCUMENT</h3>
				<hr>
				<h4>TOTAL</h4>
				<div id="totalDocumentId">${totalDocuments}</div>

			</div>
		</div>

		<div class="col-sm-3 text-left" id="Addmore-button">

			<button type="button"
				class="btn btn-info btn-lg read_more_button hvr-rectangle-out"
				data-toggle="modal" data-target="#Section">ADD MORE</button>
		</div>
		<div class="col-sm-3 text-left" id="Addmore-button">

			<button type="button"
				class="btn btn-info btn-lg read_more_button hvr-rectangle-out"
				data-toggle="modal" data-target="#Catagory">ADD MORE</button>

		</div>
		<div class="col-sm-3 text-left" id="Addmore-button">

			<button type="button"
				class="btn btn-info btn-lg read_more_button hvr-rectangle-out"
				data-toggle="modal" data-target="#Doc">ADD MORE</button>
		</div>






		<div class="col-sm-10 overflow ">
			<br>
			<table class="col-md-12">
				<!-- <thead>
					<tr class="">
						<th class="col-md-6 text-center">Link</th>
						<th class="col-md-6 text-center">Status</th>
					</tr>
				</thead>

				<tbody>



					<tr class="">
						<td class="col-md-6 text-center"><a class="links"
							href="#display-section"> Link </a></td>
						<td class="col-md-6 text-center"><span
							class="glyphicon glyphicon-time grey"></span></td>
					</tr>

					<tr class="">
						<td class="col-md-6 text-center"><a class="links"
							href="#display-section"> Link </a></td>
						<td class="col-md-6 text-center"><span
							class="glyphicon glyphicon-remove red"></span></td>
					</tr>
					<tr class="">
						<td class="col-md-6 text-center"><a class="links"
							href="#display-section"> Link </a></td>
						<td class="col-md-6 text-center"><span
							class="glyphicon glyphicon-time grey"></span></td>
					</tr>

					<tr class="">
						<td class="col-md-6 text-center"><a class="links"
							href="#display-section"> Link </a></td>
						<td class="col-md-6 text-center"><span
							class="glyphicon glyphicon-remove red"></span></td>
					</tr>

					<tr class="">
						<td class="col-md-6 text-center"><a class="links"
							href="#display-section"> Link </a></td>
						<td class="col-md-6 text-center"><span
							class="glyphicon glyphicon-ok green"></span></td>
					</tr>

					<tr class="">
						<td class="col-md-6 text-center"><a class="links"
							href="#display-section"> Link </a></td>
						<td class="col-md-6 text-center"><span
							class="glyphicon glyphicon-time grey"></span></td>
					</tr>

					<tr class="">
						<td class="col-md-6 text-center"><a class="links"
							href="#display-section"> Link </a></td>
						<td class="col-md-6 text-center"><span
							class="glyphicon glyphicon-remove red"></span></td>
					</tr>

					<tr class="">
						<td class="col-md-6 text-center"><a class="links"
							href="#display-section"> Link </a></td>
						<td class="col-md-6 text-center"><span
							class="glyphicon glyphicon-time grey"></span></td>
					</tr>

					<tr class="">
						<td class="col-md-6 text-center"><a class="links"
							href="#display-section"> Link </a></td>
						<td class="col-md-6 text-center"><span
							class="glyphicon glyphicon-remove red"></span></td>
					</tr>

					<tr class="">
						<td class="col-md-6 text-center"><a class="links"
							href="#display-section"> Link </a></td>
						<td class="col-md-6 text-center"><span
							class="glyphicon glyphicon-time grey"></span></td>
					</tr>

					<tr class="">
						<td class="col-md-6 text-center"><a class="links"
							href="#display-section"> Link </a></td>
						<td class="col-md-6 text-center"><span
							class="glyphicon glyphicon-remove red"></span></td>
					</tr>

					<tr class="">
						<td class="col-md-6 text-center"><a class="links"
							href="#display-section"> Link </a></td>
						<td class="col-md-6 text-center"><span
							class="glyphicon glyphicon-time grey"></span></td>
					</tr>

					<tr class="">
						<td class="col-md-6 text-center"><a class="links"
							href="#display-section"> Link </a></td>
						<td class="col-md-6 text-center"><span
							class="glyphicon glyphicon-remove red"></span></td>
					</tr>

					<tr class="">
						<td class="col-md-6 text-center"><a class="links"
							href="#display-section"> Link </a></td>
						<td class="col-md-6 text-center"><span
							class="glyphicon glyphicon-time grey"></span></td>
					</tr>

					<tr class="">
						<td class="col-md-6 text-center"><a class="links"
							href="#display-section"> Link </a></td>
						<td class="col-md-6 text-center"><span
							class="glyphicon glyphicon-remove red"></span></td>
					</tr>

					<tr class="">
						<td class="col-md-6 text-center"><a class="links"
							href="#display-section"> Link </a></td>
						<td class="col-md-6 text-center"><span
							class="glyphicon glyphicon-ok green"></span></td>
					</tr>

					<tr class="">
						<td class="col-md-6 text-center"><a class="links"
							href="#display-section"> Link </a></td>
						<td class="col-md-6 text-center"><span
							class="glyphicon glyphicon-time grey"></span></td>
					</tr>

					<tr class="">
						<td class="col-md-6 text-center"><a class="links"
							href="#display-section"> Link </a></td>
						<td class="col-md-6 text-center"><span
							class="glyphicon glyphicon-ok green"></span></td>
					</tr>

				</tbody> -->
			</table>
		</div>

	</div>
</div>



<!-- Modal 1-->
<div class="modal fade" id="Section" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Section</h4>
			</div>
			<div class="modal-body">
				<form class="form-inline" id="newSectionForm">
					<div class="form-group">
						<label>NAME:</label> <input type="text" class="form-control"
							id="newSectionName" placeholder="Enter NAME ">
					</div>

					<button type="button" id="newSectionFormSubmitId"
						class="btn btn-default">Submit</button>
				</form>
			</div>
			<div class="modal-footer">
				<button type="submit" class="btn btn-default" data-dismiss="modal"
					id="closeNewSectionModelId">Close</button>
			</div>
		</div>
	</div>
</div>



<!-- Modal 2-->
<div class="modal fade" id="Catagory" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Catagory</h4>
			</div>
			<div class="modal-body">
				<br>
				<form class="form-horizontal">




					<div class="form-group">
						<label class="control-label col-sm-2">SELECT SECTION</label>
						<div class="dropdown">
							<button class="btn btn-primary dropdown-toggle" type="button"
								data-toggle="dropdown">
								Dropdown Example <span class="caret"></span>
							</button>
							<ul class="dropdown-menu">
								<li><a href="#">HTML</a></li>
								<li><a href="#">CSS</a></li>
								<li><a href="#">JavaScript</a></li>
							</ul>
						</div>
					</div>


					<div class="form-group">
						<label class="control-label col-sm-2">Name:</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="email"
								placeholder="Enter name">
						</div>
					</div>




					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" class="btn btn-default">Submit</button>
						</div>
					</div>
				</form>

			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>

		</div>
	</div>
</div>


<!-- Modal 3-->
<div class="modal fade" id="Doc" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Document</h4>
			</div>


			<div class="modal-body ">
				<form class="form-horizontal">




					<div class="form-group">
						<label class="control-label col-sm-2">SELECT SECTION</label>
						<div class="dropdown">
							<button class="btn btn-primary dropdown-toggle" type="button"
								data-toggle="dropdown">
								Dropdown Example <span class="caret"></span>
							</button>
							<ul class="dropdown-menu">
								<li><a href="#">HTML</a></li>
								<li><a href="#">CSS</a></li>
								<li><a href="#">JavaScript</a></li>
							</ul>
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-sm-2" for="pwd">SELECT
							CATAGORY</label>
						<div class="dropdown">
							<button class="btn btn-primary dropdown-toggle" type="button"
								data-toggle="dropdown">
								Dropdown Example <span class="caret"></span>
							</button>
							<ul class="dropdown-menu">
								<li><a href="#">HTML</a></li>
								<li><a href="#">CSS</a></li>
								<li><a href="#">JavaScript</a></li>
							</ul>
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-sm-2">Name:</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="email"
								placeholder="Enter name">
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-sm-2" for="pwd">Desciption:</label>
						<div class="col-sm-10">
							<input type="text" class="form-control"
								placeholder="Enter description">
						</div>
					</div>



					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" class="btn btn-default">Submit</button>
						</div>
					</div>
				</form>

			</div>
			<div class="modal-footer">
				<br>
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>

</div>
</div>

