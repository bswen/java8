package com.bswen.lambdas.threads;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * countdownlatch example with timeout.
 * Created on 2018/5/17.
 */
public class ThreadComm3_3 {

    public static void main(String[] args) throws InterruptedException {
        int n = 3;
        CountDownLatch latch = new CountDownLatch(n);
        for(int i=0;i<n;i++) {
            (new Worker("worker"+i,(i+1)*1200,latch)).start();
        }
        try {
            /**
             * 	await(long timeout, TimeUnit unit)
                Causes the current thread to wait until the latch has counted down to zero, unless the thread is interrupted, or the specified waiting time elapses.
             */
            latch.await(2, TimeUnit.SECONDS);
            System.out.println("all workers done or timed out");
        }catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private static class Worker extends Thread {
        private final long sleepTime;
        private final CountDownLatch latch;

        public Worker(String name, long sleepTime, CountDownLatch latch) {
            super(name);
            this.sleepTime = sleepTime;
            this.latch = latch;
            setDaemon(true);//would cause main thread to exit if all threads stop or coutndown latch timeout
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
