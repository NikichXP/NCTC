(function(window, document, $, undefined) {
    var myMap;
    ymaps.ready(init);
    $(function() {
        $('#from-select').selectize(['r', 'g', 'b', 'a']);
        $('#to-select').selectize();
    });

    function init () {
        var multiRoute = new ymaps.multiRouter.MultiRoute({
            referencePoints: ["Start", "End"]
        }, {
            editorMidPointsType: "via",
            editorDrawOver: false
        });

        var buttonEditor = new ymaps.control.Button({
            data: { content: "Режим редактирования" }
        });

        buttonEditor.events.add("select", function () {
            multiRoute.editor.start({
                addWayPoints: true,
                removeWayPoints: true
            });
        });

        buttonEditor.events.add("deselect", function () {
            multiRoute.editor.stop();
        });

        var myMap = new ymaps.Map('map', {
            center: [56.399625, 36.71120],
            zoom: 7,
            controls: [buttonEditor]
        }, {
            buttonMaxWidth: 300
        });

        myMap.geoObjects.add(multiRoute);
    }
}) (window, document, $);
