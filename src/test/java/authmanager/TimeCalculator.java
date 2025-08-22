package authmanager;

import java.time.Instant;

public class TimeCalculator {
	
	public static void main(String[] args) {
		Instant timeNow = Instant.now();
		
		System.out.println("Creation time is "+timeNow);
		
		Instant expiry_time = timeNow.plusSeconds(3600-300);
		
		System.out.println("Expiry time is "+expiry_time);
		
		
	}

}
