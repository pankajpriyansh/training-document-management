<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<script src="js/admin/shiftBatchOfUserPage.js"></script>
<div style="float: center; margin-top: 50px; margin-left: 150px;">


	<form id="shiftMemberPageBatchFormId">

		<table class="shiftcard">
			<tr>
				<td colspan="2" align="center"><b>Shift Member </b></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<p id="shiftMemberByBatchFormMessageId" style="color: red"></p>
				</td>
			</tr>
			<tr>
				<td>From Batch :</td>
				<td><select id="shiftMemberPageFromBatchSelectBoxId"
					name="fromBatchId" required="required" class="form-control">
						<option disabled selected></option>
						<c:forEach items="${batches}" var="batch">
							<option value="${batch.id}">${batch.name}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td>Select Member</td>
				<td><select id="showMemberOfSelectedBatchBoxId" name="memberId"
					required="required" class="form-control">
						<option disabled selected></option>
				</select></td>
			</tr>
			<tr>
				<td>To Batch:</td>
				<td><select id="shiftMemberPageToBatchSelectBoxId"
					name="toBatchId" required="required" class="form-control">
						<option disabled selected></option>
						<c:forEach items="${batches}" var="batch">
							<option value="${batch.id}">${batch.name}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td colspan="2" align="right"><button type="submit"
						class="btn btn-primary">Shift</button></td>
			</tr>
		</table>
	</form>

</div>