import java.util.regex.*;
import java.util.Scanner;

public class NumberFinder {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть текст:");
        //Числа: -42, abcde(42), -3.14, 3.14, -1.5e10, 1.5e10, -1.5E+10, 1e+10, -2E-10, (-0.5), 0.5, 0, -0.5
        String inputText = scanner.nextLine();

        // Спрощений регулярний вираз
        String regex = "(\\()(-?[0-9]+(?:\\.[0-9]+)?(?:[eE][+-]?[0-9]+)?)(\\))";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(inputText);

        System.out.println("Знайдені числа:");
        while (matcher.find()) {
            System.out.println(matcher.group());
        }

        scanner.close();
    }
}