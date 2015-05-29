$("#centralButton").click(function () {
    document.location.href = "updateInProgressOrder.html";
});

$("#leftButton").click(function () {
    document.location.href = "driverHistory.html";
})

$("#rightButton").click(function () {
    document.location.href = "setting.html";
})
//Apply style to dynamically creater divs (Order Blocks).
//TO BE COMPLETED. Style constant must be developed
function applyStyle(elementToStyle) {
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
$(".mainSection").on('click', 'div.orderPlate', function (event) {
    alert('you clicked an OrderBlock element, now it will be removed. Blame yourself...');
    $(this).remove();
});

function goBack() {
    document.location.href = 'driver.html'
}