package com.wang;/*
    name wang
    */

import java.io.FileNotFoundException;

public interface OrderCenter {
    void addOrder(Order order);
    void removeOrder(Order order);
    String ordersTable();//订单的所有信息
    String orderTable(String id);//指定商品的订单信息
    void storeOreder();
    void loadOrder() throws FileNotFoundException;
}
