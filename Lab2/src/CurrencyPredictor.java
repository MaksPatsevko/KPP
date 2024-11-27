import org.apache.commons.math3.stat.regression.SimpleRegression;
import java.util.List;

public class CurrencyPredictor {

    // Метод для прогнозування курсу на основі історичних даних
    public static double predictCurrencyRate(List<Double> rates) {
        SimpleRegression regression = new SimpleRegression();

        // Підготовка даних для регресії (дні як незалежна змінна, курс як залежна)
        for (int i = 0; i < rates.size(); i++) {
            regression.addData(i, rates.get(i));
        }

        // Прогнозування курсу на основі регресії
        return regression.predict(rates.size());
    }
}
