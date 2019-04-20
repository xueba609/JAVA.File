package com.wang.proetryQuery;
import com.alibaba.druid.pool.DruidDataSource;
import com.wang.proetryQuery.config.ConfigMainContent;
import com.wang.proetryQuery.config.ObjectFactory;
import com.wang.proetryQuery.crawler.Crawler;
import com.wang.proetryQuery.crawler.common.Page;
import com.wang.proetryQuery.crawler.parse.DataPageparse;
import com.wang.proetryQuery.crawler.parse.Documentparse;
import com.wang.proetryQuery.crawler.pipeline.CansolePipeline;
import com.wang.proetryQuery.crawler.pipeline.DataStore;
import com.wang.proetryQuery.web.WebController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AnalyzeApplication {
    //日志
    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(
            AnalyzeApplication.class);

    public static void main(String[] args) {
        //日志
//
        WebController webController = ObjectFactory.getInstance().getObject(WebController.class);
        //运行了web服务，提供接口
        LOGGER.info("Web Server launch ...");
        webController.launch();

        //启动爬虫
        //不应关心内部是如何实现的
        if (args.length == 1 && args[0].equals("run-crawler")) {
            Crawler crawler = ObjectFactory.getInstance().getObject(Crawler.class);
            LOGGER.info("Crawler stared...");
            crawler.start();
        }

//
//        ConfigMainContent configProperties = new ConfigMainContent();
//        final Page page = new Page(configProperties.getCrawlerBase(),
//                configProperties.getCrawlerPath(),
//                configProperties.isCraelerDetail());
//
//
//        Crawler crawler = new Crawler();
//
//        crawler.addParse(new Documentparse());
//
//        crawler.addParse(new DataPageparse());
//
//        crawler.addPipeline(new CansolePipeline());
//
//        DruidDataSource dataSource = new DruidDataSource();
//        dataSource.setUsername(configProperties.getDbUsername());
//        dataSource.setPassword(configProperties.getDbPassword());
//        dataSource.setDriverClassName(configProperties.getDbDriverClass());
//        dataSource.setUrl(configProperties.getDbUrl());
//
//        crawler.addPipeline(new DataStore(dataSource));
//
//        crawler.addPage(page);
//        crawler.start();
    }
}



