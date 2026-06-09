package atm_design.hardware;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CashDispenser {

    private final DenominationDispenser headChain;
    private final Lock lock = new ReentrantLock();

    public CashDispenser() {
        headChain = new DenominationDispenser(500, 10);
        DenominationDispenser d100 = new DenominationDispenser(100, 10);
        DenominationDispenser d50 = new DenominationDispenser(50, 10);   // ₹500 in 50s
        DenominationDispenser d20 = new DenominationDispenser(20, 20);   // ₹400 in 20s
        DenominationDispenser d10 = new DenominationDispenser(10, 50);

        headChain.setNext(d100);
        headChain.setNext(d50);
        headChain.setNext(d20);
        headChain.setNext(d10);
    }


    public boolean dispenseCash(int requiredAmount) {
        lock.lock();
        try {
            if (requiredAmount % 10 != 0) {
                System.out.println("Hardware Error: Amount must be a multiple of 10.");
                return false;
            }
            Map<Integer, Integer> proposal = new LinkedHashMap<>();
            if (!headChain.canDispense(requiredAmount, proposal)) {
                System.out.println("Hardware Error: Exact denominations unavailable for ₹" + requiredAmount + ".");
                return false;
            }
            System.out.println("--- Dispensing Physical Cash ---");
            headChain.executeDispense(proposal);
            System.out.println("--------------------------------");
            return true;
        } finally {
            lock.unlock();
        }
    }
}
