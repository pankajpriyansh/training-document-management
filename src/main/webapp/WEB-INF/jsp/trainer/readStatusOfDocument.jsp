
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/jquery.dataTables.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/trainer-body-style.css" />
<script src="js/trainer/readStatusOfDocument.js"></script>
<link rel="stylesheet" type="text/css"
	href="css/jquery.dataTables.min.css" />
<script src="js/amcharts/amcharts.js"></script>
<script src="js/amcharts/serial.js"></script>
<script src="js/amcharts/plugins/export/export.min.js"></script>
<link rel="stylesheet" href="css/export.css" type="text/css" media="all" />
<script src="js/amcharts/themes/light.js"></script>

<script>
	$(document).ready(function() {
		jQuery.noConflict();

		$('#documentsStatusTableId').DataTable({
			"pagingType" : "full_numbers"
		});
	});
</script>
<form id="SelectBatchAndDocumentForm"
	action="./checkDocumentReadStatus.html" method="post">
	<table>
		<tr>
			<td>&nbsp; &nbsp;&nbsp;&nbsp;</td>
			<td>Select Batch</td>
			<td>&nbsp; &nbsp;</td>
			<td><select id="batchSelectBoxIdForReadStatus" name="batchId"
				class="form-control" required="required">
					<option disabled selected></option>
					<c:forEach items="${batches}" var="batch">
						<option value="${batch.id}">${batch.name}</option>
					</c:forEach>
			</select></td>
			<td>&nbsp; &nbsp;&nbsp;&nbsp;</td>
			<td>Select Document</td>
			<td>&nbsp; &nbsp;</td>

			<td><select id="documentSelectBoxIdForReadStatus"
				name="documentId" class="form-control" required="required">
					<option disabled selected></option>
			</select></td>
			<td>&nbsp; &nbsp;</td>

			<td colspan="2" align="right"><button type="submit"
					class="btn btn-primary">Search</button></td>
		</tr>
	</table>
</form>
<div class="col-sm-12 overflow ">
	<br>
	<table class="col-md-12 table table-striped table-bordered table-hover"
		id="documentsStatusTableId">
		<thead class="thead-inverse">
			<tr id="heading">
				<th>S.No.</th>
				<th>Name</th>
				<th>Email</th>
				<th>Status <select id='filterTextOfStatusColumn'
					style='display: inline-block' onchange='filterTextOfStatusColumn()'>
						<option disabled selected>Select</option>
						<option value="Read">Read</option>
						<option value="Unread">Unread</option>
						<option value="ALL">ALL</option>
				</select></th>
				<th>First Seen</th>
				<th>Last Seen</th>
				<th>Count</th>
			</tr>
		</thead>
		<tbody>

			<c:forEach items="${documentReadStatusList}" var="documentStatus"
				varStatus="status">
				<tr class="contentOfDocuments">
					<td>${status.index + 1}</td>
					<td>${documentStatus.name}</td>
					<td>${documentStatus.email}</td>
					<td><c:if test="${documentStatus.status == 1}">
							<span style="color: green"> Read </span>
						</c:if> <c:if test="${documentStatus.status == 0}">
							<span style="color: red"> Unread </span>
						</c:if></td>
					<td>${documentStatus.createddate}</td>
					<td>${documentStatus.modifieddate}</td>
					<td>${documentStatus.count}</td>
				</tr>
			</c:forEach>

		</tbody>
	</table>
</div>