$(document).ready(function() {

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

	var rejectReasonModal = document.getElementById('RejectReasonModal');
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

});
