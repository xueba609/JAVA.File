package com.wang.proetryQuery.config;

import lombok.Data;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Data
public class ConfigMainContent {
    public String crawlerBase;
    private String crawlerPath;
    private boolean craelerDetail;

    private String dbUsername;
    private String dbPassword;
    private String dbUrl;
    private String dbDriverClass;
    private boolean isStart;
    public ConfigMainContent(){
        //从外部文件中获取
        InputStream inputStream=ConfigMainContent.class.getClassLoader().getResourceAsStream("config.properties");
        Properties p=new Properties();
        try {
            p.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.crawlerBase=String.valueOf(p.get("crawler.base"));
        this.crawlerPath=String.valueOf(p.get("crawler.path"));
        this.craelerDetail=Boolean.parseBoolean(
                String.valueOf(p.get("crawler.detail"))
        );
        this.dbUsername=String.valueOf(p.get("db.username"));
        this.dbPassword=String.valueOf(p.get("db.password"));
        this.dbUrl=String.valueOf(p.get("db.url"));
        this.dbDriverClass=String.valueOf(p.get("db.driver_class"));
        this.isStart=Boolean.valueOf(String.valueOf(p.get("config_isStart")));
    }
}
