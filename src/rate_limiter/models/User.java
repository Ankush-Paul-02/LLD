package rate_limiter.models;

import rate_limiter.enums.Tier;

public record User(
        Integer id,
        Tier tier
) {
}
