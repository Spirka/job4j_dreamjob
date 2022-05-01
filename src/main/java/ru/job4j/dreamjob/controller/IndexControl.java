package ru.job4j.dreamjob.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Class IndexControl
 *
 * @author Kseniya Dergunova
 * @since 02.05.2022
 */
@RestController
public class IndexControl {

    @GetMapping("/index")
    public String index() {
        return "Greetings from Spring Boot!";
    }
}
