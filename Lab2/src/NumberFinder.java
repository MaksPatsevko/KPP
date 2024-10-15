import java.util.regex.*;
import java.util.Scanner;

public class NumberFinder {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть текст:");

        String inputText = scanner.nextLine();

        // Спрощений регулярний вираз
        String regex = "(?:\\b|-)([0-9]+(?:\\.[0-9]+)?(?:[eE][+-]?[0-9]+)?)\\b";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(inputText);

        System.out.println("Знайдені числа:");
        while (matcher.find()) {
            System.out.println(matcher.group());
        }

        scanner.close();
    }
}