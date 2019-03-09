package Test1;

import java.util.Scanner;

public class AppleTest {
    public static void main (String []args){
        Scanner src =new Scanner(System.in);
        int sum=src.nextInt();
        int a=buyApple(sum);
        System.out.println(a);
    }
    private static int buyApple(int sum) {
        if(sum>0&&sum<200){
            if(sum%8==0){
                return sum/8;
            }
            if(sum%6==0){
                return sum/6;
            }
            if(sum%8%6==0){
                return ((sum/8)+((sum%8)/6));
            }
            if(sum%6==2){
                return ((sum/8)+1);
            }
        }
        return -1;
    }
}
