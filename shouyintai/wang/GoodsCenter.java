package com.wang;/*
    name wang
    */

import java.io.FileNotFoundException;
import java.sql.SQLException;

public interface GoodsCenter {
     //通过一个接口来实现自己方法
     void addGoods(Goods goods);
     void removeGoods(String id);
     void updataGodds(Goods goods);
     boolean isExiistGoods(String goods);
     Goods getGoods(String id);//获取商品
     String listGoods();
    // void store();//存储商品信息
     void store2();
     //void load() throws FileNotFoundException;//加载商品信息    从文件读一个添加一次货物
     void load2() throws SQLException;//从数据库读商品
}

