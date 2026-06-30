package movie_ticket_booking_design.strategy.locking;

public interface LockProvider {
    boolean tryLock(String key, Integer userId, long ttl);

    void unlock(String key);

    boolean isLockExpired(String key);

    boolean isLockBy(String key, Integer userId);
}
