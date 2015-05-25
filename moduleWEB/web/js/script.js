$(function(){
	$('#login').toggle();
	$('#registration').toggle();
	$('#anon-order').toggle();
});

$('#toggle-login').click(function(){
	$('#login').toggle();
	$('#registration').toggle(false);
	$('#anon-order').toggle(false);
});

$('#toggle-registration').click(function(){
	$('#registration').toggle();
	$('#login').toggle(false);
	$('#anon-order').toggle(false);});
$('#toggle-anon-order').click(function(){
	$('#anon-order').toggle();
	$('#login').toggle(false);
	$('#registration').toggle(false);
});

//AJAX section
//AJAX POST for login
$('#login-submit').click(function(){
	if (!validateLoginData()) return;
	var JSONdata = {
		cred: $("#login-cred").val(),
		pass: $("#login-pass").val()
		
	};
	redirectWithAccessLevels(JSON.stringify(JSONdata), "api/user/login");
});
//AJAX POST for registration
$('#registration-submit').click(function(){
	if (!validateRegistrationData()) return;
	var JSONdata = {
		firstName: $("#firstName").val(),
		lastName: $("#lastName").val(),
		phone: $("#phone").val(),
		email: $("#email").val(),
		pass: $("#reg-pass").val()
	};
	redirectWithAccessLevels(JSON.stringify(JSONdata), "api/user/create");
});

$('#order-wo-reg-submit').click(function(){
	document.location.href = "createOrderWithoutRegistration.jsp";
});
$('#track-TO-submit').click(function(){
	document.location.href = "api/order/viewOrderWithoutRegistration?publicToken=" + $('#tracking-id').val();
});

function redirectWithAccessLevels(userData, url) {
	$.ajax({
		method: 'POST',
		url: url,
		contentType: "application/json; charset=utf-8",
		data: userData,
		dataType:'text',
		success: function (data,textStatus,jqXHR ) {
			document.cookie = "uuid="+jqXHR.responseText;
			//alert("uuid="+jqXHR.responseText);
			uuid = jqXHR.responseText;
			getAccessLevels(uuid);
		},
		error: function (jqXHR, textStatus, errorThrown) {
			alert(jqXHR.responseText);
		}
	})
}

function getAccessLevels(uuid) {
	$.ajax({
		method: 'POST',
		url: 'api/user/getAccessLevelsByUuid',
		contentType: "text/plain; charset=utf-8",
		data: uuid,
		dataType:'text',
		success: function (data,textStatus,jqXHR ) {
			//alert("User access levels: " + jqXHR.responseText);
			var obj = JSON.parse(data);
			if (obj.userAccessLevel.length == 1) {
				document.location.href = obj.userAccessLevel[0].level + '.jsp';
			} else {
				document.location.href = "accessLevel.jsp";
			}

		},
		error: function (jqXHR, textStatus, errorThrown) {
			alert("Seems like DB error.");
		}
	})
}

var phoneRegEx = /^\+?[0-9]{6,12}$/;
var namesRegEx = /^[a-zA-Z\s'\-]+$/;
var emailRegEx = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
var passRegEx = /^....+$/;

function validateRegistrationData() {
	//validates login credentials (e-mail or phone number) field
	if(!validateNames($("#firstName").val(), namesRegEx)){
		alert("First name:\nPlease, use only alphabetic characters!");
		return false;
	}
	if(!validateNames($("#lastName").val(), namesRegEx)){
		alert("Last name:\nPlease, use only alphabetic characters!");
		return false;
	}
	var phone = $("#phone").val();
	phone = phone.replace(/\s/g, "").replace(/\+/g, "");
	if(!validateNames(phone, phoneRegEx)){
		alert("Phone number:\nPlease, use only digits. Length from 6 to 12.");
		return false;
	}
	if(!validateNames($("#email").val(), emailRegEx)){
		alert("E-mail:\nPlease, enter a valid e-mail.");
		return false;
	}
	if(!validateNames($("#reg-pass").val(), passRegEx)){
		alert("Password:\nPassword must be at least 4 characters long.");
		return false;
	}
	if($("#pass-confirm").val() !== $("#reg-pass").val()){
		alert("Confirm password:\nPasswords don't match.");
		return false;
	}
	return true;
}

function validateLoginData() {
	var phone = $("#login-cred").val();
	phone = phone.replace(/\s/g, "").replace(/\+/g, "");
	if (!validateNames($("#login-cred").val(), emailRegEx) && !validateNames(phone, phoneRegEx)) {
		alert("E-mail:\nPlease, enter a valid e-mail or phone number.");
		return false;
	}
	if (!validateNames($("#login-pass").val(), passRegEx)) {
		alert("Password:\nPassword must be at least 4 characters long.");
		return false;
	}
    return true;
}

function validateNames (input, regEx) {
	if(input.length > 0){
		return regEx.test(input);
	}
	return false;
}






