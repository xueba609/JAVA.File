package com.pratice.wang;

public class StopTest1 {
    public static void main(String[] args) throws InterruptedException {
        MyThread10 mythread=new MyThread10();
        Thread thread=new Thread(mythread,"子线程");
         thread.start();
         Thread.sleep(1000);
         //mythread9.setFlag(false);
        //thread.stop();
        //方法interrupt()只是给受阻塞的线程发出一个中断信号，这样受阻线程就得以退出阻塞的状态。
        thread.interrupt();
    }
}
