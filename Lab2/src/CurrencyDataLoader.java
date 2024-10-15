import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class CurrencyDataLoader {
    // Завантаження історичних курсів валют за кілька днів
    public static List<Double> loadHistoricalRates(String currencyCode, int daysAgo) throws Exception {
        List<Double> rates = new ArrayList<>();
        String apiUrl = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?valcode=" + currencyCode + "&date=";

        // Завантажуємо дані за кожен день, починаючи з поточної дати
        for (int i = 0; i < daysAgo; i++) {
            String date = getDateDaysAgo(i);  // Отримуємо дату для запиту
            String url = apiUrl + date + "&json";  // Додаємо параметр json до запиту

            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            JSONArray dataArray = new JSONArray(response.toString());
            // Обробляємо дані для певної валюти
            if (dataArray.length() > 0) {
                JSONObject currency = dataArray.getJSONObject(0);  // Беремо перший елемент масиву (повинна бути тільки одна відповідь)
                rates.add(currency.getDouble("rate"));
            }
        }
        return rates;
    }

    // Метод для формування дати у форматі YYYYMMDD
    private static String getDateDaysAgo(int daysAgo) {
        java.time.LocalDate date = java.time.LocalDate.now().minusDays(daysAgo);
        return date.toString().replace("-", "");
    }
}