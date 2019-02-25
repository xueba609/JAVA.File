package com.pratice.consumer;

public class Consumer implements Runnable {
    private Goods goods;

    @Override
    public void run() {
            while (true) {
        synchronized (Testpc.queue) {
                if (!Testpc.queue.isEmpty()) {
                    try {
                        Thread.sleep(500);
                        Testpc.queue.poll();
                        System.out.println(Thread.currentThread().getName()+"消费产品");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    Testpc.queue.notify();
                }
            }
        }
    }
}
