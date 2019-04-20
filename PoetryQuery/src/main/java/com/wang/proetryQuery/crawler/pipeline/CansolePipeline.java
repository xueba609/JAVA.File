package com.wang.proetryQuery.crawler.pipeline;

import com.sun.javafx.collections.MappingChange;
import com.wang.proetryQuery.crawler.common.Page;

import java.util.Map;

public class CansolePipeline implements Pipeline {
    @Override
    public void pipeline(final Page page) {
        Map<String,Object> data=page.getDataSet().getData();
        //存储
        System.out.println(data);
    }
}
