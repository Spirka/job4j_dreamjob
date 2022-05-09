package ru.job4j.dreamjob.store;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.job4j.dreamjob.Main;
import ru.job4j.dreamjob.model.Candidate;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CandidateDbStoreTest {

    private static CandidateDbStore store;

    @BeforeClass
    public static void init() {
        store = new CandidateDbStore(new Main().loadPool());
    }

    @After
    public void clearTable() throws Exception {
        store.clearTable();
    }

    @Test
    public void findAll() {
        Candidate first = new Candidate(0, "Ivanov Ivan",
            "java developer",
            LocalDateTime.of(1990, Month.JANUARY, 15, 9, 15),
            null);
        Candidate second = new Candidate(0, "Petrov Petr",
            "java developer",
            LocalDateTime.of(1999, Month.JANUARY, 1, 9, 15),
            null);
        Arrays.asList(first, second).forEach(store::add);
        int size = store.findAll().size();
        assertThat(size, is(2));
    }

    @Test
    public void add() {
        Candidate candidate = new Candidate(0, "Ivanov Ivan",
            "java developer",
            LocalDateTime.of(1990, Month.JANUARY, 15, 9, 15),
            null);
        Candidate added = store.add(candidate);
        Candidate candidateInDb = store.findById(added.getId());
        assertThat(candidateInDb.getName(), is(added.getName()));
    }

    @Test
    public void update() {
        Candidate candidate = new Candidate(0, "Ivanov Ivan",
            "java developer",
            LocalDateTime.of(1990, Month.JANUARY, 15, 9, 15),
            null);
        Candidate added = store.add(candidate);
        Candidate updated = new Candidate(added.getId(), "Ivanov Ivan Ivanovich",
            "java developer updated",
            LocalDateTime.of(1990, Month.JANUARY, 15, 9, 15),
            null);
        store.update(updated);
        Candidate candidateInDb = store.findById(added.getId());
        assertThat(candidateInDb.getName(), is(updated.getName()));
    }

    @Test
    public void findById() {
        Candidate candidate = new Candidate(0, "Ivanov Ivan",
            "java developer",
            LocalDateTime.of(1990, Month.JANUARY, 15, 9, 15),
            null);
        Candidate created = store.add(candidate);
        int id = created.getId();
        assertThat(store.findById(id), is(created));
    }
}
