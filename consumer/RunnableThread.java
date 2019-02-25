package com.pratice.consumer;

public class RunnableThread implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            for(int i=0;i<10;i++)
            System.out.println(Thread.currentThread().getName()+"帅哥"+i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
