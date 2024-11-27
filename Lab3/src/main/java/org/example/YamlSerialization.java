package org.example;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class YamlSerialization {
    public static void main(String[] args) {
        // Створення кількох об'єктів Athlete
        List<Athlete2> athletes = new ArrayList<>();
        athletes.add(new Athlete2("Jane Smith", "Running", 3, 25));
        athletes.add(new Athlete2("John Doe", "Swimming", 5, 32));
        athletes.add(new Athlete2("Bob Johnson", "Cycling", 7, 28));

        // Пропускати поле 'sport' для спортсменів старше 30 років
        for (Athlete2 athlete2 : athletes) {
            if (athlete2.getAge() > 30) {
                athlete2.setSport(null);
            }
        }

        // Налаштування YAML
        DumperOptions options = new DumperOptions();
        options.setPrettyFlow(true);
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);

        Yaml yaml = new Yaml(options);
        String yamlFilePath = "athletes.yaml";

        // Серіалізація списку у YAML файл
        try (FileWriter writer = new FileWriter(yamlFilePath)) {
            yaml.dump(athletes, writer);
            System.out.println("Список спортсменів серіалізовано у YAML.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



