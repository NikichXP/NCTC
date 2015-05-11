//AJAX section
//classes url variable
var url = 'api/user/login';
//Data to be sent will live here
var JSONdata;
//AJAX POST for log in
$('#login-submit').click(function(){
	//Ensure form validity before gathering data for AJAX JSON transfer
	if (!validateInput(LoginForm)) { return; } 
	//Building JSON object
	JSONdata = {
		cred: $("#login-cred").val(),
		pass: $("#login-pass").val()
	};
	//Firing AJAX POST call
	$.ajax({
		method: 'POST',
		url: 'api/user/login',
		contentType: "application/json; charset=utf-8",
		data: JSON.stringify(JSONdata),
		dataType:'text',
		success: function (data,textStatus,jqXHR ) {
			alert(textStatus + " " + jqXHR.responseText);
			//document.location.href = "http://habrahabr.ru/post/123314/";
		},
		error: function (jqXHR, textStatus, errorThrown) {
			alert(jqXHR.status + ' ' + jqXHR.responseText);
			//alert(eval("(" + data + ")"));
		}
	})
});


//AJAX POST for registration
$('#registration-submit').click(function(){
	//Ensure form validity before gathering data for AJAX JSON transfer
	if(!validateInput(RegistrationForm)) { return; }
	//Building JSON object
	JSONdata = {
		firstName: $("#firstname").val(),
		lastName: $("#lastname").val(),
		phone: $("#phone").val(),
		email: $("#email").val(),
		pass: $("#reg-pass").val()
	};
	//Firing AJAX POST call
	$.ajax({
		method: 'POST',
		url: 'api/user/create',
		contentType: "application/json; charset=utf-8",
		data: JSON.stringify(JSONdata),
		dataType:'text',
		success: function (data,textStatus,jqXHR ) {
			alert(textStatus + ": " + jqXHR.responseText);
		},
		error: function (jqXHR, textStatus, errorThrown) {
			alert(jqXHR.status + ' ' + jqXHR.responseText);
		}
	})
});
//AJAX POST for tracking single order
$('#track-TO-submit').click(function(){
	JSONdata = {
		'orderid': $("#tracking-id").val()
	};
	$.ajax({
		method: 'POST',
		url: url,
		data: JSONdata,
		dataType:'json'
	})
	.done(function(){alert("success!!");})
	.fail(function(){alert("fail!");});
});
//AJAX POST for anonimous order
$('#order-wo-reg-submit').click(function(){
	alert("Hold on, not yet functionlal!");
});