package com.wang.proetryQuery.crawler.common;

import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

/*
*存储清洗的数据
 */
@ToString
public class DataSet {
    /*
    *data把Dom解析，清洗之后存储的数据
    * 标题：
    * 作者
    * 正文
     */
    private Map<String ,Object> data=new HashMap<>();
    public void putData(String key,Object value){
        this.data.put(key,value );
    }
    //获取某个数据
    public Object getData(String key){
        return this.data.get(key);
    }
    //获取所有的数据
    public Map<String,Object> getData(){
        //不要使用这种，不安全，把自己对象的引用给了外部调用
        //return this.data;
        //把自己的数据重新遍历存一份给外部
        return new HashMap<>(this.data);
    }


}
