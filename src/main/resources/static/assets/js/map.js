var map;	
var idInfoBoxAberto;
var infoBox = [];
var markers = [];

function initialize() {	
	var latlng = new google.maps.LatLng(-23.7263676, -46.5801575);
	
    var options = {
        zoom: 3,
		center: latlng,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    };

    map = new google.maps.Map(document.getElementById("locations"), options);
}

initialize();

function carregarPontos() {
	
	//$.getJSON('js/pontos.json', function(pontos) {
		
		var pontos = [
			{
				"Id": 1,
		        "Latitude": -23.7263676,
		        "Longitude": -46.5801575,
		    },
			{
				"Id": 2,
		        "Latitude": -23.7263667,
		        "Longitude": -46.580115,
		    },
			{
				"Id": 3,
		        "Latitude": -23.7263125,
		        "Longitude": -46.5801575,
		    },
			{
				"Id": 4,
		        "Latitude": -23.7263125,
		        "Longitude": -46.5801575,
		    },
		    {
		    	"Id": 5,
		    	"Latitude": -23.5424356,
		    	"Longitude": -46.5627267,
		    }
		];

		var latlngbounds = new google.maps.LatLngBounds();
		
		$.each(pontos, function(index, ponto) {
			var marker = new google.maps.Marker({
				position: new google.maps.LatLng(ponto.Latitude, ponto.Longitude),
				map: map,
				icon: 'assets/img/marcador.png'
			});
			
			var myOptions = {
				pixelOffset: new google.maps.Size(-150, 0),
				infoBoxClearance: new google.maps.Size(1, 1)
        	};

			infoBox[ponto.Id] = new InfoBox(myOptions);
			infoBox[ponto.Id].marker = marker;
			
			markers.push(marker);
			
			latlngbounds.extend(marker.position);
			
		});
		
		var optionsMarkerCluster = {
    		imagePath: 'assets/img/maps/m'
		}

		var markerCluster = new MarkerClusterer(map, markers, optionsMarkerCluster);
		
		map.fitBounds(latlngbounds);
		
	//});
	
}

carregarPontos();