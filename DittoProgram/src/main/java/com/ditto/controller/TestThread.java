package com.ditto.controller;

public class TestThread implements Runnable {

	@Override
	public void run() {
		String name = Thread.currentThread().getName();
		for (int i = 0; i < 100; i++) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("外部线程"+name);
		}
	}

}
