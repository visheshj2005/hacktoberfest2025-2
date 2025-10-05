import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WeatherForecast {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Dummy weather data for demo (can be replaced with API later)
        Map<String, String> weatherData = new HashMap<>();
        weatherData.put("delhi", "☀️ Sunny, 32°C");
        weatherData.put("mumbai", "🌧️ Rainy, 27°C");
        weatherData.put("kolkata", "⛅ Partly Cloudy, 30°C");
        weatherData.put("chennai", "🌤️ Hot and Humid, 34°C");
        weatherData.put("lucknow", "🌦️ Light Showers, 28°C");

        System.out.println("🌦️ Welcome to Weather Forecast App");
        System.out.print("Enter your city name: ");
        String city = sc.nextLine().toLowerCase();

        if (weatherData.containsKey(city)) {
            System.out.println("📍 Weather in " + capitalize(city) + ": " + weatherData.get(city));
        } else {
            System.out.println("❌ Weather data not available for this city.");
        }

        sc.close();
    }

    // Capitalize first letter of city
    private static String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
