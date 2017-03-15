<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div style="float: left; margin-top: 50px; margin-left: 150px;">

	<form id="shiftDocumentsByTrainerFormId">

		<table class="shiftcard">
			<tr>
				<td colspan="2" align="center"><b>By Trainer </b></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<p id="shiftDocumentsByTrainerFormMessageId" style="color: red"></p>
				</td>
			</tr>
			<tr>
				<td>From Trainer :</td>
				<td><select id="shiftDocumentsPageFromTrainerSelectBoxId"
					name="fromTrainerId" required="required" class="form-control">
						<option disabled selected></option>
						<c:forEach items="${trainers}" var="trainer">
							<option value="${trainer.id}">${trainer.email}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td>To Batch:</td>
				<td><select id="shiftDocumentsPageToTrainerSelectBoxId"
					name="toTrainerId" required="required" class="form-control">
						<option disabled selected></option>
						<c:forEach items="${trainers}" var="trainer">
							<option value="${trainer.id}">${trainer.email}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td colspan="2" align="right"><button type="submit"
						class="btn btn-primary">Copy</button></td>

			</tr>
		</table>
	</form>

</div>