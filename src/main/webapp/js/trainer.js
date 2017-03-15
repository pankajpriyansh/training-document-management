$(document)
		.ready(
				function() {

					var getBatchMemberGraphData = function() {
						var getJson = function() {
							var json = "";
							$.ajaxSetup({
								async : false
							});
							$.ajax({
								url : "./getBatchMemberGraphData.html",
								success : function(response) {
									json = json + "[";
									response.forEach(function(item) {
										json = json + "{";
										json = json + "\"totalMembers\" : \" "
												+ item[0] + " \",";
										json = json
												+ "\"totalDocuments\" : \" "
												+ item[2] + " \",";
										json = json + "\"batchName\" : \" "
												+ item[1] + " \" ";
										json = json + "},";
									});
									json = json.substring(0, json
											.lastIndexOf(','));
									json = json + "]";
								}
							});
							console.log(typeof (json));
							return json;
						}
						try {
							console.log(getJson());
							console.log("test");

							var charData = JSON.parse(getJson());
							console.log("test1");

							console.log(typeof (charData));
						} catch (err) {
						}

						console.log('chardata ->', charData);
						var chart = AmCharts
								.makeChart(
										"chartdivForBatch",
										{
											"type" : "serial",
											// "theme" : "light",
											"dataProvider" : charData,
											"valueAxes" : [ {
												"gridColor" : "#FFFFFF",
												"gridAlpha" : 0.2,
												"dashLength" : 0
											} ],
											"gridAboveGraphs" : true,
											"startDuration" : 1,
											"graphs" : [ {
												"balloonText" : "[[category]]: <b>[[value]]</b> members",
												"fillAlphas" : 0.8,
												"lineAlpha" : 0.2,
												"type" : "column",
												"valueField" : "totalMembers"
											} ],
											"chartCursor" : {
												"categoryBalloonEnabled" : false,
												"cursorAlpha" : 0,
												"zoomable" : false
											},
											"categoryField" : "batchName",
											"categoryAxis" : {
												"gridPosition" : "start",
												"gridAlpha" : 0,
												"tickPosition" : "start",
												"tickLength" : 20
											},
											"export" : {
												"enabled" : true
											}

										});

					}
					getBatchMemberGraphData();
					var getSectionCategoryDocumentGraphData = function() {
						var batchId = $('#batchIdForGraphData').val();
						var getJson = function() {
							var json = "";
							$.ajaxSetup({
								async : false
							});
							$
									.ajax({
										url : "./getSectionCategoryDocumentGraphData.html",
										data : {
											batchId : batchId
										},
										success : function(response) {
											json = json + "[";
											response
													.forEach(function(item) {
														json = json + "{";
														json = json
																+ "\"sectionName\" : \" "
																+ item[0]
																+ " \",";
														json = json
																+ "\"totalCategories\" : \" "
																+ item[1]
																+ " \",";
														json = json
																+ "\"totalDocuments\" : \" "
																+ item[2]
																+ " \" ";
														json = json + "},";
													});
											json = json.substring(0, json
													.lastIndexOf(','));
											json = json + "]";
										}
									});
							console.log(json);
							return json;

						}
						try {
							var charData = JSON.parse(getJson());
						} catch (err) {
						}
						console.log(typeof (charData));

						var chart = AmCharts
								.makeChart(
										"chartdiv",
										{
											"type" : "serial",
											"addClassNames" : true,
											"theme" : "light",
											"autoMargins" : false,
											"marginLeft" : 30,
											"marginRight" : 8,
											"marginTop" : 50,
											"marginBottom" : 26,
											"balloon" : {
												"adjustBorderColor" : false,
												"horizontalPadding" : 10,
												"verticalPadding" : 8,
												"color" : "red"
											},

											"dataProvider" : charData,
											"valueAxes" : [ {
												"axisAlpha" : 0,
												"position" : "left"
											} ],
											"startDuration" : 1,
											"graphs" : [
													{
														"alphaField" : "alpha",
														"balloonText" : "<span style='font-size:12px;'>[[title]] in [[category]]:<br><span style='font-size:20px;'>[[value]]</span>																	  [[additional]]</span>",
														"fillAlphas" : 1,
														"title" : "Categories",
														"type" : "column",
														"valueField" : "totalCategories",
														"dashLengthField" : "dashLengthColumn"
													},
													{
														"id" : "graph2",
														"balloonText" : "<span style='font-size:12px;'>[[title]] in [[category]] :<br><span style='font-size:20px;'>[[value]]</span>  [[additional]]</span>",
														"bullet" : "round",
														"lineThickness" : 3,
														"bulletSize" : 7,
														"bulletBorderAlpha" : 1,
														"bulletColor" : "#FFFFFF",
														"useLineColorForBulletBorder" : true,
														"bulletBorderThickness" : 3,
														"fillAlphas" : 0,
														"lineAlpha" : 1,
														"title" : "Documents",
														"valueField" : "totalDocuments",
														"dashLengthField" : "dashLengthLine"
													} ],
											"categoryField" : "sectionName",
											"categoryAxis" : {
												"gridPosition" : "start",
												"axisAlpha" : 0,
												"tickLength" : 0
											},
											"export" : {
												"enabled" : true
											}
										});

					}
					getSectionCategoryDocumentGraphData();
					$('#batchIdForGraphData').change(function(e) {
						getSectionCategoryDocumentGraphData();

					});
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
					var batchModal = document.getElementById('BatchModal');
					var documentShowStatusModal = document
							.getElementById('documentShowStatusModal');
					var dataListOfDocuments = document
							.getElementById('documentsOfShiftDocumentsPage');
					console.log(dataListOfDocuments);
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
					 * button clicked , create new Batch modal open
					 */
					$('#addMoreBatchButtonId').click(function() {
						console.log(' Batch button clicked ');
						batchModal.style.display = "block";
						$('#newBatchFormMessageId').html('');
						$("form#newBatchForm").trigger("reset");
					});

					/**
					 * close button to close new section modal
					 */
					$('#closeNewSectionModelId').click(function() {
						console.log(' Section close clicked ');
						sectionModal.style.display = "none";
					});

					/**
					 * close button to close new category modal
					 */
					$('#closeNewCategoryModelId').click(function() {
						console.log(' Section close clicked ');
						categoryModal.style.display = "none";
					});

					/**
					 * close button to close new document modal
					 */
					$('#closeNewDocumentModelId').click(function() {
						documentModal.style.display = "none";
					});

					/**
					 * close button to close new batch modal
					 */
					$('#closeNewBatchModelId').click(function() {
						batchModal.style.display = "none";
					});

					/**
					 * close button to close new section modal
					 */
					$('#closeEditDocumentModelId').click(function() {
						editDocumentModal.style.display = "none";
					});

					$('#closeDocumentShowStatusModal').click(function() {
						documentShowStatusModal.style.display = "none";
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
															alert('section added');
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
															getSectionCategoryDocumentGraphData();
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
															getSectionCategoryDocumentGraphData();
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
										console
												.log($('#DocumentNameID').val().length);
										if ($('#DocumentNameID').val().length > 30) {
											$('#newDocumentFormMessageId')
													.html(
															'Document name should not exceeds 30 chracters');
										} else {
											var documentFormData = new FormData(
													$('#newDocumentForm')[0]);
											$
													.ajax({
														type : "POST",
														url : "./saveDocument.html",
														data : documentFormData,
														processData : false,
														contentType : false,
														success : function(
																response) {
															if (response == 'FileNotPDF') {
																$(
																		'#newDocumentFormMessageId')
																		.html(
																				'Only pdf files are allowed to upload ');
															}
															if (response == 'alreadyExists') {
																$(
																		'#newDocumentFormMessageId')
																		.html(
																				'Document Exists With this name Under this batch');
															} else {
																$(
																		'#totalDocumentId')
																		.html(
																				response);
																getSectionCategoryDocumentGraphData();

																location
																		.reload();
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
															console
																	.log(textStatus);
															alert('Document Not created');
														}
													});
										}

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
										while (dataListOfCategories
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

					$(document)
							.on(
									"change",
									"input[type=radio]",
									function() {
										console.log('Radio button changed ');
										var documentId = jQuery(this).attr(
												"documentId");
										var value = jQuery(this).val();
										console.log(value, "  ", documentId);
										console.log(documentShowStatusModal);
										documentShowStatusModal.style.display = "block";
										var selectBox = document
												.getElementById('forSpecificMemberDocumentShowStatusSelectBoxId');
										console.log(selectBox.value);
										while (selectBox.hasChildNodes()) {
											selectBox
													.removeChild(selectBox.firstChild);
										}
										$
												.ajax({
													url : "./getListOfMembersByDocumentsBatchId.html",
													data : {
														documentId : documentId
													},
													success : function(response) {
														console.log(response);
														$(
																'#documentShowStatusModalDocumentId')
																.val(documentId);
														$(
																'#documentShowStatusModalStatusValue')
																.val(value);
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
														selectBox
																.appendChild(selectOption);
														response
																.forEach(function(
																		item) {
																	var option = document
																			.createElement('option');
																	option.value = item.id;
																	option.text = item.email;
																	selectBox
																			.appendChild(option);
																});
													}
												});
										/*
										 * $.ajax({ url :
										 * "./onStatusChangeOfShowHide.html",
										 * data : { documentId : documentId,
										 * status : value }, success :
										 * function(data) { alert("status change
										 * successfully "); }
										 * 
										 * });
										 */
									});

					$('#forAllDocumentShowStatusButtonId')
							.click(
									function() {
										var documentId = $(
												'#documentShowStatusModalDocumentId')
												.val();
										var value = $(
												'#documentShowStatusModalStatusValue')
												.val();

										$
												.ajax({
													url : "./onStatusChangeOfShowHide.html",
													data : {
														documentId : documentId,
														status : value
													},
													success : function(data) {
														alert("status change successfully ");
														documentShowStatusModal.style.display = "none";

													}

												});

									});
					$('#forSpecificMemberDocumentShowStatusButtonId')
							.click(
									function() {
										var documentId = $(
												'#documentShowStatusModalDocumentId')
												.val();
										var value = $(
												'#documentShowStatusModalStatusValue')
												.val();
										var memberId = $(
												'#forSpecificMemberDocumentShowStatusSelectBoxId')
												.val();

										console.log(documentId, "  ", value,
												"  ", memberId);
										$
												.ajax({
													url : "./onStatusChangeOfShowHideForSpecificMember.html",
													data : {
														documentId : documentId,
														status : value,
														memberId : memberId
													},
													success : function(data) {
														alert("status change successfully ");
														documentShowStatusModal.style.display = "none";

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
					$('.deleteDoc').click(function() {
						console.log('clicked');
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

					$("form#newBatchForm").submit(
							function(e) {
								e.preventDefault();
								var batchFormData = $("form#newBatchForm")
										.serialize();
								console.log(batchFormData);
								$.ajax({
									url : "./saveBatch.html",
									data : batchFormData,
									success : function(response) {
										if (response == 'batchExists') {
											$('#newBatchFormMessageId').html(
													'Batch Already Exists');
										} else {
											alert('batch added');
											getBatchMemberGraphData();
											$('#totalBatchId')
													.html(response[1]);
											batchModal.style.display = "none";
										}
									},
									error : function(textStatus, errorThrown) {
										console.log(textStatus);
										alert('Batch Not created');
									}
								});

							});
					$('#batchSelectBoxIdForReadStatus')
							.change(
									function() {
										var batchId = $(this).val();
										var documentSelectBox = document
												.getElementById('documentSelectBoxIdForReadStatus');
										while (documentSelectBox
												.hasChildNodes()) {
											documentSelectBox
													.removeChild(documentSelectBox.firstChild);
										}
										if (batchId != null) {
											$
													.ajax({
														url : "./getDocumentsByBatchId.html",
														type : "POST",
														data : {
															batchId : batchId
														},
														success : function(
																response) {
															console
																	.log(response);
															var selectOption = document
																	.createElement("option");
															selectOption
																	.setAttribute(
																			"selected",
																			"selected");
															selectOption
																	.setAttribute(
																			"disabled",
																			"disabled");
															documentSelectBox
																	.appendChild(selectOption);
															response
																	.forEach(function(
																			item) {
																		var option = document
																				.createElement('option');
																		option.text = item.name;
																		option.value = item.id;

																		documentSelectBox
																				.appendChild(option);
																	});

														},
														error : function(
																response) {

														}
													});
										}
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

					$(".approvedButton").click(function() {
						var requestId = jQuery(this).attr("requestId");
						var documents = jQuery(this).attr("documents");
						var memberId = jQuery(this).attr("memberId");

						console.log(requestId);
						console.log(documents);
						console.log(memberId);
						$.ajax({
							type : "POST",
							url : "./approveRequestForDocument.html",
							data : {
								requestId : requestId,
								documents : documents,
								memberId : memberId
							},
							success : function(response) {
								alert('request Approved Successfully');
								location.reload();
							},
							error : function(textStatus, errorThrown) {
								console.log(textStatus);
								alert('request Not Approved ');
							}
						});
					});

					var rejectReasonModal = document
							.getElementById('RejectReasonModal');
					$(".rejectButton").click(function() {
						rejectReasonModal.style.display = "block";
						var requestId = jQuery(this).attr("requestId");
						$('#hiddenRequestId').val(requestId);
					});

					$('#closeRejectReasonModal').click(function() {
						rejectReasonModal.style.display = "none";
					});

					$('form#rejectReasonForm').submit(function(e) {
						e.preventDefault();
						var requestId = $('#hiddenRequestId').val();
						var reason = $('#reasonTextAreaId').val();
						console.log('clicked  ', requestId, reason);
						$.ajax({
							type : "POST",
							url : "./saveReasonForRejectionOfRequest.html",
							data : {
								requestId : requestId,
								reason : reason
							},
							success : function(response) {
								alert('Reason saved');
								location.reload();
							},
							error : function(textStatus, errorThrown) {
								console.log(textStatus);
								alert('Reason not saved');
							}
						});
					});

					var documentApprovedStatusModal = document
							.getElementById('documentApprovedStatusModal');

					$('.showStatusButtonOfRequestedDocumentReports')
							.click(
									function() {
										var toMemberId = jQuery(this).attr(
												"toMemberId");
										var fromMemberId = jQuery(this).attr(
												"fromMemberId");
										documentApprovedStatusModal.style.display = "block";
										console.log(toMemberId, "   ",
												fromMemberId);
										var documentApprovedStatusTable = document
												.getElementById('documentApprovedStatusTableId');
										$
												.ajax({
													type : "POST",
													url : "./getRequestedDocumentReportsAdvanceData.html",
													data : {
														fromMemberId : fromMemberId,
														toMemberId : toMemberId
													},
													success : function(response) {
														console.log(response);
														for (var i = 1; i < documentApprovedStatusTable.rows.length;) {
															documentApprovedStatusTable
																	.deleteRow(i);
														}
														response
																.forEach(function(
																		item) {
																	var row = documentApprovedStatusTable
																			.insertRow();
																	row.style.border = "1px solid";
																	var cell1 = row
																			.insertCell(0);
																	cell1.style.border = "1px solid";
																	cell1.style.padding = "5px";
																	var cell2 = row
																			.insertCell(1);
																	cell2.style.border = "1px solid";
																	cell2.style.padding = "5px";
																	cell2.style.color = "red";
																	var cell3 = row
																			.insertCell(2);
																	cell3.style.border = "1px solid";
																	cell3.style.padding = "5px";

																	cell1.innerHTML = item.documentName;
																	console
																			.log(item.status);
																	if (item.status == " No Action Performed ")
																		cell2.style.color = "orange";
																	if (item.status == ' Approved ')
																		cell2.style.color = "green";
																	cell2.innerHTML = item.status;
																	cell3.innerHTML = item.Date;
																});
													},
													error : function(
															textStatus,
															errorThrown) {
														console.log(textStatus);
													}
												});

									});
					$('#closeDocumentApprovedStatusModal').click(function() {
						documentApprovedStatusModal.style.display = "none";
					});

				});

function filterTextOfSectionColumn() {
	var rex = new RegExp($('#filterTextOfSectionColumn').val());
	var sectionId = $('#' + $('#filterTextOfSectionColumn').val()).attr(
			'sectionId');
	var selectListOfCategories = document
			.getElementById("filterTextOfCategoryColumn");
	console.log(rex + " - " + sectionId);
	while (selectListOfCategories.hasChildNodes()) {
		selectListOfCategories.removeChild(selectListOfCategories.firstChild);
	}
	if (rex == "/ALL/") {
		clearFilter()
	} else {
		$('.contentOfDocuments').hide();
		$('.contentOfDocuments').filter(function() {
			console.log($(this).text());
			return rex.test($(this).text());
		}).show();
		$.ajax({
			url : "./getCategories.html",
			data : {
				sectionId : sectionId
			},
			success : function(response) {
				console.log(response);
				var selectOption = document.createElement("option");
				selectOption.text = "Select";
				selectOption.setAttribute("selected", "selected");
				selectOption.setAttribute("disabled", "disabled");
				selectListOfCategories.appendChild(selectOption);
				for (var i = 0; i < response.length; i++) {
					var option = document.createElement("option");
					option.value = response[i].name;
					option.text = response[i].name;
					selectListOfCategories.appendChild(option);
				}
				var allOption = document.createElement("option");
				allOption.text = "ALL";
				allOption.value = "ALL";
				selectListOfCategories.appendChild(allOption);
			}
		});

	}
}

function filterTextOfCategoryColumn() {
	var rex = new RegExp($('#filterTextOfCategoryColumn').val());
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

function filterTextOfCreatedDateColumn() {

	var rex = new RegExp($('#filterTextOfCreatedDateColumn').val());
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

function filterTextOfStatusColumn() {

	var rex = new RegExp($('#filterTextOfStatusColumn').val());
	if (rex == "/ALL/") {
		clearFilter()
	} else {
		$('.contentOfDocuments').hide();
		$('.contentOfDocuments').filter(function() {
			return rex.test($(this).text());
		}).show();

	}
}

function filterTextOfBatchColumn() {

	var rex = new RegExp($('#filterTextOfBatchColumn').val());
	if (rex == "/ALL/") {
		clearFilter()
	} else {
		$('.contentOfDocuments').hide();
		$('.contentOfDocuments').filter(function() {
			return rex.test($(this).text());
		}).show();

	}
}
function clearFilter() {
	$('.filterText').val('');
	$('.contentOfDocuments').show();
}
