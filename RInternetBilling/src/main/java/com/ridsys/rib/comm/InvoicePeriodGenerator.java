package com.ridsys.rib.comm;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class InvoicePeriodGenerator {

	public static String generate() {
		String invoice_period = "";
		try {

			DateTimeFormatter newPattern = DateTimeFormatter.ofPattern("yy");

			LocalDate curDate = LocalDate.now();

			if (Integer.parseInt(curDate.format(DateTimeFormatter.ofPattern("MM"))) < 4) {
				invoice_period = LocalDate.now().plusYears(-1).format(newPattern) + "-" + curDate.format(newPattern);
			} else {
				invoice_period = curDate.format(newPattern) + "-" + LocalDate.now().plusYears(1).format(newPattern);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return invoice_period;
	}
}
