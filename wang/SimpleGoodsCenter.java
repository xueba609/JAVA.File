package com.wang;/*
    name wang
    */



import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class SimpleGoodsCenter implements GoodsCenter {
    private static Map<String, Goods> goodsMap = new HashMap<>();
    //1.文件类
    private String filePath = System.getProperty("user.dir") + File.separator + "goods.txt";


    @Override
    public void addGoods(Goods goods) {
        //增加商品
        this.goodsMap.put(goods.getId(), goods);
    }

    @Override
    public void removeGoods(String id) {
        this.goodsMap.remove(id);
    }

    @Override
    public void updataGodds(Goods goods) {
        if (this.goodsMap.containsKey(goods.getId())) {
            this.goodsMap.put(goods.getId(), goods);
        }
    }

    @Override
    public boolean isExiistGoods(String id) {

        if (this.goodsMap.containsKey(id)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Goods getGoods(String id) {
        return this.goodsMap.get(id);
    }

    @Override
    public String listGoods() {
        StringBuilder s = new StringBuilder();
        s.append("**********商品信息**********\n");
        s.append("\t商品编号\t商品名称\t商品价格");
        s.append("\n");
        for (Map.Entry<String, Goods> entry : this.goodsMap.entrySet()) {
            Goods goods = entry.getValue();
            s.append(String.format("\t\t%s\t\t\t%s\t\t%.2f\n",
                    goods.getId(),
                    goods.getName(),
                    goods.getPrice()));

        }
        s.append("*******************************************\n");
        return s.toString();
    }

    @Override
    public void store() {
        //1.文件类
        {
            File file = new File(filePath);
            try (BufferedWriter writer = new BufferedWriter(
                    new FileWriter(file)
            )) {
                for (Map.Entry<String, Goods> entry : this.goodsMap.entrySet()) {
                    Goods goods = entry.getValue();
                    writer.write(String.format("%s:%s:%.2f\n", goods.getId(), goods.getName(), goods.getPrice()));

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //2.jdbc
        //3.maven
    }

    @Override
    public void load() {
        File file = new File(filePath);
        if (file.exists() && file.isFile()) {
            //加载，读取文件
            try (BufferedReader reader = new BufferedReader(
                    new FileReader(file)
            )) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] values = line.split(":");
                    if (values.length == 3) {
                        Goods goods = new Goods(
                                values[0],
                                values[1],
                                Double.parseDouble(values[2])
                        );
                        this.addGoods(goods);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
