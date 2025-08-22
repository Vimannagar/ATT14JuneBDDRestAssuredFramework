package utils;

import java.time.LocalDate;
import java.time.LocalTime;

public class DateAndTimeProvider {
	
	public static String getCurrentDateAndTime()
	{
		LocalDate date = LocalDate.now();
		
		LocalTime time = LocalTime.now();
		
		String value = date + "_"+time;
		System.out.println(value);
		
		return value;
	}

	public static void main(String[] args) {
		getCurrentDateAndTime();
	}
}
