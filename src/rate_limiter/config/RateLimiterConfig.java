package rate_limiter.config;

public record RateLimiterConfig(
        int maxRequests,
        int windowInSeconds
) {
}
