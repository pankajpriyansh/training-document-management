<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<script src="js/jquery-3.1.1.min.js"></script>

<link rel="stylesheet" type="text/css"
	href="css/jquery.dataTables.min.css" />
<script src="js/jquery.dataTables.min.js"></script>
<script src="js/trainer/documentsRequestPage.js"></script>

<script src="js/amcharts/amcharts.js"></script>
<script src="js/amcharts/serial.js"></script>
<script src="js/amcharts/plugins/export/export.min.js"></script>
<link rel="stylesheet" href="css/export.css" type="text/css" media="all" />
<script src="js/amcharts/themes/light.js"></script>


<script>
	$(document).ready(function() {
		jQuery.noConflict();

		$('#requestedDocumentsTableId').DataTable({
			"pagingType" : "full_numbers"
		});
	});
</script>

<div class="col-sm-12 overflow ">
	<br>
	<table class="col-md-12 table table-striped table-bordered table-hover"
		id="requestedDocumentsTableId">
		<thead class="thead-inverse">
			<tr id="heading">
				<th>S.No.</th>
				<th>Name</th>
				<th>Email</th>
				<th>Batch</th>
				<th>Reason</th>
				<th>Documents</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>

			<c:forEach items="${requestedDocuments}" var="request"
				varStatus="status">
				<tr class="contentOfDocuments">
					<td>${status.index + 1}</td>
					<td>${request.name}</td>
					<td>${request.email}</td>
					<td>${request.batch}</td>
					<td>${request.reason}</td>
					<td><c:forEach items="${request.documents}" var="document"
							varStatus="stat">
							<c:set var="documentsId"
								value="${stat.first ? '' : documentsId},${document.id}" />
							<b>${document.name}</b>
							<br>
						</c:forEach></td>
					<td><button documents="${documentsId}"
							memberId="${request.memberId}" requestId="${request.requestId}"
							class="btn btn-primary approvedButton">Approved</button>
						<button class="btn btn-danger rejectButton"
							requestId="${request.requestId}">Reject</button></td>
				</tr>
			</c:forEach>

		</tbody>
	</table>
</div>

<!-- Modal 1 -->
<div id="RejectReasonModal" class="modal">
	<div class="modal-dialog">
		<!-- Modal content -->
		<div class="modal-content"">
			<div class="modal-header">
				<button type="button" class="close" id="closeRejectReasonModal">&times;</button>
				<h4 class="modal-title">Reason</h4>
			</div>
			<div class="modal-body">
				<p id="rejectReasonFormMessageId" style="color: red"></p>
				<form id="rejectReasonForm">
					<table>
						<tr>
							<td>Reason <input type="text" style="display: none"
								id="hiddenRequestId" />
							</td>
							<td><textarea rows="5" cols="35" id="reasonTextAreaId"></textarea></td>

						</tr>
						<tr>
							<td colspan="2" align="right"><button type="submit"
									id="submitButtonOfRejectReason" class="btn btn-primary">Submit</button></td>
						</tr>
					</table>

				</form>
			</div>
		</div>
	</div>
</div>
<!-- /Modal 1 -->