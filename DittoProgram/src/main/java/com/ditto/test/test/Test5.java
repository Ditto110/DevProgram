package com.ditto.test.test;

import java.util.Base64;
import java.util.Base64.Decoder;

public class Test5 {
	public static void main(String[] args) {
		String string = Base64.getEncoder().encodeToString("asdfff".getBytes());
		System.out.println(string);
		Decoder decoder = Base64.getDecoder();
		
		System.out.println(new String(decoder.decode(string)));
	}
}
