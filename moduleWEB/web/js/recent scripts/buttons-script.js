
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
	$('#anon-order').toggle(false);
});

$('#toggle-anon-order').click(function(){
	$('#anon-order').toggle();
	$('#login').toggle(false);
	$('#registration').toggle(false);
});










