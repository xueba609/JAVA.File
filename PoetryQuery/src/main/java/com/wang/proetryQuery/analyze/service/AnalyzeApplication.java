package com.wang.proetryQuery.analyze.service;

import com.wang.proetryQuery.analyze.model.AuthorCount;
import com.wang.proetryQuery.analyze.model.WordCount;

import java.util.List;

public interface AnalyzeApplication {
    /*
    *分析作者作品数量
     */
    List<AuthorCount> analyzeProductionAmount();
    /*
    *分析词云，判断词出现的数量
     */
    List<WordCount> analyzeWordCloud();
}
