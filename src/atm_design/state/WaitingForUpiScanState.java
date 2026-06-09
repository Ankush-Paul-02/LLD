package atm_design.state;

import atm_design.core.Atm;
import atm_design.strategy.AuthStrategy;

public class WaitingForUpiScanState implements AtmState {

    private final Atm atm;

    public WaitingForUpiScanState(Atm atm) {
        this.atm = atm;
    }

    @Override
    public void initiateTransaction(AuthStrategy authStrategy) {
        System.out.println("Session is already active.");
    }

    @Override
    public void provideCred(String cred) {
        if (atm.getAuthStrategy().authenticate(cred)) {
            System.out.println("Upi Scan Successful");
            atm.setState(atm.getAuthState());
        } else {
            System.out.println("Upi Scan Failed");
            endSession();
        }
    }

    @Override
    public void requestCash(int requiredAmount) {
        System.out.println("Please scan qr code.");
    }

    @Override
    public void endSession() {
        System.out.println("Clearing qr code..");
        atm.setAuthStrategy(null);
        atm.setState(atm.getIdleState());
    }
}
