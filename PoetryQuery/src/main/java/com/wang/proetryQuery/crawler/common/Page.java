package com.wang.proetryQuery.crawler.common;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/*
*url-》detail->htmlpage->subpage->data
 */
@Data
public class Page {
    /*
    数据网站的根地址
     */
    private  final String base;

    /*
    具体网页的路径
     */
    private final String path;

    /*
    *网页的DOM对象（文档对象模型）
     */
    private HtmlPage htmlPage;

    /*
    *标示网页是否是详情页
     */
    private final boolean detail;

    /*
     *子页面对象集合
     */
    private Set<Page> subPage=new HashSet<>();

    /*
     *数据的集合
     */
    private DataSet dataSet=new DataSet();

    /*
    *页面的整个网址
     */
    public String geturl(){
        return this.base+this.path;
    }

    public Page(String base,String path,boolean detail){
        this.base=base;
        this.path=path;
        this.detail=detail;

    }
}
