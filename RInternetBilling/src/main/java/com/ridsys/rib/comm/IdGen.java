package com.ridsys.rib.comm;

import java.util.Random;

public class IdGen {

	public static String genID(String prefix) {
		int len = 25;
		String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(chars.charAt(rnd.nextInt(chars.length())));
		return prefix + sb.toString();
	}

	public static String cafIdGen(String prefix) {
		int len = 0;
		if (prefix.equals("cafformid")) {
			len = 6;
		} else {
			len = 12;
		}
		prefix = "";
		String chars = "0123456789";
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(chars.charAt(rnd.nextInt(chars.length())));
		return  sb.toString();
	}
}
