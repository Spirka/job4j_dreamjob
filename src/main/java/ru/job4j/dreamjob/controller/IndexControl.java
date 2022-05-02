package ru.job4j.dreamjob.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Class IndexControl
 *
 * @author Kseniya Dergunova
 * @since 02.05.2022
 */
@ThreadSafe
@Controller
public class IndexControl {

    @GetMapping("/index")
    public String index() {
        return "index";
    }
}
