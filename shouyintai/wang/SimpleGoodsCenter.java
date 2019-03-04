package com.wang;/*
    name wang
    */



import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class SimpleGoodsCenter implements GoodsCenter {
    private static Map<String, Goods> goodsMap = new HashMap<>();
    //1.文件类
    //private String filePath = System.getProperty("user.dir") + File.separator + "goods.txt";


    @Override
    public void addGoods(Goods goods) {
        //增加商品
        this.goodsMap.put(goods.getId(), goods);
    }

    @Override
    public void removeGoods(String id) {
        //移除商品
        this.goodsMap.remove(id);
    }

    @Override
    public void updataGodds(Goods goods) {
        //更新商品
        if (this.goodsMap.containsKey(goods.getId())) {
            this.goodsMap.put(goods.getId(), goods);
        }
    }

    @Override
    public boolean isExiistGoods(String id) {
        //判断商品是否存在
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

   /* @Override
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
        }*/

    @Override
    public void store2(){
        JdbcManage jd = new JdbcManage() {
            @Override
            public ResultSet handle() {
                return null;
            }
        };
        jd.loadDrive();
        jd.connect("root","123456");
        jd.createSql();
        //sqlDelete命令会将数据库表中的所有数据清空。
        String sqlDelete = "delete from goods";
        jd.executeOrder(sqlDelete);
        //开始将数据保存到数据库中
        for(Goods good: goodsMap.values()){
            String sql = String.format("insert into goods values (%s,'%s',%.2f)"
                    ,good.getId(),good.getName(),good.getPrice());
            jd.executeOrder(sql);
            jd.myCommit();
        }
        jd.colseAll();
    }
    @Override
    public void load2() throws SQLException {
        JdbcManage jdbcManage=new JdbcManage() {
            @Override
            public ResultSet handle() {
                return null;
            }
        };
        jdbcManage.loadDrive();
        jdbcManage.connect("root","123456");
        jdbcManage.createSql();
        String sql="select * from goods";
        jdbcManage.executeOrder(sql);
        ResultSet resultSet=jdbcManage.handle();
        while(resultSet.next()){
            Goods goods=new Goods(resultSet.getString("goods_id"),
                    resultSet.getString("goods_name"),
                    resultSet.getDouble("goods_price"));
           this.addGoods(goods);
        }
        jdbcManage.myCommit();
        jdbcManage.colseAll();
    }
}
