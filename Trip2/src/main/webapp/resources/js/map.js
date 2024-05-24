    function myMap() {
        const myLatLng = { lat: 35.8473612313022, lng: 129.218053667485 };

        var marker;
        var label;
        var flightPath;
        
        var map = new google.maps.Map(document.getElementById("googleMap"), {
          zoom: 14,
          center: myLatLng
        });

        var geocoder = new google.maps.Geocoder();

        console.log('지도 진입');

        document.body.addEventListener('click', function (event) {
          if (event.target && event.target.matches('.checkBtn')) {
            console.log('버튼 클릭 이벤트 실행');
            var addr2Input = event.target.previousElementSibling;
            console.log('이건 됨?')
            if (addr2Input) {
              var address = addr2Input.value;
              console.log('입력된 주소:', address);
              geocodeAddress(geocoder, map, address);
            }
          }

          if (event.target && event.target.matches(".deleteBtn")) {
            console.log("삭제 버튼 동작");
            var index = parseInt(event.target.getAttribute('data-index'), 10);
            deleteMarker(index);
          }

        });

        const labels = "123456789";
        let labelIndex = 0;
        var markers = [];

        function geocodeAddress(geocoder, resultMap, address) {
          console.log('지오코딩 함수 실행');

          geocoder.geocode({ 'address': address }, function (result, status) {
            console.log('지오코딩 결과:', result);
            console.log('지오코딩 상태:', status);

            if (status === 'OK') {
              resultMap.setCenter(result[0].geometry.location);
              resultMap.setZoom(18);
              var image = {
                url: "../resources/image/주석_2024-05-21_153140-removebg-preview.png", // 아이콘 이미지 경로
                scaledSize: new google.maps.Size(55, 55), // 아이콘 크기 설정 (가로, 세로)

              }

              marker = new google.maps.Marker({
                map: resultMap,
                position: result[0].geometry.location,
                icon: image,
                label: {
                  text: (markers.length+1).toString(),
                  fontSize: "30px",
                  fontWeight: "bold",
                  color: '#ffffff',
                  labelOrigin: new google.maps.Point(30, 30)
                }
              });

              markers.push(marker);

              console.log('위도:', marker.position.lat());
              console.log('경도:', marker.position.lng());

              updatePolyline();

            } else {
              alert('지오코드가 다음의 이유로 성공하지 못했습니다: ' + status);
            }
          });
        }



        function updatePolyline() {
          console.log("update")
          var path = markers.map(marker => marker.getPosition());

          console.log("path 값 : ", markers);

          if (flightPath) {
              flightPath.setMap(null);
            }
          
          flightPath = new google.maps.Polyline({
            path: path,
            geodesic: true,
            strokeColor: "#FF0000",
            strokeOpacity: 1.0,
            strokeWeight: 2
          });

          flightPath.setMap(map);
        }

        function deleteMarker(index) {
          if (markers[index]) {
            markers[index].setMap(null);
            markers.splice(index, 1);
            relabelMarkers();
            updatePolyline();
          }
        }

        function relabelMarkers(){
        	markers.forEach((marker, i) => {
        		marker.setLabel({
        			text:(i+1).toString(),
        			fontSize: "30px",
        	        fontWeight: "bold",
        	        color: '#ffffff',
        	        labelOrigin: new google.maps.Point(30, 30)
        		})
        	})
        }
      }

      document.addEventListener('DOMContentLoaded', myMap);