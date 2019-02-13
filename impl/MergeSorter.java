package com.bittech.sort.impl;

import com.bittech.sort.Element;
import com.bittech.sort.Sorter;

/**
 * 归并排序
 * <p>
 * Author: secondriver
 * Created: 2018/10/13
 */
public class MergeSorter implements Sorter {
    void merge(Element[] elements,int left,int mid,int right){
        int left_i=left;
        int right_i=mid;
        int i=0;
        Element[] data=new Element[elements.length];
        while((left_i <mid)&&(right_i<right)) {
            if (elements[left_i] .compareTo(elements[right_i])<=0 ) {
                data[i++]=elements[left_i++];
            }else{
                data[i++]=elements[right_i];
            }
            //剩余的依次放入 例如： {7 8 9 10}  {1 2 3 4}
            while(left_i<mid){
                data[i++]=elements[left_i++];
            }
            while(right_i<right){
                data[i++]=elements[right_i];
            }
            i=left;
            for(Element temp:data){
                elements[i++]=temp;
            }
        }
    }
    void mergesort(Element[] elements,int left,int right) {
        if (left >= right) {
            //区间没有元素
            return;
        }
        if (left == right - 1)
        {
            //区间只有一个元素
            return;
        }
        //遇见 unreachable statement异常，是因为你的语句出现了不可执行的语句
        int mid=left+(right-left)/2;
        mergesort(elements,left,mid);
        mergesort(elements,mid,right);
        merge(elements,left,mid,right);
    }
    @Override
    public void sort(Element[] elements) {
        //归并排序，先分离，再联合
        mergesort(elements,0,elements.length);
        }


}
