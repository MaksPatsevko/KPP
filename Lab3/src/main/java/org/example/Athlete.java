package org.example;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;


public class Athlete implements Serializable {
    private String name;
    private String sport;
    private   String[] medals; // Масив для медалей ("Gold", "Silver", "Bronze")
    private  transient int medalCount; // Поле для кількості медалей

    public Athlete(String name, String sport, String[] medals, int medalCount) {
        this.name = name;
        this.sport = sport;
        this.medals = medals;
        this.medalCount = medalCount; // Оновлюємо кількість медалей
    }

    public String getName() {
        return name;
    }

    public String getSport() {
        return sport;
    }

    public String[] getMedals() {
        return medals;
    }

    public int getMedalCount() {
        return medalCount;
    }

    public void setMedals(String[] medals) {
        this.medals = medals;
    }

    @Override
    public String toString() {
        return "Athlete{name='" + name + '\'' +
                ", sport='" + sport + '\'' +
                ", medals=" + Arrays.toString(medals) +
                ", medalCount=" + medalCount +
                '}';
    }
}
