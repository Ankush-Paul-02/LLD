package splitwise_design.entities.expenses;

import splitwise_design.entities.User;
import splitwise_design.entities.spilts.EqualSplit;
import splitwise_design.entities.spilts.Split;

import java.util.List;

public class EqualExpense extends Expense {
    public EqualExpense(User user, double amount, List<Split> splits, ExpenseMetaData expenseMetaData) {
        super(user, amount, splits, expenseMetaData);
    }

    @Override
    public boolean validate() {
        double amount = 0;

        for (Split split : this.getSplits()) {
            if (!(split instanceof EqualSplit)) {
                return false;
            }
            amount += split.getAmount();
        }

        return Math.abs(this.getAmount() - amount) < 0.00001;
    }
}
