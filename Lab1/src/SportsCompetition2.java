import java.util.*;
import java.util.Optional;

public class SportsCompetition2 {

    public static void main(String[] args) {
        List<Athlete> athletes = new ArrayList<>();

        // Додавання спортсменів
        athletes.add(new Athlete("John", "Doe", List.of("Basketball", "Soccer"), 5, 25));
        athletes.add(new Athlete("Jane", "Smith", List.of("Tennis"), 6, 22));
        athletes.add(new Athlete("Alice", "Johnson", List.of("Swimming", "Athletics"), 7, 28));
        athletes.add(new Athlete("Bob", "Brown", List.of("Soccer"), 2, 30));
        athletes.add(new Athlete("Mike", "Williams", List.of("Basketball"), 8, 26));

        // Колекції для зберігання спортсменів
        List<Athlete> multipleSportsAthletes = new ArrayList<>();
        List<Athlete> singleSportAthletes = new ArrayList<>();

        // Розділення спортсменів
        for (Athlete athlete : athletes) {
            if (athlete.getSports().size() > 1) {
                multipleSportsAthletes.add(athlete);
            } else {
                singleSportAthletes.add(athlete);
            }
        }

        // Виведення результату
        System.out.println("\nAthletes representing multiple sports:");
        for (Athlete athlete : multipleSportsAthletes) {
            System.out.println(athlete);
        }

        System.out.println("\nAthletes representing a single sport:");
        for (Athlete athlete : singleSportAthletes) {
            System.out.println(athlete);
        }

        // Мапа для групування спортсменів за видами спорту
        Map<String, List<Athlete>> athletesBySport = new HashMap<>();

        // Групування спортсменів за видами спорту
        for (Athlete athlete : athletes) {
            for (String sport : athlete.getSports()) {
                // Якщо виду спорту немає в мапі, додаємо новий список для цього виду спорту
                athletesBySport.putIfAbsent(sport, new ArrayList<>());
                // Додаємо спортсмена до списку для відповідного виду спорту
                athletesBySport.get(sport).add(athlete);
            }
        }

        // Виведення спортсменів за видами спорту
        System.out.println("\nAthletes grouped by sports:");
        for (Map.Entry<String, List<Athlete>> entry : athletesBySport.entrySet()) {
            System.out.println(entry.getKey() + ":");
            for (Athlete athlete : entry.getValue()) {
                System.out.println("  " + athlete);
            }
        }

        // Мапа для зберігання кількості спортсменів з більш ніж 5 медалями у кожному виді спорту
        Map<String, Integer> sportMedalsCount = new HashMap<>();

        // Перебір спортсменів та оновлення кількості у мапі
        for (Athlete athlete : athletes) {
            if (athlete.getMedals() > 5) {
                for (String sport : athlete.getSports()) {
                    // Оновлення кількості для кожного виду спорту
                    sportMedalsCount.put(sport, sportMedalsCount.getOrDefault(sport, 0) + 1);
                }
            }
        }

        // Виведення результату
        System.out.println("\nNumber of athletes with more than 5 medals in each sport:");
        for (Map.Entry<String, Integer> entry : sportMedalsCount.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        // Сортування за кількістю медалей і віком
        Collections.sort(athletes, new Comparator<Athlete>() {
            @Override
            public int compare(Athlete a1, Athlete a2) {
                // Сортування спочатку за кількістю медалей, а потім за віком
                if (a1.getMedals() != a2.getMedals()) {
                    return Integer.compare(a2.getMedals(), a1.getMedals());  // Більше медалей - вище в списку
                }
                return Integer.compare(a1.getAge(), a2.getAge());  // Менший вік - вище в списку
            }
        });

        // Виведення відсортованих спортсменів
        System.out.println("\nAthletes sorted by medals and age:");
        for (Athlete athlete : athletes) {
            System.out.println(athlete);
        }

        // Множина для зберігання унікальних видів спорту
        Set<String> uniqueSports = new HashSet<>();

        // Додавання видів спорту до множини
        for (Athlete athlete : athletes) {
            uniqueSports.addAll(athlete.getSports());
        }

        // Виведення унікальних видів спорту
        System.out.println("\nUnique sports:");
        for (String sport : uniqueSports) {
            System.out.println(sport);
        }

        // Введення виду спорту
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter the sport to find the athlete with the most medals:");
        String selectedSport = scanner.nextLine();

        // Пошук спортсмена з найбільшою кількістю медалей у вибраному виді спорту
        Optional<Athlete> topAthleteInSport = findTopAthleteInSport(athletes, selectedSport);

        // Виведення результату
        if (topAthleteInSport.isPresent()) {
            System.out.println("Top athlete in " + selectedSport + ": " + topAthleteInSport.get());
        } else {
            System.out.println("No athletes found in " + selectedSport);
        }

        // Закриття сканера
        scanner.close();
    }

    // Метод для знаходження спортсмена з найбільшою кількістю медалей у вибраному виді спорту
    private static Optional<Athlete> findTopAthleteInSport(List<Athlete> athletes, String sport) {
        return athletes.stream()
                .filter(athlete -> athlete.getSports().contains(sport))
                .max(Comparator.comparingInt(Athlete::getMedals));
    }
}


