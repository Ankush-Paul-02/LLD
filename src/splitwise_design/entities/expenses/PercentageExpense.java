package splitwise_design.entities.expenses;

import splitwise_design.entities.User;
import splitwise_design.entities.spilts.PercentSplit;
import splitwise_design.entities.spilts.Split;

import java.util.List;

public class PercentageExpense extends Expense {
    public PercentageExpense(User user, double amount, List<Split> splits, ExpenseMetaData expenseMetaData) {
        super(user, amount, splits, expenseMetaData);
    }

    @Override
    public boolean validate() {
        double percent = 0;

        for (Split split : this.getSplits()) {
            if (!(split instanceof PercentSplit)) {
                return false;
            }
            percent += ((PercentSplit) split).getPercent();
        }
        return Math.abs(100 - percent) < 0.00001;
    }
}
