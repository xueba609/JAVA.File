package offerTest.Array;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

/*
*给定一个无序数组，包含正数、负数和0，
* 要求从中找出3个数的乘积，使得乘积最大，要求时间复杂度：O(n)，空间复杂度：O(1)
 */
public class maxSum {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
       String s=scanner.nextLine();
       String [] data=s.split(" ");
       int [] data1=new int[data.length];
       for(int i=0;i<data.length;i++){
           data1[i]=Integer.parseInt(data[i]);
       }
       search(data1);
    }

    private static void search(int[] data1) {
        if(data1.length==3){
            System.out.println(data1[0]*data1[1]*data1[2]);
        }
        if(data1.length>3) {
        int [] a={data1[0],data1[1],data1[2]};
        Arrays.sort(a);
        int max_1=a[2];
        int max_2=a[1];
        int max_3=a[0];
            for (int i = 3; i < data1.length; i++) {
                if (data1[i] > max_3) {
                    max_3 = data1[i];
                    continue;
                } else if (data1[i] > max_2) {
                    max_2 = data1[i];
                    continue;
                }
                if (data1[i] > max_1) {
                    max_1 = data1[i];
                    continue;
                }
            }
            System.out.println(max_1*max_2*max_3);
        }
    }
}
