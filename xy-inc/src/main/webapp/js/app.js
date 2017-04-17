angular.module("xy", ["ngRoute"]);

angular.module("xy").config(['$locationProvider', function($locationProvider) {
  $locationProvider.hashPrefix('');
}]);
