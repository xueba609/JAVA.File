package com.wang;/*
    name wang
    */


import java.util.HashMap;
import java.util.Map;

public class Order {
    //订单编号
    private final String orderld;
    private final Map<String, Integer> goodInfo = new HashMap<>();

    public Order(String orderld) {
        this.orderld = orderld;

    }


    public String getOrderld() {
        return orderld;
    }


    //订单某个商品的增加，减少，删除
    public void add(String goodsId, Integer count) {
        int sum;
        if (goodInfo.containsKey(goodsId)) {
            sum = goodInfo.get(goodsId) + count;
        } else {
            sum = count;
        }
        this.goodInfo.put(goodsId, sum);
    }

    public void cancel(String goodsId, Integer count) {
        int sum;
        if (goodInfo.containsKey(goodsId)) {
            sum = goodInfo.get(goodsId) - count;
            sum = sum < 0 ? 0 : sum;
            if (sum == 0) {
                this.goodInfo.remove(goodsId);
            } else {
                this.goodInfo.put(goodsId, sum);
            }
        } else {
            this.goodInfo.put(goodsId, count);
        }
    }

    public void clear() {
        //map里面有个clear方法
        goodInfo.clear();
    }

    public Map<String,Integer> getOrderInfo() {
        return this.goodInfo;
    }
}





