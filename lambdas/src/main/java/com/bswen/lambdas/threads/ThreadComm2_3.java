package com.bswen.lambdas.threads;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * demo volatile  thread safe with AtomicInteger.
 * Created on 2018/5/15.
 */
public class ThreadComm2_3 {
    private static volatile AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        Worker worker1 = (new Worker("worker1",1));
        Worker worker2 = (new Worker("worker2",1));
        worker1.start();
        worker2.start();

        for(int i=0;i<10000;i++) {
            count.getAndIncrement();
        }
        worker1.join();
        worker2.join();
        System.out.println("count="+count.get());
    }

    private static class Worker extends Thread {
        private long sleepBeforeStart;

        public Worker(String name,long sleepBeforeStart) {
            super.setName(name);
            this.sleepBeforeStart = sleepBeforeStart;
        }

        public void run() {
            try {
                Thread.sleep(sleepBeforeStart);
                for(int i=0;i<10000;i++) {
                    count.getAndIncrement();
                }
                System.out.println(System.currentTimeMillis()+" "+getName()+" stopped");
            }catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}
