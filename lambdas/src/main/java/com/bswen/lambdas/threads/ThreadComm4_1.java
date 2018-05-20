package com.bswen.lambdas.threads;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.concurrent.CyclicBarrier;

/**
 * Using CyclicBarrier classic example.
 * Created on 2018/5/19.
 */
public class ThreadComm4_1 {
    private static Log log = LogFactory.getLog(ThreadComm4_1.class);

    public static void main(String[] args) throws InterruptedException {
        int N = 3;
        CyclicBarrier barrier = new CyclicBarrier(N,()-> log.debug("all workers done"));
        log.debug("starting the workers...");
        for(int i=1;i<=N;i++) {
            Worker worker = (new Worker("worker"+i,i*2000,barrier));
            worker.start();
        }

    }

    private static class Worker extends Thread {
        private final long sleepTime;
        private final CyclicBarrier barrier;

        public Worker(String name, long sleepTime, CyclicBarrier barrier) {
            super(name);
            this.sleepTime = sleepTime;
            this.barrier = barrier;
        }
        public void run() {
            try {
                log.debug(getName()+" started...");
                Thread.sleep(sleepTime);
                log.debug(getName()+" waiting...");
                int arriveIndex = barrier.await();
                log.debug(getName()+" arrived barrier at "+arriveIndex);
            }catch (Exception ex) {
                ex.printStackTrace();
            }finally {
            }
        }
    }
}
