package atm_design.state;

import atm_design.core.Atm;
import atm_design.strategy.AuthStrategy;

public class IdleState implements AtmState {

    private final Atm atm;

    public IdleState(Atm atm) {
        this.atm = atm;
    }

    @Override
    public void initiateTransaction(AuthStrategy authStrategy) {
        atm.setAuthStrategy(authStrategy);
        System.out.println("Initiating transaction using authStrategy: " + authStrategy.getAuthMethodName());
        atm.setState(authStrategy.getNextAtmState(atm));
    }

    @Override
    public void provideCred(String cred) {
        System.out.println("Please initiate transaction first...");
    }

    @Override
    public void requestCash(int requiredAmount) {
        System.out.println("Please initiate transaction first...");
    }

    @Override
    public void endSession() {
        System.out.println("No active session...");
    }
}
