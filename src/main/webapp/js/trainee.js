$(document)
		.ready(
				function() {

					jQuery('.DocumentBox')
							.click(
									function() {
										var description = $(this).attr(
												'documentDescription');
										var documentPath = $(this).attr(
												'documentPath');

										console.log(documentPath);
										console.log(description);
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

					var zoomed = false;
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