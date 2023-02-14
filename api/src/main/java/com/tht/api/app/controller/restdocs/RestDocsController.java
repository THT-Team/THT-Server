package com.tht.api.app.controller.restdocs;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RestDocsController {

    @GetMapping("/")
    public String getRestDocsHello() {
        return "docs/index.html";
    }

}
