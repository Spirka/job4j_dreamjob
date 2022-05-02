package ru.job4j.dreamjob.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.dreamjob.model.Candidate;
import ru.job4j.dreamjob.store.CandidateStore;

/**
 * Class CandidateControl
 *
 * @author Kseniya Dergunova
 * @since 02.05.2022
 */
@Controller
public class CandidateControl {

    private final CandidateStore store = CandidateStore.instOf();

    @GetMapping("/candidates")
    public String candidate(Model model) {
        model.addAttribute("candidates", store.findAll());
        return "candidates";
    }

    @GetMapping("/formAddCandidate")
    public String formAddCandidate(Model model) {
        return "addCandidate";
    }

    @PostMapping("/createCandidate")
    public String createPost(@ModelAttribute Candidate candidate) {
        store.create(candidate);
        return "redirect:/candidates";
    }

    @GetMapping("/formUpdateCandidate/{candidateId}")
    public String formUpdatePost(Model model, @PathVariable("candidateId") int id) {
        model.addAttribute("candidate", store.findById(id));
        return "updateCandidate";
    }

    @PostMapping("/updateCandidate")
    public String updatePost(@ModelAttribute Candidate candidate) {
        store.update(candidate);
        return "redirect:/candidates";
    }
}
