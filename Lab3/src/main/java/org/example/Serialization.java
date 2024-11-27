package org.example;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Serialization {
    public static void main(String[] args) {
        // Створюємо кілька об'єктів Athlete
        Athlete athlete1 = new Athlete("John Doe", "Swimming", new String[]{"Gold", "Silver"}, 2);
        Athlete athlete2 = new Athlete("Jane Smith", "Athletics", new String[]{"Bronze"}, 1);
        Athlete athlete3 = new Athlete("Mike Johnson", "Boxing", new String[]{"Gold", "Gold", "Silver"}, 3);

        // Додаємо спортсменів до списку
        List<Athlete> athletes = new ArrayList<>();
        athletes.add(athlete1);
        athletes.add(athlete2);
        athletes.add(athlete3);

        // Створюємо об'єкт Team, що містить список спортсменів
        Team team = new Team("Olympic Team", athletes);

        // Серіалізація об'єкта Team
        String filePath = "team.dat";
        try (FileOutputStream fileOut = new FileOutputStream(filePath);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {

            objectOut.writeObject(team);
            System.out.println("Об'єкт серіалізовано: " + team);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Десеріалізація об'єкта Team
        try (FileInputStream fileIn = new FileInputStream(filePath);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {

            Team deserializedTeam = (Team) objectIn.readObject();
            System.out.println("Об'єкт десеріалізовано: " + deserializedTeam);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
