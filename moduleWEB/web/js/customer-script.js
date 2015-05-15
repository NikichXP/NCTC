//Actual logic

// On page load {
// 	get data from server (ajax call)
// 	build Order Blocks from received data
// 	add them to HTML
// }
// How to delete blocks {
// 	listen to server
// 	delete specified blocks
// }
var obj = [
{
	"Status": "assigned",
	"Type": "regular ride",
	"From": "obolon",
	"To": "kpi",
	"Time": "14:08"
},
{
	"Status": "assigned",
	"Type": "regular ride",
	"From": "obolon",
	"To": "kpi",
	"Time": "14:08"
},
{
	"Status": "assigned",
	"Type": "regular ride",
	"From": "obolon",
	"To": "kpi",
	"Time": "14:08"
},
{
	"Status": "assigned",
	"Type": "regular ride",
	"From": "obolon",
	"To": "kpi",
	"Time": "14:08"
}
];

$(function(){

	//Actual logic. Uncomment it

	// $.ajax({
	// 	method: 'POST',
	// 	url: 'api/user_dash/history',
	// 	contentType: "text/plain; charset=utf-8",
	// 	data: uuid,
	// 	dataType: 'text',
	// 	success: function (data, textStatus, jqXHR) {
	// 		var obj = JSON.parse(data);
	// 		alert(data);

	 		//Iterates through received array (obj) to build html elements based on EACH order JSON
	// 		for(var order in obj){
	//		createOrderPlate(obj[order]);
	// 		}
	// 	},
	// 	error: function (jqXHR, textStatus, errorThrown) {
	// 		alert(jqXHR.responseText + " Error!");
	// 	}
	// })

	

	//For the purpose of debugging. DELETE IT.
	for(var order in obj){
		createOrderPlate(obj[order]);
	}

	
});

function constructOrderText (order) {

	var orderText = document.createElement('div');
	//Extracting data from JSON to construct HTML
	var orderStatus = 'Status:' + order["Status"];
	var orderType = 'Type ' + order["Type"];
	var orderFrom = 'From: ' + order["From"];
	var orderTo = 'To: ' + order["To"];
	var orderTime = 'Time: ' + order["Time"];
	var orderTextHTML = '<div><h3>description:</h3><p>' + orderStatus + '</p><p>' + orderType + '</p><p>' + orderFrom + '</p><p>' + orderTo + '</p><p>' + orderTime + '</p></div>';
	
	//Putting HTML with data inside order text block
	orderText.innerHTML = orderTextHTML;
	//Applying style to 
	orderText.style.fontWeight = 'bold';
	orderText.style.float = 'left';
	orderText.style.backgroundColor = 'red';

	return orderText;
}

function constructOrderIcon (order) {

	
	var orderIcon = document.createElement('div');

	orderIcon.style.float = 'right';
	orderIcon.style.backgroundColor = 'green';



	var animal = 'Animal' + order["Animal"];
	var orderIconHTML = '<div><h3>description:</h3><p>' + animal + '</p><p></div>';



	orderIcon.innerHTML = orderIconHTML;

	return orderIcon;
}

function createOrderPlate (order) {

	//creating order plate which will contain information about order. 
	var newOrderPlate = document.createElement('div');
	//Setting id for newly created plate. By clicking plate user will be
	//redirected to the OrderPage with corresponded id.

	//styling
	newOrderPlate.style.height = 'auto';
	newOrderPlate.style.width = '95%';
	newOrderPlate.style.border = 'solid black';
	newOrderPlate.style.marginTop = '2px';
	newOrderPlate.style.marginBottom = '2px';
	newOrderPlate.style.marginLeft = 'auto';
	newOrderPlate.style.marginRight = 'auto';
	newOrderPlate.style.backgroundColor = '#53C68A';



	var text = constructOrderText(order);

	var icon = constructOrderIcon(order);
	//first attach parents element to the DOM
	document.getElementById('customerOrdersPanel').appendChild(newOrderPlate);
	//then attach children to the parent

	newOrderPlate.appendChild(text);
	newOrderPlate.appendChild(icon);	

}


//Creating Order Blocks (in form of div in html code) by pressing MAKE ORDER button.
//Do not delete! This function will be required later

$("#centralButton").click(function () {	
	//window.location = "createOrder.jsp";
});



