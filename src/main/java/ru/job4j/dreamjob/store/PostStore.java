package ru.job4j.dreamjob.store;

import ru.job4j.dreamjob.model.Post;

import java.time.Instant;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Class PostStore
 *
 * @author Kseniya Dergunova
 * @since 02.05.2022
 */
public class PostStore {

    private static final PostStore INST = new PostStore();

    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();

    private PostStore() {
        posts.put(1, new Post(1, "Junior Java Job", "Описание", Instant.parse("2022-04-01T21:00:00Z")));
        posts.put(2, new Post(2, "Middle Java Job","Описание", Instant.parse("2022-04-01T21:00:00Z")));
        posts.put(3, new Post(3, "Senior Java Job","Описание", Instant.parse("2022-04-01T21:00:00Z")));
    }

    public static PostStore instOf() {
        return INST;
    }

    public Collection<Post> findAll() {
        return posts.values();
    }
}
