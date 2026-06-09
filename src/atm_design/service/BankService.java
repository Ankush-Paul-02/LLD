package atm_design.service;

import atm_design.entities.BankAccount;

import java.util.HashMap;
import java.util.Map;

public class BankService {

    private final Map<String, BankAccount> bankAccounts = new HashMap<>();
    private final Map<String, String> cardToAccounts = new HashMap<>();
    private final Map<String, String> cardPins = new HashMap<>();
    private final Map<String, String> upiToAccounts = new HashMap<>();

    // mock
    public BankService() {
        bankAccounts.put("A1", new BankAccount("abc123", 1000));

        cardToAccounts.put("card1", "A1");
        cardPins.put("card1", "123456");

        upiToAccounts.put("upi1", "A1");
    }


    public boolean verifyUpi(String cred) {
        return upiToAccounts.containsKey(cred);
    }

    public BankAccount getBankAccountByUpi(String cred) {
        return bankAccounts.get(upiToAccounts.get(cred));
    }

    public boolean verifyCardPin(String cardNumber, String cred) {
        return cardToAccounts.containsKey(cardNumber) && cardPins.containsKey(cardNumber) && cardPins.get(cardNumber).equals(cred);
    }

    public BankAccount getBankAccountByCard(String cardNumber) {
        return bankAccounts.get(cardToAccounts.get(cardNumber));
    }
}
