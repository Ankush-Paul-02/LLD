package atm_design.state;

import atm_design.entities.BankAccount;
import atm_design.core.Atm;
import atm_design.strategy.AuthStrategy;

public class AuthenticatedState implements AtmState {

    private final Atm atm;

    public AuthenticatedState(Atm atm) {
        this.atm = atm;
    }

    @Override
    public void initiateTransaction(AuthStrategy authStrategy) {
        System.out.println("Already authenticated");
    }

    @Override
    public void provideCred(String cred) {
        System.out.println("Already authenticated");
    }

    @Override
    public void requestCash(int requiredAmount) {
        BankAccount bankAccount = atm.getAuthStrategy().getBankAccount();
        if (bankAccount.getTotalBalance() >= requiredAmount) {
            if (bankAccount.withDraw(requiredAmount)) {
                if (atm.getCashDispenser().dispenseCash(requiredAmount)) {
                    System.out.println("Cash has been dispensed. Remaining balance: " + bankAccount.getTotalBalance());
                } else {
                    System.out.println("Dispenser has failed. Remaining balance: " + bankAccount.getTotalBalance());
                }
            } else {
                System.out.println("Transaction failed.");
            }
        } else {
            System.out.println("Insufficient funds.");
        }
        endSession();
    }

    @Override
    public void endSession() {
        System.out.println("Session ended");
        atm.setState(atm.getIdleState());
        atm.setAuthStrategy(null);
    }
}
