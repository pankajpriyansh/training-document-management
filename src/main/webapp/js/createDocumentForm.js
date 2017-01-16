$(document).ready(
		function() {
			var dataListOfCategories = document
					.getElementById('categoriesListFromJson');
			var dataListOfSections = document.getElementById('sections');

			/*
			 * $('#sectionBoxId').change(function(event) { var tempSection =
			 * $("input#sectionBoxId").val(); var sectionId = $('#' +
			 * tempSection).attr('sectionId'); $.get('./getCategories.html', {
			 * sectionId : sectionId }, function(response) {
			 * response.forEach(function(item) { var option =
			 * document.createElement('option'); // Set the value using the item
			 * in the JSON // array. option.value = item.name; option.id =
			 * item.name; // option.categoryId = item.id;
			 * option.setAttribute('categoryId', item.id); // Add the <option>
			 * element to the <datalist>.
			 * dataListOfCategories.appendChild(option); }); }); });
			 */

			$('#sectionBoxId').change(function(event) {
				var tempSection = $("input#sectionBoxId").val();
				var sectionId = $('#' + tempSection).attr('sectionId');
				console.log(tempSection + "   -   " + sectionId);
				$.ajax({
					url : "./getCategories.html",
					data : {
						sectionId : sectionId
					},
					success : function(response) {
						console.log('got response ');
						response.forEach(function(item) {
							var option = document.createElement('option'); 
							option.value = item.name;
							option.id = item.name;
							option.categoryId = item.id;
							option.setAttribute('categoryId', item.id); 
							dataListOfCategories.appendChild(option);
						});
					},
					error : function(textStatus, errorThrown) {
						console.log(textStatus);
						if (textStatus.status === 400) {
							alert('Section Not Present , Please select a section');
						}
					}
				});
			});
			$('#categoryBoxId').change(
					function(event) {

						var tempCategory = $("input#categoryBoxId").val();
						console.log($('#' + tempCategory).attr('categoryId'));

						$("input#hiddenFieldCategoryId").val(
								$('#' + tempCategory).attr('categoryId'));
					});

			$("#createSectionId").click(function() {
				var name = prompt("Please enter new Section Name");
				if (name != null) {
					$.ajax({
						url : "./saveSection.html",
						data : {sectionName : name},
						success: function(response) {
							alert('section added');
							console.log(response);
							var option = document.createElement('option');
							option.value = response.name;
							option.id = response.name;
							option.setAttribute('sectionId', response.id);
							console.log(option);
							console.log(response.name + "    -    " + response.id);
							dataListOfSections.appendChild(option);
						},
						error : function(textStatus, errorThrown) {
							console.log(textStatus);
								alert('Section Not Created');
						}
						
					});
					
					/*$.get('./saveSection.html', {
						sectionName : name
					}, function(response) {
						alert('section added');
						console.log(response);
						var option = document.createElement('option');
						option.value = response.name;
						option.id = response.name;
						option.setAttribute('sectionId', response.id);
						console.log(option);
						console.log(response.name + "    -    " + response.id);
						dataListOfSections.appendChild(option);
					});*/
				}
			});

			$("#createCategoryId").click(function() {
				console.log('dasd');
				var tempSection = $("input#sectionBoxId").val();
				if (tempSection == '') {
					alert('please select a Section');
				} else {
					var sectionId = $('#' + tempSection).attr('sectionId');
					console.log(tempSection + " - " + sectionId);
					var name = prompt("Please enter new Category Name");
					if (name != null) {
						var sectionId = $('#' + tempSection).attr('sectionId');
						
						$.ajax({
							url : "./saveCategory.html",
							data : {
								categoryName : name
								,sectionId:sectionId},
							success: function(response) {
								alert('Category added');
								console.log(response);
								var option = document.createElement('option');
								option.value = response.name;
								option.id = response.name;
								option.setAttribute('categoryId', response.id);
								dataListOfCategories.appendChild(option);
							},
							error : function(textStatus, errorThrown) {
								console.log(textStatus);
									alert('Category Not created');
							}
						});
						
					}
				}
		});
	});