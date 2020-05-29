package com.pds.carking.util;

public class GenerateCPF {
	
	private static int random(int n) {
		int randomNumber = (int) (Math.random() * n);
		return randomNumber;
	}
	
	private static int mod(int dividend, int divisor) {
		return (int) Math.round(dividend - (Math.floor(dividend / divisor) * divisor));
	}
	
	public static String randomCPF () {
		return randomCPF(false);
	}
	
	public static String randomCPF (boolean withMask) {
		int[] numbers = new int[9];
		
		int[] digits = new int[] {0, 0};
		
		String generatedCPF = "";
		
		for (int i = 0; i < 9; i++) {
			numbers[i] = random(9);
			generatedCPF += "" + numbers[i];
			digits[0] += numbers[i] * (10 - i);
			digits[1] += numbers[i] * (11 - i);
		}
		
		digits[0] = 11 - (mod(digits[0], 11));
		if (digits[0] >= 10) {
			digits[0] = 0;
		}
		digits[1] += digits[0] * 2;
		digits[1] = 11 - (mod(digits[1], 11));
		
		if (digits[1] >= 10) {
			digits[1] = 0;
		}
		
		generatedCPF += digits[0] + "" + digits[1];
		
		if (withMask) {
			generatedCPF = generatedCPF.substring(0, 3) + "." + generatedCPF.substring(3, 6) + "." + generatedCPF.substring(6, 9) + "-" + generatedCPF.substring(9);
		} 
		return generatedCPF;
	}
}
