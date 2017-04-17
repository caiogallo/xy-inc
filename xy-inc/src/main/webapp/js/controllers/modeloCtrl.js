angular.module("xy").controller("modeloCtrl", function ($scope, xyAPI) {
   xyAPI.getModels()
      .then(function(data){
         $scope.modelos = data.data;
      },function(data){
         $scope.message = data.data.error;
      });
});
