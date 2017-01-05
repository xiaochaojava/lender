package com.hzwealth.common.util;

import org.apache.shiro.crypto.hash.Md5Hash;

public class Encrypt {
	/*
	 * 两个参数：1）需要加密密码，2）盐(混淆、动态值) 用户名 3）hash次数
	 * 
	 * 例如：
	 * password=123456+“123”、“abc”
	 */
	public static String md5hash(String password, String salt){
		return new Md5Hash(password, salt, 2).toString();
	}
	
	public static void main(String[] args) {
		System.out.println(Encrypt.md5hash("ceshi005", "ceshi005"));
	}
}
