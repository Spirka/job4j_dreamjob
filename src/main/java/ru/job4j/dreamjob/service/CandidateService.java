package ru.job4j.dreamjob.service;

import org.springframework.stereotype.Service;
import ru.job4j.dreamjob.model.Candidate;
import ru.job4j.dreamjob.store.CandidateStore;

import java.util.List;

/**
 * Class CandidateService
 *
 * @author Kseniya Dergunova
 * @since 02.05.2022
 */
@Service
public class CandidateService {

    private static final CandidateService INST = new CandidateService();

    CandidateStore store = CandidateStore.instOf();

    public static CandidateService instOf() {
        return INST;
    }

    public List<Candidate> findAll() {
        return store.findAll();
    }

    public Candidate findById(int id) {
        return store.findById(id);
    }

    public void create(Candidate candidate) {
        store.add(candidate);
    }

    public void update(Candidate candidate) {
        store.update(candidate);
    }

}
