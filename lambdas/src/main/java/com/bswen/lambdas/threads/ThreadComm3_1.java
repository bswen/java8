package com.bswen.lambdas.threads;

import java.util.concurrent.CountDownLatch;

/**
 * countdownlatch example.
 * Created on 2018/5/17.
 */
public class ThreadComm3_1 {

    public static void main(String[] args) throws InterruptedException {
        int n = 3;
        CountDownLatch latch = new CountDownLatch(n);
        for(int i=0;i<n;i++) {
            (new Worker("worker"+i,(i+1)*200,latch)).start();
        }
        latch.await();
        System.out.println("all workers done");
    }

    private static class Worker extends Thread {
        private final long sleepTime;
        private final CountDownLatch latch;

        public Worker(String name, long sleepTime, CountDownLatch latch) {
            super(name);
            this.sleepTime = sleepTime;
            this.latch = latch;
        }
        public void run() {
            try {
                Thread.sleep(sleepTime);
                System.out.println(getName()+" stopped");
            }catch (Exception ex) {
                ex.printStackTrace();
            }finally {
                latch.countDown();
            }
        }
    }
}
