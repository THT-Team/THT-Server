package com.tht.api.app.controller.restdocs;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestDocsController {

    @GetMapping("/rest-docs/{text}")
    public ResponseEntity<String> getRestDocsHello(@PathVariable(name = "text") final String  text) {
        return ResponseEntity.ok("Let's Start Spring Rest Docs - " + text);
    }
}
