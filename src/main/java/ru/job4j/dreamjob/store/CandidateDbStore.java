package ru.job4j.dreamjob.store;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.Candidate;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class CandidateDbStore
 *
 * @author Kseniya Dergunova
 * @since 08.05.2022
 */
@Repository
public class CandidateDbStore {

    private static final Logger LOG = LoggerFactory.getLogger(CandidateDbStore.class);
    private final BasicDataSource pool;

    public CandidateDbStore(BasicDataSource pool) {
        this.pool = pool;
    }

    public List<Candidate> findAll() {
        List<Candidate> candidates = new ArrayList<>();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement("SELECT * FROM candidate order by id")
        ) {
            try (ResultSet it = ps.executeQuery()) {
                while (it.next()) {
                    candidates.add(new Candidate(it.getInt("id"),
                        it.getString("name"),
                        it.getString("description"),
                        it.getTimestamp("date_of_birth").toLocalDateTime(),
                        it.getBytes("photo")));
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return candidates;
    }

    public Candidate add(Candidate candidate) {
        String sql = "INSERT INTO candidate(name, description, photo, date_of_birth) VALUES (?, ?, ?, ?)";
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, candidate.getName());
            ps.setString(2, candidate.getDescription());
            ps.setBytes(3, candidate.getPhoto());
            ps.setTimestamp(4, Timestamp.valueOf(candidate.getDateOfBirth()));
            ps.execute();
            try (ResultSet id = ps.getGeneratedKeys()) {
                if (id.next()) {
                    candidate.setId(id.getInt(1));
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return candidate;
    }

    public void update(Candidate candidate) {
        String sql = "UPDATE candidate SET name = ?, description = ?, photo = ?, date_of_birth = ? WHERE id = ?";
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, candidate.getName());
            ps.setString(2, candidate.getDescription());
            ps.setBytes(3, candidate.getPhoto());
            ps.setTimestamp(4, Timestamp.valueOf(candidate.getDateOfBirth()));
            ps.setInt(5, candidate.getId());
            ps.execute();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public Candidate findById(int id) {
        String sql = "SELECT * FROM candidate WHERE id = ?";
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    return new Candidate(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getTimestamp("date_of_birth").toLocalDateTime(),
                        rs.getBytes("photo"));
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return null;
    }

    public void clearTable() {
        try (Connection connection = pool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                "delete from candidate"
            );
            statement.execute();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
