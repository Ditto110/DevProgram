package com.ditto.controller;

import java.math.BigDecimal;

public class Test1 {
	public static void main(String[] args) {
		String v1 = "233333333333312";
		String v2 = "234535436565453";
		Double v =  Double.valueOf(v2) - Double.valueOf(v1);
		
		System.out.println(new BigDecimal(v));
		System.out.println(v);
		
		
	}
}
