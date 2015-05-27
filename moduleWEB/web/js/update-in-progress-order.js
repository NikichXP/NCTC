var id;

$(document).ready(function () {
    var driverUuid = getCookie('uuid');
    $.ajax({
        method: 'POST',
        url: 'api/order/getOrderInProgressByDriverUUID',
        contentType: "application/json; charset=utf-8",
        data: driverUuid,
        dataType: 'text',
        success: function (data, textStatus, jqXHR) {
            id = data;
            ymaps.ready(function () {
                init();
                fillPageWithData()
            });
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert('You have no "in progress" order.');
            document.location.href = "driver.html";
        }
    });
    $('body').click(updatePrice);
    $('#addPathPoint').click(addPathPoint);
    $('#removeCurrentPath').click(removeCurrentPath);
    $("#submitUpdate").click(submitUpdate);
    $("#revertUpdate").click(fillPageWithData);
    $('#completeCurrentPath').click(completeCurrentPath);
    $('#completeOrder').click(completeOrder);
    $('#refuseOrder').click(refuseOrder);
});

function refuseOrder() {
    disableAllButtons();
    $.ajax({
        method: 'POST',
        url: 'api/order/refuseOrder',
        contentType: "text/plain",
        data: id,
        dataType: 'text',
        success: function (data, textStatus, jqXHR) {
            alert(data);
            document.location.href = "driver.html";
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert("Error occurred, order wasn't refused");
        }
    })
}

function completeOrder() {
    disableAllButtons();
    $.ajax({
        method: 'POST',
        url: 'api/order/completeOrder',
        contentType: "text/plain",
        data: id,
        dataType: 'text',
        success: function (data, textStatus, jqXHR) {
            alert(data);
            document.location.href = "driver.html";
        },
        error: function (jqXHR, textStatus, errorThrown) {
            fillPageWithData();
        }
    })
}

function removeCurrentPath() {
    disableAllButtons();
    var currentPathIndex = getCurrentPathIndex();
    $("input[id$='" + currentPathIndex + "']").remove();
    var index = currentPathIndex;
    while ($("input[id$='" + (index + 1) + "']").length > 0) {
        var allPointInputs = $("input[id$='" + (index + 1) + "']");
        for (var i = 0; i < allPointInputs.length; i++) {
            var startId = allPointInputs.get(i).getAttribute("id");
            allPointInputs[i].setAttribute("id", startId.match(/\D+/) + index);
        }
        index++;
    }
    buildPath($("[id^='toAddress']").length - 1);
    setUnlock("#submitUpdate");
    setUnlock("#revertUpdate");
    setUnlock("#refuseOrder");
}

function completeCurrentPath() {
    disableAllButtons();
    var currentPathIndex = getCurrentPathIndex();
    $("#toAddress" + currentPathIndex).attr("disabled", "disabled");
    $("#pathCompleted" + currentPathIndex).attr("value", true);
    setUnlock("#submitUpdate");
    setUnlock("#revertUpdate");
    setUnlock("#refuseOrder");
}

function getCurrentPathIndex() {
    return parseInt($("[id^='toAddress']").filter($("[disabled!='disabled']")).attr("id").match(/\d+/));
}

function fillPageWithData() {
    $.get("api/order/view?id=" + id, function (data) {
        disableAllButtons();
        if ($("#fromY").nextAll().filter($("[type='text']")).length > 0) {
            $("#fromY").nextAll().filter($("[type='text']")).remove();
        }

        var obj = JSON.parse(data);
        $("#contactName").attr("value", obj.contactName);
        $("#contactPhone").attr("value", obj.contactPhone);

        $("#requestedSeatsCount").attr("value", obj.requestedSeatsCount);
        $("#fromAddress").attr("value", obj.fromAddress);
        $("#fromX").attr("value", obj.fromX);
        $("#fromY").attr("value", obj.fromY);

        var flag = false;
        for (var i = 0; i < obj.toAddress.length; i++) {
            var outer = document.getElementById("importantInfo");

            var input = document.createElement("input");
            if (obj.pathCompleted[i] == "true" || flag) input.setAttribute("disabled", "disabled")
            else flag = true;
            input.setAttribute("type", "text");
            input.setAttribute("id", "toAddress" + i);
            input.setAttribute("onchange", "clearToXY(this); makeSearch(this)");
            input.setAttribute("placeholder", "To address " + i);
            input.setAttribute("value", obj.toAddress[i]);

            var input2 = document.createElement("input");
            input2.setAttribute("hidden", "hidden");
            input2.setAttribute("type", "text");
            input2.setAttribute("id", "toX" + i);
            input2.setAttribute("value", obj.toX[i]);

            var input3 = document.createElement("input");
            input3.setAttribute("hidden", "hidden");
            input3.setAttribute("type", "text");
            input3.setAttribute("id", "toY" + i);
            input3.setAttribute("value", obj.toY[i]);

            var input4 = document.createElement("input");
            input4.setAttribute("disabled", "disabled");
            input4.setAttribute("type", "text");
            input4.setAttribute("id", "distance" + i);
            input4.setAttribute("value", obj.distance[i]);

            var input5 = document.createElement("input");
            input5.setAttribute("hidden", "hidden");
            input5.setAttribute("type", "text");
            input5.setAttribute("id", "pathId" + i);
            input5.setAttribute("value", obj.pathId[i]);

            var input6 = document.createElement("input");
            input6.setAttribute("hidden", "hidden");
            input6.setAttribute("type", "text");
            input6.setAttribute("id", "pathCompleted" + i);
            input6.setAttribute("value", obj.pathCompleted[i]);

            var button = document.getElementById("addPathPoint");

            outer.insertBefore(input, button);
            outer.insertBefore(input2, button);
            outer.insertBefore(input3, button);
            outer.insertBefore(input4, button);
            outer.insertBefore(input5, button);
            outer.insertBefore(input6, button);

            makeSearch(input);
            $("#toAddress" + i).change(function () {
                buildPath($("[id^='toAddress']").length - 1);
                disableAllButtons();
                setUnlock("#submitUpdate");
                setUnlock("#revertUpdate");
                setUnlock("#refuseOrder");
            });
        }

        $("#timeRequested").attr("value", obj.timeRequested);
        $("#timeOfDriverArrival").attr("value", obj.timeOfDriverArrival);

        $("#music").attr("value", obj.musicType);

        if (obj.smokingFriendly == "true") $('input:checkbox[id=smokingFriendly]').prop('checked', true);
        if (obj.animalFriendly == "true") $('input:checkbox[id=animalFriendly]').prop('checked', true);
        if (obj.wifi == "true") $('input:checkbox[id=wifi]').prop('checked', true);
        if (obj.airConditioner == "true") $('input:checkbox[id=airConditioner]').prop('checked', true);

        $("#totalMultiplier").attr("value", parseFloat(obj.totalMultiplier).toFixed(2));
        $("#totalLength").attr("value", parseFloat(obj.totalLength).toFixed(2));
        $("#totalPrice").attr("value", parseFloat(obj.totalPrice).toFixed(2));

        $("#customerPreCreateComment").text(obj.customerPreCreateComment);
        updatePrice();

        setUnlock("#addPathPoint");
        setUnlock("#refuseOrder");
        if (!isAllPathsCompleted()) {
            setUnlock("#completeCurrentPath");
            if ($("[id^='toAddress']").length > 1) {
                setUnlock("#removeCurrentPath");
            }
        } else {
            setUnlock("#completeOrder");
        }
    });
    updatePrice();
}

function submitUpdate() {
    disableAllButtons();

    var JSONdata = {
        id: id,
        driverUserUuid: getCookie("uuid"),

        fromAddress: $("#fromAddress").val(),
        fromX: $("#fromX").val(),
        fromY: $("#fromY").val(),
        toAddress: getArrayOfToAddresses("toAddress"),
        distance: getArrayOfToAddresses("distance"),
        toX: getArrayOfToAddresses("toX"),
        toY: getArrayOfToAddresses("toY"),
        pathsCompleted: $("[id^=pathCompleted]").filter($("[value=true]")).length,

        totalLength: $("#totalLength").val(),
        totalPrice: $("#totalPrice").val()
    };

    function getArrayOfToAddresses(idWithoutNumber) {
        var arr = [];
        for (var i = 0; i < $("[id^=" + idWithoutNumber + "]").length; i++) {
            arr[i] = $("#" + idWithoutNumber + i).val()
        }
        return arr;
    }

    if (!validateBasicOrderData()) {
        setUnlock("#submitUpdate");
        setUnlock("#revertUpdate");
        setUnlock("#refuseOrder");
        return;
    }
    $.ajax({
        method: 'POST',
        url: 'api/order/updateInProgress',
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(JSONdata),
        dataType: 'text',
        success: function (data, textStatus, jqXHR) {
            alert("Order successfully updated.");
            fillPageWithData();
            setUnlock("#addPathPoint");
            setUnlock("#refuseOrder");
            if (!isAllPathsCompleted()) {
                setUnlock("#completeCurrentPath");
                if ($("[id^='toAddress']").length > 1) {
                    setUnlock("#removeCurrentPath");
                }
            } else {
                setUnlock("#completeOrder");
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            $("#submitUpdate").prop('disabled', false);
            alert(jqXHR.responseText);
        }
    })
}

function validateBasicOrderData() {
    var counter = 0;
    if (!isAllPathsCompleted) counter = getCurrentPathIndex();
    if (getCookie("uuid").length != 36) {
        alert("Wrong uuid cookie");
        return false;
    }
    if ($("#toAddress" + counter).val().length == 0 || $("#toX" + counter).val().length == 0
        || $("#toY" + counter).val().length == 0) {
        alert("Select proper destination address.");
        return false;
    }
    if (!$("#totalLength").val() > 0 || !$("#totalMultiplier").val() > 0 || !$("#totalPrice").val() > 0) {
        alert("Bad totals.");
        return false;
    }
    return true;
}

function getCookie(name) {
    var value = "; " + document.cookie;
    var parts = value.split("; " + name + "=");
    if (parts.length == 2) return parts.pop().split(";").shift();
}

function addPathPoint() {
    var outer = document.getElementById("importantInfo");
    var lastIndex = $("[id^='toAddress']").filter($("[disabled='disabled']")).length;

    if (!isAllPathsCompleted()) {
        var indexOfInProgress = $("[id^='toAddress']").filter($("[disabled!='disabled']")).attr("id").match(/\d+/);

        for (var i = lastIndex; i >= indexOfInProgress; i--) {
            var input = document.getElementById("toAddress" + i);
            input.setAttribute("id", "toAddress" + (i + 1));
            input.setAttribute("placeholder", "To address " + (i + 1));
            input.setAttribute("disabled", "disabled");

            var input2 = document.getElementById("toX" + i);
            input2.setAttribute("id", "toX" + (i + 1));

            var input3 = document.getElementById("toY" + i);
            input3.setAttribute("id", "toY" + (i + 1));

            var input4 = document.getElementById("distance" + i);
            input4.setAttribute("id", "distance" + (i + 1));

            var input4 = document.getElementById("pathId" + i);
            input4.setAttribute("id", "distance" + (i + 1));
        }

        var addedInput = document.createElement("input");
        addedInput.setAttribute("type", "text");
        addedInput.setAttribute("id", "toAddress" + indexOfInProgress);
        addedInput.setAttribute("onchange", "clearToXY(this); makeSearch(this); buildPath(" + (parseInt(lastIndex) + 1) + ")");
        addedInput.setAttribute("placeholder", "To address " + indexOfInProgress);

        var addedInput2 = document.createElement("input");
        addedInput2.setAttribute("hidden", "hidden");
        addedInput2.setAttribute("type", "text");
        addedInput2.setAttribute("id", "toX" + indexOfInProgress);

        var addedInput3 = document.createElement("input");
        addedInput3.setAttribute("hidden", "hidden");
        addedInput3.setAttribute("type", "text");
        addedInput3.setAttribute("id", "toY" + indexOfInProgress);

        var addedInput4 = document.createElement("input");
        addedInput4.setAttribute("disabled", "disabled");
        addedInput4.setAttribute("type", "text");
        addedInput4.setAttribute("id", "distance" + indexOfInProgress);

        var lastToAddress = document.getElementById("toAddress" + (parseInt(indexOfInProgress) + 1));
        outer.insertBefore(addedInput, lastToAddress);
        outer.insertBefore(addedInput2, lastToAddress);
        outer.insertBefore(addedInput3, lastToAddress);
        outer.insertBefore(addedInput4, lastToAddress);
    }
    else {
        var addedInput = document.createElement("input");
        addedInput.setAttribute("type", "text");
        addedInput.setAttribute("id", "toAddress" + lastIndex);
        addedInput.setAttribute("onchange", "clearToXY(this); makeSearch(this); buildPath(" + parseInt(lastIndex) + ")");
        addedInput.setAttribute("placeholder", "To address " + lastIndex);

        var addedInput2 = document.createElement("input");
        addedInput2.setAttribute("hidden", "hidden");
        addedInput2.setAttribute("type", "text");
        addedInput2.setAttribute("id", "toX" + lastIndex);

        var addedInput3 = document.createElement("input");
        addedInput3.setAttribute("hidden", "hidden");
        addedInput3.setAttribute("type", "text");
        addedInput3.setAttribute("id", "toY" + lastIndex);

        var addedInput4 = document.createElement("input");
        addedInput4.setAttribute("disabled", "disabled");
        addedInput4.setAttribute("type", "text");
        addedInput4.setAttribute("id", "distance" + lastIndex);

        var button = document.getElementById("addPathPoint");
        outer.insertBefore(addedInput, button);
        outer.insertBefore(addedInput2, button);
        outer.insertBefore(addedInput3, button);
        outer.insertBefore(addedInput4, button);
    }
    disableAllButtons();
    setUnlock("#submitUpdate");
    setUnlock("#revertUpdate");
    setUnlock("#refuseOrder");
}

function disableAllButtons() {
    $("[type='button']").prop('disabled', true);
}

function setLock(name) {
    $(name).prop('disabled', true);
}

function setUnlock(name) {
    $(name).prop('disabled', false);
}

function isAllPathsCompleted() {
    return $("[id^='toAddress']").filter($("[disabled!='disabled']")).length == 0;
}

function clearToXY(element) {
    $("#toX" + element.id.slice(-1)).val("");
    $("#toY" + element.id.slice(-1)).val("");
}

function updatePrice() {
    var totalMultiplier = $("#totalMultiplier").val();
    $("#totalPrice").val(parseFloat(totalMultiplier * $("#totalLength").val()).toFixed(2));
}