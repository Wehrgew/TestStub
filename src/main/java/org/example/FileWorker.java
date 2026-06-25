package org.example;

import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.Random;

@Service
public class FileWorker {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final Path filePath = Paths.get("data.txt");

    public static synchronized void save(Object object) {
        try {
            String json = objectMapper.writeValueAsString(object);
            Files.writeString(
                    filePath,
                    json + System.lineSeparator(),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
            );
        } catch (IOException e) {
            throw new RuntimeException("Ошибка записи в файл", e);
        }
    }

    public static String printRandomJson() {
        Path filePath = Paths.get("users.txt");

        try {
            List<String> lines = Files.readAllLines(filePath);

            if (lines.isEmpty()) {
                System.out.println("Файл пуст");
                return "No data";
            }

            Random random = new Random();
            String randomJson = lines.get(random.nextInt(lines.size()));
            return randomJson;
        } catch (IOException e) {
            throw new RuntimeException("Ошибка чтения файла", e);
        }

    }
}
