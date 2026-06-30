package movie_ticket_booking_design.strategy.locking;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class InMemoryLockProvider implements LockProvider {

    private static class Expiry {
        private final long deadline;
        private final Integer userId;

        Expiry(long now, long ttl, Integer userId) {
            this.deadline = now + ttl;
            this.userId = userId;
        }
    }

    private final Map<String, Expiry> locks = new ConcurrentHashMap<>();
    private final ScheduledExecutorService sweeper = Executors.newSingleThreadScheduledExecutor();

    public InMemoryLockProvider() {
        sweeper.scheduleAtFixedRate(this::sweep, 1, 1, TimeUnit.MINUTES);
    }


    private void sweep() {
        long now = System.currentTimeMillis();
        locks.entrySet().removeIf(entry -> entry.getValue().deadline <= now);
    }

    @Override
    public boolean tryLock(String key, Integer userId, long ttl) {
        long now = System.currentTimeMillis();

        Expiry expiry = new Expiry(now, ttl, userId);

        Expiry result = locks.compute(key, (k, existing) -> {
            if (existing == null || existing.deadline <= now) {
                return expiry;
            }
            return existing;
        });

        return result == expiry;
    }

    @Override
    public void unlock(String key) {
        locks.remove(key);
    }

    @Override
    public boolean isLockExpired(String key) {
        Expiry expiry = locks.get(key);

        if (expiry == null) {
            return true;
        }

        return expiry.deadline <= System.currentTimeMillis();
    }

    @Override
    public boolean isLockBy(String key, Integer userId) {
        Expiry expiry = locks.get(key);

        return expiry != null
                && expiry.deadline > System.currentTimeMillis()
                && expiry.userId.equals(userId);
    }
}