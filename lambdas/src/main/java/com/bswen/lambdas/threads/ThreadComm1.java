package com.bswen.lambdas.threads;

import java.util.Random;

/**
 * producer--consumer mode 1.
 * Created on 2018/5/14.
 */
public class ThreadComm1 {
    private static boolean producedOk = false;
    private static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Consumer consumer = new Consumer();
        Producer producer = new Producer();
        consumer.start();
        producer.start();

        consumer.join();
        producer.join();
    }

    static class Producer extends Thread {
        Random random = new Random();
        public void run() {
            synchronized (lock) {
                System.out.println("producing...");
                try {
                    Thread.sleep(random.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("produced ok");
                producedOk = true;
                lock.notify();
            }
        }
    }

    static class Consumer extends Thread {
        public void run() {
            synchronized(lock) {
                while(!producedOk) {
                    try {
                        System.out.println("consumer waiting ...");
                        lock.wait();
                        System.out.println("consumer notifed,awaken.");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("consumed");
            }
        }
    }
}
