package com.webfiche.checkpoint.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Base64;
import java.util.Scanner;
//import java.io.FileOutputStream;
import java.io.FileWriter;
//import java.io.BufferedOutputStream;
//import java.io.DataOutputStream;


import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Program to Encrypt/Decrypt String Using AES 128 bit Encryption Algorithm
 */
public class EncryptDecryptString {
	
	//private static final String encryptionKey		   = "ABCDEFGHIJKLMNOP";
	private static final String encryptionKey		   = "wFEncKeywFEncKey";
	private static final String characterEncoding	   = "UTF-8";
	private static final String cipherTransformation	= "AES/CBC/PKCS5PADDING";
	private static final String aesEncryptionAlgorithem = "AES";
	private static String moduleName	= "EncryptDecryptString."; 
	//
	private static void PrintLog(String logMsg) {
		System.out.println(java.util.Calendar.getInstance().getTime() +
							"> DatabasServlet." + moduleName + ": " + logMsg);
	}
//
	/**
	 * Method for Encrypt Plain String Data
	 * @param plainText
	 * @return encryptedText
	 */
	public static String encrypt(String plainText) {
		String encryptedText = "";
		try {
			Cipher cipher					= Cipher.getInstance(cipherTransformation);
			byte[] key						= encryptionKey.getBytes(characterEncoding);
			SecretKeySpec secretKey			= new SecretKeySpec(key, aesEncryptionAlgorithem);
			IvParameterSpec ivparameterspec	= new IvParameterSpec(key);
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivparameterspec);
			byte[] cipherText				= cipher.doFinal(plainText.getBytes("UTF8"));
			Base64.Encoder encoder			= Base64.getEncoder();
			encryptedText					= encoder.encodeToString(cipherText);
		} catch (Exception E) {
			PrintLog("Encrypt Exception : "+E.getMessage());
		}
		return encryptedText;
	}
	/**
	 * Method For Get encryptedText and Decrypted provided String
	 * @param encryptedText
	 * @return decryptedText
	 */
	public static String decrypt(String encryptedText) {
		String decryptedText = "";
		try {
			Cipher cipher					= Cipher.getInstance(cipherTransformation);
			byte[] key						= encryptionKey.getBytes(characterEncoding);
			SecretKeySpec secretKey			= new SecretKeySpec(key, aesEncryptionAlgorithem);
			IvParameterSpec ivparameterspec	= new IvParameterSpec(key);
			cipher.init(Cipher.DECRYPT_MODE, secretKey, ivparameterspec);
			Base64.Decoder decoder			= Base64.getDecoder();
			byte[] cipherText				= decoder.decode(encryptedText.getBytes("UTF8"));
			decryptedText					= new String(cipher.doFinal(cipherText), "UTF-8");
		} catch (Exception E) {
			PrintLog("Encrypt Exception : "+E.getMessage());
		}
		return decryptedText;
	}
	//
	public static String GetAccess(String webInf) {
		String decryPtedString	= "";
		String encryPtedString	= "";
		String stringPassFile	= "plainStringPass.cfg";
		String encPassFile		= "encPass.cfg";
		String plainString		= "";
		File	findFile		= null;
		BufferedReader inRead	= null;
		FileWriter	writeArec	= null;
		PrintLog("In GetAccess: " + webInf + stringPassFile);
		try {
			boolean fileExists	= false;
			findFile	= new File(webInf + stringPassFile);
			fileExists	= findFile.exists();
			PrintLog("File Exists1 " + webInf + stringPassFile + " " + fileExists);
			if (fileExists == true) {
				inRead		= new BufferedReader(new FileReader(webInf + stringPassFile));
				/*
				while ((plainString = inRead.readLine()) != null) {
					if (plainString.length() == 0) {
						PrintLog("Empty file " + webInf + stringPassFile);
						continue;
					}
				}
				*/
				plainString = inRead.readLine();
				//PrintLog("Plain String " + plainString);
				encryPtedString	= encrypt(plainString);
				// rename file after encryption
				writeArec			= new FileWriter(webInf + encPassFile);
				writeArec.write(encryPtedString);
				//File strPassFile	= new File(webInf + stringPassFile);
				inRead.close();
				findFile.delete();
				decryPtedString	= plainString;
				writeArec.close();
			} else {
				findFile = new File(webInf + encPassFile);
				fileExists	= findFile.exists();
				PrintLog("File Exists2 " + webInf + encPassFile + " " + fileExists);
				if (fileExists == true) {
					inRead		= new BufferedReader(new FileReader(webInf + encPassFile));
					/*
					while ((encryPtedString = inRead.readLine()) != null) {
						if (encryPtedString.length() == 0) {
							PrintLog("Empty file " + encPassFile);
							continue;
						}
					}
					*/
					encryPtedString = inRead.readLine();
					PrintLog("Encrypted String " + encryPtedString);
					decryPtedString	= decrypt(encryPtedString);
					inRead.close();
				}
			}
		} catch (IOException e) {
			PrintLog("Error in Processing for Password file " + e);
		}
		return decryPtedString;
	}
	//
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter String : ");
		String plainString = sc.nextLine();
		
		String encyptStr   = encrypt(plainString);
		String decryptStr  = decrypt(encyptStr);
		
		System.out.println("Plain   String  : "+plainString);
		System.out.println("Encrypt String  : "+encyptStr);
		System.out.println("Decrypt String  : "+decryptStr);
		sc.close();
	}
	//vU4/Z8LZkEKqylDpRkJUN4eWUpVDTWn+LIhehtSx3ns=
}
