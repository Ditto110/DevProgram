package com.ditto.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TestMemory {
	public static void main(String[] args) throws FileNotFoundException {
		String path = "e:/test/schedule_node43.241.76.213_2018-02-10.log";
		long freeMemory = 0L;
		try {
			File file = new File(path);
			System.out.println("文件大小:"+file.length());	//读取文件，这个文件是3G
			Runtime runtime = Runtime.getRuntime();
			long totalMemory = runtime.totalMemory();
			System.out.println("总内存是:"+totalMemory);	//总内存  电脑是8G
			BufferedReader bf = new BufferedReader(new FileReader(file),10*1024*1024); // 读入文件
/*			while (bf.readLine()!=null) {
				bf.ready();
				freeMemory = runtime.freeMemory();
				System.out.println("使用内存:"+(totalMemory-freeMemory)); //剩余内存
				System.out.println("剩余内存:"+freeMemory); //剩余内存
			}*/
			bf.close();
			System.out.println("剩余内存:"+freeMemory); //剩余内存
			Thread.sleep(5000);
			bf.close();
			System.out.println("剩余内存:"+freeMemory); //剩余内存
	/*		LineIterator iterator = FileUtils.lineIterator(file);
			while (iterator.hasNext()) {
				long freeMemory = runtime.freeMemory();
				System.out.println("剩余内存:"+freeMemory); //剩余内存
			}*/
/*			List<String> readAllLines = Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8);
			long freeMemory = runtime.freeMemory();
			System.out.println("剩余内存:"+freeMemory); //剩余内存
*//*			while (iterator.hasNext()) {
			}*/
/*			FileReader fileReader = new FileReader(file);
			long freeMemory = runtime.freeMemory();
			System.out.println("剩余内存:"+freeMemory); //剩余内存
*/		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}
