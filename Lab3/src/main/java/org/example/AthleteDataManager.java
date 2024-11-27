package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AthleteDataManager {

    // Зберігає імена, види спорту та кількість медалей (буферизовано), окремо зберігає медалі
    public void saveAthletes(List<Athlete> athletes, String athleteFilePath, String medalsFilePath) {
        // Запис імен, видів спорту та кількості медалей
        try (FileOutputStream fileOut = new FileOutputStream(athleteFilePath);
             BufferedOutputStream bufferedOut = new BufferedOutputStream(fileOut);
             ObjectOutputStream objectOut = new ObjectOutputStream(bufferedOut)) {

            for (Athlete athlete : athletes) {
                objectOut.writeObject(athlete);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Запис медалей для кожного спортсмена
        try (FileOutputStream medalsOut = new FileOutputStream(medalsFilePath);
             ObjectOutputStream medalsObjectOut = new ObjectOutputStream(medalsOut)) {

            for (Athlete athlete : athletes) {
                medalsObjectOut.writeObject(athlete.getMedals());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Завантажує дані про спортсменів, відновлюючи інформацію про кількість медалей
    public List<Athlete> loadAthletes(String athleteFilePath, String medalsFilePath) {
        List<Athlete> athletes = new ArrayList<>();

        // Читання імен, видів спорту та кількості медалей
        try (FileInputStream fileIn = new FileInputStream(athleteFilePath);
             BufferedInputStream bufferedIn = new BufferedInputStream(fileIn);
             ObjectInputStream objectIn = new ObjectInputStream(bufferedIn)) {

            while (true) {
                try {
                    Athlete athlete = (Athlete) objectIn.readObject();
                    athletes.add(athlete);
                } catch (EOFException e) {
                    break; // Кінець файлу
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Читання медалей і відновлення кількості медалей
        try (FileInputStream medalsIn = new FileInputStream(medalsFilePath);
             ObjectInputStream medalsObjectIn = new ObjectInputStream(medalsIn)) {

            for (Athlete athlete : athletes) {
                try {
                    String[] medals = (String[]) medalsObjectIn.readObject();
                    athlete.setMedals(medals);
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return athletes;
    }
}
