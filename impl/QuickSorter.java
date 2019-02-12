package com.bittech.sort.impl;

import com.bittech.sort.Element;
import com.bittech.sort.Sorter;

/**
 * 快速排序
 * <p>
 * Author: secondriver
 * Created: 2018/10/13
 */
public class QuickSorter implements Sorter {
    int Search(Element[] elements, int left, int right){
        int begin=left;
        int end=right;
        Element del=elements[right];
        while(begin<end){
            while(elements[begin].compareTo(del)<0) {
                begin++;
            }
            elements[end]=elements[begin];
            while(elements[end].compareTo(del)>0){
                end--;
            }
            elements[begin]=elements[end];
        }
        elements[begin]=del;
        return begin;
    }
    void Quicksort(Element[] elements,int left,int right){
        if(left>=right){
            return;
        }
        int del=Search(elements,left,right);
        Quicksort(elements,left,del-1);
        Quicksort(elements,del+1,right);
    }
    @Override
    //快速排序是选一个基准值，基准值左边的比他小，右边比他大
    public void sort(Element[] elements) {
        Quicksort(elements,0,elements.length-1);
    }
}
