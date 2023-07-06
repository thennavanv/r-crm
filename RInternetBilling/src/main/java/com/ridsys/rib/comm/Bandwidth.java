package com.ridsys.rib.comm;

import org.decimal4j.util.DoubleRounder;

public class Bandwidth {
	
	public static String calBWDecimal(double value) {
		String result = "0";
		// Gigabytes
		if (value > 1000000000) {
			value = value / 1000000000;
			return DoubleRounder.round(value, 2) + " Gb";
		}

		// Megabytes
		if (value > 1000000) {
			value = value / 1000000;
			return DoubleRounder.round(value, 2) + " Mb";
		}

		// Kilobytes
		if (value > 1000) {
			value = value / 1000;
			return DoubleRounder.round(value, 2) + " Kb";
		}

		// Bytes
		if (value <= 1000) {
			return value + " B";
		}
		return result;
	}
	
	public static String calBWBinary(double value) {
		String result = "0";
		// Gigabytes
		if (value > 1073741824) {
			value = value / 1073741824;
			return DoubleRounder.round(value, 2)+ " Gb";
		}

		// Megabytes
		if (value > 1048576) {
			value = value / 1048576;
			return DoubleRounder.round(value, 2) + " Mb";
		}

		// Kilobytes
		if (value > 1024) {
			value = value / 1024;
			return DoubleRounder.round(value, 2) + " Kb";
		}

		// Bytes
		if (value <= 1024) {
			return value + " B";
		}
		return result;
	}
}
