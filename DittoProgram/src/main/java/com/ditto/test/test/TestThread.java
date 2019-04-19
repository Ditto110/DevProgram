package com.ditto.test.test;

public class TestThread {
	public static void main(String[] args) throws InterruptedException  {
		String test = test();
		System.out.println(test);
	}
	public static String test() throws InterruptedException {
		Thread thread1 = new Thread(new TThread1());
		thread1.start();
		Thread thread2 = new Thread(new TThread2());
		thread2.start();
		Thread thread3 = new Thread(new TThread3());
		thread3.start();
		thread2.join();
		thread1.join();
		thread3.join();
		System.out.println("main");
		return "ok";
	}
}

class TThread1 implements Runnable{

	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("T1");
		}
	}
}

class TThread2 implements Runnable{

	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("T2");
		}
	}
	
}

class TThread3 implements Runnable{
	
	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("T3");
		}
	}
	
}