package rate_limiter.models;

import rate_limiter.config.RateLimiterConfig;
import rate_limiter.enums.RateLimitType;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class TokenBucketRateLimiter extends RateLimiter {

    private final Map<Integer, Integer> tokens = new ConcurrentHashMap<>();
    private final Map<Integer, Long> lastRefillTime = new HashMap<>();


    public TokenBucketRateLimiter(RateLimiterConfig configuration) {
        super(configuration, RateLimitType.TOKEN_BUCKET);
    }

    @Override
    public boolean allowRequest(Integer userId) {
        AtomicBoolean isAllowed = new AtomicBoolean(false);
        long now = System.currentTimeMillis();

        tokens.compute(userId, (k, currentTokens) -> {
            if (currentTokens == null) {
                currentTokens = getConfiguration().maxRequests();
            }

            currentTokens = refillTokens(k, currentTokens, now);

            if (currentTokens > 0) {
                isAllowed.set(true);
                return currentTokens - 1;
            }
            return currentTokens;
        });
        return isAllowed.get();
    }

    private int refillTokens(Integer userId, int currentTokens, long now) {
        RateLimiterConfig config = getConfiguration();

        int capacity = config.maxRequests();
        int refillTokensPerSeconds = config.windowInSeconds();

        long lastRefill = lastRefillTime.getOrDefault(userId, now);

        long timeDiff = (now - lastRefill) / 1000;

        int tokensToAdd = (int) timeDiff * refillTokensPerSeconds;

        if (tokensToAdd > 0) {
            currentTokens = Math.min(capacity, tokensToAdd + currentTokens);
            lastRefillTime.put(userId, now);
        }

        return currentTokens;
    }
}
