package ru.job4j.dreamjob.controller;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.ui.Model;
import ru.job4j.dreamjob.model.City;
import ru.job4j.dreamjob.model.Post;
import ru.job4j.dreamjob.service.CityService;
import ru.job4j.dreamjob.service.PostService;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;

public class PostControlTest {

    @Test
    public void whenPosts() {
        List<Post> posts = Arrays.asList(
            new Post(1, "New post"),
            new Post(2, "New post")
        );
        HttpSession session = mock(HttpSession.class);
        Model model = mock(Model.class);
        PostService postService = mock(PostService.class);
        when(postService.findAll()).thenReturn(posts);
        CityService cityService = mock(CityService.class);
        PostControl postController = new PostControl(
            postService,
            cityService
        );
        String page = postController.posts(model, session);
        verify(model).addAttribute("posts", posts);
        assertThat(page, is("posts"));
    }

    @Test
    @Ignore
    public void whenWrongData() {
        List<Post> posts = Arrays.asList(
            new Post(1, "New post"),
            new Post(2, "New post")
        );
        List<Post> wrongData = Arrays.asList(
            new Post(3, "New post"),
            new Post(4, "New post")
        );
        HttpSession session = mock(HttpSession.class);
        Model model = mock(Model.class);
        PostService postService = mock(PostService.class);
        when(postService.findAll()).thenReturn(posts);
        CityService cityService = mock(CityService.class);
        PostControl postController = new PostControl(
            postService,
            cityService
        );
        String page = postController.posts(model, session);
        verify(model).addAttribute("posts", wrongData);
        assertThat(page, is("posts"));
    }

    @Test
    public void whenCreatePost() {
        Post input = new Post(1, "New post");
        PostService postService = mock(PostService.class);
        CityService cityService = mock(CityService.class);
        PostControl postController = new PostControl(
            postService,
            cityService
        );
        String page = postController.createPost(input);
        verify(postService).create(input);
        assertThat(page, is("redirect:/posts"));
    }

    @Test
    public void whenFormAddPost() {
        List<City> cities = Arrays.asList(
            new City(1, "New city"),
            new City(2, "New city"));
        HttpSession session = mock(HttpSession.class);
        PostService postService = mock(PostService.class);
        CityService cityService = mock(CityService.class);
        Model model = mock(Model.class);
        PostControl postController = new PostControl(postService, cityService);
        when(cityService.getAllCities()).thenReturn(cities);
        String page = postController.formAddPost(model, session);
        verify(model).addAttribute("cities", cities);
        assertThat(page, is("addPost"));
    }

    @Test
    public void whenFormUpdatePost() {
        Post created = new Post(1, "new Post");
        Post update = new Post(1, "Update post",
            new City(1),
            "desc",
            LocalDateTime.now(),
            true);
        PostService postService = mock(PostService.class);
        CityService cityService = mock(CityService.class);
        PostControl postController = new PostControl(
            postService,
            cityService
        );
        postController.createPost(created);
        String page = postController.updatePost(update);
        verify(postService).update(update);
        assertThat(page, is("redirect:/posts"));
    }
}
