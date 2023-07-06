package com.ridsys.rib.PaymentGateway.RazorPay;

import java.nio.charset.Charset;
import java.util.Random;

public class Assets {

	public static String generate_tid(int n) {

		// length is bounded by 256 Character
		byte[] array = new byte[256];
		new Random().nextBytes(array);

		String randomString = new String(array, Charset.forName("UTF-8"));

		// Create a StringBuffer to store the result
		StringBuilder r = new StringBuilder();

		// Append first 20 alphanumeric characters
		// from the generated random String into the result
		for (int k = 0; k < randomString.length(); k++) {

			char ch = randomString.charAt(k);

			if (((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || (ch >= '0' && ch <= '9')) && (n > 0)) {

				r.append(ch);
				n--;
			}
		}

		// return the resultant string with current time in millisecond
//		 + "-" + ZonedDateTime.now().toInstant().toEpochMilli()
		return r.toString();
	}
}
