package com.pratice.wang;

public class SleepTest {
    public static void main(String[] args) {
        MyThread7 ss=new MyThread7();
        new Thread(ss).start();
        new Thread(ss).start();
        new Thread(ss).start();

    }
}
