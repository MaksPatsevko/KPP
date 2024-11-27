package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonSerialization {
    public static void main(String[] args) {
        // Створення списку спортсменів
        List<Athlete> athletes = new ArrayList<>();
        athletes.add(new Athlete("John Doe", "Swimming", new String[]{"Gold", "Silver", "Bronze", "Gold", "Silver"}, 5));
        athletes.add(new Athlete("Jane Smith", "Athletics", new String[]{"Gold", "Silver", "Bronze"}, 3));
        athletes.add(new Athlete("Mike Johnson", "Boxing", new String[]{"Gold", "Silver"}, 2));

        // Серіалізація списку об'єктів у формат JSON
        String jsonFilePath = "athletes.json";
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter writer = new FileWriter(jsonFilePath)) {
            gson.toJson(athletes, writer);
            System.out.println("Список спортсменів серіалізовано у JSON.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Десеріалізація списку об'єктів з формату JSON
        try (FileReader reader = new FileReader(jsonFilePath)) {
            Athlete[] deserializedAthletes = gson.fromJson(reader, Athlete[].class);
            System.out.println("Список спортсменів десеріалізовано з JSON: ");
            for (Athlete athlete : deserializedAthletes) {
                System.out.println("Name: " + athlete.getName() +
                        ", Sport: " + athlete.getSport() +
                        ", Medals: " + athlete.getMedals() +
                        ", medalCount: " + athlete.getMedalCount());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
