
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/jquery.dataTables.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/admin-body-style.css" />
<script src="js/admin/memberActivateDeactivate.js"></script>
<link rel="stylesheet" type="text/css"
	href="css/jquery.dataTables.min.css" />

<script>
	$(document).ready(function() {
		jQuery.noConflict();

		$('#membersTableId').DataTable({
			"pagingType" : "full_numbers"
		});
	});
</script>
<form id="memberActivateDeactivateForm"
	action="./setMemberActivateDeactivateStatus.html" method="post"
	novalidate>
	<table>
		<tr>
			<td>&nbsp; &nbsp;&nbsp;&nbsp;</td>
			<td>Select Role:</td>
			<td>&nbsp; &nbsp;</td>
			<td><select id="roleSelectBoxId" name="roleId"
				class="form-control" required="required">
					<option disabled selected></option>
					<option value="3">Trainee</option>
					<option value="2">Trainer</option>
			</select></td>
			<td>&nbsp; &nbsp;&nbsp;&nbsp;</td>

			<td id="batchSelectBoxColumnId" style="display: none">Select
				Batch &nbsp; &nbsp;&nbsp;<select id="batchSelectBoxId"
				name="batchId" class="form-control" required="required">
					<option disabled selected></option>
					<c:forEach items="${batches}" var="batch">
						<option value="${batch.id}">${batch.name}</option>
					</c:forEach>
			</select>
			</td>
			<td>&nbsp; &nbsp;&nbsp;&nbsp;</td>
			<td colspan="2" align="right"><button type="submit"
					class="btn btn-primary">Search</button></td>
		</tr>
	</table>
</form>
<div class="col-sm-12 overflow" style="height: 400px">
	<br>
	<table class="col-md-12 table table-striped table-bordered table-hover"
		id="membersTableId">
		<thead class="thead-inverse">
			<tr id="heading">
				<th>S.No.</th>
				<th>Name</th>
				<th>Email</th>
				<th>Contact</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${members}" var="member" varStatus="status">
				<tr class="contentOfDocuments">
					<td>${status.index + 1}</td>
					<td>${member.firstname}${member.lastname}</td>
					<td>${member.email}</td>
					<td>${member.contact}</td>
					<td><c:if test="${member.isActive == 1}">
							<button class="deactivateMember btn btn-danger"
								memberId="${member.id}">DeActivate</button>
						</c:if> <c:if test="${member.isActive == 2}">
							<button class="activateMember btn btn-primary"
								memberId="${member.id}">Activate</button>
						</c:if></td>
				</tr>
			</c:forEach>

		</tbody>
	</table>
</div>