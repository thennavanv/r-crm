package com.ridsys.rib.comm;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OnlinePaymentAssets {

	public static String getHashCodeFromString(String str)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {

		MessageDigest md = MessageDigest.getInstance("SHA-512");
		md.update(str.getBytes("UTF-8"));
		byte byteData[] = md.digest();

		StringBuffer hashCodeBuffer = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			hashCodeBuffer.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		}
		return hashCodeBuffer.toString().toUpperCase();
	}

	public static String generateOrderID() {
		int len = 25;
		String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(chars.charAt(rnd.nextInt(chars.length())));
		return sb.toString();
	}

	public static String hashCalForEbuzz(String type, String str) {
		byte[] hashseq = str.getBytes();
		StringBuffer hexString = new StringBuffer();
		try {
			MessageDigest algorithm = MessageDigest.getInstance(type);
			algorithm.reset();
			algorithm.update(hashseq);
			byte messageDigest[] = algorithm.digest();
			for (int i = 0; i < messageDigest.length; i++) {
				String hex = Integer.toHexString(0xFF & messageDigest[i]);
				if (hex.length() == 1) {
					hexString.append("0");
				}
				hexString.append(hex);
			}

		} catch (NoSuchAlgorithmException nsae) {
		}
		return hexString.toString();
	}

	public static String encryptThisString(String input) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			byte[] messageDigest = md.digest(input.getBytes());
			BigInteger no = new BigInteger(1, messageDigest);
			String hashtext = no.toString(16);
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}
			return hashtext;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	public static String easebuzzGatewayInitialCalling(String txnid, String amount, String key, String productinfo,
			String firstname, String phone, String email, String surl, String furl, String hash, String url,
			String submerchantid) {

		System.out.println("key=" + key + "&txnid=" + txnid + "&amount=" + amount + "&productinfo=" + productinfo
				+ "&firstname=" + firstname + "&phone=" + phone + "&email=" + email + "&surl=" + surl + "&furl=" + furl
				+ "&sub_merchant_id=" + submerchantid + "&hash=" + hash);

		OkHttpClient client = new OkHttpClient();

		MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
		RequestBody body = RequestBody.create(mediaType,
				"key=" + key + "&txnid=" + txnid + "&amount=" + amount + "&productinfo=" + productinfo + "&firstname="
						+ firstname + "&phone=" + phone + "&email=" + email + "&surl=" + surl + "&furl=" + furl
						+ "&sub_merchant_id=" + submerchantid + "&hash=" + hash);

		Request request = new Request.Builder().url(url).post(body)
				.addHeader("Content-Type", "application/x-www-form-urlencoded").addHeader("Accept", "application/json")
				.build();

		Response response = null;
		try {
			response = client.newCall(request).execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String result = "";
		try {
			result = response.body().string();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}

	public static String easebuzzRefreshCalling(String txnid, String key, String amount, String email, String hash,
			String phone, String mode) {
		OkHttpClient client = new OkHttpClient();
		String url = "";
		if (mode.equalsIgnoreCase("TEST")) {
			url = "https://testdashboard.easebuzz.in/transaction/v1/retrieve";
		} else {
			url = "https://dashboard.easebuzz.in/transaction/v1/retrieve";
		}

		System.out.println("url------------" + url);
		MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
		RequestBody body = RequestBody.create(mediaType, "txnid=" + txnid + "&key=" + key + "&amount=" + amount
				+ "&email=" + email + "&phone=" + phone + "&hash=" + hash);
		Request request = new Request.Builder().url(url).post(body)
				.addHeader("Content-Type", "application/x-www-form-urlencoded").addHeader("Accept", "application/json")
				.build();

		Response response = null;
		try {
			response = client.newCall(request).execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String result = "";
		try {
			result = response.body().string();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(result);

		return result;
	}
}
