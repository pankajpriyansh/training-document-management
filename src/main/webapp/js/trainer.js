$(document)
		.ready(
				function() {

					var dataListOfSections = document
							.getElementById('sections');
					var dataListOfCategories = document
							.getElementById('categoriesListFromJson');
					var dataListOfMembers = document.getElementById('members');
					var sectionModal = document.getElementById('SectionModal');
					var categoryModal = document
							.getElementById('CategoryModal');
					var documentModal = document
							.getElementById('DocumentModal');
					var editDocumentModal = document
							.getElementById('editDocumentModal');
					var CheckDocumentReadStatusModal = document
							.getElementById('CheckDocumentReadStatusModal');
					/*
					 * var documentsTableId = document
					 * .getElementById('documentsTableId'); var readStatusDivId =
					 * document .getElementById('readStatusDivId');
					 */
					/**
					 * button clicked , create new section modal open
					 */
					$('#addMoreSectionButtonId').click(function() {
						console.log('button clicked ');
						sectionModal.style.display = "block";
						$('#newSectionFormMessageId').html('');
						$("form#newSectionForm").trigger("reset");
					});

					/**
					 * button clicked , create new Category modal open
					 */
					$('#addMoreCategoryButtonId').click(function() {
						console.log(' Catagory button clicked ');
						categoryModal.style.display = "block";
						$('#newCategoryFormMessageId').html('');
						$("form#newCategoryForm").trigger("reset");
					});

					/**
					 * button clicked , create new Document modal open
					 */
					$('#addMoreDocumentButtonId').click(function() {
						console.log(' Document button clicked ');
						documentModal.style.display = "block";
						$('#newDocumentFormMessageId').html('');
						$("form#newDocumentForm").trigger("reset");
					});

					/**
					 * close button to close new section modal
					 */
					$('#closeNewSectionModelId').click(function() {
						console.log(' Section close clicked ');
						sectionModal.style.display = "none";
					});

					/**
					 * close button to close new section modal
					 */
					$('#closeNewCategoryModelId').click(function() {
						console.log(' Section close clicked ');
						categoryModal.style.display = "none";
					});

					/**
					 * close button to close new section modal
					 */
					$('#closeNewDocumentModelId').click(function() {
						console.log(' Section close clicked ');

						documentModal.style.display = "none";
					});

					/**
					 * close button to close new section modal
					 */
					$('#closeEditDocumentModelId').click(function() {
						console.log(' EditDocument close clicked ');
						editDocumentModal.style.display = "none";
					});

					/**
					 * close new section modal after click on submit Button Of
					 * Create NewSection, and save section in database and edit
					 * update sections list in page
					 */

					$("form#newSectionForm")
							.submit(
									function(e) {
										e.preventDefault();
										console.log('form submitted');
										console.log('clicked model submit ');
										var sectionFormData = $(
												"form#newSectionForm")
												.serialize();
										console.log(sectionFormData);
										$
												.ajax({
													url : "./saveSection.html",
													data : sectionFormData,
													success : function(response) {
														if (response == 'sectionExists') {
															$(
																	'#newSectionFormMessageId')
																	.html(
																			'Section Already Exists');
														} else if (response == 'containSpace') {
															$(
																	'#newSectionFormMessageId')
																	.html(
																			'Use Underscore in place of Space');
														} else {
															alert('scetion added');
															console
																	.log(response);
															console
																	.log(response[0]);
															$('#totalSectionId')
																	.html(
																			response[1]);
															var option = document
																	.createElement('option');
															option.value = response[0].name;
															option.id = response[0].name;
															option.sectionId = response[0].id;
															option
																	.setAttribute(
																			'sectionId',
																			response[0].id);
															console.log(option);
															dataListOfSections
																	.appendChild(option);
															console
																	.log('response appended ');
															$(
																	"form#newSectionForm")
																	.trigger(
																			"reset");
															sectionModal.style.display = "none";
														}
													},
													error : function(
															textStatus,
															errorThrown) {
														console.log(textStatus);
														alert('Section Not created');
													}
												});

									});

					/**
					 * close new section modal after click on submit Button Of
					 * Create NewSection
					 */
					$("form#newCategoryForm")
							.submit(
									function(e) {
										e.preventDefault();
										console.log('Category model submit ');

										var tempSection = $(
												"input#selectSectionBoxIdInCreateCategory")
												.val();
										var sectionId = $('#' + tempSection)
												.attr('sectionId');

										$(
												"input#hiddenFieldSectionIdInCreateCategory")
												.val(sectionId);
										console.log(sectionId);
										var categoryFormData = $(
												"form#newCategoryForm")
												.serialize();
										console.log('form data ->'
												+ categoryFormData);

										$
												.ajax({
													url : "./saveCategory.html",
													data : categoryFormData,
													success : function(response) {

														if (response == 'categoryExists') {
															$(
																	'#newCategoryFormMessageId')
																	.html(
																			'Category Already Exists under this Section');
														} else if (response == 'containSpace') {
															$(
																	'#newCategoryFormMessageId')
																	.html(
																			'Use Underscore in place of Space');
														} else {
															alert('Category added');
															console
																	.log(response);
															$(
																	'#totalCategoryId')
																	.html(
																			response[1]);
															$(
																	"form#newCategoryForm")
																	.trigger(
																			"reset");
															categoryModal.style.display = "none";
														}
													},
													error : function(
															textStatus,
															errorThrown) {
														console.log(textStatus);
														alert('Category Not created');
													}
												});

									});
					/**
					 * close new section modal after click on submit Button Of
					 * Create NewSection
					 */
					$("form#newDocumentForm")
							.submit(
									function(e) {
										e.preventDefault();
										var documentFormData = new FormData(
												$('#newDocumentForm')[0]);
										$
												.ajax({
													type : "POST",
													url : "./saveDocument.html",
													data : documentFormData,
													processData : false,
													contentType : false,
													success : function(response) {
														if (response == 'FileNotPDF') {
															$(
																	'#newDocumentFormMessageId')
																	.html(
																			'Only pdf files are allowed to upload ');
														} else {
															$(
																	'#totalDocumentId')
																	.html(
																			response);
															location.reload();
															$(
																	"form#newDocumentForm")
																	.trigger(
																			"reset");
															documentModal.style.display = "none";
														}
													},
													error : function(
															textStatus,
															errorThrown) {
														console.log(textStatus);
														alert('Document Not created');
													}
												});

									});

					$("form#editDocumentForm").submit(
							function(e) {
								e.preventDefault();
								var documentFormData = new FormData(
										$('#editDocumentForm')[0]);
								$.ajax({
									type : "POST",
									url : "./editDocument.html",
									data : documentFormData,
									processData : false,
									contentType : false,
									success : function(response) {
										location.reload();
									},
									error : function(textStatus, errorThrown) {
										console.log(textStatus);
										alert('Document Not Editted');
									}
								});
								$("form#newDocumentForm").trigger("reset");
								documentModal.style.display = "none";

							});

					$('#selectSectionBoxIdInCreateDocument')
							.change(
									function(event) {
										console.log('changed section ');
										if (dataListOfCategories
												.hasChildNodes()) {
											dataListOfCategories
													.removeChild(dataListOfCategories.firstChild);
										}
										$('#categoryBoxId').val('');
										var tempSection = $(
												"input#selectSectionBoxIdInCreateDocument")
												.val();
										var sectionId = $('#' + tempSection)
												.attr('sectionId');
										console.log(tempSection + " - "
												+ sectionId);
										if (sectionId == undefined) {
											$('#newDocumentFormMessageId')
													.html(
															'Section Not Present , Please select asection');

										} else {
											$
													.ajax({
														url : "./getCategories.html",
														data : {
															sectionId : sectionId
														},
														success : function(
																response) {
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
															console
																	.log(textStatus);
															if (textStatus.status === 400) {
																alert('Section Not Present , Please select asection');
															}
														}
													});
										}
									});

					$('#categoryBoxId').change(
							function(event) {
								console.log('in category change event');
								var tempCategory = $("input#categoryBoxId")
										.val();
								var categoryId = $('#' + tempCategory).attr(
										'categoryId');
								if (categoryId == undefined) {
									$('#newDocumentFormMessageId').html(
											'Category not present');

								} else {
									console.log(categoryId);
									$("input#hiddenFieldCategoryId").val(
											categoryId);
								}
							});

					$(document).on("change", "input[type=radio]", function() {
						console.log('Radio button changed ');
						var documentId = jQuery(this).attr("documentId");
						var value = jQuery(this).val();
						console.log(value, "  ", documentId);

						$.ajax({
							url : "./onStatusChangeOfShowHide.html",
							data : {
								documentId : documentId,
								status : value
							},
							success : function(data) {
								alert("status change successfully ");
							}

						});
					});

					/**
					 * when click on edit button , documents details will
					 * display on modal
					 */
					jQuery(".editDoc")
							.click(
									function() {
										var documentId = jQuery(this).attr(
												"documentId");
										console.log(documentId);
										$
												.ajax({
													url : "./getDocument.html",
													data : {
														documentId : documentId
													},
													success : function(data) {
														console.log(data);
														$(
																'form#editDocumentForm textarea[name="description"]')
																.val(
																		data.description);
														$(
																'form#editDocumentForm input[name="name"]')
																.val(data.name);
														$(
																'form#editDocumentForm input[name="hiddenDocumentId"]')
																.val(data.id);
														editDocumentModal.style.display = "block";
													}
												});
									});
					$('#deleteDocumentButtonId').click(function() {
						var documentId = jQuery(this).attr("documentId");
						console.log(documentId);
						$.ajax({
							type : "POST",
							url : "./deleteDocument.html",
							data : {
								documentId : documentId
							},
							success : function(data) {
								console.log(data);
								location.reload();
							}
						});
					});

					/**
					 * button clicked , create new Document modal open
					 */
					$('#readStatusLinkId')
							.click(
									function() {
										console
												.log(' readStatusLinkId button clicked ');
										CheckDocumentReadStatusModal.style.display = "block";
										$(
												'#checkDocumentReadStatusFormMessageId')
												.html('');
										$("form#checkDocumentReadStatusForm")
												.trigger("reset");
										$
												.ajax({
													url : "./getMembers.html",
													success : function(response) {
														console.log(response);
														response
																.forEach(function(
																		item) {
																	console
																			.log(item);
																	var option = document
																			.createElement('option');
																	option.value = item.firstname
																			+ "_"
																			+ item.lastname;
																	option.id = item.firstname
																			+ "_"
																			+ item.lastname;
																	option.memberId = item.id;
																	option
																			.setAttribute(
																					'memberId',
																					item.id);
																	dataListOfMembers
																			.appendChild(option);
																});

													}
												});
									});

					$('#closeCheckDocumentReadStatusModalId')
							.click(
									function() {
										$("form#checkDocumentReadStatusForm")
												.trigger("reset");
										$('#statusRead').html('');
										$('#statusUnRead').html('');
										$('#FirstSeen').html('');
										$('#LastSeen').html('');
										while (dataListOfMembers
												.hasChildNodes()) {
											dataListOfMembers
													.removeChild(dataListOfMembers.firstChild);
										}
										CheckDocumentReadStatusModal.style.display = "none";
									});

					$("form#checkDocumentReadStatusForm")
							.submit(
									function(e) {
										e.preventDefault();
										var tempDocument = $(
												"input#checkDocumentReadStatusFormDocumentsId")
												.val();
										var documentIdOfReadStatus = $(
												'#' + tempDocument).attr(
												'documentId');
										if (documentIdOfReadStatus == undefined) {
											$(
													'#checkDocumentReadStatusFormMessageId')
													.html(
															'Document not present');
										}
										var tempMember = $(
												"input#checkDocumentReadStatusFormMemberId")
												.val();
										var memberSelectedOptionId = '#'
												+ tempMember;
										var memberId = $('#' + tempMember)
												.attr('memberid');
										if (memberId == undefined) {
											$(
													'#checkDocumentReadStatusFormMessageId')
													.html('Member not present');
										}

										$
												.ajax({
													type : "POST",
													url : "./checkDocumentStatus.html",
													data : {
														documentIdOfReadStatus : documentIdOfReadStatus,
														memberId : memberId
													},
													success : function(response) {
														if (response == 'UnRead') {
															$('#statusRead')
																	.html('');

															$('#statusUnRead')
																	.html(
																			'UnRead');
															$('#FirstSeen')
																	.html('');
															$('#LastSeen')
																	.html('');

														} else {
															console
																	.log(response);
															$('#statusUnRead')
																	.html('');
															$('#statusRead')
																	.html(
																			'Read');
															$('#FirstSeen')
																	.html(
																			response[1]);
															$('#LastSeen')
																	.html(
																			response[2]);

														}
													},
													error : function(
															textStatus,
															errorThrown) {
														console.log(textStatus);
														alert('Not able to got read status');
													}
												});
										$("form#checkDocumentReadStatusForm")
												.trigger("reset");

									});

				});

function filterText() {
	var rex = new RegExp($('#filterText').val());
	if (rex == "/ALL/") {
		clearFilter()
	} else {
		$('.contentOfDocuments').hide();
		$('.contentOfDocuments').filter(function() {
			console.log($(this).text());
			return rex.test($(this).text());
		}).show();
	}
}

function clearFilter() {
	$('.filterText').val('');
	$('.contentOfDocuments').show();
}
