package ru.job4j.dreamjob.store;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.job4j.dreamjob.Main;
import ru.job4j.dreamjob.model.City;
import ru.job4j.dreamjob.model.Post;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Class PostDBStoreTest
 *
 * @author Kseniya Dergunova
 * @since 08.05.2022
 */
public class PostDbStoreTest {

    private static PostDbStore store;

    @BeforeClass
    public static void init() {
        store = new PostDbStore(new Main().loadPool());
    }

    @After
    public void clearTable() {
        store.clearTable();
    }

    @Test
    public void whenCreatePost() {
        Post post = new Post(0, "Java Job");
        post.setCity(new City(1));
        Post created = store.add(post);
        Post postInDb = store.findById(created.getId());
        assertThat(postInDb.getName(), is(post.getName()));
    }

    @Test
    public void whenUpdatePost() {
        Post post = new Post(0, "Java Job");
        post.setCity(new City(1));
        Post created = store.add(post);
        Post updated = new Post(created.getId(), "Updated Java Job");
        updated.setCity(new City(2));
        store.update(updated);
        Post postInDb = store.findById(created.getId());
        assertThat(postInDb.getName(), is("Updated Java Job"));
        assertThat(postInDb.getCity().getId(), is(updated.getCity().getId()));
    }

    @Test
    public void whenFindByIdThenItFound() {
        Post post = new Post(0, "Java Job");
        post.setCity(new City(1));
        Post created = store.add(post);
        int id = created.getId();
        assertThat(store.findById(id), is(created));
    }

    @Test
    public void whenFindAll() {
        Post first = new Post(0, "Java Job first");
        first.setCity(new City(1));
        Post second = new Post(0, "Java Job second");
        second.setCity(new City(2));
        Post third = new Post(0, "Java Job third");
        third.setCity(new City(3));
        Arrays.asList(first, second, third).forEach(store::add);
        List<Post> postList = store.findAll();
        assertThat(postList.size(), is(3));
    }
}
