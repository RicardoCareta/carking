package com.pds.carking.util;

import java.text.NumberFormat;

public class MoneyFormatter {

	public static String formatMoney (float value) {
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		String moneyString = formatter.format(value);
		return moneyString; 
	}
}
