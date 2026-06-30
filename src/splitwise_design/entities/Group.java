package splitwise_design.entities;

import splitwise_design.entities.expenses.Expense;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Group {
    private Integer id;
    private String name;
    private String description;

    private List<User> users;
    private List<Expense> expenses;

    public void addUser(User user) {
        if (!this.users.contains(user)) {
            this.users.add(user);
        }
    }

    public void addExpense(Expense expense) {
        this.expenses.add(expense);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    public Group(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.users = new CopyOnWriteArrayList<>();
        this.expenses = new CopyOnWriteArrayList<>();
    }
}
