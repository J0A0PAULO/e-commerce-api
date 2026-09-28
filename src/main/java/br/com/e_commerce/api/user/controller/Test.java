package br.com.e_commerce.api.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/test")
@RestController
public class Test {

    @GetMapping
    public String test() {
        return "Retorna um teste";
    }


}
