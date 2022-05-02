package ru.job4j.dreamjob.store;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.Candidate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Class CandidateStore
 * Хранилище кандидатов
 *
 * @author Kseniya Dergunova
 * @since 02.05.2022
 */
@ThreadSafe
@Repository
public class CandidateStore {

    private final Map<Integer, Candidate> candidates = new ConcurrentHashMap<>();
    private final AtomicInteger ids = new AtomicInteger(4);

    public CandidateStore() {
        this.candidates.put(1, new Candidate(1, "Иванов Иван Иванович"));
        this.candidates.put(2, new Candidate(2, "Петров Петр Петрович"));
        this.candidates.put(3, new Candidate(3, "Иванова Мария Ивановна"));
    }


    public List<Candidate> findAll() {
        return new ArrayList<>(candidates.values());
    }

    public Candidate findById(int id) {
        return candidates.get(id);
    }

    public void add(Candidate candidate) {
        candidate.setId(ids.incrementAndGet());
        candidates.put(candidate.getId(), candidate);
    }

    public void update(Candidate candidate) {
        candidates.put(candidate.getId(), candidate);
    }
}
