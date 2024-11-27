package org.example;
import org.yaml.snakeyaml.Yaml;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class YamlDeserialization {
    public static void main(String[] args) {
        String yamlFilePath = "athletes.yaml";

        // Десеріалізація YAML файлу
        try (FileReader reader = new FileReader(yamlFilePath)) {
            Yaml yaml = new Yaml();
            // Десеріалізуємо прямо в список Athlete2
            List<Athlete2> deserializedAthletes = yaml.load(reader);

            // Вивід десеріалізованих даних
            System.out.println("Список спортсменів десеріалізовано з YAML: ");
            for (Athlete2 athlete2 : deserializedAthletes) {
                System.out.println("Name: " + athlete2.getName() +
                        ", Sport: " + athlete2.getSport() +
                        ", Medals: " + athlete2.getMedals() +
                        ", Age: " + athlete2.getAge());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
