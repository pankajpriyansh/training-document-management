<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<script src="js/jquery-3.1.1.min.js"></script>

<link rel="stylesheet" type="text/css"
	href="css/jquery.dataTables.min.css" />
<script src="js/jquery.dataTables.min.js"></script>
<script src="js/trainer/documentRequestReportsPage.js"></script>

<script>
	$(document).ready(function() {
		jQuery.noConflict();

		$('#requestedDocumentReportsTableId').DataTable({
			"pagingType" : "full_numbers"
		});
		/* 	$('#documentApprovedStatusTableId').DataTable({
				"pagingType" : "full_numbers"
			});
		 */
	});
</script>

<div class="col-sm-12 overflow ">
	<br>
	<table class="col-md-12 table table-striped table-bordered table-hover"
		id="requestedDocumentReportsTableId">
		<thead class="thead-inverse">
			<tr id="heading">
				<th>S.No.</th>
				<th>Name</th>
				<th>Email</th>
				<th>Batch</th>
				<th>Reports</th>
			</tr>
		</thead>
		<tbody>

			<c:forEach items="${requestedDocumentReports}" var="request"
				varStatus="status">
				<tr class="contentOfDocuments">
					<td>${status.index + 1}</td>
					<td>${request.name}</td>
					<td>${request.email}</td>
					<td>${request.batch}</td>
					<td><button toMemberId="${request.toMemberId}"
							fromMemberId="${request.fromMemberId}"
							class="btn btn-primary showStatusButtonOfRequestedDocumentReports">Show
							Status</button></td>
				</tr>
			</c:forEach>

		</tbody>
	</table>
</div>
<!-- 
<!-- Modal 1 -->
<div id="documentApprovedStatusModal" class="modal">
	<div class="modal-dialog">
		<div class="modal-content"">
			<div class="modal-header">
				<button type="button" class="close"
					id="closeDocumentApprovedStatusModal">&times;</button>
				<h4 class="modal-title">Status</h4>
			</div>
			<div class="modal-body">
				<table id="documentApprovedStatusTableId" style="border: 1px solid;">
					<thead class="thead-inverse">
						<tr id="heading">
							<th style="border: 1px solid; padding: 5px"><b>Document
									Name</b></th>
							<th style="border: 1px solid; padding: 5px"><b>Status</b></th>
							<th style="border: 1px solid; padding: 5px"><b>Date</b></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${request.documents}" var="document"
							varStatus="stat">
							<c:set var="documentData" value="${fn:split(document, '=')}" />
							<tr>
								<td>${documentData[0]}</td>
								<td>${documentData[1]}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
<!-- /Modal 1 -->