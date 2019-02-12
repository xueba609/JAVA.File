package com.bittech.sort.impl;

import com.bittech.sort.Element;
import com.bittech.sort.Sorter;

/**
 * 插入排序
 * <p>
 * Author: secondriver
 * Created: 2018/10/13
 */
public class InsertSorter implements Sorter {
    //直接插入排序
    @Override
    public void sort(Element[] elements) {
    for(int i=1;i<elements.length-1;i++){
        Element key=elements[i];
        int j;
        for(j=i-1;j>=0;j--){
            //插入的元素与前面已排好序的元素的进行比较，java中比较用compareTo
             if(key.compareTo(elements[j])>=0){
                 break;
             } else{
                 elements[j+1]=elements[j];
             }
        }
        elements[j+1]=key;
    }
    }
}
