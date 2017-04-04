<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<script src="js/trainer/shiftDocumentsPage.js"></script>

<div class="shiftdatadcontent">
	<div style="float: left; margin-top: 50px; margin-left: 100px;">


		<form id="shiftDocumentsPageBatchFormId">

			<table class="shiftcard">
				<tr>
					<td colspan="2" align="center"><b>By Batch </b></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<p id="shiftDocumentsByBatchFormMessageId" style="color: red"></p>
					</td>
				</tr>
				<tr>
					<td>From Batch :</td>
					<td><select id="shiftDocumentsPageFromBatchSelectBoxId"
						name="fromBatchId" required="required" class="form-control">
							<option disabled selected></option>
							<c:forEach items="${batches}" var="batch">
								<option value="${batch.id}">${batch.name}</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td>To Batch:</td>
					<td><select id="shiftDocumentsPageToBatchSelectBoxId"
						name="toBatchId" required="required" class="form-control">
							<option disabled selected></option>
							<c:forEach items="${batches}" var="batch">
								<option value="${batch.id}">${batch.name}</option>
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



	<div style="float: left; margin-top: 50px; margin-left: 50px;">


		<form id="shiftDocumentsPageBatchSectionFormId">

			<table class="shiftcard">
				<tr>
					<td colspan="2" align="center"><b>By Section </b></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<p id="shiftDocumentsByBatchSectionFormMessageId"
							style="color: red"></p>
					</td>
				</tr>
				<tr>
					<td>From Batch :</td>
					<td><select id="shiftDocumentsPageFromBatchSectionSelectBoxId"
						name="fromBatchId" required="required" class="form-control">
							<option disabled selected></option>
							<c:forEach items="${batches}" var="batch">
								<option value="${batch.id}">${batch.name}</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td>From Section :</td>
					<td><select id="shiftDocumentsPageFromSectionSelectBoxId"
						name="sectionId" required="required" class="form-control">
							<option disabled selected></option>
							<c:forEach items="${sections}" var="section">
								<option value="${section.id}">${section.name}</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td>To Batch:</td>
					<td><select id="shiftDocumentsPageToBatchSectionSelectBoxId"
						name="toBatchId" required="required" class="form-control">
							<option disabled selected></option>
							<c:forEach items="${batches}" var="batch">
								<option value="${batch.id}">${batch.name}</option>
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



	<!-- 	<div style="float: right; margin-right: 300px; margin-top: 50px">
 -->
	<div style="float: left; margin-top: 50px; margin-left: 50px;">
		<form id="shiftDocumentsPageCategoryFormId">
			<table class="shiftcard">
				<tr>
					<td colspan="2" align="center"><b>By Category </b></td>
				</tr>
				<tr>
					<td>Select Batch:</td>
					<td><select id="shiftDocumentsPageByCategoryBatchSelectBoxId"
						name="fromBatchId" required="required" class="form-control">
							<option disabled selected></option>
							<c:forEach items="${batches}" var="batch">
								<option value="${batch.id}">${batch.name}</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td>Documents List:</td>
					<td><input list="documentsOfShiftDocumentsPage"
						id="shiftDocumentsPageDocumentBoxId" placeholder="Select Document"
						required class="form-control"> <datalist
							id="documentsOfShiftDocumentsPage">
							<%-- <c:forEach items="${documents}" var="document">
							<option id="${document.name}" value="${document.name}"
								documentId="${document.id}">
						</c:forEach> --%>
						</datalist></td>
					<td><input type="text" style="display: none" name="documentId"
						id="shiftDocumentsPageHiddenBoxId"></td>
				</tr>
				<tr>
					<td>From Category:</td>
					<td><select id="shiftDocumentsPageFromCategorySelectBoxId"
						name="fromCategory" required class="form-control">
							<option disabled selected></option>
					</select></td>
				</tr>
				<tr>
					<td>To Category:</td>
					<td><select id="shiftDocumentsPageToCategorySelectBoxId"
						name="toCategory" required class="form-control">
							<option disabled selected></option>
					</select></td>
				</tr>
				<tr>
					<td colspan="2" align="right"><button type="submit"
							class="btn btn-primary">Shift</button></td>

				</tr>
			</table>
		</form>
	</div>
</div>