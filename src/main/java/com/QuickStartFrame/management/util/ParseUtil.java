package com.QuickStartFrame.management.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class ParseUtil {
	/**
	 * 简单对编码转换-编码
	 * @param data
	 * @return
	 */
	public static String encodeData(String data) {
		String strRtn = "";
		if (data == null)
			return strRtn;
		try {
			strRtn = Eryptogram.encryptData2(data);//URLEncoder.encode(data, Constant.ENC_UTF8);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strRtn;
	}

	/**
	 * 简单对编码转换-解码
	 * @param data
	 * @return
	 */
	public static String decodeData(String data) {
		String strRtn = "";
		if (data == null)
			return strRtn;
		try {
			strRtn = Eryptogram.decryptData2(data);//URLDecoder.decode(data, Constant.ENC_UTF8);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strRtn;
	}
	
	/**
	 * 返回本地生成的PNR
	 * @param length
	 * @return
	 */
	public static String getLocalPnr(int length) { // length表示生成字符串的长度
		String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; // 生成字符串从此序列中取
		String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"; // 生成字符串从此序列中取
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		int number = random.nextInt(base.length());
		sb.append(base.charAt(number));
		for (int i = 0; i < length - 1; i++) {
			int num = random.nextInt(str.length());
			sb.append(str.charAt(num));
		}
		return sb.toString();
	}
	
	/**
	 * 返回本地生成的Farebasis
	 * @param length
	 * @return
	 */
	public static String getLocalFarebasis(int length) { // length表示生成字符串的长度
		String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; // 生成字符串从此序列中取
		String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; // 生成字符串从此序列中取
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		int number = random.nextInt(base.length());
		sb.append(base.charAt(number));
		for (int i = 0; i < length - 1; i++) {
			int num = random.nextInt(str.length());
			sb.append(str.charAt(num));
		}
		return sb.toString();
	}
	
	/**gzip 压缩*/
	public static byte[] compressBytes(byte[] bytes) throws IOException {
        ByteArrayOutputStream baos = null;
        baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(new GZIPOutputStream(baos));
        dos.write(bytes);
        dos.close();
        return baos.toByteArray();
	}
	/**gzip 解压缩*/
    public static byte[] decompressBytes(byte[] bytes) throws IOException {
        GZIPInputStream gis = new GZIPInputStream(new ByteArrayInputStream(bytes));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int len;
        while ( (len = gis.read(buf)) >= 0) {
            baos.write(buf, 0, len);
        }
        gis.close();
        baos.close();
        return baos.toByteArray();
    }
}
