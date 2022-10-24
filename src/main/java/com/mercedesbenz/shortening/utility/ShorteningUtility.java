package com.mercedesbenz.shortening.utility;

import java.util.HashMap;
import java.util.Random;

/**
 * Provides encoding and decoding methods Base10 to Base62 and vice versa
 */
public class ShorteningUtility {
	private HashMap<String, String> keyMap; // key-url
	private HashMap<String, String> valueMap; // url-key
												// map to
												// quickly
												// check
												// // map
	private String domain;

	private char myChars[]; // This
							// array
							// is
							// used
							// for
							// character
							// to
							// number
	// mapping
	private Random myRand; // Random
							// object
							// used
							// to
							// generate
							// random
							// integers
	private int keyLength; // the
							// key
							// length
							// in URL
							// defaults
							// to 8

	public ShorteningUtility() {
		keyMap = new HashMap<String, String>();
		valueMap = new HashMap<String, String>();
		myRand = new Random();
		keyLength = 5;
		myChars = new char[62];
		for (int i = 0; i < 62; i++) {
			int j = 0;
			if (i < 6) {
				j = i + 48;
			} else if (i > 5 && i <= 30) {
				j = i + 55;
			} else {
				j = i + 61;
			}
			myChars[i] = (char) j;
		}
		domain = "http://merced.es/";
	}

	// shortenURL
	// the public method which can be called to shorten a given URL
	public String shortenURL(String longURL) {
		String shortURL = "";
		if (valueMap.containsKey(longURL)) {
			shortURL = domain + valueMap.get(longURL);
		} else {
			shortURL = domain + getKey(longURL);
		}
		// add https part
		return shortURL;
	}

	/*
	 * Get Key method
	 */
	private String getKey(String longURL) {
		String key;
		key = generateKey();
		keyMap.put(key, longURL);
		valueMap.put(longURL, key);
		return key;
	}

	// generateKey
	private String generateKey() {
		String key = "";
		boolean flag = true;
		while (flag) {
			key = "";
			for (int i = 0; i <= keyLength; i++) {
				key += myChars[myRand.nextInt(62)];
			}
			// System.out.println("Iteration: "+ counter + "Key: "+ key);
			if (!keyMap.containsKey(key)) {
				flag = false;
			}
		}
		return key;
	}

}