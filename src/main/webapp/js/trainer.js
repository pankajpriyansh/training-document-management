$(document)
		.ready(
				function() {

					var dataListOfCategories = document
							.getElementById('categoriesListFromJson');

					/**
					 * Create new Section
					 */
					// $("#newSectionForm").submit(function() {
					$("#newSectionForm").on('submit', function(event) {
						event.preventDefault();
						console.log('subkmitted');
						console.log($('#newSectionName').val());

//					$('#Section').modal('');
//						alert('closed');
/*
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
									// $('#closeNewSectionModelId').click();
									$('#Section').hide();

									$('#closeNewSectionModelId').click();

									// $("#Section").modal('close');
									*//**
									 * console.log(response); var option =
									 * document.createElement('option');
									 * option.value = response.name; option.id =
									 * response.name;
									 * option.setAttribute('sectionId',
									 * response.id); console.log(option);
									 * console.log(response.name + " - " +
									 * response.id);
									 * dataListOfSections.appendChild(option);
									 *//*
								},
								error : function(textStatus, errorThrown) {
									console.log(textStatus);
									alert('Section Not Created');
								}

							});
						}*/

					});

					/**
					 * Create new Category
					 */
					$("#newCategoryForm").submit(
							function() {
								console.log('Category submitted ');
								var tempSection = $('#selectSectionBoxId')
										.val();
								console.log(categoryName);
								if (categoryName == '') {
									alert('please select a section');
								} else {
									var sectionId = $('#' + tempSection).attr(
											'sectionId');
									var categoryName = $('#newCategoryName')
											.val();

									if (categoryName != null) {
										$.ajax({
											url : "./saveCategory.html",
											data : {
												categoryName : categoryName,
												sectionId : sectionId
											},
											success : function(response) {
												alert('Category added');
												console.log(response);
												/*
												 * var option =
												 * document.createElement('option');
												 * option.value = response.name;
												 * option.id = response.name;
												 * option.setAttribute('categoryId',
												 * response.id);
												 * dataListOfCategories.appendChild(option);
												 */
											},
											error : function(textStatus,
													errorThrown) {
												console.log(textStatus);
												alert('Category Not created');
											}
										});
									}

								}

							});

					$('#selectSectionBoxIdInCreateDocument')
							.change(
									function(event) {
										$('#categoryBoxId').val('');
										var tempSection = $(
												"input#selectSectionBoxIdInCreateDocument")
												.val();
										var sectionId = $('#' + tempSection)
												.attr('sectionId');
										console.log(tempSection + "   -   "
												+ sectionId);
										$
												.ajax({
													url : "./getCategories.html",
													data : {
														sectionId : sectionId
													},
													success : function(response) {
														console
																.log('got response ');
														response
																.forEach(function(
																		item) {
																	var option = document
																			.createElement('option');
																	option.value = item.name;
																	option.id = item.name;
																	option.categoryId = item.id;
																	option
																			.setAttribute(
																					'categoryId',
																					item.id);
																	dataListOfCategories
																			.appendChild(option);
																});
													},
													error : function(
															textStatus,
															errorThrown) {
														console.log(textStatus);
														if (textStatus.status === 400) {
															alert('Section Not Present , Please select a section');
														}
													}
												});
									});

					$('#categoryBoxId').change(
							function(event) {

								var tempCategory = $("input#categoryBoxId")
										.val();
								console.log($('#' + tempCategory).attr(
										'categoryId'));

								$("input#hiddenFieldCategoryId").val(
										$('#' + tempCategory)
												.attr('categoryId'));
							});
				});