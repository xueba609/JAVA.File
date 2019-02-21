package com.pratice.wang;

import sun.awt.geom.AreaOp;

public class MyThread1 implements Runnable{
    private String extra;
    public MyThread1(String extra){
        this.extra=extra;
    }
    @Override
    public void run() {
        for(int i=0;i<10;i++){
            System.out.println(this.extra+",i="+i);
        }
    }
}
