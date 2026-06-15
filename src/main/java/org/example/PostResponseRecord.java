package org.example;

import java.time.LocalDateTime;

public record PostResponseRecord(String login, String password, LocalDateTime date) {}
