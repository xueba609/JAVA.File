package com.pratice.wang;

public class MyThread3 extends Thread {

    private int ticket = 10; // 一共10张票

    @Override
    public void run() {
        while (this.ticket > 0) {
            System.out.println("剩余票数：" + this.ticket--);
        }
    }
}



