package com.pratice.consumer;

import java.util.Queue;

public class Producer implements Runnable {
    private Goods goods;

    @Override
    public void run() {

        while (true) {
            synchronized (Testpc.queue) {
                if (Testpc.queue.isEmpty()) {
                    try {
                        Thread.sleep(1000);
                        Goods goods=new Goods();
                        Testpc.queue.add(goods);
                        System.out.println(Thread.currentThread().getName() + ",生产产品");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        Testpc.queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
