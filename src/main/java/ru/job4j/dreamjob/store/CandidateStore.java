package ru.job4j.dreamjob.store;

import ru.job4j.dreamjob.model.Candidate;
import ru.job4j.dreamjob.model.Post;

import java.time.Instant;
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
        this.candidates.put(1, new Candidate(1, "Иванов Иван Иванович", "Описание", Instant.parse("1989-04-01T21:00:00Z")));
        this.candidates.put(2, new Candidate(2, "Петров Петр Петрович", "Описание", Instant.parse("1978-04-01T21:00:00Z")));
        this.candidates.put(3, new Candidate(3, "Иванова Мария Ивановна", "Описание", Instant.parse("1999-04-01T21:00:00Z")));
    }

    public static CandidateStore instOf() {
        return INST;
    }

    public Collection<Candidate> findAll() {
        return candidates.values();
    }
}
