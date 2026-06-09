package atm_design.strategy;

import atm_design.entities.BankAccount;
import atm_design.entities.Card;
import atm_design.core.Atm;
import atm_design.service.BankService;
import atm_design.state.AtmState;

public class DebitCardAuth implements AuthStrategy {

    private final Card card;
    private final BankService bankService;

    public DebitCardAuth(Card card, BankService bankService) {
        this.card = card;
        this.bankService = bankService;
    }

    @Override
    public boolean authenticate(String cred) {
        return bankService.verifyCardPin(card.cardNumber(), cred);
    }

    @Override
    public BankAccount getBankAccount() {
        return bankService.getBankAccountByCard(card.cardNumber());
    }

    @Override
    public AtmState getNextAtmState(Atm atm) {
        System.out.println("Card inserted. Please enter your pin!");
        return atm.getWaitingForUpiPinState();
    }

    @Override
    public String getAuthMethodName() {
        return DebitCardAuth.class.getName();
    }
}
