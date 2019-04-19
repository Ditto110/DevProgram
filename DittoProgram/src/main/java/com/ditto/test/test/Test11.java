package com.ditto.test.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Test11 {
	public static void main(String[] args) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dweek = "43-2018";
		SimpleDateFormat sdfs = new SimpleDateFormat("ww-yyyy");
		Date date = sdfs.parse(dweek);
		/*Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);*/
		System.out.println(sdf.format(date));
	}
}
