/**
 * Created by Ann on 09.05.2015.
 */
var myMap;
var myPath;
//var rootURL = "http://localhost:8085/TestMaps_war_exploded/map.html";
//var coord;

// Дождёмся загрузки API и готовности DOM.
ymaps.ready(init);


function init() {
    // Создание экземпляра карты и его привязка к контейнеру с
    // заданным id ("map").
    var geolocation = ymaps.geolocation;
    myMap = new ymaps.Map('map', {
        // При инициализации карты обязательно нужно указать
        // её центр и коэффициент масштабирования.
        center: [50.45,30.52], // Киев
        zoom: 11
    });
    geolocation.get({
        provider: 'browser',
        mapStateAutoApply: true
    }).then(function (result) {
        // Синим цветом пометим положение, полученное через браузер.
        // Если браузер не поддерживает эту функциональность, метка не будет добавлена на карту.
        result.geoObjects.options.set('preset', 'islands#blueCircleIcon');
        myMap.geoObjects.add(result.geoObjects);
        var coords=result.geoObjects.position;
        document.getElementById("fromX").value=coords[0];
        document.getElementById("fromY").value = coords[1];
        var firstGeoObject=result.geoObjects.get(0);
        document.getElementById("fromAddress").value=firstGeoObject.properties.get('text');
    }, function(e){
        alert("Couldn't detect your location");
    });
};

function makeSearch(element) {
    alert(element.value + " " + element.id);
    var myGeocoder = ymaps.geocode(element.value, {
        /**
         * Опции запроса
         * @see https://api.yandex.ru/maps/doc/jsapi/2.1/ref/reference/geocode.xml
         */
        // boundedBy: myMap.getBounds(), // Сортировка результатов от центра окна карты
        // strictBounds: true, // Вместе с опцией boundedBy будет искать строго внутри области, указанной в boundedBy
        results: 1 // Если нужен только один результат, экономим трафик пользователей
    });
    myGeocoder.then(function (res) {
        // Выбираем первый результат геокодирования.
        var firstGeoObject = res.geoObjects.get(0);
        // Координаты геообъекта.
        var coords = firstGeoObject.geometry.getCoordinates();
        // Область видимости геообъекта.
        //var bounds = firstGeoObject.properties.get('boundedBy');
        if (element.id.indexOf("from") == 0) {
            document.getElementById("fromX").value = coords[0];
            document.getElementById("fromY").value = coords[1];
        }
        else {
            document.getElementById("toX" + element.id.slice(-1)).value = coords[0];
            document.getElementById("toY" + element.id.slice(-1)).value = coords[1];
        }

        // Добавляем первый найденный геообъект на карту.
        myMap.geoObjects.add(firstGeoObject);
        // Масштабируем карту на область видимости геообъекта.
        //myMap.setBounds(bounds, {
        //    checkZoomRange: true // проверяем наличие тайлов на данном масштабе.
        //});
    });
    if (document.getElementById('fromAddress').value != '' && document.getElementById('toAddress0').value != '') {
        if (element.id != "fromAddress") {
            buildPath(element.id.slice(-1));
        } else buildPath(0);
    }
    return false;
}

function buildPath(index) {
    myMap.geoObjects.removeAll();
    myMap.geoObjects.each(function (geoObject) {
        myMap.geoObjects.remove(geoObject);
        return false;
    });
    var pointsArray = [];
    pointsArray.push(document.getElementById('fromAddress').value);

    for(var i = 0; i <= index; i++) {
        pointsArray.push(document.getElementById('toAddress' + i).value);
        alert(pointsArray);
    }
    myPath= new ymaps.route(
        pointsArray
    , {
        // Router options
        mapStateAutoApply: true // automatically position the map
    }).then(function (route) {
        myMap.geoObjects.add(route);
            var Paths=route.getPaths();
            for(var i = 0; i <= Paths.getLength(); i++) {
                document.getElementById('distance'+i).value=Paths.get(i).getLength();
                console.log(Paths.get(i).getLength());
            }
            //console.log(Paths);
        document.getElementById('totalLength').value=route.getLength();
    }, function (error) {
        alert("An error occurred: " + error.message);
    });
    return false;
}
