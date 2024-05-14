<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
*{
	margin:0;
	padding:0;
}
</style>
</head>
<body>
<!-- [ 영국 런던을 중심으로 구글지도 만들기 ] -->
<div style="position:relative; display:inline-block; width:700px; height: 1000px; background-color: white;"><p>지도</p></div>
<div id="googleMap" style="position:absolute; display:inline-block; width: 80%;height: 1000px;"></div>


</body>
<script>
   function myMap(){
      var mapOptions = { 
            center:new google.maps.LatLng(33.4889179032603,126.498229141199),
            zoom:5
      };
 
      var map = new google.maps.Map( 
             document.getElementById("googleMap") 
            , mapOptions );
   }
</script> 
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyD-nI2V_bsNjQF5ZQ4mlq8o8sr1oZ6bLi0&callback=myMap"></script>
</html>