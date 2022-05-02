package ru.job4j.dreamjob.store;

import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.Post;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
@Repository
public class PostStore {

    private final AtomicInteger ids = new AtomicInteger(4);
    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();

    private PostStore() {
        posts.put(1, new Post(1, "Junior Java Job"));
        posts.put(2, new Post(2, "Middle Java Job"));
        posts.put(3, new Post(3, "Senior Java Job"));
    }

    public List<Post> findAll() {
        return new ArrayList<>(posts.values());
    }

    public void add(Post post) {
        post.setCreated(LocalDateTime.now());
        post.setId(ids.incrementAndGet());
        this.posts.put(post.getId(), post);
    }

    public Post findById(int id) {
        return posts.get(id);
    }

    public void update(Post post) {
        posts.put(post.getId(), post);
    }
}
