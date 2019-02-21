package com.pratice.wang;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class MyThread extends Thread {
    public static class TestDemo {
        public static void main(String[] args) throws InterruptedException,
                ExecutionException {
            FutureTask<String> task = new FutureTask<>(new MyThread5()) ;
            new Thread(task).start();
            new Thread(task).start();
            System.out.println(task.get());
        }
    }

}
