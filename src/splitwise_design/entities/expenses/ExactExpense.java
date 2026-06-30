package splitwise_design.entities.expenses;

import splitwise_design.entities.User;
import splitwise_design.entities.spilts.ExactSplit;
import splitwise_design.entities.spilts.Split;

import java.util.List;

public class ExactExpense extends Expense {
    public ExactExpense(User user, double amount, List<Split> splits, ExpenseMetaData expenseMetaData) {
        super(user, amount, splits, expenseMetaData);
    }

    @Override
    public boolean validate() {
        double amount = 0;

        for (Split split : this.getSplits()) {
            if (!(split instanceof ExactSplit)) {
                return false;
            }
            amount += split.getAmount();
        }
        return Math.abs(this.getAmount() - amount) < 0.00001;
    }
}
