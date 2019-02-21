package com.pratice.wang;

import java.util.concurrent.Callable;

public class MyThread5  implements Callable<String> {
    private int ticket = 10 ; // 一共10张票
    @Override
    public String call() throws Exception {
        while(this.ticket>0){
            System.out.println("剩余票数："+this.ticket -- );
        }
        return "票卖完了，下次吧。。。" ;
    }
}
