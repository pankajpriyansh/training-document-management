$(document)
		.ready(
				function() {

					var documentApprovedStatusModal = document
							.getElementById('documentApprovedStatusModal');

					$('.showStatusButtonOfRequestedDocumentReports')
							.click(
									function() {
										var toMemberId = jQuery(this).attr(
												"toMemberId");
										var fromMemberId = jQuery(this).attr(
												"fromMemberId");
										documentApprovedStatusModal.style.display = "block";
										console.log(toMemberId, "   ",
												fromMemberId);
										var documentApprovedStatusTable = document
												.getElementById('documentApprovedStatusTableId');
										$
												.ajax({
													type : "POST",
													url : "./getRequestedDocumentReportsAdvanceData.html",
													data : {
														fromMemberId : fromMemberId,
														toMemberId : toMemberId
													},
													success : function(response) {
														console.log(response);
														for (var i = 1; i < documentApprovedStatusTable.rows.length;) {
															documentApprovedStatusTable
																	.deleteRow(i);
														}
														response
																.forEach(function(
																		item) {
																	var row = documentApprovedStatusTable
																			.insertRow();
																	row.style.border = "1px solid";
																	var cell1 = row
																			.insertCell(0);
																	cell1.style.border = "1px solid";
																	cell1.style.padding = "5px";
																	var cell2 = row
																			.insertCell(1);
																	cell2.style.border = "1px solid";
																	cell2.style.padding = "5px";
																	cell2.style.color = "red";
																	var cell3 = row
																			.insertCell(2);
																	cell3.style.border = "1px solid";
																	cell3.style.padding = "5px";

																	cell1.innerHTML = item.documentName;
																	console
																			.log(item.status);
																	if (item.status == " No Action Performed ")
																		cell2.style.color = "orange";
																	if (item.status == ' Approved ')
																		cell2.style.color = "green";
																	cell2.innerHTML = item.status;
																	cell3.innerHTML = item.Date;
																});
													},
													error : function(
															textStatus,
															errorThrown) {
														console.log(textStatus);
													}
												});

									});
					$('#closeDocumentApprovedStatusModal').click(function() {
						documentApprovedStatusModal.style.display = "none";
					});

				});