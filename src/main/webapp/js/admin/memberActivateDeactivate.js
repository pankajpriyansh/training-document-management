$(document).ready(
		function() {
			var batchSelectBoxColumnId = document
					.getElementById('batchSelectBoxColumnId');
			$('#roleSelectBoxId').change(function() {
				if ($(this).val() == 3)
					batchSelectBoxColumnId.style.display = "block";
				else
					batchSelectBoxColumnId.style.display = "none";

			});

			$('.activateMember').click(function() {
				var memberId = $(this).attr("memberId");
				$.ajax({
					url : "./memberIsActive.html",
					data : {
						memberId : memberId,
						status : true
					},
					success : function(data) {
						alert("Member Activated");
						location.reload();
					}

				});
			});

			$('.deactivateMember').click(function() {
				var memberId = $(this).attr("memberId");
				$.ajax({
					url : "./memberIsActive.html",
					data : {
						memberId : memberId,
						status : false
					},
					success : function(data) {
						alert("Member DeActivated");
						location.reload();
					}

				});
			});

		});
