package rate_limiter.factory;

import rate_limiter.config.RateLimiterConfig;
import rate_limiter.enums.RateLimitType;
import rate_limiter.models.RateLimiter;
import rate_limiter.models.SlidingWindowRateLimiter;
import rate_limiter.models.TokenBucketRateLimiter;

public class RateLimiterFactory {
    public static RateLimiter getInstance(RateLimitType type, RateLimiterConfig config) {
        return switch (type) {
            case TOKEN_BUCKET -> new TokenBucketRateLimiter(config);
            case SLIDING_WINDOW -> new SlidingWindowRateLimiter(config);
        };
    }
}
