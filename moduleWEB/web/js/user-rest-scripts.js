$("#setting-submit").click(function(){
	var JSONdata = {
		uuid: getCookie('uuid')
	};
	setUserData(JSON.stringify(JSONdata), "api/user/cookie_user");
});
$("#order-history-submit").click(function(){
	setTableHistoryOrder("api/user_dash/history");
});

$("#create-order-submit").click(function(){
	document.location.href = "createOrder.html";
});

function setTableHistoryOrder(url) {
	$.ajax({
		method: 'POST',
		url: url,
		dataType: 'text',
		success: function (data, textStatus, jqXHR) {
			var obj;
			obj = JSON.parse(data);
			drawTable(obj.orderHistory, "#historyOrderTable");
		},
		error: function (jqXHR, textStatus, errorThrown) {
			alert("Bad response from server");
		}
	})
}
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

function setUserData(userData, url){
	var uuid = getCookie("uuid");
	$.ajax({
		method: 'POST',
		url: 'api/user/cookie_user',
		contentType: "text/plain; charset=utf-8",
		data: uuid,
		dataType: 'text',
		success: function (data, textStatus, jqXHR) {
			var obj = JSON.parse(data);
			var str = "";
			var index = 0;
			for (var i = 0; i < obj.userAccessLevel.length; i++) {
				str = str + '<a href="' + obj.userAccessLevel[i].level
					+ '.html">Login as: '
					+ obj.userAccessLevel[i].level
					+ '</a>' + '<br>';
				index++;
			}
			if (index == 1) {
				document.location.href = obj.userAccessLevel[0].level + '.html';
			} else {
				document.getElementById("accessLevel").innerHTML = str;
			}
		},
		error: function (jqXHR, textStatus, errorThrown) {
			alert(uuid + " Error!");
		}
	})
}

function getCookie(name) {
	var value = "; " + document.cookie;
	var parts = value.split("; " + name + "=");
	if (parts.length == 2) return parts.pop().split(";").shift();
}
