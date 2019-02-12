package com.bittech.sort.impl;

import com.bittech.sort.Element;
import com.bittech.sort.Sorter;

/**
 * 选择排序
 * <p>
 * Author: secondriver
 * Created: 2018/10/13
 */
public class SelectSorter implements Sorter {
    void  swap ( Element[] data,  int  a,  int  b) {
        Element  t = data [a];
        data [a] = data [b];
        data [b] = t;
    }
    @Override
    //直接选择排序

    public void sort(Element[] elements) {
      int left=0;
      int right=elements.length-1;
      while(left<right){
          int j=0;
          int min=left;
          int max=right;
          for(j=left+1;j<=right;j++){
              if(elements[j].compareTo(elements[min])<0){
                  min=j;
              }
              if(elements[j].compareTo(elements[max])>0){
                  max=j;
              }
          }
          swap(elements,left,min);
          //如果最大值在左边的话，交换后最大值位置就会发生变化，新的最大值位置在最小值位置上
          if(max==left){
              max=min;
          }
          swap(elements,right,max);
          left++;
          right--;
      }
    }
}
