$('#order-history-submit').click(function(){
	JSONdata = {
		startOrder: "Start",
		endOrder: "End",
		price: "0"
	};
	setTableHistoryOrder(JSON.stringify(JSONdata), "api/user_dash/history");
});

//$('#current-order-submit').click(function(){
//	JSONdata = {
//		firstName: $("#firstName").val(),
//		lastName: $("#lastName").val(),
//		phone: $("#phone").val(),
//		email: $("#email").val(),
//		pass: $("#reg-pass").val()
//	};
//	setTableCurrentOrder(JSON.stringify(JSONdata), "api/userdashboard/update");
//});
//
//$('#setting-submit').click(function(){
//	settings("api/user/create");
//});
//
//function setTableCurrentOrder(userDasboardData, url) {
//	$.ajax({
//		method: 'POST',
//		url: url,
//		contentType: "application/json; charset=utf-8",
//		data: userData,
//		dataType:'text',
//		success: function (data,textStatus,jqXHR ) {
//			//alert(textStatus + " " + jqXHR.responseText);
//			document.cookie = "uuid="+jqXHR.responseText;
//			alert("uuid="+jqXHR.responseText);
//			uuid = jqXHR.responseText;
//			getAccessLevels(uuid);
//		},
//		error: function (jqXHR, textStatus, errorThrown) {
//			alert(jqXHR.status + ' ' + jqXHR.responseText);
//			//alert(eval("(" + data + ")"));
//		}
//	})
//}

function setTableHistoryOrder(userDasboardData, url) {
	$.ajax({
		method: 'POST',
		url: url,
		contentType: "application/json; charset=utf-8",
		data: userDasboardData,
		dataType:'text',
		success: function (data,textStatus,jqXHR ) {
			drawTable(data, "#historyOrderTable")
		},
		error: function (jqXHR, textStatus, errorThrown) {
			alert(jqXHR.status + ' ' + jqXHR.responseText);
			//alert(eval("(" + data + ")"));
		}
	})
}

//function settings(url) {
//	$.ajax({
//		method: 'POST',
//		url: url,
//		contentType: "application/json; charset=utf-8",
//		data: userData,
//		dataType:'text',
//		success: function (data,textStatus,jqXHR ) {
//			//alert(textStatus + " " + jqXHR.responseText);
//			document.cookie = "uuid="+jqXHR.responseText;
//			alert("uuid="+jqXHR.responseText);
//			uuid = jqXHR.responseText;
//			getAccessLevels(uuid);
//		},
//		error: function (jqXHR, textStatus, errorThrown) {
//			alert(jqXHR.status + ' ' + jqXHR.responseText);
//			//alert(eval("(" + data + ")"));
//		}
//	})
//}


//draw table
function drawTable(data, table) {
	for (var i = 0; i < data.length; i++) {
		drawBody(data[i], table);
	}
}
function drawBody(rowData, table) {
	var row = $("<tbody><tr>")
	$(table).append(row);
	row.append($("<td>" + rowData.startOrder + "</td>"));
	row.append($("<td>" + rowData.endOrder + "</td>"));
	row.append($("<td>" + rowData.price + "</td></tr></tbody>"));
}