(function() {
    'use strict';

    angular.module('taxiApp.controllers',[]).
    controller('CreateOrderController', ['$scope', '$http', '$location', function($scope, $http, $location){
        $scope.order = {
            'phone': '',
            'gender': 'any'
        };

        $scope.createOrder = function() {
            if (!$scope.order.phone.toString().match(/^\+\d{12}$/g)) {
                return;
            }
            console.log($scope.order);
//            $http.post('api/order/create', JSON.stringify($scope.order))
//            .success(function(order) {
//                $location.path('order' + order.id);
//            });
            $.ajax({
                 type: "POST",
                 url: "api/order/create",
                 contentType: "application/json; charset=utf-8",
                 data: JSON.stringify($scope.order),
                 dataType: "json",
                 success: function (result) {
                    alert(JSON.stringify(result));
                    $location.path('home');
                 },
                 error: function (jqXHR, textStatus, errorThrown) {
                     alert(jqXHR.status + ' ' + jqXHR.responseText);
                 }
             });
        };
    }])
})();
