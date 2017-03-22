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
							return json;
						}
						try {
							var charData = JSON.parse(getJson());
						} catch (err) {
						}
						console.log(charData);
						var chart = AmCharts
								.makeChart(
										"chartdivForBatch",
										{
											"type" : "serial",
											"addClassNames" : true,

											"autoMargins" : false,
											"marginLeft" : 30,
											"marginRight" : 8,
											"marginTop" : 50,
											"marginBottom" : 26,
											"balloon" : {
												"adjustBorderColor" : false,
												"horizontalPadding" : 10,
												"verticalPadding" : 8,
												"color" : "black"
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
														"title" : "Members ",
														"type" : "column",
														"valueField" : "totalMembers",
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
											"categoryField" : "batchName",
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
							return json;

						}
						try {
							var charData = JSON.parse(getJson());
						} catch (err) {
						}
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
					var batchModal = document.getElementById('BatchModal');
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
																				'Only pdf/mp4 files are allowed to upload ');
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
																getBatchMemberGraphData();
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

				});
