package rate_limiter;

import rate_limiter.enums.Tier;
import rate_limiter.models.User;
import rate_limiter.service.RateLimiterService;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class RateLimiterDemo {
    public static void main(String[] args) throws InterruptedException {

        RateLimiterService rateLimiterService = new RateLimiterService();

        User freeUser = new User(1, Tier.FREE);
        User proUser = new User(2, Tier.PRO);

        for (int i = 1; i <= 15; i++) {
            boolean isPass = rateLimiterService.allowRequest(freeUser);

            System.out.println("Request: " + i + ", for user:: " + freeUser + " is: " + (isPass ? "executed" : "failed"));
            Thread.sleep(1000);
        }

        int passed = 0, failed = 0;
        for (int i = 1; i <= 500; i++) {
            boolean isPass = rateLimiterService.allowRequest(proUser);
            System.out.println("Request: " + i + ", for user:: " + proUser + " is: " + (isPass ? "executed" : "failed"));
            if (isPass) {
                passed++;
            } else {
                failed++;
            }
            System.out.println("Passed: " + passed + ", Failed: " + failed);
        }

        checkConcurrentRateLimiting(rateLimiterService);
    }

    private static void checkConcurrentRateLimiting(RateLimiterService rateLimiterService)
            throws InterruptedException {

        User freeUser = new User(1, Tier.FREE);

        int totalThreads = 100;

        AtomicInteger allowed = new AtomicInteger();
        AtomicInteger blocked = new AtomicInteger();

        try (ExecutorService es = Executors.newFixedThreadPool(totalThreads)) {

            CyclicBarrier barrier = new CyclicBarrier(totalThreads);
            CountDownLatch latch = new CountDownLatch(totalThreads);

            for (int i = 1; i <= totalThreads; i++) {

                final int requestNumber = i;

                es.submit(() -> {
                    try {
                        // Ensure all threads start together.
                        barrier.await();

                        boolean isAllowed = rateLimiterService.allowRequest(freeUser);

                        if (isAllowed) {
                            allowed.incrementAndGet();
                        } else {
                            blocked.incrementAndGet();
                        }

                        System.out.println(
                                "Request: " + requestNumber +
                                        " -> " +
                                        (isAllowed ? "ALLOWED" : "BLOCKED") +
                                        ", Thread: " +
                                        Thread.currentThread().getName()
                        );

                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        latch.countDown();
                    }
                });
            }

            // Wait for all requests to finish.
            latch.await();

            System.out.println("\n===== Summary =====");
            System.out.println("Total Requests : " + totalThreads);
            System.out.println("Allowed        : " + allowed.get());
            System.out.println("Blocked        : " + blocked.get());
        }
    }
}
