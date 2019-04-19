package com.ditto.controller;

import org.springframework.util.Base64Utils;

public class TestDeCode {
	public final static String KEY = "skyworth.beemarket_tv_2017_hercules";
	public final static String ENCODING = "UTF-8";
	public final static String DES = "DES";
	public static void main(String[] args) {
		String info ="gXgAh94Va9M=";
//		byte[] temp = Base64Decoder(info);
		byte[] temp = Base64Utils.decode(info.getBytes());
		String string = new String(temp.toString());
		System.out.println(string);
		//        byte[] buf = decrypt(temp,KEY.getBytes(ENCODING));

	}
}
