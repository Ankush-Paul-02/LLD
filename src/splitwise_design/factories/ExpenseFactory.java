package splitwise_design.factories;

import splitwise_design.entities.ExpenseType;
import splitwise_design.entities.User;
import splitwise_design.entities.expenses.*;
import splitwise_design.entities.spilts.PercentSplit;
import splitwise_design.entities.spilts.Split;

import java.util.List;

public class ExpenseFactory {
    public static Expense createExpense(
            ExpenseType expenseType,
            double amount,
            User paidBy,
            List<Split> splits,
            ExpenseMetaData expenseMetaData
    ) {
        switch (expenseType) {
            case ExpenseType.EQUAL:
                int totalSplits = splits.size();
                double splitAmount = ((double) Math.round(amount * 100) / totalSplits) / 100.0;
                for (Split split : splits) {
                    split.setAmount(splitAmount);
                }
                splits.getFirst().setAmount(splitAmount + (amount - splitAmount * totalSplits));
                return new EqualExpense(
                        paidBy,
                        amount,
                        splits,
                        expenseMetaData
                );

            case ExpenseType.EXACT:
                return new ExactExpense(
                        paidBy,
                        amount,
                        splits,
                        expenseMetaData
                );
            case ExpenseType.PERCENT:
                for (Split split : splits) {
                    PercentSplit percentSplit = (PercentSplit) split;
                    split.setAmount((amount * percentSplit.getPercent()) / 100.0);
                }
                return new PercentageExpense(
                        paidBy,
                        amount,
                        splits,
                        expenseMetaData
                );
            default: throw new IllegalArgumentException("Invalid ExpenseType");

        }
    }
}
