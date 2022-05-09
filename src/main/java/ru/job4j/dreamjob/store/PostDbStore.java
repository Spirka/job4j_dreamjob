package ru.job4j.dreamjob.store;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.City;
import ru.job4j.dreamjob.model.Post;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Class PostDbStore
 *
 * @author Kseniya Dergunova
 * @since 03.05.2022
 */
@Repository
public class PostDbStore {

    private static final Logger LOG = LoggerFactory.getLogger(PostDbStore.class);
    private final BasicDataSource pool;

    public PostDbStore(BasicDataSource pool) {
        this.pool = pool;
    }

    public List<Post> findAll() {
        List<Post> posts = new ArrayList<>();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement("SELECT * FROM post order by id")
        ) {
            try (ResultSet it = ps.executeQuery()) {
                while (it.next()) {
                    posts.add(new Post(it.getInt("id"),
                        it.getString("name"),
                        new City(it.getInt("city_id")),
                        it.getString("description"),
                        it.getTimestamp("created").toLocalDateTime(),
                        it.getBoolean("visible")));
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return posts;
    }

    public Post add(Post post) {
        String sql = "INSERT INTO post(name, city_id, description, created) VALUES (?, ?, ?, ?)";
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, post.getName());
            ps.setInt(2, post.getCity().getId());
            ps.setString(3, post.getDescription());
            ps.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            ps.execute();
            try (ResultSet id = ps.getGeneratedKeys()) {
                if (id.next()) {
                    post.setId(id.getInt(1));
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return post;
    }

    public void update(Post post) {
        String sql = "UPDATE post SET name = ?, city_id = ?, description = ?, visible = ? WHERE id = ?";
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, post.getName());
            ps.setInt(2, post.getCity().getId());
            ps.setString(3, post.getDescription());
            ps.setBoolean(4, post.isVisible());
            ps.setInt(5, post.getId());
            ps.execute();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public Post findById(int id) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement("SELECT * FROM post WHERE id = ?")) {
            ps.setInt(1, id);
            try (ResultSet it = ps.executeQuery()) {
                if (it.next()) {
                    return new Post(it.getInt("id"),
                        it.getString("name"),
                        new City(it.getInt("city_id")),
                        it.getString("description"),
                        it.getTimestamp("created").toLocalDateTime(),
                        it.getBoolean("visible"));
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage() + e);
        }
        return null;
    }

    public void clearTable() {
        try (Connection connection = pool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                "delete from post"
            );
            statement.execute();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
