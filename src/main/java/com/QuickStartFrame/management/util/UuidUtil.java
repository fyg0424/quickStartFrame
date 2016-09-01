package com.QuickStartFrame.management.util;

import java.util.UUID;
/**
 * 
 *生成8位supplierKey
 * 
 *
 *
 */
public class UuidUtil {
	public static String getSupplierKey(){
		String md5Value = UUID.randomUUID().toString().replace("-", "").toUpperCase();
        String shortMD5Value = md5Value.substring(6, 14).toUpperCase();
        return shortMD5Value;
	}
	public static void main(String[] args) {
		System.err.println(UuidUtil.getSupplierKey());
	}

}
