package com.ditto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/multiThread")
public class MultiThreadTest {
	
	@RequestMapping("/t1")
	@ResponseBody
	public String testMultiThread(String t1) {
		if ("t1".equals(t1)) {
			Thread outerThread = new Thread(new TestThread());
//			mainThread();
			Thread subThread = new Thread(new subThread());
			outerThread.start();
			subThread.start();
			try {
				subThread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "finish";
	}

	class subThread implements Runnable{

		@Override
		public void run() {
			Thread thread1 = new Thread(new InnerClass1());
			Thread thread2 = new Thread(new InnerClass2());
			Thread thread3 = new Thread(new InnerClass3());
			thread1.start();
			thread2.start();
			thread3.start();
			try {
				thread1.join();
				thread2.join();
				thread3.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	private void mainThread() {
		Thread thread1 = new Thread(new InnerClass1());
		Thread thread2 = new Thread(new InnerClass2());
		Thread thread3 = new Thread(new InnerClass3());
		thread1.start();
		thread2.start();
		thread3.start();
		try {
			thread1.join();
			thread2.join();
			thread3.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class InnerClass1 implements Runnable{
	
	@Override
	public void run() {
		String name = Thread.currentThread().getName();
		for (int i = 0; i < 1000; i++) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("内部线程1"+name);
		}
	}}
class InnerClass2 implements Runnable{
	
	@Override
	public void run() {
		String name = Thread.currentThread().getName();
		for (int i = 0; i < 1000; i++) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("内部线程2"+name);
		}
	}}
class InnerClass3 implements Runnable{
	
	@Override
	public void run() {
		String name = Thread.currentThread().getName();
		for (int i = 0; i < 1000; i++) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("内部线程3"+name);
		}
	}}

