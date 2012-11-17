package org.li.wallet.resources;

import java.util.Calendar;

public class ExpenseDto {
    public String author;
    public double amount;
    public String reason;
    public Calendar calendar;

    public ExpenseDto() {
        // TODO Auto-generated constructor stub
    }

    public ExpenseDto(String author, double amount, String reason, Calendar calendar) {
        this.author = author;
        this.amount = amount;
        this.reason = reason;
        this.calendar = calendar;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    @Override
    public String toString() {
        return "ExpenseDto [author=" + author + ", amount=" + amount
                + ", reason=" + reason + ", calendar=" + calendar + "]";
    }

}
