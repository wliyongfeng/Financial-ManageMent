package org.li.wallet.resources;

public class MyDate implements Comparable<MyDate>{
	private int year;
	private int month;
	private int day;

	public MyDate() {
		// TODO Auto-generated constructor stub
	}

	public MyDate(int year, int month, int day) {
		this.year = year;
		this.month = month;
		this.day = day;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	@Override
	public String toString() {
		return new StringBuilder().append(year).append('/').append(month)
				.append('/').append(day).toString();
	}

	public int compareTo(MyDate date) {
		if (year != date.getYear()) {
			return year - date.getYear();
		}
		if (month != date.getMonth()) {
			return month - date.getMonth();
		}
		if (day != date.getDay()) {
			return day - date.getDay();
		}
		return 0;
	}

}
