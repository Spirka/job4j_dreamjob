package ru.job4j.dreamjob.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.dreamjob.model.Post;
import ru.job4j.dreamjob.store.PostStore;

import java.util.List;

/**
 * Class PostService
 *
 * @author Kseniya Dergunova
 * @since 02.05.2022
 */
@ThreadSafe
@Service
public class PostService {

    private final PostStore postStore;

    public PostService(PostStore postStore) {
        this.postStore = postStore;
    }

    public List<Post> findAll() {
        return postStore.findAll();
    }

    public Post findById(int id) {
        return postStore.findById(id);
    }

    public void create(Post post) {
        postStore.add(post);
    }

    public void update(Post post) {
        postStore.update(post);
    }
}
