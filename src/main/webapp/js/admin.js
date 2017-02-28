$(document).ready(function() {

	$(document).on("change", "input[type=radio]", function() {
		console.log('Radio button changed ');
		var memberId = jQuery(this).attr("memberId");
		var value = jQuery(this).val();
		console.log(value, "  ", memberId);

		$.ajax({
			url : "./activateMember.html",
			data : {
				memberId : memberId
			},
			success : function(data) {
				alert("Member Activated");
				location.reload();
			}

		});

	});
	$('#roleSelectBoxId').change(function() {
		var memberId = jQuery(this).attr("memberId");
		var value = jQuery(this).val();
		console.log(value, "  ", memberId);
		$.ajax({
			url : "./changeRole.html",
			data : {
				memberId : memberId,
				role : value
			},
			success : function(data) {
				alert("Role Changed");
			}

		});

	});
});

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

function clearFilter() {
	$('.filterText').val('');
	$('.contentOfDocuments').show();
}
