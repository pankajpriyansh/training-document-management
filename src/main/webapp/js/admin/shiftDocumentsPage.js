$(document)
		.ready(
				function() {

					var selectBoxForDocument = document
							.getElementById('documentsSelectBoxId');
					$('#shiftDocumentsPageFromTrainerSelectBoxId')
							.change(
									function() {
										console.log('changed');
										var memberId = $(this).val();
										console.log(memberId);
										while (selectBoxForDocument
												.hasChildNodes()) {
											selectBoxForDocument
													.removeChild(selectBoxForDocument.firstChild);
										}
										console.log('button clicked ');
										$
												.ajax({
													url : "./getListOfDocumentsByTrainer.html",
													data : {
														memberId : memberId
													},
													success : function(response) {
														var documents = response;
														console
																.log(
																		"in success function",
																		documents);

														console
																.log('got response in documents ');
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
														selectBoxForDocument
																.appendChild(selectOption);
														response
																.forEach(function(
																		item) {
																	var option = document
																			.createElement('option');
																	option.value = item.id;
																	option.text = item.name;
																	selectBoxForDocument
																			.appendChild(option);
																});

													},
													error : function(
															textStatus,
															errorThrown) {
														console.log(textStatus);
														alert('list not fetch ');
													}
												});
									});

					$("form#shiftDocumentsByTrainerFormId")
							.submit(
									function(e) {
										e.preventDefault();
										console.log('form submitting');
										$('#shiftDocumentsByTrainerFormMessageId').innerHTML = "";
										var shiftDocumentsByTrainerFormData = $(
												"form#shiftDocumentsByTrainerFormId")
												.serialize();
										console
												.log(shiftDocumentsByTrainerFormData);
										$
												.ajax({
													url : "./copyDocumentsByTrainer.html",
													data : shiftDocumentsByTrainerFormData,
													success : function(response) {
														console.log(response);
														if (response == 'bothSame') {
															$(
																	'#shiftDocumentsByTrainerFormMessageId')
																	.html(
																			" please Select Different Trainers ");
														} else {
															alert('documents copy successfully');
															$(
																	"form#shiftDocumentsByTrainerFormId")
																	.trigger(
																			"reset");
															while (selectBoxForDocument.childNodes.length > 1) {
																selectBoxForDocument
																		.removeChild(selectBoxForDocument.lastChild);
															}

														}

													},
													error : function(
															textStatus,
															errorThrown) {
														console.log(textStatus);
														alert('documents not copied ');
													}
												});

									});

				});