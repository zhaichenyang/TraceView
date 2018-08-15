package com.zcy.secret;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


/**
 * AES
 */
public class AESUtil {
	public static final String DEFAULT_CODING = "utf-8";

	public static final String DEFAULT_KEY = "clic8888";

	/**
	 * AES AES/ECB/PKCS5Padding
	 */
	public static String encrypt(String content, String key) throws Exception {
		byte[] input = content.getBytes(DEFAULT_CODING);

		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] digest = md.digest(key.getBytes(DEFAULT_CODING));
		SecretKeySpec sks = new SecretKeySpec(digest, "AES");
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, sks);

		byte[] cipherText = new byte[cipher.getOutputSize(input.length)];
		int ctLength = cipher.update(input, 0, input.length, cipherText, 0);
		ctLength += cipher.doFinal(cipherText, ctLength);

		return parseByte2HexStr(cipherText);
	}

	/**
	 * AES AES/ECB/PKCS5Padding
	 */
	public static String decrypt(String encrypted, String key) throws Exception {
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] digest = md.digest(key.getBytes(DEFAULT_CODING));
		SecretKeySpec sks = new SecretKeySpec(digest, "AES");
		Cipher dcipher = Cipher.getInstance("AES");
		dcipher.init(Cipher.DECRYPT_MODE, sks);

		byte[] contentBytes = dcipher.doFinal(toByte(encrypted));
		return new String(contentBytes, DEFAULT_CODING);
	}

	/**
	 */
	private static byte[] toByte(String hexString) {
		int len = hexString.length() / 2;
		byte[] result = new byte[len];
		for (int i = 0; i < len; i++) {
			result[i] = Integer.valueOf(hexString.substring(2 * i, 2 * i + 2),
					16).byteValue();
		}
		return result;
	}

	/**
	 */
	private static String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex);
		}
		return sb.toString();
	}
}