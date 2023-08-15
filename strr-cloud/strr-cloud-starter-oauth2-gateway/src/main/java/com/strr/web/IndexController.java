package com.strr.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.result.view.Rendering;

@Controller
public class IndexController {
    @Value("${url.web:http://127.0.0.1:8080}")
    private String webUrl;

    @GetMapping("/")
    public Rendering index() {
        return Rendering.redirectTo(webUrl).build();
    }
}
