package com.QuickStartFrame.management.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class AESPlus {
	/**
	 * 加密
	 * @param strKey
	 * @param strIn
	 * @return
	 * @throws Exception
	 */
	 public static String encrypt(String strKey, String strIn) {
        try {
			SecretKeySpec skeySpec = getKey(strKey);
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			IvParameterSpec iv = new IvParameterSpec(new byte[16]);//初始化为16空字节
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
			
			byte[] encrypted = cipher.doFinal(strIn.getBytes());

			return Base64.encodeBase64String(encrypted);
		} catch (Exception e) {
			return "";
		}
	 }
	 /**
	  * 解密
	  * @param strKey
	  * @param strIn
	  * @return
	  * @throws Exception
	  */
	 public static String decrypt(String strKey, String strIn){
        try {
			SecretKeySpec skeySpec = getKey(strKey);
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			IvParameterSpec iv = new IvParameterSpec(new byte[16]);//初始化为16空字节
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			byte[] encrypted1 = Base64.decodeBase64(strIn);
			byte[] original = cipher.doFinal(encrypted1);
			String originalString = new String(original);
			return originalString;
		} catch (Exception e) {
			return "";
		}
	 }

	 private static SecretKeySpec getKey(String strKey) throws Exception {
        byte[] arrBTmp = strKey.getBytes();
        byte[] arrB = new byte[16]; // 创建一个空的16位字节数组（默认值为0）

        for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
            arrB[i] = arrBTmp[i];
        }

        SecretKeySpec skeySpec = new SecretKeySpec(arrB, "AES");

        return skeySpec;
	 }
}
