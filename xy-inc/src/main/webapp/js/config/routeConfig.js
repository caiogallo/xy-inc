angular.module("xy").config(function ($routeProvider) {
   $routeProvider
      .when("/modelos", {
         templateUrl: "partials/modelos.html",
         controller: "modeloCtrl"
      })
      .when("/detalheModelo/:nomeModelo", {
         templateUrl: "partials/detalhesModelo.html",
         controller: "dadosModeloCtrl"
      })
      .when("/novoDadosModelo/:nomeModelo", {
         templateUrl: "partials/novoDadosModelo.html",
         controller: "dadosModeloCtrl"
      })
      .when("/editaDadosModelo/:nomeModelo/:id", {
         templateUrl: "partials/novoDadosModelo.html",
         controller: "dadosModeloCtrl"
      })
      .otherwise({redirectTo: "/modelos"});
});
