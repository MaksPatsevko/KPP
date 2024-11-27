package org.example;
import java.io.Serializable;

public class Athlete2 implements Serializable {
    private String name;
    private String sport;
    private int medals;
    private int age;

    // Конструктор за замовчуванням
    public Athlete2() {}

    // Конструктор з параметрами
    public Athlete2(String name, String sport, int medals, int age) {
        this.name = name;
        this.sport = sport;
        this.medals = medals;
        this.age = age;
    }

    // Геттери та сеттери
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public int getMedals() {
        return medals;
    }

    public void setMedals(int medals) {
        this.medals = medals;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // toString для зручного виводу
    @Override
    public String toString() {
        return "Athlete{" +
                "name='" + name + '\'' +
                ", sport='" + sport + '\'' +
                ", medals=" + medals +
                ", age=" + age +
                '}';
    }
}

