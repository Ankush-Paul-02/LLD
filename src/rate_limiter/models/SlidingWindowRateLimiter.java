package rate_limiter.models;

import rate_limiter.config.RateLimiterConfig;
import rate_limiter.enums.RateLimitType;

import java.util.Deque;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

public class SlidingWindowRateLimiter extends RateLimiter {

    private final Map<Integer, Deque<Long>> userRequests = new ConcurrentHashMap<>();

    public SlidingWindowRateLimiter(RateLimiterConfig configuration) {
        super(configuration, RateLimitType.SLIDING_WINDOW);
    }

    @Override
    public boolean allowRequest(Integer userId) {

        long now = System.currentTimeMillis();

        Deque<Long> request = userRequests.computeIfAbsent(
                userId,
                k -> new ConcurrentLinkedDeque<>()
        );

        synchronized (request) {

            // remove requests that are outside of time window
            while (!request.isEmpty() && now - request.peekFirst() >= getConfiguration().windowInSeconds()) {
                request.pollFirst();
            }

            // reject the request if limit is already reached
            if (request.size() >= getConfiguration().maxRequests()) {
                return false;
            }

            request.addLast(now);
            return true;
        }
    }
}
