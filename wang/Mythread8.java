package com.pratice.wang;

public class Mythread8 implements Runnable {
    @Override
    public void run() {
        try {
            System.out.println("主线程停止前的时间");
            JoinTest.printTime();
            Thread.sleep(2000);
            System.out.println("主线程停止结束的时间");
            JoinTest.printTime();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
