package com.pratice.wang;

public class MyThread7 implements Runnable {
    @Override
    public void run() {
        for(int i=0;i<10;i++){
            Thread.yield();
            System.out.println("当前线程"+Thread.currentThread().getName()+", i="+i);
        }
    }
}
