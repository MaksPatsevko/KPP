import org.json.JSONObject;

public class CurrencyDataProcessor {
    public static void processCurrencyData(String jsonResponse) {
        JSONObject jsonObject = new JSONObject(jsonResponse);
        if (jsonObject.getBoolean("success")) {
            JSONObject rates = jsonObject.getJSONObject("rates");
            for (String date : rates.keySet()) {
                System.out.println("Date: " + date + ", Rate: " + rates.getJSONObject(date).getDouble("USD"));
            }
        } else {
            System.out.println("Error fetching data: " + jsonObject.getString("error"));
        }
    }
}