package com.bittech.sort.impl;

import com.bittech.sort.Element;
import com.bittech.sort.Sorter;

/**
 * 堆排序
 * <p>
 * Author: secondriver
 * Created: 2018/10/13
 */
public class HeapSorter implements Sorter {
    void  swap ( Element[] data,  int  a,  int  b) {
        Element  t = data [a];
        data [a] = data [b];
        data [b] = t;
    }

    void AdjustHeap(Element[] elements,int size,int root){
        int left=root*2+1;
        int right=root*2+2;
        //越界返回
        if(left>=size){
            return;
        }
        //假设左孩子最大
        int max=left;
        if((right<size)&&(elements[right].compareTo(elements[left])>0)){
            max=right;
        }
        if(elements[root].compareTo(max)>0){
            return;
        }
        swap(elements,root,max);
        AdjustHeap(elements,size,max);
    }
    void CreteHeap(Element[] elements,int size){
        for(int i=size/2-1;i>=0;i--){
            AdjustHeap(elements,size,i);
        }
    }
    @Override
    //稳定的排序
    // 时间复杂度均为O( log2N)，所以堆排序的时间复杂度为：O( N*log2N)
    public void sort(Element[] elements) {
        //建大堆
        CreteHeap(elements,elements.length);
        int i;
        for(i=0;i<elements.length;i++) {
            //第一个元素和最后elements.length-1元素进行交换
          swap(elements,0,elements.length-1);
          //重新建堆
          AdjustHeap(elements,elements.length-i,0);
        }
    }
}
