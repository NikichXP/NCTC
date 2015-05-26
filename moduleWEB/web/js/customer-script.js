//Auxiliary counter to add number to fresh OT divs
var counter = 0;
//Creating Order Blocks (in form of div in html code) by pressing MAKE ORDER button.
//Do not delete! This function will be required later
$("#centralButton").click(function () {
	document.location.href = "createOrder.jsp";
	//var newOrderPlate = document.createElement('div');
	//newOrderPlate.className = 'orderPlate';
	//newOrderPlate.id = 'taxiOrder'  + counter++;
	//newOrderPlate.textContent = 'TAXI ORDER # ' + counter;
	//applyStyle (newOrderPlate);
	//document.getElementById('customerOrdersPanel').appendChild(newOrderPlate);
});

$("#leftButton").click(function () {
	document.location.href = "customerOrderHistory.jsp";
});

$("#rightButton").click(function () {
	document.location.href = "../setting.jsp";
});


//Apply style to dynamically creater divs (Order Blocks).
//TO BE COMPLETED. Style constant must be developed
function applyStyle (elementToStyle){
	elementToStyle.style.height = '150px';
	elementToStyle.style.width = '95%';
	elementToStyle.style.border = 'solid black';
	elementToStyle.style.marginTop = '2px';
	elementToStyle.style.marginBottom = '2px';
	elementToStyle.style.marginLeft = 'auto';
	elementToStyle.style.marginRight = 'auto';
	elementToStyle.style.backgroundColor = '#6AD644';
}
//Remove Order by clicking it
//Just for the showcase
$(".mainSection").on('click', 'div.orderPlate', function(event) {
	alert('you clicked an OrderBlock element, now it will be removed. Blame yourself...');
	$(this).remove();
});