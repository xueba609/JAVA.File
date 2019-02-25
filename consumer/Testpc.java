package com.pratice.consumer;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import static com.pratice.consumer.CPCAmount.MAX_Capcity;
import static com.pratice.consumer.CPCAmount.MAx_Producer;
import static com.pratice.consumer.CPCAmount.Max_Consumer;

public class Testpc {
    public static Queue<Goods> queue = new ArrayBlockingQueue<>(MAX_Capcity);

    public static void main(String[] args) {
            Goods goods;
            Producer producer = new Producer();
            //多生产者
            for (int i = 0; i < MAx_Producer; i++) {
                Thread thread = new Thread(producer, "生产者" + i);
                thread.start();
            }
        Consumer consumer = new Consumer();
        //多消费者
        for (int i = 0; i < Max_Consumer; i++) {
            Thread thread1 = new Thread(consumer, "消费者" + i);
            thread1.start();
        }

  }
}
