package put.io.testing.mocks;

import java.util.List;

public interface IExpenseRepository {
    void changeExpenses(List<Expense> ex);

    List<Expense> getExpenses();

    List<Expense> getExpensesByCategory(String category);

    void addExpense(Expense expense);

    void deleteExpense(Expense expense);

    void loadExpenses();

    void saveExpenses();
}
