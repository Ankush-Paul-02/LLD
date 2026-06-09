package atm_design.strategy;

import atm_design.entities.BankAccount;
import atm_design.core.Atm;
import atm_design.state.AtmState;

public interface AuthStrategy {
    boolean authenticate(String cred);

    BankAccount getBankAccount();

    AtmState getNextAtmState(Atm atm);

    String getAuthMethodName();
}
