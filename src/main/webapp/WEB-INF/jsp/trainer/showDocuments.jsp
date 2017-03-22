<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/pdf.js"></script>
<script src="js/pdf.worker.js"></script>

<link href="css/video-js.min.css" rel="stylesheet">
<script src="js/video.min.js"></script>
<!-- 
<script
	src="https://cdn.datatables.net/1.10.13/js/jquery.dataTables.min.js"></script> -->

<script src="js/jquery.dataTables.min.js"></script>

<link rel="stylesheet" type="text/css"
	href="css/jquery.dataTables.min.css" />
<link rel="stylesheet" type="text/css" href="css/trainer-body-style.css" />
<script src="js/trainer/showDocuments.js"></script>

<script>
	$(document).ready(function() {
		jQuery.noConflict();

		$('#documentsTableId').DataTable({
			"pagingType" : "full_numbers"
		});
	});
</script>

<style type="text/css">
.pdf {
	height: 400px;
	overflow-x: scroll;
	overflow-y: scroll;
}
</style>

<div class="col-sm-12 overflow ">
	<br>

	<table class="col-md-12 table table-striped table-bordered table-hover"
		id="documentsTableId">
		<thead class="thead-inverse">
			<tr id="heading">
				<th>S.No.</th>
				<th>Section <select id='filterTextOfSectionColumn'
					style='display: inline-block'
					onchange='filterTextOfSectionColumn()'>
						<option disabled selected>Select</option>
						<c:forEach items="${sections}" var="section">
							<option id="${section.name}" value="${section.name}"
								sectionId="${section.id}">${section.name}</option>
						</c:forEach>
						<option value="ALL">ALL</option>
				</select>
				</th>
				<th>Category <select id='filterTextOfCategoryColumn'
					style='display: inline-block'
					onchange='filterTextOfCategoryColumn()'>
						<option disabled selected>Select</option>
						<option value="ALL">ALL</option>
				</select></th>
				<th>Name</th>
				<th>Batch <select id="filterTextOfBatchColumn"
					style='display: inline-block' onchange='filterTextOfBatchColumn()'>
						<option disabled selected>Select</option>
						<c:forEach items="${batches}" var="batch">
							<option value="${batch.name}">${batch.name}</option>
						</c:forEach>
						<option value="ALL">ALL</option>
				</select></th>
				<th>Created Date <input type=date
					(yyyy-mm-dd) id='filterTextOfCreatedDateColumn'
					style='display: inline-block'
					onchange='filterTextOfCreatedDateColumn()' /></th>
				<th>Operation <select id="filterTextOfOperationColumn"
					style='display: inline-block'>
						<option disabled selected>Select</option>
						<option value="SHOW">SHOW</option>
						<option value="HIDE">HIDE</option>
						<option value="ALL">ALL</option>
				</select>
				</th>
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
					<td><c:forEach items="${batches}" var="batch">
							<c:if test="${document.getBatchId() == batch.id}"> ${batch.name}</c:if>
						</c:forEach></td>
					<td>${document.getCreatedDate()}</td>
					<td><input type="radio" documentId="${document.getId()}"
						name="action${document.getId()}" value="show"
						<c:if test="${document.getIsShow() == 1}"> checked</c:if> />SHOW
						<br> <input type="radio" name="action${document.getId()}"
						documentId="${document.getId()}" value="hide"
						<c:if test="${document.getIsShow() == 2}"> checked</c:if> />HIDE</td>
					<td><button class="btn btn-primary btn-xs editDoc "
							documentId="${document.getId()}">Edit</button>
						<button class="btn btn-danger btn-xs deleteDoc"
							documentId="${document.getId()}">Delete</button>
						<button class="btn btn-success btn-xs displayDoc"
							documentPath="${document.getFilePath()}"
							documentName="${document.getName()}">Display</button></td>
				</tr>
			</c:forEach>

		</tbody>
	</table>
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
								id="submitButtonOfEditDocument" class="btn btn-primary">Submit</button></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</div>
<!-- /Modal 4 -->


<!-- Modal 5 -->
<div id="documentShowStatusModal" class="modal">
	<div class="modal-dialog">
		<!-- Modal content -->
		<div class="modal-content"">
			<div class="modal-header">
				<button type="button" class="close"
					id="closeDocumentShowStatusModal">&times;</button>
				<h3 class="modal-title" align="center">Status Change of
					Document</h3>
			</div>
			<div class="modal-body">
				<input type="text" id="documentShowStatusModalDocumentId"
					style="display: none"> <input type="text"
					id="documentShowStatusModalStatusValue" style="display: none">
				<br> For All :
				<button id="forAllDocumentShowStatusButtonId"
					class="btn btn-primary">Change Status</button>
				<br> <br> <br> <br>For Specific Member :<br>
				Select Member : <select
					id="forSpecificMemberDocumentShowStatusSelectBoxId">
					<option disabled selected>Select</option>

				</select> <br>
				<button id="forSpecificMemberDocumentShowStatusButtonId"
					class="btn btn-primary">Change Status</button>


			</div>
		</div>
	</div>
</div>
<!-- /Modal5 -->

<!-- Modal 6 -->
<div id="displayDocumentModal" class="modal">
	<div class="modal-dialog " style="height: 800px; margin-left: 130px">
		<div class="modal-content" id="mcnt" style="width: 1100px;">
			<div class="modal-header">
				<button type="button" class="close" id="closeDisplayDocumentModelId"">&times;</button>
				<h3 class="modal-title" id="displayDocumentModalHeadingId"
					align="center"></h3>
			</div>
			<div id="buttonsOfPdf" align="center">
				<button type="button" id="previouspage"
					class="btn btn-primary btn-xs ">Previous</button>
				<button class="btn" id="zoom">
					<span class="glyphicon glyphicon-zoom-out gray"></span>
				</button>
				<button type="button" id="nextpage" class="btn btn-primary btn-xs">Next</button>
			</div>
			<div class="pdf" id="page" align="center">
				<canvas id="canvas"></canvas>
			</div>
			<div align="center">
				<video id="my-player" class="video-js" controls preload="auto"
					width="500px" height="350px" poster="images/videoimg.jpg"
					data-setup='{}' style="display: none">
				</video>
			</div>
		</div>
	</div>
</div>
<!-- Modal 6 -->
