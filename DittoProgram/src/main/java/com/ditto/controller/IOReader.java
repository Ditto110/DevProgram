package com.ditto.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class IOReader {
	private MappedByteBuffer[] mappedBufArray;
	private int count = 0;
	private int number;
	private FileInputStream fileIn;
	private long fileLength;
	private int arraySize;
	private byte[] array;

	public IOReader(String fileName, int arraySize) throws IOException {
		this.fileIn = new FileInputStream(fileName);
		FileChannel fileChannel = fileIn.getChannel();
		this.fileLength = fileChannel.size(); // 连接文件的大小、将硬盘的数据虚拟映射到内存空间大小
		this.number = (int) Math.ceil((double) fileLength / (double) Integer.MAX_VALUE);
		this.mappedBufArray = new MappedByteBuffer[number];// 内存文件映射数组
		
		long preLength = 0;
		long regionSize = (long) Integer.MAX_VALUE;// 映射区域的大小
		for (int i = 0; i < number; i++) {// 将文件的连续区域映射到内存文件映射数组中
			if (fileLength - preLength < (long) Integer.MAX_VALUE) {
				regionSize = fileLength - preLength;// 最后一片区域的大小
			}
			//映射模式，映射的起始位置，映射的偏移量，需要赋值给fileCHannel的大小
			mappedBufArray[i] = fileChannel.map(FileChannel.MapMode.READ_ONLY, preLength, regionSize);
			preLength += regionSize;// 下一片区域的开始
		}
		this.arraySize = arraySize;
	}

	public int read() {
		if (count >= number) {
			return -1;
		}
		int limit = mappedBufArray[count].limit();
		
		int position = mappedBufArray[count].position();
		
		if (limit - position > arraySize) {
			array = new byte[arraySize];
			mappedBufArray[count].get(array);
			return arraySize;
		} else {// 本内存文件映射最后一次读取数据
			array = new byte[limit - position];
			mappedBufArray[count].get(array);
			if (count < number) {
				count++;// 转换到下一个内存文件映射
			}
			return limit - position;
		}
	}

	public void close() throws IOException {
		fileIn.close();
		array = null;
	}

	public byte[] getArray() {
		return array;
	}

	public long getFileLength() {
		return fileLength;
	}

	public static void main(String[] args) throws IOException {

		IOReader reader = new IOReader("e:\\test\\schedule_node43.241.76.213_2018-02-10.log", 65536);
		long start = System.nanoTime();
		long start1 = System.currentTimeMillis();
		while (reader.read() != -1) {
			long end = System.nanoTime();
			long end1 = System.currentTimeMillis();
			reader.close();
			System.out.println("MappedBiggerFileReader: " + (end - start));
			System.out.println(end1 - start1);
			/*
			 * BufferedReader reader = new BufferedReader( new
			 * FileReader("e:\\test\\schedule_node43.241.76.213_2018-02-10.log")); String
			 * line = null; long start1 = System.currentTimeMillis(); while ((line =
			 * reader.readLine()) != null); long end1 = System.currentTimeMillis();
			 * System.out.println(end1 - start1);
			 */
		}
	}
}
