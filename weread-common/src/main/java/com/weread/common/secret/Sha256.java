package com.weread.common.secret;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Sha256工具
 * @author lisheng
 */
public class Sha256 {
	protected static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	protected static MessageDigest messagedigest = null;
	private static final Logger LOGGER = LoggerFactory.getLogger(Sha256.class);
	static {
		try {
			messagedigest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error("SHA-256 init error: "+e.getMessage(), e);
		}
	}
	
	public static String sha256_mac(String message, String key){
        String outPut= null;
        try{
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(key.getBytes(),"HmacSHA256");
            sha256_HMAC.init(secret_key);
            byte[] bytes = sha256_HMAC.doFinal(message.getBytes());
            outPut = bufferToHex(bytes);
        }catch (Exception e){
        	LOGGER.error("Error HmacSHA256========"+e.getMessage(), e);
        }
        return outPut;
    }

	/**
	 * String进行MD5
	 * 
	 * @param s
	 * @return
	 */
	public static String getSHA256(String s) {
		return getSHA256(s.getBytes());
	}

	/**
	 * byte[]数组进行MD5
	 * 
	 * @param bytes
	 * @return
	 */
	public static String getSHA256(byte[] bytes) {
		messagedigest.update(bytes);
		return bufferToHex(messagedigest.digest());
	}

	private static String bufferToHex(byte bytes[]) {
		return bufferToHex(bytes, 0, bytes.length);
	}

	private static String bufferToHex(byte bytes[], int m, int n) {
		StringBuffer stringbuffer = new StringBuffer(2 * n);
		int k = m + n;
		for (int l = m; l < k; l++) {
			appendHexPair(bytes[l], stringbuffer);
		}
		return stringbuffer.toString();
	}

	private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
		char c0 = hexDigits[(bt & 0xf0) >> 4];
		char c1 = hexDigits[bt & 0xf];
		stringbuffer.append(c0);
		stringbuffer.append(c1);
	}
	

}