var osmUrl = 'https://tile.openstreetmap.org/{z}/{x}/{y}.png',
                    osmAttrib = '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors',
                    osm = L.tileLayer(osmUrl, {maxZoom: 15, attribution: osmAttrib});

            var map = L.map('map').setView([-4.036, -79.201], 15);

            L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
                attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
            }).addTo(map);L.marker([-3.6479459576294744,-79.22366480590895]).addTo(map)
.bindPopup('Unidad Educativa Calasanz')´)
,.openPopup();
L.marker([-3.979222472236886,-79.20434745524521]).addTo(map)
.bindPopup('Universidad Nacional De Loja')´)
,.openPopup();
