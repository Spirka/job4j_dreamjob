package ru.job4j.dreamjob.store;

import ru.job4j.dreamjob.model.Post;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Class PostStore
 * Хранилище вакансий
 *
 * @author Kseniya Dergunova
 * @since 02.05.2022
 */
public class PostStore {

    private static final PostStore INST = new PostStore();
    private final AtomicInteger ids = new AtomicInteger(4);
    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();

    private PostStore() {
        posts.put(1, new Post(1, "Junior Java Job", "Описание", LocalDateTime.now()));
        posts.put(2, new Post(2, "Middle Java Job","Описание", LocalDateTime.now()));
        posts.put(3, new Post(3, "Senior Java Job","Описание", LocalDateTime.now()));
    }

    public static PostStore instOf() {
        return INST;
    }

    public Collection<Post> findAll() {
        return posts.values();
    }

    public void create(Post post) {
        post.setId(ids.incrementAndGet());
        post.setCreated(LocalDateTime.now());
        this.posts.put(post.getId(), post);
    }

    public Post findById(int id) {
        return posts.get(id);
    }

    public void update(Post post) {
        posts.put(post.getId(), post);
    }
}
