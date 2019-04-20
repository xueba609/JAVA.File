package com.wang.proetryQuery.crawler.parse;

import com.gargoylesoftware.htmlunit.html.*;
import com.wang.proetryQuery.crawler.common.Page;

/*
 *详情界面进行解析，解析出内容
 * 解析完给管道清洗
 */
public class DataPageparse implements Parse {
    @Override
    public void parse(Page page) {
        //判断是否是详情页面
        if (!page.isDetail()) {
            return;
        }
        HtmlPage htmlPage = page.getHtmlPage();
        HtmlElement body=htmlPage.getBody();
        System.out.println(htmlPage);
        //标题
        //h1只有一个，所以取0
        //text()意思是转成字符串
        String titlePath="//div[@class='cont']/h1/text()";
        DomText titleDom = (DomText) body.getByXPath(titlePath).get(0);
        String title=titleDom.asText();
        //朝代
        String dynastyPath="//div[@class='cont']/p/a[1]";
        HtmlAnchor dyanstDom= (HtmlAnchor) body.getByXPath(dynastyPath).get(0);
        String dynasty=dyanstDom.asText();
        //作者
        String authorPath="//div[@class='cont']/p/a[2]";
        HtmlAnchor authorDom=(HtmlAnchor) body.getByXPath(authorPath).get(0);
        String author=authorDom.asText();
        //内容
        String contentPath="//div[@class='cont']/div[@class='contson']";
        HtmlDivision contentDom=(HtmlDivision) body.getByXPath(contentPath).get(0);
        String content=contentDom.asText();

        //方便，在这里只需要把你想要的page数据加进去
        page.getDataSet().putData("title",title);
        page.getDataSet().putData("dynasty",dynasty);
        page.getDataSet().putData("author",author);
        page.getDataSet().putData("content",content);
        page.getDataSet().putData("url",page.geturl());


    }
}
