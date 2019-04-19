package com.ditto.test.test;

import org.mortbay.jetty.security.Credential.MD5;

public class Test18 {
	public static void main(String[] args) {
		String digest = MD5.digest("asdasd");
		System.out.println(digest);
	}
}
