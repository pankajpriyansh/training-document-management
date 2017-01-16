<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="js/jquery-3.1.1.min.js" type="text/javascript"></script>
<script src="js/createDocumentForm.js" type="text/javascript"></script>

<fieldset>
	<legend>Create Document</legend>
	<button id="createSectionId" name="createSection">Add New
		Section</button>
	<button id="createCategoryId" name="createCategory">Add New
		Category</button>
	<form action="./saveDocument.html" method="post"
		enctype="multipart/form-data">
		<table>
			<tr>
				<td>Section :</td>
				<td><input list="sections" name="section" id="sectionBoxId"
					placeholder="Select Section"> <datalist id="sections">
						<c:forEach items="${sections}" var="section">
							<option id="${section.name}" value="${section.name}"
								sectionId="${section.id}">
						</c:forEach>
					</datalist></td>
			</tr>
			<tr>
				<td>Category :</td>
				<td><input list="categoriesListFromJson" name="category"
					id="categoryBoxId" placeholder="Select Category"> <datalist
						id="categoriesListFromJson">
					</datalist></td>
				<td><input type="text" id="hiddenFieldCategoryId"
					name="category_id" style="display: none" /></td>
			</tr>
			<tr>
				<td>Name :</td>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<td>Description :</td>
				<td><textarea rows="5" cols="20" name="description"></textarea></td>
			<tr>
				<td>Select File to Upload :</td>
				<td><input type="file" name="fileToUpload" id="fileChooser" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="save"></td>
			</tr>
		</table>
	</form>
</fieldset>
