package ru.job4j.dreamjob.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.dreamjob.model.City;
import ru.job4j.dreamjob.model.Post;
import ru.job4j.dreamjob.store.PostDbStore;

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

    private final PostDbStore postStore;
    private final CityService cityService;

    public PostService(PostDbStore postStore, CityService cityService) {
        this.postStore = postStore;
        this.cityService = cityService;
    }

    public List<Post> findAll() {
        List<Post> posts = postStore.findAll();
        posts.forEach(post -> {
            if (post.getCity() != null) {
                City city = cityService.findById(post.getCity().getId());
                post.setCity(city);
            }
        });
        return posts;
    }

    public Post findById(int id) {
        Post post = postStore.findById(id);
        if(post.getCity() != null) {
            City city = cityService.findById(post.getCity().getId());
            post.setCity(city);
        }
        return post;
    }

    public void create(Post post) {
        if (post.getCity() != null) {
            City city = cityService.findById(post.getCity().getId());
            post.setCity(city);
        }
        postStore.add(post);
    }

    public void update(Post post) {
        postStore.update(post);
    }
}
