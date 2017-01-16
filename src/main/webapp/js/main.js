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

window.addEventListener('load',
		function() { // on page load
			console.log('page elment clicked ');
			document.getElementById('nextpage').addEventListener('click',
					openNextPage);
			document.getElementById('previouspage').addEventListener('click',
					openPrevPage);
			document.getElementById('zoom').addEventListener('click',
					toggleZoom);

		}, false)

var pdfFile;
var currPageNumber = 1;

var openNextPage = function() {
	console.log('inside open next page ');
	var pageNumber = Math.min(pdfFile.numPages, currPageNumber + 1);
	if (pageNumber !== currPageNumber) {
		currPageNumber = pageNumber;
		openPage(pdfFile, currPageNumber);
	}
};

var openPrevPage = function() {
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
	pdfFile.getPage(pageNumber).then(function(page) {
		viewport = page.getViewport(1);

		if (zoomed) {
			var scale = pageElement.clientWidth / viewport.width;
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

PDFJS.disableStream = true;
PDFJS.getDocument('./files/Spring Framework Personal Notes.pdf').then(
		function(pdf) {
			pdfFile = pdf;
			console.log(pageElement);
			openPage(pdf, 1);
		});
