angular.module("xy").value(function ($routeProvider) {
   $routeProvider.when("/modelos", {
    templateUrl: "partials/modelos.html",
    controller: "indexCtrl"
   });
   $routeProvider.otherwise({redirectTo: "/modelos"});
});
