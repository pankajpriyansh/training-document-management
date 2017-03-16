<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" type="text/css" href="css/reset-style.css" />
<!-- <link href="css/font-awesome.min.css" rel="stylesheet" />
 -->
<link
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet"
	integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN"
	crossorigin="anonymous">
<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/jquery.validate.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/trainer-body-style.css" />
<script src="js/trainer/trainer.js"></script>

<script src="js/amcharts/amcharts.js"></script>
<script src="js/amcharts/serial.js"></script>
<script src="js/amcharts/plugins/export/export.min.js"></script>
<link rel="stylesheet" href="css/export.css" type="text/css" media="all" />
<script src="js/amcharts/themes/light.js"></script>
<!-- 
<script src="https://www.amcharts.com/lib/3/amcharts.js"></script>
<script src="https://www.amcharts.com/lib/3/serial.js"></script>
<script
	src="https://www.amcharts.com/lib/3/plugins/export/export.min.js"></script>
<link rel="stylesheet"
	href="https://www.amcharts.com/lib/3/plugins/export/export.css"
	type="text/css" media="all" />
<script src="https://www.amcharts.com/lib/3/themes/light.js"></script>
 -->
<div class="container-fluid text-center">
	<div class="col-sm-12 text-left">
		<div class="col-sm-4 text-left">
			Select Batch : <select id="batchIdForGraphData">
				<option disabled selected>Select</option>

				<c:forEach items="${batches}" var="batch" varStatus="status">
					<c:choose>
						<c:when test="${status.index == 0}">
							<option value="${batch.id}" selected>${batch.name}</option>
						</c:when>
						<c:otherwise>
							<option value="${batch.id}">${batch.name}</option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select>
		</div>
	</div>
	<div id="charts">

		<!-- Graph1 Division -->
		<div id="chartdiv"></div>
		<div id="chartdivForBatch"></div>
	</div>
	<div class="row content ">
		<!-- <div class="col-sm-2 sidenav">
			<h3>Welcome</h3>
			Links to be displayed on side nav bar
			<a href="#" id="readStatusLinkId">Read Status</a>

		</div> -->

		<div class="col-sm-12 text-left" id="display-section">

			<div class="col-sm-2 text-left ">
				<div class="col-sm-11 card" id="section">
					<i class="fa fa-pie-chart fa-4" aria-hidden="true"></i>
					<h3>SECTION</h3>
					<hr>
					<h4>TOTAL</h4>
					<div id="totalSectionId">${totalSections}</div>
				</div>
			</div>

			<div class="col-sm-2 text-left ">
				<div class="col-sm-11 card" id="category">
					<i class="fa fa-th-large icon"></i>
					<h3>CATEGORY</h3>
					<hr>
					<h4>TOTAL</h4>
					<div id="totalCategoryId">${totalCategories}</div>
				</div>
			</div>
			<div class="col-sm-2 text-left ">
				<div class="col-sm-11 card" id="document">
					<i class="fa fa-upload" aria-hidden="true"></i>
					<h3>DOCUMENT</h3>
					<hr>
					<h4>TOTAL</h4>
					<div id="totalDocumentId">${totalDocuments}</div>
				</div>
			</div>

			<div class="col-sm-2 text-left ">
				<div class="col-sm-11 card" id="batch">
					<i class="fa fa-users" aria-hidden="true"></i>
					<h3>BATCH</h3>
					<hr>
					<h4>TOTAL</h4>
					<div id="totalBatchId">${totalBatches}</div>
				</div>
			</div>
			<div class="col-sm-2 text-left ">
				<div class="col-sm-11 card" id="updates">
					<i class="fa fa-newspaper-o" aria-hidden="true"></i>
					<h3>UPDATES</h3>
					<hr>
					<h4>TOTAL</h4>
					<div id=""></div>
				</div>
			</div>
			<div class="col-sm-2 text-left ">
				<div class="col-sm-11 card" id="blog">
					<i class="fa fa-book" aria-hidden="true"></i>
					<h3>BLOG</h3>
					<hr>
					<h4>TOTAL</h4>
					<div id=""></div>
				</div>
			</div>


		</div>

		<div class="col-sm-2 text-left" id="Addmore-button">
			<button type="button"
				class="btn btn-primary  read_more_button hvr-rectangle-out"
				data-toggle="modal" id="addMoreSectionButtonId">ADD MORE</button>
		</div>
		<div class="col-sm-2 text-left" id="Addmore-button">
			<button type="button"
				class="btn btn-primary  read_more_button hvr-rectangle-out"
				data-toggle="modal" id="addMoreCategoryButtonId">ADD MORE</button>

		</div>
		<div class="col-sm-2 text-left" id="Addmore-button">
			<button type="button"
				class="btn btn-primary  read_more_button hvr-rectangle-out"
				data-toggle="modal" id="addMoreDocumentButtonId">ADD MORE</button>
		</div>



		<div class="col-sm-2 text-left" id="Addmore-button">
			<button type="button"
				class="btn btn-primary  read_more_button hvr-rectangle-out"
				data-toggle="modal" id="addMoreBatchButtonId">ADD MORE</button>
		</div>
		<div class="col-sm-2 text-left" id="Addmore-button">
			<button type="button"
				class="btn btn-primary  read_more_button hvr-rectangle-out"
				data-toggle="modal" id="">ADD MORE</button>
		</div>
		<div class="col-sm-2 text-left" id="Addmore-button">
			<button type="button"
				class="btn btn-primary  read_more_button hvr-rectangle-out"
				data-toggle="modal" id="">ADD MORE</button>
		</div>

	</div>
</div>


<!-- Modal 1 -->
<div id="SectionModal" class="modal">
	<div class="modal-dialog">
		<!-- Modal content -->
		<div class="modal-content"">
			<div class="modal-header">
				<button type="button" class="close" id="closeNewSectionModelId">&times;</button>
				<h4 class="modal-title">Section</h4>
			</div>
			<div class="modal-body">
				<p id="newSectionFormMessageId" style="color: red"></p>
				<form id="newSectionForm">
					<table>
						<tr>
							<td>NAME</td>
							<td><input type="text" class="form-control"
								name="sectionName" id="sectionName" placeholder="Enter NAME "
								required></td>
						</tr>
						<tr>
							<td colspan="2"><button type="submit"
									id="submitButtonOfCreateNewSection" class="btn btn-primary">Submit</button></td>
						</tr>
					</table>

				</form>
			</div>
		</div>
	</div>
</div>
<!-- /Modal 1 -->

<!-- Modal 2 -->
<div id="CategoryModal" class="modal">
	<div class="modal-dialog">

		<!-- Modal content -->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" id="closeNewCategoryModelId">&times;</button>
				<h3 class="modal-title">Section</h3>
			</div>
			<div class="modal-body">
				<p id="newCategoryFormMessageId" style="color: red"></p>

				<form id="newCategoryForm">

					<table>
						<tr>
							<td>Section</td>
							<td class="dropdown"><input list="sections" name="section"
								id="selectSectionBoxIdInCreateCategory"
								placeholder="Select Section" required> <datalist
									id="sections" class="sections">
									<c:forEach items="${sections}" var="section">
										<option id="${section.name}" value="${section.name}"
											sectionId="${section.id}">
									</c:forEach>
								</datalist></td>
							<td><input type="text"
								id="hiddenFieldSectionIdInCreateCategory" name="sectionId"
								style="display: none" /></td>
						</tr>
						<tr>
							<td>Name</td>
							<td><input type="text" class="form-control"
								name="categoryName" id="newCategoryName"
								placeholder="Enter name" required></td>
						</tr>
						<tr>
							<td colspan="2"><button type="submit"
									id="submitButtonOfCreateNewCategory" class="btn btn-primary">Submit</button></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
</div>

<!-- Modal 3 -->
<div id="DocumentModal" class="modal">
	<div class="modal-dialog">
		<!-- Modal content -->
		<div class="modal-content"">
			<div class="modal-header">
				<button type="button" class="close" id="closeNewDocumentModelId"">&times;</button>
				<h3 class="modal-title">Document</h3>
			</div>
			<div class="modal-body">
				<p id="newDocumentFormMessageId" style="color: red"></p>
				<form id="newDocumentForm" name="newDocumentForm"
					enctype="multipart/form-data" method="post">

					<table>
						<tr>
							<td>Select Batch</td>
							<td><select name="batchId">
									<option disabled selected>Select</option>
									<c:forEach items="${batches}" var="batch">
										<option value="${batch.id}">${batch.name}</option>
									</c:forEach>
							</select></td>
						</tr>
						<tr>
							<td>Section</td>
							<td class="dropdown"><input list="sections" name="section"
								id="selectSectionBoxIdInCreateDocument"
								placeholder="Select Section" required> <datalist
									class="sections" id="sections">
									<c:forEach items="${sections}" var="section">
										<option id="${section.name}" value="${section.name}"
											sectionId="${section.id}">
									</c:forEach>
								</datalist></td>
						</tr>
						<tr>
							<td>Category</td>
							<td class="dropdown"><input list="categoriesListFromJson"
								name="category" id="categoryBoxId" placeholder="Select Category"
								required> <datalist id="categoriesListFromJson">
								</datalist></td>
							<td><input type="text" id="hiddenFieldCategoryId"
								name="category_id" style="display: none" /></td>
						</tr>
						<tr>
							<td>Name</td>
							<td><input type="text" class="form-control" name="name"
								id="DocumentNameID" placeholder="Enter name" required></td>
						</tr>
						<tr>
							<td>Desciption</td>
							<td><textarea rows="5" cols="20" class="form-control"
									name="description" placeholder="Enter description" required></textarea></td>
						</tr>
						<tr>
							<td>Select File to Upload</td>
							<td><input type="file" class="form-control"
								name="fileToUpload" id="fileChooser" required /></td>
						</tr>
						<tr>
							<td colspan="2"><button type="submit"
									id="submitButtonOfCreateNewDocument" class="btn btn-primary">Submit</button></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
</div>


<!-- Modal 4 
<div id="editDocumentModal" class="modal">
	<div class="modal-dialog">
		<div class="modal-content"">
			<div class="modal-header">
				<button type="button" class="close" id="closeEditDocumentModelId"">&times;</button>
				<h3 class="modal-title">EditDocument</h3>
			</div>
			<form id="editDocumentForm" name="editDocumentForm">
				<table>
					<tr>
						<td>Name</td>
						<td><input type="text" class="form-control" name="name"
							placeholder="Enter name" required></td>
						<td><input type="text" style="display: none;"
							name="hiddenDocumentId"></td>
					</tr>
					<tr>
						<td>Desciption</td>
						<td><textarea rows="5" cols="20" class="form-control"
								name="description" placeholder="Enter description" required></textarea></td>
					</tr>

					<tr>
						<td colspan="2"><button type="submit"
								id="submitButtonOfEditDocument" class="btn btn-success">Submit</button></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</div>
 /Modal 4 -->




<!-- Modal 6 -->
<div id="BatchModal" class="modal">
	<div class="modal-dialog">
		<!-- Modal content -->
		<div class="modal-content"">
			<div class="modal-header">
				<button type="button" class="close" id="closeNewBatchModelId">&times;</button>
				<h4 class="modal-title">Batch</h4>
			</div>
			<div class="modal-body">
				<p id="newBatchFormMessageId" style="color: red"></p>
				<form id="newBatchForm">
					<table>
						<tr>
							<td>NAME</td>
							<td><input type="text" class="form-control" name="batchName"
								id="batchName" placeholder="Enter NAME " required></td>
						</tr>
						<tr>
							<td colspan="2"><button type="submit"
									id="submitButtonOfCreateNewBatch" class="btn btn-primary">Submit</button></td>
						</tr>
					</table>

				</form>
			</div>
		</div>
	</div>
</div>
<!-- /Modal 6 -->