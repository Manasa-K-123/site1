document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById('weatherForm');
    const weatherDetails = document.getElementById('weatherDetails');

    // Set default city and fetch weather details when the page loads
    const defaultCity = "Delhi"; // Set your default city here
    document.getElementById('cityInput').value = defaultCity;
    getWeather(defaultCity);

    form.addEventListener('submit', async function (event) {
        event.preventDefault();

        const cityInput = document.getElementById('cityInput').value;
        await getWeather(cityInput);
    });

    document.getElementById('getLocationButton').addEventListener('click', getLocation);

    function getLocation() {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(sendPositionToBackend);
        } else {
            console.log("Geolocation is not supported by this browser.");
        }
    }

    async function sendPositionToBackend(position) {
        const latitude = position.coords.latitude;
        const longitude = position.coords.longitude;

        // Send latitude and longitude to backend
        try {
            const response = await fetch(`/bin/getaddress?latitude=${latitude}&longitude=${longitude}`);
            if (!response.ok) {
                throw new Error('Error fetching city data');
            }
            const cityData = await response.json();
            const city = cityData.city;
            document.getElementById('cityInput').value = city;
            getWeather(city);
        } catch (error) {
            console.error('Error fetching city data:', error);
        }
    }

     function getWeather(city) {
        fetch(`/bin/getWeather?city=${city}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error('City not found');
                }
                return response.json();
            })
            .then(data => {
                renderWeatherDetails(data);
            })
            .catch(error => {
                console.error('Error fetching weather data:', error);
                weatherDetails.innerHTML = 'City not found';
            });
    }

   function renderWeatherDetails(data) {
    const weatherHtml = `
        <h2 class="weather-details__title">Weather Details for ${data.city}</h2>
        <div class="weather-details__content">
            <div><p >Temperature: <span class="weather-details__temperature" style="border: 1px solid black; width: 50px; height: 20px;">${data.temperature}°C</span></p></div>
            <p>Feels Like: <span class="weather-details__feels-like" style="border: 1px solid black; width: 50px; height: 20px;">${data.feelsLike}°C</span></p>
            <p>Min Temperature: <span class="weather-details__min-temperature" style="border: 1px solid black; width: 50px; height: 20px;">${data.minTemperature}°C</span></p>
            <p>Max Temperature: <span class="weather-details__max-temperature" style="border: 1px solid black; width: 50px; height: 20px;">${data.maxTemperature}°C</span></p>
            <p>Pressure: <span class="weather-details__pressure" style="border: 1px solid black; width: 50px; height: 20px;">${data.pressure} hPa</span></p>
            <p>Humidity: <span class="weather-details__humidity" style="border: 1px solid black; width: 50px; height: 20px;">${data.humidity}%</span></p>
            <p>Wind Speed: <span class="weather-details__wind-speed" style="border: 1px solid black; width: 50px; height: 20px;">${data.windSpeed} m/s</span></p>
            <p>Description: <span class="weather-details__description" style="border: 1px solid black; width: 200px; height: 20px;">${data.weatherDescription}</span></p>
        </div>
    `;
    weatherDetails.innerHTML = weatherHtml;
}
});
