package com.ditto.test;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class T{

	public static void main(String[] args) throws FileNotFoundException {
		PrintWriter ps = new PrintWriter(new BufferedOutputStream(new FileOutputStream("d:ok.txt")),true);
		ps.println("23");
		tst2();
	}
	
	public static void tst2() throws FileNotFoundException {
		PrintWriter ps = new PrintWriter(new BufferedOutputStream(new FileOutputStream("d:ok.txt")),true);
		ps.println("eeeeeee");
	}
}
