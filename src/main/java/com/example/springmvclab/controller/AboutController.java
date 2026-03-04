package com.example.springmvclab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AboutController {

    @GetMapping("/about")
    public String about(Model model) {

        model.addAttribute("appName", "Spring MVC Lab");
        model.addAttribute("version", "1.0");
        model.addAttribute("author", "Gavril Ziro Karna");

        List<String> technologies = List.of(
                "Spring Boot",
                "Thymeleaf",
                "Tailwind CSS",
                "Java 25"
        );

        model.addAttribute("technologies", technologies);

        return "about";
    }
}
