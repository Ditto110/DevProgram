package com.ditto.test.test;


import java.util.regex.Pattern;

/**
 * @author
 */
public class Test19 {

	private static Pattern pattern = Pattern.compile("[\u4e00-\u9fa5]");
	public static void main(String[] args) {


//		String regex = "^.*[0-9]{4}_[0-9]{2}_[0-9]{3}$";
//		String input = "tb_hive_stat_qmz_2019_02_235";
//
//		boolean matches = Pattern.matches(regex, input);
//		System.out.println("tb_hive_stat_qmz_2019_02_25".matches("^.*[0-9]{4}_[0-9]{2}_[0-9]{2}$"));

//		System.out.println("啊s达".matches("^[\u4e00-\u9fa5]+"));

		/*Matcher matcher= pattern.matcher("阿萨德");
		boolean find = matcher.find();
		boolean matches = matcher.matches();

		System.out.println(Pattern.matches("^[\u4e00-\u9fa5]+$","达啊"));*/
//		System.out.println("0-3".matches("[03]-[03]"));

	}
}
