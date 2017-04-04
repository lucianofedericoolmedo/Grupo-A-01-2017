<html>
<head>
<title>Google Maps Example</title>
<style type="text/css">
div#map_container{
	width:50%;
	height:50%;
	margin: 0 auto;
}
</style>
<script type="text/javascript"
   src="https://maps.googleapis.com/maps/api/js?sensor=false&key=AIzaSyBIxrL384l4aB3C3mDMffKEhbnScbQVm54&libraries=places"></script>

<script type="text/javascript">


  function prepareMap(origin,destination) {
  	var directionsDisplay = new google.maps.DirectionsRenderer();
	var directionsService = new google.maps.DirectionsService();
    var myOptions = {	
      mapTypeId: google.maps.MapTypeId.ROADMAP
    };
    var map = new google.maps.Map(document.getElementById("map_container"),myOptions);
    prepareRequest(origin,destination,directionsDisplay,directionsService,myOptions,map);
  }
  
  function prepareRequest(origin,destination,directionsDisplay,directionsService,myOptions,map) {
	  var request = {
		origin: origin,
		destination: destination,
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
  
  function loadMap(){
	var origin = document.getElementById("origin").value;
	var destination = document.getElementById("destination").value;
	prepareMap(origin,destination);
  }
  
  function complete(evt){
	  var input = document.getElementById(evt.id);
      var autocomplete = new google.maps.places.Autocomplete(input);
  }
  
  
</script>
</head>

<body>

<div>
	<input type="text" id="origin" onkeypress="complete(this)"> </input>
	<input type="text" id="destination" onkeypress="complete(this)"> </input>
</div>

<button onclick="loadMap()">Buscar</button>

<div id="map_container"></div>
</body>

</html>
