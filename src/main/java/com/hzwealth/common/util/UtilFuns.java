package com.hzwealth.common.util;

public class UtilFuns {
	// 拼接字符串，前綴+內容+後綴+分隔符
	static public String joinStr(String[] aStr, String sPrefix, String sSuffix, String SplitFlag) {
		StringBuffer sBuffer = new StringBuffer();
		if (aStr != null) {
			for (int i = 0; i < aStr.length; i++) {
				sBuffer.append(sPrefix).append(aStr[i]).append(sSuffix).append(SplitFlag);
			}
			sBuffer.delete(sBuffer.length() - SplitFlag.length(), sBuffer.length()); // 去掉最后的分隔符
																						// SplitFlag
		} else {
			sBuffer = sBuffer.append("");
		}
		return sBuffer.toString();
	}

	// 将null字符串转为空串
	public static String convertNull(String strvalue) {
		try {
			if (strvalue.equals("null") || strvalue.length() == 0) {
				return "";
			} else {
				return strvalue.trim();
			}
		} catch (Exception e) {
			return "";
		}
	}

	public static String convertNull(Object o) {
		try {
			String strvalue = String.valueOf(o);
			if (strvalue.equals(null) || strvalue.equals("null") || strvalue.length() == 0) {
				return "";
			} else {
				return strvalue.trim();
			}
		} catch (Exception e) {
			return "";
		}
	}
	
	/* 验证数组是否为空 */
	public static boolean arrayValid(Object[] objects) {
		if (objects != null && objects.length > 0) {
			return true;
		} else {
			return false;
		}
	}	
}
