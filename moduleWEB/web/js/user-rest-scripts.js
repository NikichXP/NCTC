$("#order-history-submit").click(function(){
	setTableHistoryOrder("api/user_dash/history");
});

$("#current-order-submit").click(function(){
	document.location.href = "createOrderTest.jsp";
});

function setTableHistoryOrder(url) {
	$.ajax({
		method: 'POST',
		url: url,
		dataType: 'text',
		success: function (data, textStatus, jqXHR) {
			var obj = JSON.parse(data);
			alert(data);
			drawTable(obj.orderHistory ,"#historyOrderTable")
		},
		error: function (jqXHR, textStatus, errorThrown) {
			alert("Bad response from server");
		}
	})
};
//draw table
function drawTable(data, table) {
	for (var i = 0; i < data.length; i++) {
		drawBody(data[i], table);
	}
}
function drawBody(rowData, table) {
	var row = $("<tbody><tr>")
	$(table).append(row);
	row.append($("<td></td>"));
	row.append($("<td>" + rowData.startOrder + "</td>"));
	row.append($("<td>" + rowData.endOrder + "</td>"));
	row.append($("<td>" + rowData.price + "</td></tr></tbody>"));
}