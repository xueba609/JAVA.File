package com.pratice.wang;

import javax.xml.crypto.Data;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JoinTest {
    public static void main(String[] args) throws InterruptedException {
        Mythread8 ss = new Mythread8();
        Thread thread = new Thread(ss, "子线程");
        thread.start();
        System.out.println(Thread.currentThread().getName());
        thread.join();
        System.out.println("代码结束");
    }

    public static void printTime() {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=format.format(date);
        System.out.println(time);
    }
}