<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="icon" href="D:/html assigments/traineer/logo.png" />
<link rel="stylesheet" type="text/css" href="css/reset-style.css" />
<link
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet"
	integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN"
	crossorigin="anonymous">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/jquery.validation/1.15.1/jquery.validate.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/trainer-body-style.css" />
<script src="js/trainer.js"></script>
<div class="container-fluid text-center">
	<div class="row content ">
		<div class="col-sm-2 sidenav">
			<h3>Welcome</h3>
			<!-- Links to be displayed on side nav bar -->
			<a href="#" id="readStatusLinkId">Read Status</a>

		</div>

		<div class="col-sm-10 text-left" id="display-section">

			<div class="col-sm-3 text-left card" id="section">
				<i class="fa fa-pie-chart fa-4" aria-hidden="true"></i>
				<h3>SECTION</h3>
				<hr>
				<h4>TOTAL</h4>
				<div id="totalSectionId">${totalSections}</div>
			</div>

			<div class="col-sm-3 text-left card" id="category">
				<i class="fa fa-th-large icon"></i>
				<h3>CATAGORY</h3>
				<hr>
				<h4>TOTAL</h4>
				<div id="totalCategoryId">${totalCategories}</div>

			</div>
			<div class="col-sm-3 text-left card" id="document">
				<i class="fa fa-upload" aria-hidden="true"></i>
				<h3>DOCUMENT</h3>
				<hr>
				<h4>TOTAL</h4>
				<div id="totalDocumentId">${totalDocuments}</div>

			</div>
		</div>

		<div class="col-sm-3 text-left" id="Addmore-button">
			<button type="button"
				class="btn btn-info btn-lg read_more_button hvr-rectangle-out"
				data-toggle="modal" id="addMoreSectionButtonId">ADD MORE</button>
		</div>
		<div class="col-sm-3 text-left" id="Addmore-button">

			<button type="button"
				class="btn btn-info btn-lg read_more_button hvr-rectangle-out"
				data-toggle="modal" id="addMoreCategoryButtonId">ADD MORE</button>

		</div>
		<div class="col-sm-3 text-left" id="Addmore-button">

			<button type="button"
				class="btn btn-info btn-lg read_more_button hvr-rectangle-out"
				data-toggle="modal" id="addMoreDocumentButtonId">ADD MORE</button>
		</div>

		<div class="col-sm-10 overflow ">
			<br>

			<!-- 	<!-- Check whether read or not 
			<div id="readStatusDivId">asdiuasdku</div>
 -->

			<table
				class="col-md-12 table table-striped table-bordered table-hover"
				id="documentsTableId">
				<thead class="thead-inverse">
					<tr id="heading">
						<th>S.No.</th>
						<th>Section <select id='filterText'
							style='display: inline-block' onchange='filterText()'>
								<option disabled selected>Select</option>
								<c:forEach items="${sections}" var="section">
									<option value="${section.name}">${section.name}</option>
								</c:forEach>
								<option value="ALL">ALL</option>
						</select>
						</th>
						<th>Category</th>
						<th>Name</th>
						<th>Operation</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>

					<c:forEach items="${documents}" var="document" varStatus="status">
						<tr class="contentOfDocuments">
							<c:set var="filePathArray"
								value="${fn:split(document.getFilePath(), '/')}" />
							<td>${status.index + 1}</td>
							<td>${filePathArray[0]}</td>
							<td>${filePathArray[1]}</td>
							<td>${document.getName()}</td>
							<td><input type="radio" documentId="${document.getId()}"
								name="action${document.getId()}" value="show"
								<c:if test="${document.getIsShow() == 1}"> checked</c:if> />SHOW
								<input type="radio" name="action${document.getId()}"
								documentId="${document.getId()}" value="hide"
								<c:if test="${document.getIsShow() == 2}"> checked</c:if> />HIDE</td>
							<td><button class="btn btn-primary btn-xs editDoc "
									documentId="${document.getId()}">Edit</button>
								<button class="btn btn-danger btn-xs "
									id="deleteDocumentButtonId" documentId="${document.getId()}">Delete</button></td>
						</tr>
					</c:forEach>

				</tbody>
			</table>
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
									id="submitButtonOfCreateNewSection" class="btn btn-success">Submit</button></td>
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
									id="submitButtonOfCreateNewCategory" class="btn btn-success">Submit</button></td>
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
								placeholder="Enter name" required></td>
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
									id="submitButtonOfCreateNewDocument" class="btn btn-success">Submit</button></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
</div>


<!-- Modal 4 -->
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
<!-- /Modal 4 -->


<!-- Modal 5 -->
<div id="CheckDocumentReadStatusModal" class="modal">
	<div class="modal-dialog">
		<!-- Modal content -->
		<div class="modal-content"">
			<div class="modal-header">
				<button type="button" class="close"
					id="closeCheckDocumentReadStatusModalId">&times;</button>
				<h3 class="modal-title" align="center">Read Status</h3>
			</div>
			<div class="modal-body">
				<p id="checkDocumentReadStatusFormMessageId" style="color: red"></p>
				<form id="checkDocumentReadStatusForm"
					name="checkDocumentReadStatusForm">
					<table>
						<tr>
							<td>Document</td>
							<td class="dropdown"><input list="documents" name="document"
								id="checkDocumentReadStatusFormDocumentsId"
								placeholder="Select Document" required> <datalist
									id="documents">
									<c:forEach items="${documents}" var="document">
										<option id="${document.name}" value="${document.name}"
											documentId="${document.id}">
									</c:forEach>
								</datalist></td>
						</tr>
						<tr>
							<td>Member</td>
							<td class="dropdown"><input list="members" name="member"
								id="checkDocumentReadStatusFormMemberId"
								placeholder="Select Member" required> <datalist
									id="members">
									<%-- <c:forEach items="${members}" var="member">
										<option id="${member.firstname}" value="${member.firstname}"
											documentId="${member.id}">
									</c:forEach> --%>
								</datalist></td>
							<td><input type="text" id="hiddenFieldMemberId"
								name="memberId" style="display: none" /></td>

						</tr>
						<tr>
							<td colspan="2" align="right"><button type="submit"
									id="submitButtonOfcheckDocumentReadStatusForm"
									class="btn btn-warning">Check</button></td>
						</tr>
					</table>
				</form>
				<br>
				<table>
					<tr>
						<td>Status :</td>
						<td><span id="statusRead" style="color: green"></span> <span
							id="statusUnRead" style="color: red"></span></td>
					</tr>
					<tr>
						<td>First Seen :</td>
						<td><span id="FirstSeen" style="color: green"></span>
					</tr>
					<tr>
						<td>Last Seen :</td>
						<td><span id="LastSeen" style="color: green"></span>
					</tr>
				</table>

			</div>
		</div>
	</div>
</div>
<!-- /Modal5 -->