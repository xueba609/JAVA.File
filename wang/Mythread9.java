package com.pratice.wang;

public class Mythread9 implements Runnable {
    private volatile boolean flag = true;
    @Override
    public void run() {
        int i = 0;
        while (flag) {
            try {
                Thread.sleep(1000);
                i++;
                System.out.println("线程：" + Thread.currentThread().getName() + "第" + i + "次执行");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
