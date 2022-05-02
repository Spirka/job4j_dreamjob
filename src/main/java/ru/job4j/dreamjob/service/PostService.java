package ru.job4j.dreamjob.service;

import ru.job4j.dreamjob.model.Post;
import ru.job4j.dreamjob.store.PostStore;

import java.util.List;

/**
 * Class PostService
 *
 * @author Kseniya Dergunova
 * @since 02.05.2022
 */
public class PostService {

    private static final PostService INST = new PostService();

    PostStore postStore = PostStore.instOf();

    public static PostService instOf() {
        return INST;
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
