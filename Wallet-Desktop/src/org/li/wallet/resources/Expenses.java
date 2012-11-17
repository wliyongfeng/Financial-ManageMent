package org.li.wallet.resources;

import java.util.List;

public class Expenses {
    private List<ExpenseDto> expenses;

    public Expenses() {
        // TODO Auto-generated constructor stub
    }

    public Expenses(List<ExpenseDto> expenses) {
        this.expenses = expenses;
    }

    public List<ExpenseDto> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<ExpenseDto> expenses) {
        this.expenses = expenses;
    }

    @Override
    public String toString() {
        return "Expenses [expenses=" + expenses + "]";
    }

}
