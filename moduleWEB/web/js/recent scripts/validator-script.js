//Various regular expressions to parse user inputs
var phoneRegEx = /[0-9]{6,10}/;
var namesRegEx = /[^0-9][a-zA-Z]{2,}/;
var emailRegEx = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
var passRegEx = /^[0-9a-zA-Z]\w{3,14}$/;

//Objects represent status of the input field
//Initially false values to restrict from submitting empty form
var LoginForm = {
	'logincred': false,
	'loginpass': false
}
var RegistrationForm = {
	'firstname': false,
	'lastname': false,
	'phone': false,
	'email': false,
	'pass': false
}

//Used to validate fields input before creating AJAX methods
function validateNames (input, regEx) {
	if(input.length > 0){
		if(regEx.test(input)){ return true; }
		else { return false; }
	}
	else { alert("must not be empty!"); }
}

function validateInput(form){
	for(var key in form){
		if(form.hasOwnProperty(key)){
			if(form[key] == true){
				//do nothing
			}
			else{
				//validation is not successful due to invalid input data
				alert("Please, check your entry!");
				return false;
			}
		}
	}
	//all input seem to be ok
	return true;
}

//Validates input immediately after focus is taken away from input field
$("body").focusout(function(event){
	
	switch(event.target.id){

		//validates login credentials (e-mail or phone number) field
		case "login-cred":
		if(validateNames($("#login-cred").val(), emailRegEx)){
			LoginForm.cred = true;
		}
		else if(validateNames($("#login-cred").val(), phoneRegEx)){
			LoginForm.logincred = true;
		}
		else{
			alert("Please, enter e-mail or phone number you used for registration.");
		}
		break;

		//validates login section password field
		case "login-pass":
		if(validateNames($("#login-pass").val(), passRegEx)){
			LoginForm.loginpass = true;
		}
		else{
			alert("password must contain at least 6 and maximum 12 characters!");
		}
		break;

		//validates registration section
		case "firstName" :
		if(validateNames($("#firstName").val(), namesRegEx)){
		RegistrationForm.firstname = true;
		}
		else{
			alert("Please, use only a-z or A-Z characters!");
		}
		break;
		case "lastName":
		if(validateNames($("#lastName").val(), namesRegEx)){
		RegistrationForm.lastname = true;
		}
		else{
			alert("Please, use only a-z or A-Z characters!");
		}
		break;
		//
		case "phone":
		if(validateNames($("#phone").val(), phoneRegEx)){
		RegistrationForm.phone = true;
		}
		else{
			alert("Please, use only digits. Length from 5 to 10.");
		}
		break;
		//
		case "email":
		if(validateNames($("#email").val(), emailRegEx)){
		RegistrationForm.email = true;
		}
		else{
			alert("Please, enter only proper e-mail address.");
		}
		break;
		//
		case "reg-pass":
		if(validateNames($("#reg-pass").val(), passRegEx)){
		RegistrationForm.pass = true;
		}
		else{
			alert("don't use special characters. length: 3-14");
		}
		break;
		//
		case "pass-confirm":
		if($("#pass-confirm").val() === $("#reg-pass").val()){
		RegistrationForm.pass = true;
		}
		else{
			alert("don't use special characters. length: 3-14");	
		}

	}
});



$("body").focusin(function(event){
	switch(event.target.id){

		//validates login credentials (e-mail or phone number) field
		case "login-cred":
			LoginForm.cred = false;
		break;

		//validates login section password field
		case "login-pass":
			LoginForm.pass = false;
		break;

		//validates registration section
		case "firstname" :
		RegistrationForm.firstname = false;
		break;
		case "lastname":
		RegistrationForm.lastname = false;
		break;
		case "phone":
		RegistrationForm.phone = false;
		break;	
		case "email":
		RegistrationForm.email = false;
		break;
		case "reg-pass":
		RegistrationForm.pass = false;
		break;
		case "pass-confirm":
		RegistrationForm.pass = false;

	}
});

