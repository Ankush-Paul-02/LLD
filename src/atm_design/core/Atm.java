package atm_design.core;

import atm_design.hardware.CashDispenser;
import atm_design.service.BankService;
import atm_design.state.*;
import atm_design.strategy.AuthStrategy;

public class Atm {

    private final AtmState idleState;
    private final AtmState authState;
    private final AtmState waitingForPinState;
    private final AtmState waitingForUpiPinState;

    private AtmState currentState;
    private AuthStrategy authStrategy;

    private final BankService bankService;
    private final CashDispenser cashDispenser;

    public Atm(BankService bankService, CashDispenser cashDispenser) {
        this.idleState = new IdleState(this);
        this.waitingForPinState = new WaitingForPinState(this);
        this.waitingForUpiPinState = new WaitingForUpiScanState(this);
        this.authState = new AuthenticatedState(this);

        this.currentState = this.idleState;
        this.bankService = bankService;
        this.cashDispenser = cashDispenser;
    }

    // Pass-through state methods
    public void initiateTransaction(AuthStrategy strategy) {
        currentState.initiateTransaction(strategy);
    }

    public void provideCredentials(String credentials) {
        currentState.provideCred(credentials);
    }

    public void requestCash(int amount) {
        currentState.requestCash(amount);
    }


    public AtmState getAtmForUpiScanState() {
        return null;
    }

    public void setAuthStrategy(AuthStrategy authStrategy) {
        this.authStrategy = authStrategy;
    }

    public void setState(AtmState nextAtmState) {
        this.currentState = nextAtmState;
    }

    public AtmState getAuthState() {
        return authState;
    }

    public AtmState getIdleState() {
        return idleState;
    }

    public AtmState getWaitingForPinState() {
        return waitingForPinState;
    }

    public AtmState getWaitingForUpiPinState() {
        return waitingForUpiPinState;
    }

    public AtmState getCurrentState() {
        return currentState;
    }

    public AuthStrategy getAuthStrategy() {
        return authStrategy;
    }

    public BankService getBankService() {
        return bankService;
    }

    public CashDispenser getCashDispenser() {
        return cashDispenser;
    }
}
