package com.pratice.consumer;

public class Goods {
    private int id;
    private String name;
    public void set (int id,String name){
        this.id=id;
        this.name=name;
    }
    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
