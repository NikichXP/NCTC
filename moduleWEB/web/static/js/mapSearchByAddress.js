/**
 * Created by Ann on 09.05.2015.
 */
var myMap;
var myPath;
//var rootURL = "http://localhost:8085/TestMaps_war_exploded/map.html";
//var coord;

// Дождёмся загрузки API и готовности DOM.
    ymaps.ready(init);



function init () {
    // Создание экземпляра карты и его привязка к контейнеру с
    // заданным id ("map").
    myMap = new ymaps.Map('map', {
        // При инициализации карты обязательно нужно указать
        // её центр и коэффициент масштабирования.

        center: [50.450097, 30.523397], // Киев
        zoom: 10
    });
}

function makeSearch(value, resultx,resulty){
   // var value = document.getElementById("fromAddress0").value;
    ymaps.geocode(value, {
        /**
         * Опции запроса
         * @see https://api.yandex.ru/maps/doc/jsapi/2.1/ref/reference/geocode.xml
         */
        // boundedBy: myMap.getBounds(), // Сортировка результатов от центра окна карты
        // strictBounds: true, // Вместе с опцией boundedBy будет искать строго внутри области, указанной в boundedBy
        results: 1 // Если нужен только один результат, экономим трафик пользователей
    }).then(function (res) {
        // Выбираем первый результат геокодирования.
        var firstGeoObject = myMap.geoObjects.get(0),
        // Координаты геообъекта.
            coords = firstGeoObject.geometry.getCoordinates(),
        // Область видимости геообъекта.
            bounds = firstGeoObject.properties.get('boundedBy');
        resultx.value=parseFloat(coords[0]);
        resulty.value=parseFloat(coords[1]);

        // Добавляем первый найденный геообъект на карту.
        myMap.geoObjects.add(firstGeoObject);
        // Масштабируем карту на область видимости геообъекта.
        myMap.setBounds(bounds, {
            checkZoomRange: true // проверяем наличие тайлов на данном масштабе.
        });
    });
    if (document.getElementById('fromAddress').value!='' && document.getElementById('toAddress0').value!=''){
        buildPath();
    }
    return false;
}

function buildPath(){
    myMap.geoObjects.each(function (geoObject) {
           myMap.geoObjects.remove(geoObject);
            return false;
    });
    var fromPoint=document.getElementById('fromAddress').value;
    var toPoint=document.getElementById('toAddress0').value;
    myPath= new ymaps.route([
        fromPoint,
        toPoint
    ], {
        // Router options
        mapStateAutoApply: true // automatically position the map
    }).then(function (route) {
        myMap.geoObjects.add(route);
        document.getElementById('totalLength').value=route.getLength();
    }, function (error) {
        alert("An error occurred: " + error.message);
    });

    return false;
}
