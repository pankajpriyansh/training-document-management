$(document)
		.ready(
				function() {
					var showMemberOfSelectedBatchBoxId = document
							.getElementById('showMemberOfSelectedBatchBoxId');
					$('#shiftMemberPageFromBatchSelectBoxId')
							.change(
									function(e) {
										var batchId = $(this).val();

										while (showMemberOfSelectedBatchBoxId
												.hasChildNodes()) {
											showMemberOfSelectedBatchBoxId
													.removeChild(showMemberOfSelectedBatchBoxId.firstChild);
										}
										$
												.ajax({

													url : "./getMembersByBatchId.html",
													data : {
														batchId : batchId
													},
													success : function(response) {
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
														showMemberOfSelectedBatchBoxId
																.appendChild(selectOption);
														response
																.forEach(function(
																		item) {
																	var option = document
																			.createElement('option');
																	option.value = item.id;
																	option.text = item.email;
																	showMemberOfSelectedBatchBoxId
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

					$("form#shiftMemberPageBatchFormId")
							.submit(
									function(e) {
										e.preventDefault();

										console.log('submitted');
										var formData = $(
												"form#shiftMemberPageBatchFormId")
												.serialize();
										console.log(formData);

										$
												.ajax({
													type : "POST",
													url : "./shiftMemberByBatch.html",
													data : formData,
													success : function(response) {
														if (response == 'bothBatchSame') {
															$(
																	'#shiftMemberByBatchFormMessageId')
																	.html(
																			'Please Select Different Batch');
														} else {

															console
																	.log(response);

															$(
																	'#shiftMemberByBatchFormMessageId')
																	.html();
															alert('Member Shift to new Batch');
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
										$("form#shiftMemberPageBatchFormId")
												.trigger("reset");

									});

				});