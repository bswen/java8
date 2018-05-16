package com.bswen.lambdas.threads;

/**
 * Created on 2018/5/15.
 */
public class ThreadComm2 {
    private static volatile boolean flag = true;

    public static void main(String[] args) throws InterruptedException {
        (new Worker("worker1",500,0)).start();
        (new Worker("worker2",500,0)).start();
        Thread.sleep(1000);

        System.out.println(System.currentTimeMillis()+" main stopped the flag");
        flag = false;
    }

    private static class Worker extends Thread {
        private long sleepBeforeStart;
        private long sleepInterval;

        public Worker(String name,long sleepBeforeStart,long sleepInterval) {
            super.setName(name);
            this.sleepBeforeStart = sleepBeforeStart;
            this.sleepInterval = sleepInterval;
        }

        public void run() {
            try {
                Thread.sleep(sleepBeforeStart);
                while(flag) {
                    //System.out.println(getName()+" is working ");
                    Thread.sleep(sleepInterval);
                }
                System.out.println(System.currentTimeMillis()+" "+getName()+" stopped");
            }catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}
