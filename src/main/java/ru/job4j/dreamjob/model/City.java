package ru.job4j.dreamjob.model;

import java.util.Objects;

/**
 * Class City
 *
 * @author Kseniya Dergunova
 * @since 03.05.2022
 */
public class City {

    private int id;
    private String name;

    public City() {
    }

    public City(int id) {
        this.id = id;
    }

    public City(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof City)) return false;
        City city = (City) o;
        return id == city.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "City{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
    }
}