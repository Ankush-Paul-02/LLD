package rate_limiter.service;

import rate_limiter.config.RateLimiterConfig;
import rate_limiter.enums.RateLimitType;
import rate_limiter.enums.Tier;
import rate_limiter.factory.RateLimiterFactory;
import rate_limiter.models.RateLimiter;
import rate_limiter.models.User;

import java.util.HashMap;
import java.util.Map;

public class RateLimiterService {

    private final Map<Tier, RateLimiter> rateLimiters = new HashMap<>();

    public RateLimiterService() {
        rateLimiters.put(
                Tier.FREE,
                RateLimiterFactory.getInstance(
                        RateLimitType.TOKEN_BUCKET,
                        new RateLimiterConfig(
                                10,
                                60
                        )
                )
        );

        rateLimiters.put(
                Tier.PRO,
                RateLimiterFactory.getInstance(
                        RateLimitType.SLIDING_WINDOW,
                        new RateLimiterConfig(
                                100,
                                60
                        )
                )
        );
    }

    public boolean allowRequest(User user) {
        RateLimiter rateLimiter = rateLimiters.get(user.tier());
        if (rateLimiter == null) {
            System.out.println("User tier " + user.tier() + " is not available");
            throw new RuntimeException("User tier " + user.tier() + " is not available");
        }
        return rateLimiter.allowRequest(user.id());
    }
}
