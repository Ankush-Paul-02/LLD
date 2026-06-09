package atm_design.entities;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    private final String accountNumber;
    private double totalBalance;
    private final Lock lock = new ReentrantLock();

    public BankAccount(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.totalBalance = initialBalance;
    }

    public double getTotalBalance() {
        return totalBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public boolean withDraw(double requiredAmount) {
        lock.lock();
        try {
            if (totalBalance >= requiredAmount) {
                // simulate network delay to test thread safety
                Thread.sleep(50);
                totalBalance -= requiredAmount;
                return true;
            }
            return false;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        } finally {
            lock.unlock();
        }
    }
}
