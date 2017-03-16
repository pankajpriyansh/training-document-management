<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<link rel="stylesheet" type="text/css" href="css/admin-body-style.css" />
<script src="js/admin/shiftDocumentsPage.js"></script>


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
				<td>From :</td>
				<td><select id="shiftDocumentsPageFromTrainerSelectBoxId"
					name="fromTrainerId" required="required" class="form-control">
						<option disabled selected></option>
						<c:forEach items="${trainers}" var="trainer">
							<option value="${trainer.id}">${trainer.email}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td>Select Document</td>
				<td><select name="documentsId" id="documentsSelectBoxId"
					multiple="multiple" required>
						<option disabled selected>Select</option>
						<c:forEach items="${documents}" var="document">
							<option value="${document.id}">${document.name}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td>To :</td>
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