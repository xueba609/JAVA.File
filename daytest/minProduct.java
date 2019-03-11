package Test1;

import java.util.Scanner;

/*
求两个数的最小乘积
 */
public class minProduct {
    public static void main(String[] args) {
        Scanner src = new Scanner(System.in);
        while (src.hasNextInt()) {
            int a = src.nextInt();
            int b = src.nextInt();
            minproduct(a, b);
        }
    }

    private static void minproduct(int a, int b) {
        if(a<b){
            int temp=b;
            b=a;
            a=temp;
        }
        int x = a;
        while(true){
            if(x%b==0){
                System.out.println(x);
                return ;
            }
            x += a;
        }
    }
}
   /* private static void minproduct(int a, int b) {
        if (a % b == 0) {
            System.out.println(a);
        } else if (b % a == 0) {
            System.out.println(b);
        } else if(a>b){
            search(a, b);
        }else{
            search(b,a);
        }
    }*/
   /* private static void search(int a,int b) {
        int c=0;
        for (int i=2;i<a;i++){
            if(a%i==0&&b%i==0){
                c=i;
            }
        }
        System.out.println((a*b)/c);

    }
}*/

   /* private static void minproduct(int a, int b) {
        if(a%b==0){
            System.out.println(a);
        }else if(b%a==0){
            System.out.println(b);
        }else if((a%2==0)&&(b%2==0)){
            int c=a*b;
            recursion(c,a,b);
        }else{
            System.out.println(a*b);
        }
    }

    private static void recursion(int i, int a, int b) {
        int q=i/2;
        if((q%a==0)&&(q%b==0)){
            recursion(q,a,b);
        }else{
            System.out.println(q);
        }
    }


}*/
