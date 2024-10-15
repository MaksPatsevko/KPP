import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class CurrencyForecastApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        while (true) {
            // Введення коду валюти
            System.out.print("Введіть код валюти (наприклад, USD): ");
            String currencyCode = scanner.nextLine().toUpperCase(); // Перевіряємо, що код валюти великими літерами

            // Введення майбутньої дати
            System.out.print("Введіть дату для прогнозу (в форматі YYYY-MM-DD): ");
            String futureDateInput = scanner.nextLine();

            try {
                LocalDate futureDate = LocalDate.parse(futureDateInput, formatter);
                LocalDate currentDate = LocalDate.now();

                // Різниця в днях між майбутньою датою і сьогоднішньою
                int daysUntilFutureDate = (int) java.time.temporal.ChronoUnit.DAYS.between(currentDate, futureDate);

                if (daysUntilFutureDate <= 0) {
                    System.out.println("Майбутня дата повинна бути після сьогоднішньої.");
                    continue;
                }



                // Завантаження та прогнозування
                List<Double> rates = CurrencyDataLoader.loadHistoricalRates(currencyCode, daysUntilFutureDate);
                double predictedRate = CurrencyPredictor.predictCurrencyRate(rates);

                // Виведення прогнозованого курсу з точністю до 4 знаків після коми
                System.out.printf("Прогнозований курс валюты %s на %s: %.4f%n", currencyCode, futureDate, predictedRate);
            } catch (Exception e) {
                System.out.println("Сталася помилка: " + e.getMessage());
            }

            // Пропонуємо користувачу повторити операцію
            System.out.print("Бажаєте зробити новий прогноз? (так/ні): ");
            String repeat = scanner.nextLine().trim().toLowerCase();
            if (!repeat.equals("так")) {
                break;
            }
        }

        System.out.println("Дякуємо за використання програми прогнозування валютних курсів!");
    }
}