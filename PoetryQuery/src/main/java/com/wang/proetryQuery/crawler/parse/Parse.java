package com.wang.proetryQuery.crawler.parse;

import com.wang.proetryQuery.crawler.common.Page;

public interface Parse {
    /*
    *解析页面
    * 先解析文档页面--》详情页面--》数据
     */
    void parse(final Page page);
}
