package ru.job4j.dreamjob.model;

import java.time.Instant;
import java.util.Objects;

/**
 * Class Candidate
 *
 * @author Kseniya Dergunova
 * @since 02.05.2022
 */
public class Candidate {

    /**
     * Идентификатор
     */
    private int id;
    /**
     * ФИО
     */
    private String name;

    /**
     * Описание
     */
    private String description;

    /**
     * Дата рождения
     */
    private Instant dateOfBirth;

    public Candidate() {
    }

    public Candidate(int id, String name, String description, Instant dateOfBirth) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dateOfBirth = dateOfBirth;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Instant dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Candidate)) return false;
        Candidate candidate = (Candidate) o;
        return id == candidate.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Candidate{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", dateOfBirth=" + dateOfBirth +
            '}';
    }
}
