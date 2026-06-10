package rate_limiter.models;

import rate_limiter.config.RateLimiterConfig;
import rate_limiter.enums.RateLimitType;

public abstract class RateLimiter {
    private final RateLimiterConfig configuration;
    private final RateLimitType rateLimitType;

    public RateLimiter(RateLimiterConfig configuration, RateLimitType rateLimitType) {
        this.configuration = configuration;
        this.rateLimitType = rateLimitType;
    }

    public abstract boolean allowRequest(Integer userId);

    public RateLimiterConfig getConfiguration() {
        return configuration;
    }
}
