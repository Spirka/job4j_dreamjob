package ru.job4j.dreamjob.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Class Post
 *
 * @author Kseniya Dergunova
 * @since 02.05.2022
 */
public class Post {

    /**
     * Идентификатор
     */
    private int id;

    /**
     * Название вакансии
     */
    private String name;

    /**
     * Описание
     */
    private String description;

    /**
     * Видимость
     */
    private boolean visible;

    /**
     * Дата создания
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime created;

    public Post() {
    }

    public Post(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Post(int id, String name, String description, LocalDateTime created) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.created = created;
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

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Post)) return false;
        Post post = (Post) o;
        return id == post.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Post{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", created=" + created +
            ", visible=" + visible +
            '}';
    }
}
