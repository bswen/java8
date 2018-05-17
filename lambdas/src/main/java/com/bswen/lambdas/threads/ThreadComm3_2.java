package com.bswen.lambdas.threads;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * demo countdownlatch with thread pool.
 * Created on 2018/5/17.
 */
public class ThreadComm3_2 {
    public static void main(String[] args) throws InterruptedException {
        int n = 3;
        CountDownLatch latch = new CountDownLatch(n);
        ExecutorService pool
                //= Executors.newCachedThreadPool();
                = Executors.newFixedThreadPool(n);
        for(int i=0;i<n;i++) {
            pool.submit(new Worker("worker"+i,(i+1)*200,latch));
        }
        latch.await();
        pool.shutdown();//you must call this, because all pool thread is NON-DAEMON, main thread would not exit
        System.out.println("all workers in pool stopped");
    }


    private static class Worker implements Runnable {
        private final long sleepTime;
        private final CountDownLatch latch;
        private final String name;

        public Worker(String name, long sleepTime, CountDownLatch latch) {
            this.name = name;
            this.sleepTime = sleepTime;
            this.latch = latch;
        }
        public void run() {
            try {
                Thread.sleep(sleepTime);
                System.out.println(name+" stopped");
            }catch (Exception ex) {
                ex.printStackTrace();
            }finally {
                latch.countDown();
            }
        }
    }
}
