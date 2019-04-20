package com.wang.proetryQuery.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.wang.proetryQuery.analyze.dao.AnalyzeDao;
import com.wang.proetryQuery.analyze.dao.impl.AnalyzeDaoImpl;
import com.wang.proetryQuery.analyze.service.AnalyzeServiceImpl;
import com.wang.proetryQuery.crawler.Crawler;
import com.wang.proetryQuery.crawler.common.Page;
import com.wang.proetryQuery.crawler.parse.DataPageparse;
import com.wang.proetryQuery.crawler.parse.Documentparse;
import com.wang.proetryQuery.crawler.pipeline.CansolePipeline;
import com.wang.proetryQuery.crawler.pipeline.DataStore;
import com.wang.proetryQuery.web.WebController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/*
*这里主要存放各种对象，避免主程序繁重
 */
public class ObjectFactory {
    //单例，饿汉式
    private static final ObjectFactory instance = new ObjectFactory();
    private final Logger logger=LoggerFactory.getLogger(ObjectFactory.class);
    //存放所有对象
    private  final Map<Class,Object> objectHashMap =new HashMap<>();

    private  ObjectFactory(){
        //1.初始化配置对象
        initConfigMainContent();

        //2.数据源对象
        initDataSource();

        //3.爬虫对象
        initCrawler();

        //4.web对象
        initWebController();

        //5.打印清单
        PrintObjectList();
    }



    private void initWebController() {
        //数据源
        DataSource dataSource = getObject(DataSource.class);
        AnalyzeDao analyzeDao = new AnalyzeDaoImpl(dataSource);
        AnalyzeServiceImpl analyzeApplication = new AnalyzeServiceImpl(analyzeDao);
        WebController webController=new WebController(analyzeApplication);

        objectHashMap .put(WebController.class,webController);
    }

    private void initCrawler() {
        ConfigMainContent configMainContent=getObject(ConfigMainContent.class);
        DataSource dataSource=getObject(DataSource.class);
        final Page page=new Page(
                configMainContent.getCrawlerBase(),
                configMainContent.getCrawlerPath(),
                configMainContent.isCraelerDetail());

        Crawler crawler=new Crawler();

        crawler.addParse(new Documentparse());
        crawler.addParse(new DataPageparse());
        //通过配置的值来判断是否启动
        if(configMainContent.isStart()) {
            crawler.addPipeline(new CansolePipeline());
        }
        crawler.addPipeline(new DataStore(dataSource));
        crawler.addPage(page);
        objectHashMap .put(Crawler.class,crawler);
    }

    private void initDataSource() {
        ConfigMainContent configMainContent=getObject(ConfigMainContent.class);
        DruidDataSource dataSource=new DruidDataSource();
        dataSource.setUsername(configMainContent.getDbUsername() );
        dataSource.setPassword(configMainContent.getDbPassword());
        dataSource.setDriverClassName(configMainContent.getDbDriverClass());
        dataSource.setUrl(configMainContent.getDbUrl());
        objectHashMap .put(DataSource.class,dataSource);
    }

    private void initConfigMainContent() {
        ConfigMainContent configMainContent=new ConfigMainContent();

        objectHashMap.put(ConfigMainContent.class,configMainContent);
        //这一步方便我们查找配置信息哪里出错
        logger.info(" ConfigMainContent info:\n{}",configMainContent.toString());
    }

    public  <T> T getObject(Class classz){
        if(!objectHashMap .containsKey(classz)){
            throw new IllegalArgumentException(classz.getName()+"not found");
        }
        return (T) objectHashMap .get(classz);
    }

    public static ObjectFactory getInstance(){
        return instance;
    }
    private void  PrintObjectList(){
        logger.info("------------对象工厂清单----------");
        for(Map.Entry<Class,Object> entry:objectHashMap .entrySet()) {
            logger.info(String.format("\t[%-5s]->[%s]",
                    entry.getKey().getCanonicalName(), entry.getValue().getClass().getCanonicalName()));
        }
        logger.info("----------打印完毕------------");
        }
    }

