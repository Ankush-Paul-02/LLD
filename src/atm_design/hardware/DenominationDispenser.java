package atm_design.hardware;

import java.util.Map;

public class DenominationDispenser {
    private final int denomination;
    private int count;
    private DenominationDispenser next;

    public DenominationDispenser(int denomination, int initialAmount) {
        this.denomination = denomination;
        this.count = initialAmount;
    }

    public void setNext(DenominationDispenser next) {
        this.next = next;
    }

    public boolean canDispense(int remainingAmount, Map<Integer, Integer> proposal) {
        int notesNeeded = remainingAmount / denomination;
        int notesToDispense = Math.min(notesNeeded, count);

        if (notesToDispense > 0) {
            proposal.put(denomination, notesToDispense);
            remainingAmount -= (notesToDispense * denomination);
        }

        if (remainingAmount == 0) {
            return true;
        }
        if (next != null) {
            return next.canDispense(remainingAmount, proposal);
        }

        return false;
    }

    public void executeDispense(Map<Integer, Integer> proposal) {
        if (proposal.containsKey(denomination)) {
            int notesToDrop = proposal.get(denomination);
            this.count -= notesToDrop;
            System.out.println("  -> Dropping " + notesToDrop + " x ₹" + denomination);
        }

        if (next != null) {
            next.executeDispense(proposal);
        }
    }
}
