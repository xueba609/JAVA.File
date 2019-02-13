package com.bittech.sort.impl;

import com.bittech.sort.Element;
import com.bittech.sort.Sorter;

/**
 * 希尔排序
 * <p>
 * Author: secondriver
 * Created: 2018/10/13
 */
public class ShellSorter implements Sorter {

    void elseInsertSorter(Element[] elements,int size,int gap){
        int i,j;
        Element key;
        for(i=gap;i<size;i++){
            key=elements[i];
            for(j=i-gap;j>=0;j -=gap){
                if(key.compareTo(elements[j])>0){
                    break;
                }else{
                    elements[j+gap]=elements[j];
                }
            }
            elements[j+gap]=key;
        }
    }
    @Override
        public void sort (Element[]elements){
            int gap = elements.length;
            while (true) {
                gap = gap / 3 + 1;
                elseInsertSorter(elements, elements.length, gap);
                if (gap == 1) {
                    break;
                }
            }
        }
    }