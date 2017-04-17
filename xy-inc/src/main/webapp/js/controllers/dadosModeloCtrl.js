angular.module("xy").controller("dadosModeloCtrl", function ($scope, $route, $location, xyAPI) {
   $scope.nomeModelo = $route.current.params.nomeModelo;
   $scope.message = '';
   $scope.estrutura = [
     {
       chave: null,
       valor: null,
     }
   ]

   var _editarDocumento = function(){
     if($route.current.params.id){
       xyAPI.getById($route.current.params.nomeModelo, $route.current.params.id)
          .then(function(result){
            var template = [
            ];
            var keys = Object.keys(result.data.data);
            keys.forEach(function(item){
              template.push({chave: item, valor: result.data.data[item]});
            });
            $scope.estrutura = template;
            $scope.editar = true;
          },function(data){
             $scope.message = data.data.error;
          })
       }
   }

   var _getAll = function(nomeModelo){
      xyAPI.getAll(nomeModelo)
         .then(function(data){
            $scope.allData = data.data;
         },function(data){
             $scope.message = data.data.error;
         });
   };

   $scope.delete = function(nomeModelo, id) {
      xyAPI.deleteModelValue(nomeModelo, id)
         .then(function(data){
            _getAll(nomeModelo);
         },function(data){
             $scope.message = data.data.error;
         });
   };

   $scope.salvar = function(nomeModelo, estrutura, editar){
     var postJson = {
     }
     estrutura.forEach(function(item, index, arr){
         if(item.chave){
             postJson[item.chave] = item.valor;
         }
     });


     if(editar){

       xyAPI.update(nomeModelo, postJson, $route.current.params.id)
          .then(function(data){
              $location.path("/detalheModelo/" + nomeModelo);
          },function(data){
             $scope.message = data.data.error;
          });
     }else{
       xyAPI.save(nomeModelo, postJson)
          .then(function(data){
              $location.path("/detalheModelo/" + nomeModelo);
          },function(data){
             $scope.message = data.data.error;
          });
      }
   }

   $scope.adicionarLinha = function(estrutura) {
     estrutura.push({chave: null, valor: null});
   }

   _getAll($route.current.params.nomeModelo);
   _editarDocumento();
});
