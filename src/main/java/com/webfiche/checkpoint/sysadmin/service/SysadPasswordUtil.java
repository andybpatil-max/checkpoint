/*
 * Copyright (c) 2003 eSoftLabs, LLC
 * All rights reserved. 
 */
package com.webfiche.checkpoint.sysadmin.service;

import java.security.MessageDigest;
import org.apache.commons.codec.binary.*;

//
public final class SysadPasswordUtil {
	private static SysadPasswordUtil instance;

	private SysadPasswordUtil() {
	}
	//
	public synchronized String encrypt(String plaintext) throws Exception {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA"); // step 2
		} catch (Throwable e) {
			// throw new SystemUnavailableException(e.getMessage());
		}
		try {
			md.update(plaintext.getBytes("UTF-8")); // step 3
		} catch (Throwable e) {
			// throw new SystemUnavailableException(e.getMessage());
		}
		byte raw[] = md.digest(); // step 4
		// String hash = (new BASE64Encoder()).encode(raw); //step 5
		byte[] encoded = Base64.encodeBase64(raw);
		String hash = new String(encoded);
		return hash; // step 6
	}
	//
	public static synchronized SysadPasswordUtil getInstance() // step 1
	{
		if (instance == null) {
			return new SysadPasswordUtil();
		} else {
			return instance;
		}
	}
}
