//todo review for removal

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
	redirectWithAccessLevels(JSON.stringify(JSONdata), "api/user/login");
});
//AJAX POST for registration
$('#registration-submit').click(function(){
	JSONdata = {
		firstName: $("#firstName").val(),
		lastName: $("#lastName").val(),
		phone: $("#phone").val(),
		email: $("#email").val(),
		pass: $("#reg-pass").val()
	};
	redirectWithAccessLevels(JSON.stringify(JSONdata), "api/user/create");
});

function redirectWithAccessLevels(userData, url) {
	$.ajax({
		method: 'POST',
		url: url,
		contentType: "application/json; charset=utf-8",
		data: userData,
		dataType:'text',
		success: function (data,textStatus,jqXHR ) {
			//alert(textStatus + " " + jqXHR.responseText);
			document.cookie = "uuid="+jqXHR.responseText;
			alert("uuid="+jqXHR.responseText);
			uuid = jqXHR.responseText;
			getAccessLevels(uuid);
		},
		error: function (jqXHR, textStatus, errorThrown) {
			alert(jqXHR.status + ' ' + jqXHR.responseText);
			//alert(eval("(" + data + ")"));
		}
	})
}

function getAccessLevels(uuid) {
	$.ajax({
		method: 'POST',
		url: 'api/user/nextAfterSubmit',
		contentType: "text/plain; charset=utf-8",
		data: uuid,
		dataType:'text',
		success: function (data,textStatus,jqXHR ) {
			alert("User access levels: " + jqXHR.responseText);
			document.location.href = "access_level.jsp";
		},
		error: function (jqXHR, textStatus, errorThrown) {
			alert(uuid + " Error!");
		}
	})
}

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




