package offerTest.otherWhat;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

import java.util.Scanner;

/*
*哈利波特找零钱
*    实际钱     给的钱
* 给 10.16.27 14.1.28
*     找的钱
* 返回3.2.1
* 加隆—西科 1:17  西科—纳特1：29
 */

public class looseChange {
    private static final int[] W = {17*29,29,1};
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        //next自动可以进行区分空格
        String[] p = in.next().split("\\.");
        String[] a = in.next().split("\\.");
        int[] P = {Integer.parseInt(p[0]),Integer.parseInt(p[1]),Integer.parseInt(p[2])};
        int[] A = {Integer.parseInt(a[0]),Integer.parseInt(a[1]),Integer.parseInt(a[2])};
        //全部准换为最低位
        int ta = A[0]*W[0]+A[1]*W[1]+A[2]*W[2];
        int tp = P[0]*W[0]+P[1]*W[1]+P[2]*W[2];
        int t = ta-tp;
        if(ta<tp){
            System.out.print("-");
            t = -t;
        }
        //%后是本位后面的值，/是本位的值
       // System.out.println(t/W[0]+"."+t%W[0]/W[1]+"."+t%W[0]%W[1]/W[2]);
    }
}
