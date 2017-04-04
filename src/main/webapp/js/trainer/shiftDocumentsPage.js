$(document)
		.ready(
				function() {
					var dataListOfDocuments = document
							.getElementById('documentsOfShiftDocumentsPage');

					$("form#shiftDocumentsPageBatchSectionFormId")
							.submit(
									function(e) {
										e.preventDefault();

										console.log('submitted');
										var formData = $(
												"form#shiftDocumentsPageBatchSectionFormId")
												.serialize();
										console.log(formData);

										$
												.ajax({
													type : "POST",
													url : "./shiftDocumentsBySection.html",
													data : formData,
													success : function(response) {
														if (response == 'bothBatchSame') {
															$(
																	'#shiftDocumentsByBatchSectionFormMessageId')
																	.html(
																			'Please Select Different Batch');
														} else {

															console
																	.log(response);

															$(
																	'#shiftDocumentsByBatchSectionFormMessageId')
																	.html("");
															$(
																	"form#shiftDocumentsPageBatchSectionFormId")
																	.trigger(
																			"reset");
															alert('data Copied to new Batch');
															// location.reload();
														}
													},
													error : function(
															textStatus,
															errorThrown) {
														console.log(textStatus);
														alert('Document Not  Copied');
													}
												});
										$("form#shiftDocumentsPageBatchFormId")
												.trigger("reset");

									});

					$("form#shiftDocumentsPageBatchFormId")
							.submit(
									function(e) {
										e.preventDefault();

										console.log('submitted');
										var formData = $(
												"form#shiftDocumentsPageBatchFormId")
												.serialize();
										console.log(formData);

										$
												.ajax({
													type : "POST",
													url : "./shiftDocumentsByBatch.html",
													data : formData,
													success : function(response) {
														if (response == 'bothBatchSame') {
															$(
																	'#shiftDocumentsByBatchFormMessageId')
																	.html(
																			'Please Select Different Batch');
														} else {

															console
																	.log(response);

															$(
																	'#shiftDocumentsByBatchFormMessageId')
																	.html();
															alert('data Copied to new Batch');
															// location.reload();
														}
													},
													error : function(
															textStatus,
															errorThrown) {
														console.log(textStatus);
														alert('Document Not  Editted');
													}
												});
										$("form#shiftDocumentsPageBatchFormId")
												.trigger("reset");

									});

					$('#shiftDocumentsPageByCategoryBatchSelectBoxId')
							.change(
									function(event) {
										var batchId = $(this).val();

										while (dataListOfDocuments
												.hasChildNodes()) {
											dataListOfDocuments
													.removeChild(dataListOfDocuments.firstChild);
										}
										$
												.ajax({
													url : "./getDocumentsByBatchId.html",
													data : {
														batchId : batchId
													},
													success : function(response) {
														console.log(response);
														var selectOption = document
																.createElement("option");
														selectOption.text = "Select";
														selectOption
																.setAttribute(
																		"selected",
																		"selected");
														selectOption
																.setAttribute(
																		"disabled",
																		"disabled");
														dataListOfDocuments
																.appendChild(selectOption);
														response
																.forEach(function(
																		item) {
																	var option = document
																			.createElement('option');

																	option.value = item.name;
																	option.id = item.name;
																	option.documentId = item.id;
																	option
																			.setAttribute(
																					'documentId',
																					item.id);
																	console
																			.log(option);
																	dataListOfDocuments
																			.appendChild(option);
																});
													},
													error : function(
															textStatus,
															errorThrown) {
														console.log(textStatus);
													}
												});
									});

					$('#shiftDocumentsPageDocumentBoxId')
							.change(
									function() {
										console.log('changed');
										console.log($(this).val());
										var fromCategorySelectBox = document
												.getElementById('shiftDocumentsPageFromCategorySelectBoxId');
										var toCategorySelectBox = document
												.getElementById('shiftDocumentsPageToCategorySelectBoxId');

										while (fromCategorySelectBox
												.hasChildNodes()) {
											fromCategorySelectBox
													.removeChild(fromCategorySelectBox.firstChild);
										}
										while (toCategorySelectBox
												.hasChildNodes()) {
											toCategorySelectBox
													.removeChild(toCategorySelectBox.firstChild);
										}
										var tempDoc = $(this).val();
										var documentId = $('#' + tempDoc).attr(
												'documentId');
										$('#shiftDocumentsPageHiddenBoxId')
												.val(documentId);
										console.log(documentId);
										$
												.ajax({
													url : "./getCategoryFromDocumentId.html",
													data : {
														documentId : documentId
													},
													success : function(response) {
														console.log(response);
														var option = document
																.createElement("option");
														option.text = response.name;
														option.value = response.id;
														option.setAttribute(
																"selected",
																"selected");
														/*
														 * option.setAttribute(
														 * "disabled",
														 * "disabled");
														 */
														fromCategorySelectBox
																.appendChild(option);

													}
												});

										$
												.ajax({
													url : "./getCategoriesUnderASectionByDocumentId.html",
													data : {
														documentId : documentId
													},
													success : function(response) {
														console.log(response);
														var selectOption = document
																.createElement("option");
														selectOption.text = "";
														selectOption
																.setAttribute(
																		"selected",
																		"selected");
														selectOption
																.setAttribute(
																		"disabled",
																		"disabled");
														toCategorySelectBox
																.appendChild(selectOption);
														response
																.forEach(function(
																		item) {
																	var option = document
																			.createElement('option');
																	option.text = item.name;
																	option.value = item.id;
																	console
																			.log(option);
																	toCategorySelectBox
																			.appendChild(option);
																});
													}
												});
									});

					$('form#shiftDocumentsPageCategoryFormId')
							.submit(
									function(e) {
										e.preventDefault();
										var formData = $(
												"form#shiftDocumentsPageCategoryFormId")
												.serialize();
										console.log(formData);

										$
												.ajax({
													url : "./shiftDocumentsByCategory.html",
													data : formData,
													success : function(response) {
														console.log(response);
														alert('document moved to new Category');
														// location.reload();
													},
													error : function(
															textStatus,
															errorThrown) {
														console.log(textStatus);
														alert('Document Not  Moved');
													}
												});
										$(
												"form#shiftDocumentsPageCategoryFormId")
												.trigger("reset");

									});

				});