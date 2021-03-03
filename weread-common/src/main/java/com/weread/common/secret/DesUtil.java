package com.weread.common.secret;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import com.weread.common.base.SystemConfig;

/**
 * DES工具类
 * 
 * @date 2019年1月12日
 */
public class DesUtil {
	private static final String ALGORITHM = "DES";
	private static final String TRANSFORMATION = "DES/ECB/PKCS5Padding";
	private static final String KEY = "4YztMHI7PsT4rLZN";

	private DesUtil() { }

	public static byte[] generateKey() {
		try {
			SecureRandom sr = new SecureRandom();
			KeyGenerator kg = KeyGenerator.getInstance(ALGORITHM);
			kg.init(sr);
			SecretKey secretKey = kg.generateKey();
			return secretKey.getEncoded();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static byte[] encrypt(byte[] src, byte[] key) throws Exception {
		SecureRandom sr = new SecureRandom();
		DESKeySpec dks = new DESKeySpec(key);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
		SecretKey secretKey = keyFactory.generateSecret(dks);
		Cipher cipher = Cipher.getInstance(TRANSFORMATION);
		cipher.init(Cipher.ENCRYPT_MODE, secretKey, sr);
		return cipher.doFinal(src);
	}

	public static byte[] decrypt(byte[] src, byte[] key) throws Exception {
		SecureRandom sr = new SecureRandom();
		DESKeySpec dks = new DESKeySpec(key);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
		SecretKey secretKey = keyFactory.generateSecret(dks);
		Cipher cipher = Cipher.getInstance(TRANSFORMATION);
		cipher.init(Cipher.DECRYPT_MODE, secretKey, sr);
		return cipher.doFinal(src);
	}

//	private static String byte2hex(byte[] b) {
//		String hs = "";
//		String temp = "";
//		for (int n = 0; n < b.length; n++) {
//			temp = (java.lang.Integer.toHexString(b[n] & 0XFF));
//			if (temp.length() == 1)
//				hs = hs + "0" + temp;
//			else
//				hs = hs + temp;
//		}
//		return hs.toUpperCase();
//
//	}
//
//	private static byte[] hex2byte(byte[] b) {
//		if ((b.length % 2) != 0)
//			throw new IllegalArgumentException("length not even");
//		byte[] b2 = new byte[b.length / 2];
//		for (int n = 0; n < b.length; n += 2) {
//			String item = new String(b, n, 2);
//			b2[n / 2] = (byte) Integer.parseInt(item, 16);
//		}
//		return b2;
//	}

	public static String decode(String src, String key) {
		String decryptStr = "";
		try {
			byte[] decrypt = decrypt(Base64.decodeToBytes(src), key.getBytes());
			decryptStr = new String(decrypt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return decryptStr;
	}

	public static String encode(String src, String key) {
		byte[] bytes = null;
		String encryptStr = "";
		try {
			bytes = encrypt(src.getBytes(SystemConfig.DEF_ENCODE), key.getBytes());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (bytes != null)
			encryptStr = Base64.encode(bytes);
		return encryptStr;
	}

	/**
	 * 解密
	 */
	public static String decode(String src) {
		return decode(src, KEY);
	}

	/**
	 * 加密
	 */
	public static String encode(String src) {
		return encode(src, KEY);
	}
}
