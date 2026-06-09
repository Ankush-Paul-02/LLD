package atm_design.state;

import atm_design.core.Atm;
import atm_design.strategy.AuthStrategy;

public class WaitingForPinState implements AtmState {

    private final Atm atm;

    public WaitingForPinState(Atm atm) {
        this.atm = atm;
    }

    @Override
    public void initiateTransaction(AuthStrategy authStrategy) {
        System.out.println("Session is already initiated");
    }

    @Override
    public void provideCred(String cred) {
        if (atm.getAuthStrategy().authenticate(cred)) {
            System.out.println("Pin accepted");
            atm.setState(atm.getAuthState());
        } else {
            System.out.println("Invalid credential");
            endSession();
        }
    }

    @Override
    public void requestCash(int requiredAmount) {
        System.out.println("Pin requested");
    }

    @Override
    public void endSession() {
        System.out.println("Session ended");
        atm.setState(atm.getIdleState());
        atm.setState(null);
    }
}
