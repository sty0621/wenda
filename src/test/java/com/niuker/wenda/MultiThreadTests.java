package com.niuker.wenda;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class MultiThreadTests {
	public static void main(String[] args) {
		//testTread();
		//testBlockingQueue();
		//testThreadLocal();
		//testWithoutAtomic();
		testWithAtomic();
	}
	
	private static int counter = 0;
	private static AtomicInteger atomicInteger = new AtomicInteger(0);

	public static void testWithoutAtomic() {
		for(int i = 0; i < 10; i++) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						Thread.sleep(1000);
						for(int j = 0; j < 10; j++) {
							counter++;
							System.out.println(counter);
						}
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
	}
	
	public static void testWithAtomic() {
		for(int i = 0; i < 10; i++) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						Thread.sleep(1000);
						for(int j = 0; j < 10; j++) {
							System.out.println(atomicInteger.incrementAndGet());
						}
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
	}
	
	public static void testTread() {
		for(int i = 0; i < 10; i++) {
			new MyThread(i).start();
		}
	}
	
	public static void testBlockingQueue() {
		BlockingQueue<String> q = new ArrayBlockingQueue<String>(100);
		new Thread(new Producer(q)).start();
		new Thread(new Consumer(q), "Consumer1").start();
		new Thread(new Consumer(q), "Consumer2").start();
	}
	
	private static ThreadLocal<Integer> threadLocalUserIds = new ThreadLocal<>();
	
	private static int userId;
	
	private static void testThreadLocal() {
		for(int i = 0; i < 10; i++) {
			final int finalI = i;
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						threadLocalUserIds.set(finalI);
						Thread.sleep(1000);
						System.out.println("ThreadLocal:" + threadLocalUserIds.get());
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}).start();
		}
	}
}



class MyThread extends Thread{
	private int tid;
	
	public MyThread(int tid) {
		this.tid = tid;
	}

	@Override
	public void run() {
		try {
			for(int i = 0; i < 10; i++) {
			Thread.sleep(1000);
			System.out.println(String.format("T%d:%d", tid, i));
			} 
		}catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
	}
}

class Consumer implements Runnable{

	private BlockingQueue<String> q;
	
	public Consumer(BlockingQueue<String> q) {
		super();
		this.q = q;
	}


	@Override
	public void run() {
		try {
			while(true) {
				System.out.println(Thread.currentThread().getName() + ":" + q.take());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
}

class Producer implements Runnable{

	private BlockingQueue<String> q;
	
	public Producer(BlockingQueue<String> q) {
		super();
		this.q = q;
	}


	@Override
	public void run() {
		try {
			for(int i = 0; i < 100; i++) {
				Thread.sleep(1000);
				q.put(String.valueOf(i));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
}
