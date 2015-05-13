//Auxiliary counter 
var counter = 0;
//Creating Order Blocks (in form of div in html code) by pressing MAKE ORDER button.
$("#order").click(function () {
	var newOrder = document.createElement('div');
	newOrder.className = 'orderblock';
	newOrder.id = 'orderDiv'  + counter++;
	newOrder.textContent = 'TAXI ORDER # ' + counter;
	applyOrderblockStyle (newOrder);
	document.getElementById('thepanel').appendChild(newOrder);
})

//Apply style to dynamically creater divs (Order Blocks).
function applyOrderblockStyle (elementToStyle){
	elementToStyle.style.height = '150px';
	elementToStyle.style.width = '95%';
	elementToStyle.style.border = 'solid black';
	elementToStyle.style.marginTop = '2px';
	elementToStyle.style.marginBottom = '2px';
	elementToStyle.style.marginLeft = 'auto';
	elementToStyle.style.marginRight = 'auto';
	elementToStyle.style.backgroundColor = '#6AD644';
	//var css = '.orderblock:hover{background-color:orange;}';
	//elementToStyle.style.cssText = css;

}
//Remove Order by clicking it
$(".orderspanel").on('click', 'div.orderblock', function(event) {
    alert('you clicked an OrderBlock element, now it will be removed. Blame yourself...');
    $(this).remove();
});

