package ua.nechay.transfermaker.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author anechaev
 * @since 25.01.2023
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping
    public String test() {
        System.out.println("lol"); //TODO: clean
        return "Just a simple test content";
    }
}
