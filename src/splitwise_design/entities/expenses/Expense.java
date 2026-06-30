package splitwise_design.entities.expenses;

import splitwise_design.entities.User;
import splitwise_design.entities.spilts.Split;

import java.util.List;

public abstract class Expense {
    private final double amount;
    private final User paidBy;
    private final List<Split> splits;
    private final ExpenseMetaData expenseMetaData;

    public Expense(User user, double amount, List<Split> splits, ExpenseMetaData expenseMetaData) {
        this.amount = amount;
        this.paidBy = user;
        this.splits = splits;
        this.expenseMetaData = expenseMetaData;
    }

    public abstract boolean validate();

    public double getAmount() {
        return amount;
    }

    public User getPaidBy() {
        return paidBy;
    }

    public List<Split> getSplits() {
        return splits;
    }

    public ExpenseMetaData getExpenseMetaData() {
        return expenseMetaData;
    }
}
