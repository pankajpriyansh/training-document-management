$(document)
		.ready(
				function() {

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

				});

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

function clearFilter() {
	$('.filterText').val('');
	$('.contentOfDocuments').show();
}
