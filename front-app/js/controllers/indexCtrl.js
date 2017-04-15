angular.module("xy").controller("indexCtrl", function ($scope, xyAPI) {

   xyAPI.getModels()
      .then(function(data){
         $scope.modelos = data.data;
      },function(data){
         console.log(data)
      });


});
