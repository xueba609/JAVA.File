package com.pratice.wang;

public class MyThread6 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //获取当前线程的名字
            System.out.println("当前线程"+Thread.currentThread().getName()+" i="+i);
        }
    }
}
