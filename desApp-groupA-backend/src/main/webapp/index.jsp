<html>
<head>
<title>Google Map Hello World Example</title>
<style type="text/css">
div#map_container{
	width:50%;
	height:50%;
	margin: 0 auto;
}
</style>
<script type="text/javascript"
   src="https://maps.googleapis.com/maps/api/js?sensor=false&key=AIzaSyBIxrL384l4aB3C3mDMffKEhbnScbQVm54"></script>

<script type="text/javascript">
  function loadMap() {
  	var directionsDisplay = new google.maps.DirectionsRenderer();
	var directionsService = new google.maps.DirectionsService();
    var myOptions = {	
      mapTypeId: google.maps.MapTypeId.ROADMAP
    };
    var map = new google.maps.Map(document.getElementById("map_container"),myOptions);
    var request = {
		origin: "Roque Sáenz Peña 352",
		destination: "Av. Calchaquí 3950",
		travelMode: google.maps.DirectionsTravelMode.DRIVING,
		unitSystem: google.maps.DirectionsUnitSystem.METRIC,
		provideRouteAlternatives: true
	};
    directionsService.route(request, function(response, status) {
    if (status == google.maps.DirectionsStatus.OK) {
        directionsDisplay.setMap(map);
        directionsDisplay.setDirections(response);
    } else {
            alert("No existen rutas entre ambos puntos");
    }
	});
  }
</script>
</head>

<body onload="loadMap()">
<div id="map_container"></div>
</body>

</html>
