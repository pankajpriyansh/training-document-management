<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://cdn.datatables.net/1.10.13/js/jquery.dataTables.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/admin-body-style.css" />
<script src="js/admin.js"></script>
<script>
	$(document).ready(function() {
		jQuery.noConflict();

		$('#documentsTableId').DataTable({
			"pagingType" : "full_numbers"
		});
	});
</script>

<div class="col-sm-10 overflow ">
	<br>
	<table class="col-md-12 table table-striped table-bordered table-hover"
		id="documentsTableId">
		<thead class="thead-inverse">
			<tr id="heading">
				<th>S.No.</th>
				<th>Name</th>
				<th>Contact</th>
				<th>Email</th>
				<th>Batch</th>
				<th>Role</th>
				<th>Created Date <input type=date
					(yyyy-mm-dd) id='filterTextOfCreatedDateColumn'
					style='display: inline-block'
					onchange='filterTextOfCreatedDateColumn()' />
				</th>
				<th>Status</th>
			</tr>
		</thead>
		<tbody>

			<c:forEach items="${nonActiveMembers}" var="member"
				varStatus="status">
				<tr class="contentOfDocuments">
					<td>${status.index + 1}</td>
					<td>${member[0].firstname}${member[0].lastname}</td>
					<td>${member[0].contact}</td>
					<td>${member[0].email}</td>
					<td>${member[1]}</td>
					<td><select id="roleSelectBoxId" memberId="${member[0].id}">
							<option value="1"
								<c:if test="${member[0].role == 1}"> selected</c:if>>Admin</option>
							<option value="2"
								<c:if test="${member[0].role == 2}"> selected</c:if>>Trainer</option>
							<option value="3"
								<c:if test="${member[0].role == 3}"> selected</c:if>>Trainee</option>
					</select></td>
					<td>${member[0].createdDate}</td>
					<td><input type="radio" memberId="${member[0].id}"
						name="action${member[0].id}" value="Active"
						<c:if test="${member[0].isActive == 1}"> checked</c:if> />Active
						<br> <input type="radio" name="action${member[0].id}"
						memberId="${member[0].id}" value="Block"
						<c:if test="${member[0].isActive == 2}"> checked</c:if> />Block</td>

				</tr>
			</c:forEach>

		</tbody>
	</table>
</div>