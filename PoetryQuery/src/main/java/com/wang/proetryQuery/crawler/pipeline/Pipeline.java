package com.wang.proetryQuery.crawler.pipeline;

import com.wang.proetryQuery.crawler.common.Page;

public interface Pipeline {
    /*
    *管道，对数据进行清洗
     */
    void pipeline(final Page page);
}
