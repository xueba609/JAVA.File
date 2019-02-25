package com.pratice.consumer;

import java.util.concurrent.*;

/*用于测试线程池

 */
public class Test {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        RunnableThread runnableThread=new RunnableThread();
//        ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor(3,5,20,TimeUnit.MILLISECONDS ,
//                new LinkedBlockingDeque<>());
//        //execute()方法用于提交不需要返回值的任务，所以无法判断任务是否被线程池执行成功。
//        threadPoolExecutor.execute(runnableThread);
        CallableThread callableThread =new CallableThread();
        ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor(3,5,20,TimeUnit.MILLISECONDS ,
               new LinkedBlockingDeque<>());
        for(int i=0;i<5;i++){
            Future<String> future=threadPoolExecutor.submit(callableThread);
            String str=future.get();
            System.out.println(str);
        }
    }
}
