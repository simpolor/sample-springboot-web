package io.simpolor.http.util;

import java.util.Random;

public class RandomUtil {

	public static String getCharacter(int length) {
		StringBuffer sb = new StringBuffer();
		Random rnd = new Random();
		for (int i = 0; i < length; i++) {
		    int rIndex = rnd.nextInt(3);
		    switch (rIndex) {
		    case 0: // a-z
		    	sb.append((char) ((int) (rnd.nextInt(26)) + 97));
		        break;
		    case 1: // A-Z
		    	sb.append((char) ((int) (rnd.nextInt(26)) + 65));
		        break;
		    case 2: // 0-9
		    	sb.append((rnd.nextInt(10)));
		        break;
		    }
		}
		return sb.toString();
	}
	
	public static String getEnglish(int length) {
		StringBuffer sb = new StringBuffer();
		Random rnd = new Random();
		for (int i = 0; i < length; i++) {
		    int rIndex = rnd.nextInt(2);
		    switch (rIndex) {
		    case 0: // a-z
		    	sb.append((char) ((int) (rnd.nextInt(26)) + 97));
		        break;
		    case 1: // A-Z
		    	sb.append((char) ((int) (rnd.nextInt(26)) + 65));
		        break;
		    }
		}
		return sb.toString();
	}
	
	public static String getUpperCase(int length) {
		StringBuffer sb = new StringBuffer();
		Random rnd = new Random();
		for (int i = 0; i < length; i++) {
			sb.append((char) ((int) (rnd.nextInt(26)) + 65));
		}
		return sb.toString();
	}
	
	public static String getLowwerCase(int length) {
		StringBuffer sb = new StringBuffer();
		Random rnd = new Random();
		for (int i = 0; i < length; i++) {
			sb.append((char) ((int) (rnd.nextInt(26)) + 97));
		}
		return sb.toString();
	}
	
	public static String getNumber(int length) {
		StringBuffer sb = new StringBuffer();
		Random rnd = new Random();
		for (int i = 0; i < length; i++) {
			sb.append((rnd.nextInt(10)));
		}
		return sb.toString();
	}
	
	public static String getInt(int length) {
		StringBuffer sb = new StringBuffer();
		Random rnd = new Random();
		for (int i = 0; i < length; i++) {
			sb.append((rnd.nextInt(10)));
		}
		return sb.toString();
	}

}
