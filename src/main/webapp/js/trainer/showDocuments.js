$(document)
		.ready(
				function() {

					var editDocumentModal = document
							.getElementById('editDocumentModal');
					var documentShowStatusModal = document
							.getElementById('documentShowStatusModal');

					$('#closeDocumentShowStatusModal').click(function() {
						documentShowStatusModal.style.display = "none";
					});

					$('#closeEditDocumentModelId').click(function() {
						editDocumentModal.style.display = "none";
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