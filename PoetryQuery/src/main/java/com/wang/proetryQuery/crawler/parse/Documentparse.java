package com.wang.proetryQuery.crawler.parse;
/*
*文档解析器
 */

import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.wang.proetryQuery.config.ConfigMainContent;
import com.wang.proetryQuery.crawler.common.Page;

public class Documentparse implements Parse {
    @Override
    public void parse(final Page page) {
        if (page.isDetail()) {
            return;
        }
        HtmlPage htmlPage = page.getHtmlPage();

        System.out.println(htmlPage);
        /*如果每次进入的不是详情页面，则进行操作。
        *通过源代码分析，<div class="typecont">
        *               <a helf="    ">
        返回一个集合，通过freEach循环
        */
        //获取网页的连接
        htmlPage.getBody()
                .getElementsByAttribute("div", "class", "typecont")
                .forEach(div -> {
                            //a标签节点的集合
                            DomNodeList<HtmlElement> aNodeList = div.getElementsByTagName("a");
                            aNodeList.forEach(aNode -> {
                                        //将取出的超链接放入到子页面对象集合中
                                        String path = aNode.getAttribute("href");//获取a标签下的属性为href的连接

                                        Page subPage = new Page(page.getBase(), path, true);

                                        System.out.println(subPage);

                                        page.getSubPage().add(subPage);
                                    }
                            );
                }
        );
    }

    }
