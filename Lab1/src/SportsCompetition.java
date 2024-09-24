import java.util.*;
import java.util.stream.Collectors;

class Athlete {
    private String firstName;
    private String lastName;
    private List<String> sports;
    private int medals;
    private int age;

    public Athlete(String firstName, String lastName, List<String> sports, int medals, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.sports = sports;
        this.medals = medals;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<String> getSports() {
        return sports;
    }

    public int getMedals() {
        return medals;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " (" + age + " years old) - Medals: " + medals + ", Sports: " + sports;
    }
}

public class SportsCompetition {

    public static void main(String[] args) {
        List<Athlete> athletes = new ArrayList<>();

        // Додавання спортсменів
        athletes.add(new Athlete("John", "Doe", Arrays.asList("Basketball", "Soccer"), 5, 25));
        athletes.add(new Athlete("Jane", "Smith", Arrays.asList("Tennis"), 6, 22));
        athletes.add(new Athlete("Alice", "Johnson", Arrays.asList("Swimming", "Athletics"), 7, 28));
        athletes.add(new Athlete("Bob", "Brown", Arrays.asList("Soccer"), 2, 30));
        athletes.add(new Athlete("Mike", "Williams", Arrays.asList("Basketball"), 8, 26));

        // Розподіл спортсменів на тих, хто представляє кілька видів спорту і тих, хто представляє один
        Map<Boolean, List<Athlete>> partitionedAthletes = athletes.stream()
                .collect(Collectors.partitioningBy(athlete -> athlete.getSports().size() > 1));

        // Ті, хто представляє кілька видів спорту
        List<Athlete> multiSportAthletes = partitionedAthletes.get(true);
        System.out.println("Athletes who represent multiple sports:");
        multiSportAthletes.forEach(System.out::println);

        // Ті, хто представляє один вид спорту
        List<Athlete> singleSportAthletes = partitionedAthletes.get(false);
        System.out.println("\nAthletes who represent only one sport:");
        singleSportAthletes.forEach(System.out::println);

        // Групування спортсменів за видами спорту
        Map<String, List<Athlete>> athletesBySport = athletes.stream()
                .flatMap(athlete -> athlete.getSports().stream()
                        .map(sport -> new AbstractMap.SimpleEntry<>(sport, athlete)))
                .collect(Collectors.groupingBy(Map.Entry::getKey, Collectors.mapping(Map.Entry::getValue, Collectors.toList())));

        // Виведення спортсменів за кожним видом спорту
        System.out.println("\nAthletes grouped by sport:");
        athletesBySport.forEach((sport, athleteList) -> {
            System.out.println(sport + ":");
            athleteList.forEach(System.out::println);
        });

        // Групування спортсменів за видами спорту, у яких кількість медалей більше 5
        Map<String, Long> sportMedalWinners = athletes.stream()
                .filter(athlete -> athlete.getMedals() > 5)  // Фільтруємо спортсменів, які мають більше 5 медалей
                .flatMap(athlete -> athlete.getSports().stream()
                        .map(sport -> new AbstractMap.SimpleEntry<>(sport, athlete)))
                .collect(Collectors.groupingBy(Map.Entry::getKey, Collectors.counting()));

        // Виведення кількості спортсменів за кожним видом спорту, які мають більше 5 медалей
        System.out.println("\nNumber of athletes with more than 5 medals by sport:");
        sportMedalWinners.forEach((sport, count) -> System.out.println(sport + ": " + count));

        // Сортування за кількістю медалей і віком
        List<Athlete> sortedAthletes = athletes.stream()
                .sorted(Comparator.comparingInt(Athlete::getMedals).reversed()  // Сортуємо за кількістю медалей (більше -> менше)
                        .thenComparingInt(Athlete::getAge))  // Сортуємо за віком (молодші -> старші)
                .collect(Collectors.toList());

        // Виведення відсортованих спортсменів
        System.out.println("\nAthletes sorted by medals and age:");
        sortedAthletes.forEach(System.out::println);

        // Список всіх унікальних видів спорту
        List<String> uniqueSports = athletes.stream()
                .flatMap(athlete -> athlete.getSports().stream())  // Отримуємо потік видів спорту кожного спортсмена
                .distinct()  // Видаляємо дублікати
                .collect(Collectors.toList());  // Збираємо у список

        // Виведення унікальних видів спорту
        System.out.println("\nUnique sports:");
        uniqueSports.forEach(System.out::println);


        // Створення сканера для введення даних користувачем
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Виведення нумерованого списку видів спорту
            System.out.println("\nSelect a sport to find the athlete with the most medals:");
            for (int i = 0; i < uniqueSports.size(); i++) {
                System.out.println((i + 1) + ". " + uniqueSports.get(i));
            }
            System.out.println("0. Exit");

            // Зчитування вибору користувача
            int choice = scanner.nextInt();

            // Перевірка на вихід з програми
            if (choice == 0) {
                System.out.println("Exiting the program.");
                break;
            }

            // Перевірка на коректний вибір
            if (choice < 1 || choice > uniqueSports.size()) {
                System.out.println("Invalid choice. Please select a valid number.");
                continue;
            }

            // Отримання вибраного виду спорту
            String selectedSport = uniqueSports.get(choice - 1);

            // Пошук спортсмена з найбільшою кількістю медалей у вибраному виді спорту
            Optional<Athlete> topAthleteInSport = athletes.stream()
                    .filter(athlete -> athlete.getSports().contains(selectedSport))
                    .max(Comparator.comparingInt(Athlete::getMedals));

            // Виведення результату
            if (topAthleteInSport.isPresent()) {
                System.out.println("Top athlete in " + selectedSport + ": " + topAthleteInSport.get());
            } else {
                System.out.println("No athletes found in " + selectedSport);
            }
        }

        // Закриття сканера
        scanner.close();
    }
}
