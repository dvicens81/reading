package com.reading.reading.print;

import com.reading.reading.entity.ClientEntity;
/**
 * Print results on console
 * @author 
 *
 */
public interface ReadingPrint {
	
	public static void printHeader() {
		System.out.println("-----------------------------------------------------------------------------");
	    System.out.printf("%10s %15s %12s %12s", "CLIENT ID", "REGARDING", "MEDIAN", "MONTH");
	    System.out.println();
	    System.out.println("-----------------------------------------------------------------------------");
	}
	
	public static void printResults(ClientEntity clientEntity) {
		 System.out.format("%10s %10s %15s %11s", clientEntity.getId(), clientEntity.getReading(), clientEntity.getMedian(), clientEntity.getMonth());
	     System.out.println();
	}
	
	public static void printFooter() {
		System.out.println("-----------------------------------------------------------------------------");
	}
	
}
