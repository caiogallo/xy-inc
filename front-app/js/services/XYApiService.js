angular.module("xy").factory("xyAPI", function($http, config){

   var _getModels = function(){
      return $http.get(config.baseUrl + "models/v1");
   };

   var _getAll = function(model){
      return $http.get(config.baseUrl + "v1/" +model);
   };

   return {
      getAll: _getAll,
      getModels: _getModels
   };

});
