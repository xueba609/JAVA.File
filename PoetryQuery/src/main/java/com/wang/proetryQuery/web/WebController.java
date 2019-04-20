package com.wang.proetryQuery.web;

import com.google.gson.Gson;
import com.wang.proetryQuery.analyze.model.AuthorCount;
import com.wang.proetryQuery.analyze.service.AnalyzeApplication;
import com.wang.proetryQuery.analyze.model.WordCount;
import com.wang.proetryQuery.config.ObjectFactory;
import com.wang.proetryQuery.crawler.Crawler;
import spark.ResponseTransformer;
import spark.Spark;
import java.util.List;
/*
*1.Sparkjava框架完成Web API开发
* 2.Servlet  技术实现（到时候继承这个类）
 */
public class WebController {
    private final AnalyzeApplication analyzeApplication;

    public WebController(AnalyzeApplication analyzeApplication) {
        this.analyzeApplication = analyzeApplication;
    }
    //url地址
    //->http://127.0.0.1:4567/
    //->/analyze/AuthorCount
    public List<AuthorCount> analyzeproductionAmoubt(){
        return analyzeApplication.analyzeProductionAmount();
    }
    //url地址
    //->http://127.0.0.1:4567/
    //->/analyze/WordCount
    public List<WordCount> analyzeWordAmount(){
        return analyzeApplication.analyzeWordCloud();
    }
    //运行web
    public void launch(){
        ResponseTransformer transformer=new JSONResponseTransformer();
        //前端静态文件的目录
        Spark.staticFileLocation("/static");
        //服务端接口
        Spark.get("/analyze/author_count",((request, response) ->
        analyzeproductionAmoubt()),transformer);
        Spark.get("/analyze/word_cloud",((request, response) ->
        analyzeWordAmount()),transformer);
        Spark.get("/crawler/stop", ((request, response) -> {
            Crawler crawler = ObjectFactory.getInstance().getObject(Crawler.class);
            crawler.stop();
            return "爬虫停止";
        }));
    }
    public  static class JSONResponseTransformer implements ResponseTransformer{
        //Object ->string
        private  Gson gson=new Gson();
        @Override
        public String render(Object model) throws Exception {
            return gson.toJson(model);
        }
    }
}

