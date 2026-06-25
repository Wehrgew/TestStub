package org.example;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
public class TestController {

    DataBaseWorker dbWorker = new DataBaseWorker();

    @GetMapping("/")
    public User hello(
            @RequestParam String login
    ) throws InterruptedException {

        ResponseDelay.startDelay();

        return dbWorker.getUserByLogin(login);
    }

    @PostMapping("/")
    public PostResponseRecord login(@Valid @RequestBody PostRequestRecord requestBody) throws InterruptedException {
        ResponseDelay.startDelay();
        User user = new User(
                requestBody.login(),
                requestBody.password(),
                Date.valueOf(LocalDate.now()),
                requestBody.email()

        );
        dbWorker.insertUser(user);
        return new PostResponseRecord(
                requestBody.login(),
                requestBody.password(),
                LocalDateTime.now(),
                requestBody.email()
        );
    }
}
