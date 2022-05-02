package ru.job4j.dreamjob.store;

import ru.job4j.dreamjob.model.Candidate;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Class CandidateStore
 * Хранилище кандидатов
 *
 * @author Kseniya Dergunova
 * @since 02.05.2022
 */
public class CandidateStore {

    private static final CandidateStore INST = new CandidateStore();

    private final Map<Integer, Candidate> candidates = new ConcurrentHashMap<>();

    public CandidateStore() {
        this.candidates.put(1, new Candidate(1, "Иванов Иван Иванович", "Описание", LocalDateTime.of(1980, 1, 29, 12, 0)));
        this.candidates.put(2, new Candidate(2, "Петров Петр Петрович", "Описание", LocalDateTime.of(1989, 3, 15, 12, 0)));
        this.candidates.put(3, new Candidate(3, "Иванова Мария Ивановна", "Описание", LocalDateTime.of(1999, 2, 23, 12, 0)));
    }

    public static CandidateStore instOf() {
        return INST;
    }

    public Collection<Candidate> findAll() {
        return candidates.values();
    }

    public Candidate findById(int id) {
        return candidates.get(id);
    }

    public void create(Candidate candidate) {
        candidates.putIfAbsent(candidate.getId(), candidate);
    }

    public void update(Candidate candidate) {
        candidates.put(candidate.getId(), candidate);
    }
}
