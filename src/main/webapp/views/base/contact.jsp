<!DOCTYPE html>
<html>
<head>
    <style type="text/css">
        html, body { height: 100%; margin: 0; padding: 0; }
        #map { height: 50%;width: 50% }
    </style>
</head>
<body>
<div id="map"></div>
<script type="text/javascript">

    var map;
    function initMap() {
        map = new google.maps.Map(document.getElementById('map'), {
            center: {lat: 49.848121, lng: 24.020093},
            zoom: 18
        });
    }


</script>
<script async defer
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAwNHubKrgOPpginynbFWqebzRl4QZwASw&callback=initMap">
</script>
</body>
</html>