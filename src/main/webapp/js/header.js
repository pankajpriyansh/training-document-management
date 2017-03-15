$(document)
		.ready(
				function() {

					var changePasswordModal = document
							.getElementById('changePasswordModal');

					$('#changePassword').click(function(e) {
						e.preventDefault();
						changePasswordModal.style.display = "block";
						$('#changePasswordModalFormMessageId').html('');
						$("form#changePasswordForm").trigger("reset");
					});

					/**
					 * close button to close change password modal
					 */
					$('#closechangePasswordModalId').click(function() {
						changePasswordModal.style.display = "none";
					});

					$("form#changePasswordForm")
							.submit(
									function(e) {
										e.preventDefault();
										$('#changePasswordModalFormMessageId')
												.html('');
										var oldPassword = $('#oldPassword')
												.val();
										var newPassword1 = $('#newPassword1')
												.val();
										var newPassword2 = $('#newPassword2')
												.val();

										if (newPassword1 != newPassword2) {
											$(
													'#changePasswordModalFormMessageId')
													.html(
															'Both new Passwords Must be same');
										} else if (!newPassword1
												.match(/^(?=.*[a-zA-Z])(?=.*\d)(?=.*[!@#$%^&*()_+])[A-Za-z\d][A-Za-z\d!@#$%^&*()_+]{7,16}$/)) {
											$(
													'#changePasswordModalFormMessageId')
													.html(
															'New Password must have alteast 1 letter, 1 digit , 1 special symbol and size between 8 - 16');

										} else if (!newPassword2
												.match(/^(?=.*[a-zA-Z])(?=.*\d)(?=.*[!@#$%^&*()_+])[A-Za-z\d][A-Za-z\d!@#$%^&*()_+]{7,16}$/)) {
											$(
													'#changePasswordModalFormMessageId')
													.html(
															' Retype New Password must have alteast 1 letter, 1 digit , 1 special symbol and size between 8 - 16');

										} else {
											$
													.ajax({
														url : "./changePassword.html",
														data : {
															oldPassword : oldPassword,
															newPassword : newPassword1
														},
														success : function(
																response) {
															if (response == 'errorOfAuthentication') {
																$(
																		'#changePasswordModalFormMessageId')
																		.html(
																				'Old Password Incorrect');
															} else {
																alert('Password changed Successfully');
																changePasswordModal.style.display = "none";
															}
														},
														error : function(
																textStatus,
																errorThrown) {
															console
																	.log(textStatus);
															alert('Password not changed ');
														}
													});
										}
									});

				});