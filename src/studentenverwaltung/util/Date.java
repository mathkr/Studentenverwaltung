package studentenverwaltung.util;

import java.util.Calendar;

public class Date {
	private int year;
	private int month;
	private int day;

	private static Date today;

	static { // initialize todays date
		int currentYear, currentMonth, currentDay;
		Calendar cal = Calendar.getInstance();

		currentYear = cal.get(Calendar.YEAR);
		currentMonth = cal.get(Calendar.MONTH) + 1;
		currentDay = cal.get(Calendar.DAY_OF_MONTH);

		today = new Date(currentYear, currentMonth, currentDay);
	}

	public Date(int year, int month, int day){
		this.year = year;
		this.month = month;
		this.day = day;
	}

	public int getYear(){
		return year;
	}

	public int getMonth(){
		return month;
	}

	public int getDay(){
		return day;
	}

	public int compareTo(Date date){
		/*
		 * returns:
		 * 	-1 if this date is before date
		 * 	 0 if this date == date
		 * 	 1 if this date is after date
		 */

		if(getYear() < date.getYear()){
			return -1;
		} else if (getYear() > date.getYear()){
			return 1;
		} else {
			if(getMonth() < date.getMonth()){
				return -1;
			} else if (getMonth() > date.getMonth()){
				return 1;
			} else {
				if(getDay() < date.getDay()){
					return -1;
				} else if(getDay() > date.getDay()){
					return 1;
				} else {
					return 0;
				}
			}
		}
	}

	public static Date getToday(){
		return today;
	}

	public int getYearsSince(Date date){
		if(compareTo(date) <= 0) return 0;

		int res = getYear() - date.getYear();

		if(date.getMonth() > getMonth()){
			--res;
		} else if(date.getMonth() == getMonth()){
			if(date.getDay() > getDay()){
				--res;
			}
		}

		return res;
	}

	@Override
	public String toString(){
		return String.format("%02d.%02d.%04d", getDay(), getMonth(), getYear());
	}
}