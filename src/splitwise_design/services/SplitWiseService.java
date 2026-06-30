package splitwise_design.services;

import splitwise_design.entities.ExpenseType;
import splitwise_design.entities.Group;
import splitwise_design.entities.User;
import splitwise_design.entities.expenses.Expense;
import splitwise_design.entities.expenses.ExpenseMetaData;
import splitwise_design.entities.spilts.Split;
import splitwise_design.factories.ExpenseFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SplitWiseService {
    private final UserService userService;
    private final GroupService groupService;

    // map of user -> Map of owed user - amount
    private final Map<Integer, Map<Integer, Double>> globalBalanceSheet;

    public SplitWiseService(UserService userService, GroupService groupService) {
        this.userService = userService;
        this.groupService = groupService;
        this.globalBalanceSheet = new ConcurrentHashMap<>();
    }

    public void addUser(User user) {
        userService.addUser(user);
        globalBalanceSheet.putIfAbsent(user.id(), new ConcurrentHashMap<>());
    }

    public User getUser(Integer userId) {
        return userService.getUser(userId);
    }

    public void createGroup(Integer id, String name, String desc) {
        groupService.createGroup(id, name, desc);
    }

    public void addUserToGroup(Integer userId, Integer groupId) {
        groupService.addUser(getUser(userId), groupId);
    }

    public void addExpense(Integer groupId, Double amount, Integer paidBy, ExpenseType expenseType, List<Split> splits, ExpenseMetaData expenseMetaData) {
        User user = getUser(paidBy);
        Expense expense = ExpenseFactory.createExpense(expenseType, amount, user, splits, expenseMetaData);

        if (!expense.validate()) {
            throw new RuntimeException("Invalid expense");
        }


        if (groupId != null && groupService.isGroupExist(groupId)) {
            Group group = groupService.getGroup(groupId);

            for (Split split : splits) {
                if (!group.getUsers().contains(split.getUser())) {
                    throw new RuntimeException("Invalid group");
                }
            }
            group.addExpense(expense);
        }

        for (Split split : splits) {
            Integer paidTo = split.getUser().id();
            double paidAmount = split.getAmount();

            if (!paidBy.equals(paidTo)) {
                globalBalanceSheet.get(paidBy).compute(paidTo, (k, v) -> (v == null ? 0 : v) + paidAmount);
                globalBalanceSheet.get(paidTo).compute(paidBy, (k, v) -> (v == null ? 0 : v) - paidAmount);
            }
        }
        System.out.println("Successfully added expense " + expense);
    }

    public void showBalance() {
        boolean isEmpty = true;
        System.out.println("\nGlobal balance sheet\n");
        for (Map<Integer, Double> balance : globalBalanceSheet.values()) {
            for (Map.Entry<Integer, Double> entry : balance.entrySet()) {
                if (entry.getValue() > 0) {
                    isEmpty = false;
                    System.out.println(entry.getKey() + ": " + entry.getValue());
                }
            }
        }
        if (isEmpty) {
            System.out.println("No global balance sheet");
        }
        System.out.println("Successfully showed balance sheet");
    }
}
