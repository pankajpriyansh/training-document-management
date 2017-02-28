$(document)
		.ready(
				function() {
					var loginModal = document.getElementById('loginModal');
					var registrationModal = document
							.getElementById('registrationModal');

					$('#registrationButton').click(function() {
						registrationModal.style.display = "block";
						loginModal.style.display = "none";
						$('#registrationFormMessageId').html('');

					});

					$('#loginButton').click(function() {
						registrationModal.style.display = "none";
						$('#loginFormMessageId').html('');
						loginModal.style.display = "block";
					});

					/*
					 * $("form#loginForm") .submit( function(e) {
					 * e.preventDefault(); console.log('form submitted'); var
					 * loginFormData = $("form#loginForm") .serialize(); $
					 * .ajax({ url : "./authenticate.html", data :
					 * loginFormData, success : function(response) {
					 * console.log(response); if (response ==
					 * 'errorOfAuthentication') { console .log(' response is
					 * errorOfAuthentication'); $( '#loginFormMessageId') .html(
					 * 'Incorrect Email or Password'); $( "#loginFormMessageId")
					 * .fadeOut( "slow"); $("form#loginForm") .trigger(
					 * "reset"); } else { $("form#loginForm") .trigger(
					 * "reset"); loginModal.style.display = "none"; switch
					 * (response.role) { case 1: break; case 2:
					 * window.location.href = "./trainer.html"; break; case 3:
					 * window.location.href = "./trainee.html"; break; } } } });
					 * 
					 * });
					 */

					$('#closeLoginModelId').click(function() {
						loginModal.style.display = "none";
					});

					/*
					 * $("form#registrationForm").submit( function(e) {
					 * e.preventDefault(); console.log('form submitted'); var
					 * registrationFormData = $(
					 * "form#registrationForm").serialize(); $.ajax({ url :
					 * "./saveMember.html", data : registrationFormData, success :
					 * function(response) { alert('Member added'); }, error :
					 * function(textStatus, errorThrown) {
					 * console.log(textStatus); alert('Member Not created'); }
					 * }); $("form#registrationForm").trigger("reset");
					 * registrationModal.style.display = "none"; });
					 */

					$('#closeRegistrationModelId').click(function() {
						registrationModal.style.display = "none";
					});

					$.validator.addMethod("regx", function(value, element,
							regexpr) {
						return regexpr.test(value);
					}, "");

					/* Registration Form Validation */
					$("form#registrationForm")
							.validate(
									{
										rules : {
											firstname : {
												required : true,
												regx : /^[a-zA-Z]+$/,
												minlength : 3,
												maxlength : 16
											},
											lastname : {
												required : true,
												regx : /^[a-zA-Z]+$/,
												minlength : 3,
												maxlength : 16
											},
											contact : {
												required : true,
												regx : /^[0-9]*$/,
												minlength : 10,
												maxlength : 11
											},
											email : {
												required : true,
												regx : /[a-zA-Z_][a-zA-Z0-9\.]+@(yash\.com|YASH\.COM|YASH\.com)/
											},
											password : {
												required : true,
												minlength : 7,
												maxlength : 16,
												regx : /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[!@#$%^&*()_+])[A-Za-z\d][A-Za-z\d!@#$%^&*()_+]{7,16}$/
											}
										},
										// Specify validation error messages
										messages : {
											firstname : {
												required : "Please enter your firstname",
												regx : "firstname can have only alphabets",
												minlength : "firstname must have atleast 3 characters",
												maxlength : "firstname must have atmost 16 characters"
											},
											lastname : {
												required : "Please enter your lastname",
												regx : "lastname contain only alphabets",
												minlength : "lastname must have atleast 3 characters",
												maxlength : "lastname must have atmost 16 characters"
											},
											contact : {
												required : "Please provide contact no.",
												regx : "contact no. must be integer",
												minlength : "contact no. have atleast 10 digits",
												maxlength : "contact no. have atmost 11 digits"
											},
											password : {
												required : "Please provide a password",
												minlength : "Password contain at least 7 characters",
												maxlength : "Password contain at most 16 characters",
												regx : "Password must have alteast 1 letter, 1 digit , 1 special symbol"
											},
											email : {
												required : "Please enter your email address",
												regx : "Email must be valid and  have Yash domain"
											}
										},
										submitHandler : function(form) {
											var registrationFormData = $(
													"form#registrationForm")
													.serialize();
											$
													.ajax({
														type : "POST",
														url : "./saveMember.html",
														data : registrationFormData,
														success : function(
																response) {
															if (response == 'emailExists') {
																$(
																		'#registrationFormMessageId')
																		.html(
																				'Email Already Exists');
															} else {
																alert('Member added');
																$(
																		"form#registrationForm")
																		.trigger(
																				"reset");
																registrationModal.style.display = "none";
															}
														},
														error : function(
																textStatus,
																errorThrown) {
															console
																	.log(textStatus);
															alert('Member Not created');
														}
													});

										}
									});

					$("form#loginForm")
							.validate(
									{
										rules : {
											email : {
												required : true,
												email : true
											},
											password : {
												required : true,
												minlength : 5
											}
										},
										// Specify validation error messages
										messages : {
											password : {
												required : "Please provide a password",
												minlength : "Your password must be at least 5 characters long"
											},
											email : "Please enter a valid email address"
										},
										submitHandler : function(form) {
											var loginFormData = $(
													"form#loginForm")
													.serialize();
											$
													.ajax({
														type : "POST",
														url : "./authenticate.html",
														data : loginFormData,
														success : function(
																response) {
															console
																	.log(response);
															if (response == 'errorOfAuthentication') {
																$(
																		'#loginFormMessageId')
																		.html(
																				'Incorrect Email or Password');
																$(
																		"form#loginForm")
																		.trigger(
																				"reset");

															} else {
																console
																		.log('authenticated');
																$(
																		"form#loginForm")
																		.trigger(
																				"reset");
																loginModal.style.display = "none";
																// redirect
																// based on
																// roles
																console
																		.log(response.role);
																switch (response.role) {
																case 1: {
																	console
																			.log('for admin');
																	window.location.href = "./admin.html"
																	break;
																}
																case 2:
																	window.location.href = "./trainer.html";
																	break;
																case 3:
																	window.location.href = "./trainee.html";
																	break;
																}
															}
														}
													});
										}
									});

				});