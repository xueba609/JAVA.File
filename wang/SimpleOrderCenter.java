package com.wang;/*
    name wang
    */

import com.sun.org.apache.xpath.internal.operations.Or;

import java.io.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SimpleOrderCenter implements OrderCenter {
    private static Map<String, Order> orderMap = new HashMap<>();
    private GoodsCenter goodscenter;
    private String filePath = System.getProperty("user.dir") + File.separator + "order.txt";
    public SimpleOrderCenter(GoodsCenter goodscenter) {
        this.goodscenter = goodscenter;
    }

    @Override
    public void addOrder(Order order) {
        this.orderMap.put(order.getOrderld(), order);
    }

    @Override
    public void removeOrder(Order order) {

        this.orderMap.remove(order.getOrderld());
    }

    @Override
    //所有订单的信息
    public String ordersTable() {
        StringBuilder sb = new StringBuilder();
        sb.append("----------------------");
        sb.append("\n");
        sb.append("订单编号    订单总价");
        sb.append("\n");
        //所用订单商品的循环
        for (Order order : this.orderMap.values()) {
            double tatalPrice = 0.0D;
            Map<String, Integer> goodMap = order.getOrderInfo();
            for (Map.Entry<String, Integer> entry : goodMap.entrySet()) {
                String goodId = entry.getKey();
                Integer InterCounter = entry.getValue();
                Goods goods = goodscenter.getGoods(goodId);
                tatalPrice += goods.getPrice() * InterCounter;
            }
            sb.append(String.format("%2s\t\t%.2f", order.getOrderld(), tatalPrice));
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    //指定订单的所有商品信息
    public String orderTable(String id) {
        StringBuilder sb = new StringBuilder();
        Order order = this.orderMap.get(id);
        sb.append("===============================");
        sb.append("\n");
        sb.append("编号: " + order.getOrderld());
        sb.append("\n");
        sb.append("打印时间: " + LocalDate.now().toString());
        sb.append("\n");
        sb.append("===============================");
        sb.append("\n");
        sb.append("编号     名称      数量     单价");
        sb.append("\n");
        double total = 0.0D;
        for (Map.Entry<String, Integer> entry : order.getOrderInfo().entrySet()) {

            Goods goods = this.goodscenter.getGoods(entry.getKey());
            sb.append(String.format("%2s\t%s\t%d\t%.2f", entry.getKey(), goods.getName(), entry.getValue(), goods.getPrice()));

            total += goods.getPrice() * entry.getValue();

            sb.append("\n");
        }
        sb.append("===============================");
        sb.append("\n");
        sb.append(String.format("总价: %.2f", total));
        sb.append("\n");
        sb.append("===============================");
        sb.append("\n");
        return sb.toString();
    }

    @Override
    public void storeOreder() {
        File file = new File(filePath);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Order order : this.orderMap.values()) {
                double tatalPrice = 0.0D;
                Map<String, Integer> goodMap = order.getOrderInfo();
                for (Map.Entry<String, Integer> entry : goodMap.entrySet()) {
                    String goodId = entry.getKey();
                    Integer InterCounter = entry.getValue();
                    Goods goods = goodscenter.getGoods(goodId);
                    tatalPrice += goods.getPrice() * InterCounter;
                }
                writer.write(String.format("%s\t\t%.2f", order.getOrderld(), tatalPrice));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadOrder() {
        File file = new File(filePath);
        if (file.exists() && file.isFile()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null){
                     String[] value=line.split(":");
                     if(line.length()==2){
                         orderTable(value[0]);
                     }
                    }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
