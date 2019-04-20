package com.wang.proetryQuery.analyze.dao;

import com.wang.proetryQuery.analyze.entity.PoetyInfo;
import com.wang.proetryQuery.analyze.model.AuthorCount;

import java.util.List;
/*这一层主要从数据库拿数据
*
 */
public interface AnalyzeDao {
    /*
    *分析作者的创作数量
     */
    List<AuthorCount> analyzeProductionAmount();

    /*
    *查询所有的诗文，提供给业务层进行分析
     */
    List<PoetyInfo> queryAllPoetyInfo();

}
