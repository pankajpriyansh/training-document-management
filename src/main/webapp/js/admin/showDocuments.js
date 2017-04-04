$(document)
		.ready(
				function() {
					var editDocumentModal = document
							.getElementById('editDocumentModal');

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

					var displayDocumentModal = document
							.getElementById('displayDocumentModal');

					$('#closeDisplayDocumentModelId').click(function() {
						displayDocumentModal.style.display = "none";
						$('#my-player_html5_api').attr("src", "");
					});

					$('.displayDoc')
							.click(
									function() {
										displayDocumentModal.style.display = "block";
										var videoBoxId = document
												.getElementById('my-player');
										videoBoxId.style.display = "none";
										$('#my-player_html5_api').attr("src",
												"");

										var documentPath = jQuery(this).attr(
												"documentPath");
										var documentName = jQuery(this).attr(
												"documentName");
										$('#displayDocumentModalHeadingId')
												.html(documentName);

										console.log(documentPath);
										if (documentPath.endsWith(".mp4")) {
											document.getElementById('page').style.display = "none";
											document
													.getElementById('buttonsOfPdf').style.display = "none";
											videoBoxId.style.display = "block";
											console.log('file is mp4');
											$('#my-player_html5_api').attr(
													"style", "display : block");
											$('#my-player_html5_api')
													.attr(
															"src",
															'Documents/'
																	+ documentPath);
										} else {
											document.getElementById('page').style.display = "block";
											document
													.getElementById('buttonsOfPdf').style.display = "block";
											PDFJS.disableStream = true;
											PDFJS
													.getDocument(
															'Documents/'
																	+ documentPath)
													.then(
															function(pdf) {
																pdfFile = pdf;
																console
																		.log(pageElement);
																openPage(pdf, 1);
															});
										}
									});

					$('#canvas').on("contextmenu", function(e) {
						e.preventDefault();
					});

					$('#my-player').on("contextmenu", function(e) {
						e.preventDefault();
					});

					$('#nextpage').click(function() {
						console.log('next page clicked ');
						openNextPage();
					});
					$('#previouspage').click(function() {
						console.log('previouspage page clicked ');
						openPrevPage();
					});
					$('#zoom').click(function() {
						console.log('zoomd ');
						toggleZoom();
					});

					if (!window.requestAnimationFrame) {
						window.requestAnimationFrame = (function() {
							return window.webkitRequestAnimationFrame
									|| window.mozRequestAnimationFrame
									|| window.oRequestAnimationFrame
									|| window.msRequestAnimationFrame
									|| function(callback, element) {
										window.setTimeout(callback, 1000 / 60);
									};
						})();
					}

					var canvas = document.getElementById('canvas');
					var context = canvas.getContext('2d');
					var pageElement = document.getElementById('page');

					var pdfFile;
					var currPageNumber = 1;

					var openNextPage = function() {
						console.log('inside open next page ');
						var pageNumber = Math.min(pdfFile.numPages,
								currPageNumber + 1);
						if (pageNumber !== currPageNumber) {
							currPageNumber = pageNumber;
							openPage(pdfFile, currPageNumber);
						}
					};

					var openPrevPage = function() {
						console.log('inside open previous page ');
						var pageNumber = Math.max(1, currPageNumber - 1);
						if (pageNumber !== currPageNumber) {
							currPageNumber = pageNumber;
							openPage(pdfFile, currPageNumber);
						}
					};

					var zoomed = true;
					var toggleZoom = function() {
						console.log('inside toggle zoom');
						zoomed = !zoomed;
						openPage(pdfFile, currPageNumber);
					};

					var fitScale = 1;
					var openPage = function(pdfFile, pageNumber) {
						var scale = zoomed ? fitScale : 1;
						pdfFile.getPage(pageNumber).then(
								function(page) {
									viewport = page.getViewport(1);

									if (zoomed) {
										var scale = pageElement.clientWidth
												/ viewport.width;
										viewport = page.getViewport(scale);
									}

									canvas.height = viewport.height;
									canvas.width = viewport.width;

									var renderContext = {
										canvasContext : context,
										viewport : viewport
									};

									page.render(renderContext);
								});
					};

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