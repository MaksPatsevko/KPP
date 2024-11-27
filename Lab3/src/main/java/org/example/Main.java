package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Athlete> athletes = new ArrayList<>();
        athletes.add(new Athlete("John Doe", "Swimming", new String[]{"Gold", "Silver", "Bronze", "Gold", "Silver"}, 5));
        athletes.add(new Athlete("Jane Smith", "Athletics", new String[]{"Gold", "Silver", "Bronze"}, 3));
        athletes.add(new Athlete("Mike Johnson", "Boxing", new String[]{"Gold", "Silver"}, 2));

        AthleteDataManager manager = new AthleteDataManager();

        String athleteFilePath = "athletes.dat";   // Файл для імен та виду спорту
        String medalsFilePath = "medals.dat";      // Файл для медалей

        // Запис даних про спортсменів
        manager.saveAthletes(athletes, athleteFilePath, medalsFilePath);
        System.out.println("Дані спортсменів записано у файли.");

        // Читання даних про спортсменів
        List<Athlete> loadedAthletes = manager.loadAthletes(athleteFilePath, medalsFilePath);
        System.out.println("Дані спортсменів зчитано з файлів:");

        for (Athlete athlete : loadedAthletes) {
            System.out.println("Name: " + athlete.getName());
            System.out.println("Sport: " + athlete.getSport());
            System.out.println("Medal Count: " + athlete.getMedalCount());
            System.out.println("Medals: " + String.join(", ", athlete.getMedals()));
            System.out.println(); // Додаємо пустий рядок для кращого форматування
        }
    }
}
