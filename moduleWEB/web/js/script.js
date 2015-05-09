//todo review for removal
$(function(){
	$('#login').toggle();
	$('#registration').toggle();
	$('#anon-order').toggle();
});


$('#toggle-login').click(function(){
	$('#login').toggle();
});

$('#toggle-registration').click(function(){
	$('#registration').toggle();
});

$('#toggle-anon-order').click(function(){
	$('#anon-order').toggle();
});

//AJAX section
//classes url variable
var url = 'api/user/login';
var JSONdata;
//AJAX POST for log in
$('#login-submit').click(function(){
	JSONdata = {
		cred: $("#login-cred").val(),
		pass: $("#login-pass").val()
	};
	$.ajax({
		method: 'POST',
		url: 'api/user/login',
		contentType: "application/json; charset=utf-8",
		data: JSON.stringify(JSONdata),
		dataType:'text',
		success: function (data,textStatus,jqXHR ) {
			//alert(textStatus + " " + jqXHR.responseText);
			document.location.href = "CustomerTO.html";
		},
		error: function (jqXHR, textStatus, errorThrown) {
			alert(jqXHR.status + ' ' + jqXHR.responseText);
			//alert(eval("(" + data + ")"));
		}
	})
});
//AJAX POST for registration
$('#registration-submit').click(function(){
	JSONdata = {
		firstName: $("#firstname").val(),
		lastName: $("#lastname").val(),
		phone: $("#phone").val(),
		email: $("#email").val(),
		pass: $("#reg-pass").val()
	};
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
//AJAX POST for
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
//AJAX POST for
$('#order-wo-reg-submit').click(function(){
	
	alert("Hold on, not yet functionlal!");
});




