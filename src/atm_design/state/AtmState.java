package atm_design.state;

import atm_design.strategy.AuthStrategy;

public interface AtmState {
    void initiateTransaction(AuthStrategy authStrategy);

    void provideCred(String cred);

    void requestCash(int requiredAmount);

    void endSession();
}
