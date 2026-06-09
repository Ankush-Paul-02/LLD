package atm_design.strategy;

import atm_design.entities.BankAccount;
import atm_design.core.Atm;
import atm_design.service.BankService;
import atm_design.state.AtmState;

public class UpiAuth implements AuthStrategy {

    private BankAccount bankAccount;
    private final BankService bankService;

    public UpiAuth(BankService bankService) {
        this.bankService = bankService;
    }

    @Override
    public boolean authenticate(String cred) {
        if (bankService.verifyUpi(cred)) {
            this.bankAccount = bankService.getBankAccountByUpi(cred);
            return true;
        }
        return false;
    }

    @Override
    public BankAccount getBankAccount() {
        return bankAccount;
    }

    @Override
    public AtmState getNextAtmState(Atm atm) {
        System.out.println("Please scan the qr code using your upi app!");
        return atm.getAtmForUpiScanState();
    }

    @Override
    public String getAuthMethodName() {
        return UpiAuth.class.getName();
    }
}
