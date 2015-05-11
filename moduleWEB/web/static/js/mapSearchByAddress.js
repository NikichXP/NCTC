/**
 * Created by Ann on 09.05.2015.
 */
var geoResult;
//var rootURL = "http://localhost:8085/TestMaps_war_exploded/map.html";
//var coord;

// Дождёмся загрузки API и готовности DOM.
    ymaps.ready(init);



function init () {
    // Создание экземпляра карты и его привязка к контейнеру с
    // заданным id ("map").
    var myMap = new ymaps.Map('map', {
        // При инициализации карты обязательно нужно указать
        // её центр и коэффициент масштабирования.
        center: [55.76, 37.64], // Москва
        zoom: 10
    });

ymaps.geocode('Киев', {
    /**
     * Опции запроса
     * @see https://api.yandex.ru/maps/doc/jsapi/2.1/ref/reference/geocode.xml
     */
    // boundedBy: myMap.getBounds(), // Сортировка результатов от центра окна карты
    // strictBounds: true, // Вместе с опцией boundedBy будет искать строго внутри области, указанной в boundedBy
    results: 1 // Если нужен только один результат, экономим трафик пользователей
}).then(function (res) {
    // Выбираем первый результат геокодирования.
    var firstGeoObject = res.geoObjects.get(0),
    // Координаты геообъекта.
        coords = firstGeoObject.geometry.getCoordinates(),
    // Область видимости геообъекта.
        bounds = firstGeoObject.properties.get('boundedBy');
    console.log(coords);

    // Добавляем первый найденный геообъект на карту.
    myMap.geoObjects.add(firstGeoObject);
    // Масштабируем карту на область видимости геообъекта.
    myMap.setBounds(bounds, {
        checkZoomRange: true // проверяем наличие тайлов на данном масштабе.
    });

    /**
     * Все данные в виде javascript-объекта.
     */
    console.log("er Все данные геообъекта: ", firstGeoObject.properties.getAll());
    /**
     * Метаданные запроса и ответа геокодера.
     * @see https://api.yandex.ru/maps/doc/geocoder/desc/reference/GeocoderResponseMetaData.xml
     */
    console.log('Метаданные ответа геокодера: ', res.metaData);
    /**
     * Метаданные геокодера, возвращаемые для найденного объекта.
     * @see https://api.yandex.ru/maps/doc/geocoder/desc/reference/GeocoderMetaData.xml
     */
    console.log('Метаданные геокодера: ', firstGeoObject.properties.get('metaDataProperty.GeocoderMetaData'));
    /**
     * Точность ответа (precision) возвращается только для домов.
     * @see https://api.yandex.ru/maps/doc/geocoder/desc/reference/precision.xml
     */
    console.log('precision', firstGeoObject.properties.get('metaDataProperty.GeocoderMetaData.precision'));
    /**
     * Тип найденного объекта (kind).
     * @see https://api.yandex.ru/maps/doc/geocoder/desc/reference/kind.xml
     */
    console.log('Тип геообъекта: %s', firstGeoObject.properties.get('metaDataProperty.GeocoderMetaData.kind'));
    console.log('Название объекта: %s', firstGeoObject.properties.get('name'));
    console.log('Описание объекта: %s', firstGeoObject.properties.get('description'));
    console.log('Полное описание объекта: %s', firstGeoObject.properties.get('text'));
});
}

function showAddress (value) {
    //var map = document.getElementById("map").id;
    // Удаление предыдущего результата поиска
    myMap.removeOverlay(geoResult);

    // Запуск процесса геокодирования
    var geocoder = new ymaps.Geocoder(value, {results: 1, boundedBy: myMap.getBounds()});

    // Создание обработчика для успешного завершения геокодирования
    YMaps.Events.observe(geocoder, geocoder.Events.Load, function () {
        // Если объект был найден, то добавляем его на карту
        // и центрируем карту по области обзора найденного объекта
        if (this.length()) {
            geoResult = this.get(0);
            myMap.addOverlay(geoResult);
            myMap.setBounds(geoResult.getBounds());
        } else {
            alert("Ничего не найдено")
        }
    });
}

