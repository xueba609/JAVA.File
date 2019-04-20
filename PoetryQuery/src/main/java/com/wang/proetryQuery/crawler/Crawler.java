package com.wang.proetryQuery.crawler;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.wang.proetryQuery.crawler.common.Page;
import com.wang.proetryQuery.crawler.parse.Parse;
import com.wang.proetryQuery.crawler.pipeline.Pipeline;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Crawler {
        private static final Logger LOGGER= (Logger) LoggerFactory.getLogger(
            Crawler.class);
    //存放文档页面（超链接）
    //未被采集和解析的页面
   private  Queue<Page> docQueue=new LinkedBlockingQueue<>();
    //放详情页面(处理好的）
    private final Queue<Page> detailQueue=new LinkedBlockingQueue<>();
    //采集器
    private final WebClient webClient;
    //所有的解析器
    private  List<Parse> parseList=new LinkedList<>();
    //管道清洗器
    private final List<Pipeline> pipelineList=new LinkedList<>();
    //线程执行器
    private final   ExecutorService executorService;
    public Crawler(){
        this.webClient=new WebClient(BrowserVersion.CHROME);
        this.webClient.getOptions().setJavaScriptEnabled(false);
        this.executorService= Executors.newFixedThreadPool(8, new ThreadFactory() {
            //线程工厂，自己给自己线程命名,从0开始
            private final AtomicInteger id=new AtomicInteger(0);
            @Override
            public Thread newThread(Runnable r) {
                Thread thread=new Thread(r);
                thread.setName("Crawler_Thread-"+id.getAndIncrement());
                return thread;
            }
        });
    }

    /*
    *启动（爬取，解析，清洗
     */
    public   void start(){
        this.executorService.submit(this::parse);
        this.executorService.submit(this::pipeline);
    }
    private void parse(){
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                LOGGER.error("Parse occur exception {} .",e.getMessage());
                //e.printStackTrace();
            }
            //文档队列
          final Page page=  this.docQueue.poll();
            if(page==null){
                continue;
            }
            //多线程,单->多，调度器里面一方
            this.executorService.submit(() -> {
                try {
                    //采集
                    HtmlPage htmlPage =Crawler.this.webClient.getPage(page.geturl());
                    page.setHtmlPage(htmlPage);
                    for(Parse parse:parseList){
                        parse.parse(page);
                    }
                    //解析
                    if(page.isDetail()){
                        //如果是详情页面
                        Crawler.this.detailQueue.add(page);
                    }else{
                        //文档页面，则继续进入队列，继续进行解析
                        Iterator<Page> iterator=page.getSubPage().iterator();
                        while(iterator.hasNext()){
                            Page subPage=iterator.next();
                            Crawler.this.docQueue.add(subPage);
                            iterator.remove();
                        }
                    }
                } catch (IOException e) {
                    LOGGER.error("Parse  task occur exception {} .",e.getMessage());
                }
            });
        }
    }
    private void pipeline(){
        while(true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                LOGGER.error("Parse occur exception {} .",e.getMessage());
            }
            final Page page = this.detailQueue.poll();
            if (page == null) {
                continue;
            }
            this.executorService.submit(() -> {
                for(Pipeline pipeline:Crawler.this.pipelineList){
                    pipeline.pipeline(page);
                }
            });
        }
    }
    public void addPage(Page page){
        this.docQueue.add(page);
    }
    public void addParse(Parse parse){
        this.parseList.add(parse);
    }
    public void addPipeline(Pipeline pipeline){
        this.pipelineList.add(pipeline);
    }
    /*
    *停止，线程池停止就行。
     */
    public void stop(){
        if(this.executorService != null && !this.executorService.isShutdown()){
            this.executorService.shutdown();
        }
        LOGGER.info("Crawler stop");
    }

}
