angular.module("xy").factory("xyAPI", function($http, config){

   var _getModels = function(){
      return $http.get(config.baseUrl + "models/v1");
   };

   var _getAll = function(model){
      return $http.get(config.baseUrl + "v1/" +model);
   };

   var _deleteModelValue = function(model, id){
      return $http.delete(config.baseUrl + "v1/" + model + "/" + id);
   };

   var _save = function(model, line){
      return $http.post(config.baseUrl + "v1/" + model, line);
   };

   var _update = function(model, line, id){
      return $http.put(config.baseUrl + "v1/" + model + "/" + id, line);
   };

   var _getById = function(model, id){
     return $http.get(config.baseUrl + "v1/" + model + "/" + id);
   }

   return {
      save: _save,
      update: _update,
      getAll: _getAll,
      deleteModelValue: _deleteModelValue,
      getById: _getById,
      getModels: _getModels
   };

});
