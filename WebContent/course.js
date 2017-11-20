var courseId;
var userId;
var coursetagId;

var app = angular.module("myApp", ["ngRoute"]);
app.config(function($routeProvider) {
	$routeProvider.when("/", {
		templateUrl : "login.html",
		controller : "loginCtrl"
		

	})
	.when("/mainInstructor", {
		templateUrl : "mainInstructor.html",
		controller : "instracturCtrl"

	})
	 
	.when("/mainStudent", {
		templateUrl : "mainStudent.html",
		controller : "studentCtrl"
			
	})
	
	.when("/coursepage", {
		templateUrl : "coursepage.html",
		controller : "coursepage"

	})

	
})
app.controller("loginCtrl", function($scope, $http, $location) {
	$http.post("http://localhost:8080/coursemanagement/SPALoginServlet?logout=logout")
	.then(function(response) {
		console.log(response.data);
		
	});
	$scope.getuser = function() {
		$http.get("http://localhost:8080/coursemanagement/rest/user/getUser?username="+$scope.username+"&password="+$scope.password)
				.then(function(response) {
					
					console.log(response.data);
					  userId=response.data.id;
					  console.log(userId)
				 	  if (response.data.type == "s") {
				 		 $http.post("http://localhost:8080/coursemanagement/SPALoginServlet?userid="+response.data.id)
							.then(function(response) {
								console.log(response.data);
							});
				 		   $location.path("/mainStudent");
						 
					 };  
					 
					 if (response.data.type == "i") {
						 $http.post("http://localhost:8080/coursemanagement/SPALoginServlet?userid="+response.data.id)
							.then(function(response) {
								console.log(response.data);
							});
						    $location.path("/mainInstructor");
							 
					 }; 
					 
					 if (response.data.type == "m") {
						 $http.post("http://localhost:8080/coursemanagement/SPALoginServlet?userid="+response.data.id)
							.then(function(response) {
								console.log(response.data);
							});
						   $location.path("/mainManager");
								 
					 };  
				});
		$scope.forgotpassword = function (){
			 $(".hidebtn").hide();
			$(".forgotpassword").show();
		};
			$scope.send = function (){ 
			$http.get("http://localhost:8080/coursemanagement/rest/user/forgotPassword?username="+$scope.username)
			 .then(function(response) {
			 
				 
			});
		};
		
	};
	});

app.controller("studentCtrl", function($scope, $http, $location) {
	$(".tagcourse").hide();
	$(".studentCoursesdiv").hide();
	$(".allavilableCourse").show();
	$http.post("http://localhost:8080/coursemanagement/SPALoginServlet")
	.then(function(response) {
		$scope.userid=response.data;
	   if($scope.userid=="failed"){
	    	alert("login first");
	    	  $location.path("/");
	    }else{
	$http.get("http://localhost:8080/coursemanagement/rest/course/getAvileableCourses")
	.then(function(response) {
		$scope.getAvileableCourses = response.data;
		console.log($scope.getAvileableCourses);
		
		 courseId = response.data.id;
		
	});
	    };
		
	});
	$scope.showStudentsCourses = function (){
	
		$(".tagcourse").hide();
		$(".studentCoursesdiv").show();
		$(".allavilableCourse").hide();
				$http.get("http://localhost:8080/coursemanagement/rest/Usercourse/getCoursesBytUserId?userId="+$scope.userid)
				.then(function(response) {
					$scope.Studentcourses = response.data;
					console.log(response.data);
		
				});
			};
		
		$scope.redirect = function (selectedcourse){
			courseId = selectedcourse;
			console.log(selectedcourse)
			   $http.post("http://localhost:8080/coursemanagement/SPALoginServlet?courseid="+selectedcourse)
			.then(function(response) {
				console.log(response.data);
			});
			 $location.path("/coursepage");
		 };
		$scope.getCoursesByTagCourse = function (selectedcourse){
			coursetagId = selectedcourse;
			
			$http.get("http://localhost:8080/coursemanagement/rest/course/getCoursesByTagCourse?coursetagId="+coursetagId)
			.then(function(response) {
				$scope.tagcourse = response.data;
				$(".tagcourse").show();
				$(".studentCoursesdiv").hide();
				$(".allavilableCourse").hide();
				console.log(response.data);
			});
			
		};
	
});
		app.controller("coursepage", function($scope, $http, $location) {
			$http.post("http://localhost:8080/coursemanagement/SPALoginServlet")
			.then(function(response) {
				$scope.userid=response.data;
			   if($scope.userid=="failed"){
			    	alert("login first");
			    	  $location.path("/");
			    }else{
		    $(document).ready(function (){
		        $("#GeneralClick").click(function (){
		            $('html, body').animate({
		                scrollTop: $("#General").offset().top
		            }, 1000);
		        });
		        $("#MessageBoardClick").click(function (){
		            $('html, body').animate({
		                scrollTop: $("#MessageBoard").offset().top
		            }, 1000);
		        });
		        $("#ScheduleClick").click(function (){
		            $('html, body').animate({
		                scrollTop: $("#Schedule").offset().top
		            }, 1000);
		        });
		        $("#PresentationsClick").click(function (){
		            $('html, body').animate({
		                scrollTop: $("#Presentations").offset().top
		            }, 1000);
		        });
		        $("#ResourcesClick").click(function (){
		            $('html, body').animate({
		                scrollTop: $("#Resources").offset().top
		            }, 1000);
		        });

		    });
		    $http.post("http://localhost:8080/coursemanagement/SPALoginServlet?getcourseid")
			.then(function(response) {
				$scope.courseid=response.data;
		
			$http.get("http://localhost:8080/coursemanagement/rest/course/GetCourseById?courseId="+$scope.courseid)
			.then(function(response) {
				$scope.coursesInfo = response.data;
				console.log(response.data);
				
				$http.get("http://localhost:8080/coursemanagement/rest/Coursetag/getCoursetagByCourseId?courseId="+$scope.courseid)
				.then(function(response) {
					$scope.taglist = response.data;
				});
				$http.get("http://localhost:8080/coursemanagement/rest/instructor/getInstructorByCourseId?courseId="+$scope.courseid)
				.then(function(response) {
					$scope.instructors = response.data;
				});
				
				
			});
			});
			    };
				
			});
			$scope.register = function (){
				$http.get("http://localhost:8080/coursemanagement/rest/Usercourse/createUsercourse?course="+$scope.courseid+"&user="+$scope.userid)
				.then(function(response) {
					var reply = response.data;
					console.log(response.data);
					 if (reply.id == 0){ 
						 alert("register succedded");
					 }else{
						 alert("register faild");
					 }
					console.log(userId)
				});
				
			};
		
	});
 		app.controller("instracturCtrl", function($scope, $http, $location) {
 			$(".tagcourse").hide();
 			$(".studentCoursesdiv").hide();
 			$(".allavilableCourse").show();
			$http.post("http://localhost:8080/coursemanagement/SPALoginServlet")
			.then(function(response) {
				$scope.userid=response.data;
			   if($scope.userid=="failed"){
			    	alert("login first");
			    	  $location.path("/");
			    }else{
			$http.get("http://localhost:8080/coursemanagement/rest/course/getAvileableCourses")
			.then(function(response) {
				$scope.getAvileableCourses = response.data;
				console.log($scope.getAvileableCourses);			
			});
			$http.get("http://localhost:8080/coursemanagement/rest/Coursetag/getall")
			.then(function(response) {
				$scope.taglist = response.data;
				console.log(response.data);
			});
		};
		$scope.showStudentsCourses = function (){
			
			$(".tagcourse").hide();
			$(".studentCoursesdiv").show();
			$(".allavilableCourse").hide();
					$http.get("http://localhost:8080/coursemanagement/rest/Usercourse/getCoursesBytUserId?userId="+$scope.userid)
					.then(function(response) {
						$scope.Studentcourses = response.data;
						console.log(response.data);
			
					});
					$http.get("http://localhost:8080/coursemanagement/rest/Coursetag/getCoursetagByCourseId?courseId="+$scope.courseid)
					.then(function(response) {
						$scope.taglist = response.data;
					});
				};
			
			$scope.redirect = function (selectedcourse){
				courseId = selectedcourse;
				console.log(selectedcourse)
				   $http.post("http://localhost:8080/coursemanagement/SPALoginServlet?courseid="+selectedcourse)
				.then(function(response) {
					console.log(response.data);
				});
				 $location.path("/coursepage");
			 };
			$scope.getCoursesByTagCourse = function (selectedcourse){
				coursetagId = selectedcourse;				
				$http.get("http://localhost:8080/coursemanagement/rest/course/getCoursesByTagCourse?coursetagId="+coursetagId)
				.then(function(response) {
					$scope.tagcourse = response.data;
					$(".tagcourse").show();
					$(".studentCoursesdiv").hide();
					$(".allavilableCourse").hide();
					console.log(response.data);
				});
				
			};
		       });			
		});
 