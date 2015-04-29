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


$('#reg-submit').click(function(){
	var name1 = $('#firstname').val();
	var name2 = $('#lasttname').val();
	var name3 = $('#phone').val();
	var name4 = $('#email').val();
	var name5 = $('#pass').val();
	var name6 = $('#pass-confirm').val();
	var result = name1 + " " + name2 + " " + name3 + " " + name4 + " " + name5 + " " + name6;
	alert(result);
});
$('#no-reg-order').click(function(){
	var loginCredentials = $('#login-cred').val();
	var loginPass = $('#login-pass').val();
});
$('#login').click(function(){
	var name = $('#firstname').val();
	alert(name);
});

var bla = $('#txt_name').val();