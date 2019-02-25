package com.pratice.consumer;

import java.util.concurrent.Callable;

public class CallableThread implements Callable<String> {
    @Override
    public String call() throws Exception {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName()+"   "+i);
        }
            return Thread.currentThread().getName();
    }
}
