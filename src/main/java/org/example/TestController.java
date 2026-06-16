package org.example;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
public class TestController {

    @GetMapping("/")
    public Map<String, String> hello() throws InterruptedException {
        ResponseDelay.startDelay();
        return Map.of(
                "login", "login1",
                "password", "password1"
        );
    }

    @PostMapping("/")
    public PostResponseRecord login(@Valid @RequestBody PostRequestRecord requestBody) throws InterruptedException {
        ResponseDelay.startDelay();
        return new PostResponseRecord(
                requestBody.login(),
                requestBody.password(),
                LocalDateTime.now()
        );
    }
}
