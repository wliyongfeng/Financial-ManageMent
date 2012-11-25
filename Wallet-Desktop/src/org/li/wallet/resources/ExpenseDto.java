package org.li.wallet.resources;

public class ExpenseDto {
	public String author;
	public double amount;
	public String reason;
	public MyDate date;

	public ExpenseDto() {
		// TODO Auto-generated constructor stub
	}

	public ExpenseDto(String author, double amount, String reason, MyDate date) {
		this.author = author;
		this.amount = amount;
		this.reason = reason;
		this.date = date;
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

	public MyDate getDate() {
		return date;
	}

	public void setDate(MyDate date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "ExpenseDto [amount=" + amount + ", author=" + author
				+ ", date=" + date + ", reason=" + reason + "]";
	}

}
