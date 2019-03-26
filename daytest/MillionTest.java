package Test1.Test326;
/*
*百万富翁和陌生人
 */


import java.util.Scanner;

public class MillionTest {
    public static void main(String[] args) {
        Scanner str=new Scanner(System.in);
        Integer s=str.nextInt();
        commetManey(s);
    }

    private static void commetManey(Integer s) {
        Integer millionSum=1;
        Integer strangeSum=10000;
        Integer temp=1;
       while(s>1){
           if(strangeSum<Integer.MAX_VALUE&&millionSum<Integer.MAX_VALUE) {
               strangeSum += 10000;
               temp = temp * 2;
               millionSum += temp;
               s--;
           }
       }
       System.out.println(millionSum+" "+strangeSum);
    }
}
