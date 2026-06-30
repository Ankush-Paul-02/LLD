package splitwise_design.entities.spilts;

import splitwise_design.entities.User;

public class ExactSplit extends Split {

    public ExactSplit(User user, double amount) {
        super(user);
        this.amount = amount;
    }
}
