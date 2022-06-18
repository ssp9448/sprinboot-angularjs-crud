app.config(function($routeProvider) {
    $routeProvider
    .when("/", {
      templateUrl : "/pages/main.html"
    })
    .when("/about", {
      templateUrl : "/pages/about.html"
    }).when("/add", {
      templateUrl : "/pages/add.html"
    }).when("/contact", {
        templateUrl : "/pages/contact.html"
    }).when("/error", {
      templateUrl : "/pages/error.html"
    }).otherwise({redirectTo:'/error'})
  });
  app.controller('students', function($rootScope,$window,$scope, $http,myservice) {
    $rootScope.msg="";
    $http({
      method : "GET",
        url : "http://localhost:8080/"
    }).then(function mySuccess(response) {
      $scope.users = response.data;
    }, function myError(response) {
      $scope.users = response;
    });
    
    $scope.deleteStudent = function(std) {
      $http({
          method: "DELETE",
          url: 'http://localhost:8080/' + std.id
      }).then(function mySuccess(response){
           msg="Delete Successfully !!!"
           $window.location.href="/index.html";
      },function myError(response) {
            $scope.data = response.data;
        $scope.status = response.status;
      });
    }; 
    $scope.editStudent = function(std) {
    $http({
      method : "GET",
        url : "http://localhost:8080/"+std.id
    }).then(function mySuccess(response) {
      myservice.registerForm = response.data;
      
    }, function myError(response) {
         msg = response;
    });
};

  });
  app.service('myservice', function() {
      this.registerForm = {
        id: -1,
        name: "",
        email: "",
        dob:""
      }
    });
  app.controller('registerCtrl', function($window,$scope, $http,myservice) {

// Now load the data from myservice
$scope.myservice=myservice;

// HTTP POST/PUT methods for add/edit student  
$scope.submitStudent = function() {

    var method = "";
    var url = "";

    if (myservice.registerForm.id == -1) {
        method = "POST";
        url = 'http://localhost:8080/create';
    } else {
        method = "PUT";
        url = 'http://localhost:8080/'+myservice.registerForm.id;
    }
    console.log(angular.toJson(myservice.registerForm))
    $http({
        method: method,
        url: url,
        data: angular.toJson(myservice.registerForm),
        headers: {
            'Content-Type': 'application/json'
        }
      }).then(function mySuccess(response){
       $scope.msg="Saved Successfully !!!"
       $window.location.href="/";
   },function myError(response) {
    $scope.data = response.data;
    $scope.status = response.status;
   });
};
});
  app.controller('headerCtrl', function($scope, $location) {
    $scope.websiteName= "BVRSOFTWARE";
    $scope.mycUrl=$location.absUrl()
  });
  
app.controller('footerCtrl', function($scope) {
    $scope.websitelink = "bvrsoftware.com";
  });
