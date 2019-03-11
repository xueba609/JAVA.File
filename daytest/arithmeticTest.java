package Test1;

import java.util.Scanner;

public class arithmeticTest {
    public static void main(String[] args) {
        Scanner str = new Scanner(System.in);
        int a = str.nextInt();
        if(arithmeticsum(a)==0){
            sum(a);
        }
    }

    private static void sum(int a) {
        int sum=0;
        int c=2;
        for(int i=0;i<a;i++){
            sum+=c;
            c+=3;
        }
        System.out.println(sum);
    }

    private static int arithmeticsum(int a) {
        if (a <= 0) {
            return -1;
        }
        return 0;
    }
}
