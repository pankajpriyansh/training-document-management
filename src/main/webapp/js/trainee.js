$(document)
		.ready(
				function() {

					var copyToClipboard = function() {
						var aux = document.createElement("input");
						aux.setAttribute("value", "test");
						document.body.appendChild(aux);
						aux.select();
						document.execCommand("copy");
						document.body.removeChild(aux);
					}

					$(window).keyup(function(e) {
						if (e.keyCode == 44) {
							copyToClipboard();
						}
					});

					$('#canvas').on("contextmenu", function(e) {
						e.preventDefault();
					});

			/*		$(window).focus(function() {
						$("body").show();
					}).blur(function() {
						$("body").hide();
					});
*/
					jQuery('.DocumentBox')
							.click(
									function() {
										var description = $(this).attr(
												'documentDescription');
										var documentPath = $(this).attr(
												'documentPath');
										var documentId = $(this).attr(
												'documentId');
										$('#' + documentId).removeClass(
												'glyphicon-time').addClass(
												'glyphicon-ok');
										$.ajax({
											url : "./markDocumentRead.html",
											data : {
												documentId : documentId
											},
											success : function(response) {
												console.log(response);
											}
										});
										$('.description p').html(description);

										PDFJS.disableStream = true;
										PDFJS.getDocument(
												'Documents/' + documentPath)
												.then(function(pdf) {
													pdfFile = pdf;
													console.log(pageElement);
													openPage(pdf, 1);
												});

									})

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

					/**
					 * button clicked , create raiseRequestModal modal open
					 */
					var raiseRequestModal = document
							.getElementById('RaiseRequestModal');
					$('#raiseRequest')
							.click(
									function() {
										var selectBox = document
												.getElementById('trainersEmailSelectBox');
										console.log(selectBox.value);
										while (selectBox.hasChildNodes()) {
											selectBox
													.removeChild(selectBox.firstChild);
										}
										console.log('button clicked ');
										$
												.ajax({
													url : "./GetTrainerList.html",

													success : function(response) {
														var trainers = response;
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
														selectBox
																.appendChild(selectOption);
														response
																.forEach(function(
																		item) {
																	var option = document
																			.createElement('option');
																	option.value = item.email;
																	option.text = item.email;
																	selectBox
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

										var selectBoxForDocument = document
												.getElementById('documentsSelectBox');
										while (selectBoxForDocument
												.hasChildNodes()) {
											selectBoxForDocument
													.removeChild(selectBoxForDocument.firstChild);
										}
										console.log('button clicked ');
										$
												.ajax({
													url : "./GetDocumentList.html",

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
																	option.value = item.name;
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

										raiseRequestModal.style.display = "block";
										$('#raiseRequestFormMessageId')
												.html('');
										$("form#raiseRequestForm").trigger(
												"reset");

									});

					/**
					 * close button to close raiseRequest modal
					 */
					$('#closeRaiseRequestModelId').click(function() {
						console.log(' raiseRequest close clicked ');
						RaiseRequestModal.style.display = "none";
					});

					/**
					 * raise new request
					 */

					$("form#raiseRequestForm")
							.submit(
									function(e) {
										e.preventDefault();
										console
												.log('form submitted raise request');
										console
												.log('clicked model submit  raise request');
										var raiseRequestFormData = $(
												"form#raiseRequestForm")
												.serialize();
										console.log("data====",
												raiseRequestFormData);
										$
												.ajax({
													url : "./MailController.html",
													data : raiseRequestFormData,
													success : function(response) {
														raiseRequestModal.style.display = "none";
														alert('Request Sent Successfully');

													},
													error : function(
															textStatus,
															errorThrown) {
														console.log(textStatus);
														alert('mail not sent ');
													}
												});

									});

				});