package com.pratice.wang;

public class MyThread10 implements Runnable {
                    private boolean flag = true;
                    @Override
                    public void run() {
                        int i = 1;
                        while (flag) {
                            try {
                                //这里阻塞之后,线程被调用了interrupte()方法，
                                Thread.sleep(1000);
                                //默认中断标志是false,
                /*当调用interurupted,将
                中断标志改为true，若非阻塞状态
                则为true,并结束，当阻塞状态
                重新设置为false，*/
                boolean bool = Thread.currentThread().isInterrupted();
                if (bool) {
                    System.out.println("非阻塞情况下执行该操作。。。线程状态" + bool);
                    break;
                }
                System.out.println("第"+i+"次执行，线程名称为:"+Thread.currentThread().getName());
                i++;
            } catch (InterruptedException e) {
                System.out.println("退出了");
                boolean bool = Thread.currentThread().isInterrupted();
                System.out.println(bool);
                return;
            }
        }
    }
    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}

