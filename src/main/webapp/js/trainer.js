$(document).ready(function() {

	$("#newSectionForm").submit(function() {
		console.log('subkmitted');
		console.log($('#newSectionName').val());
		var sectionName = $('#newSectionName').val();
		if (sectionName != null) {
			$.ajax({
				url : "./saveSection.html",
				data : {
					sectionName : sectionName
				},
				success : function(response) {
					console.log('scetion added');
					console.log(response);
					/* $('#closeNewSectionModelId').click(); */

					// $("#Section").modal('close');
					/**
					 * console.log(response); var option =
					 * document.createElement('option'); option.value =
					 * response.name; option.id = response.name;
					 * option.setAttribute('sectionId', response.id);
					 * console.log(option); console.log(response.name + " - " +
					 * response.id); dataListOfSections.appendChild(option);
					 */
				},
				error : function(textStatus, errorThrown) {
					console.log(textStatus);
					/* alert('Section Not Created'); */
				}

			});
		}

	});

});