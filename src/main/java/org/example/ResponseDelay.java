package org.example;

import java.util.concurrent.ThreadLocalRandom;

public class ResponseDelay
{
    public static void startDelay() throws InterruptedException {
        long delay = ThreadLocalRandom.current()
                .nextLong(1000, 2001);

        Thread.sleep(delay);
    }

    public static void startDelay(int from, int to) throws InterruptedException {
        long delay = ThreadLocalRandom.current()
                .nextLong(from, to + 1);

        Thread.sleep(delay);
    }
}
