package offerTest.otherWhat;

import java.util.Scanner;

public class WinMoney {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int [] a=new int[n];
        int [] b=new int[n];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < n; j++){
                if(i==0){
                    a[j]=scanner.nextInt();
                }else {
                    b[j]=scanner.nextInt();
                }
        }
    }
        System.out.println(money(n,a,b));
    }
    private static int money(int n, int[] a, int[] b) {
        int temp=0;
        for(int i=0;i<n;i++) {
            if (a[i] > 0 && b[i] > 0 ) {
                if (a[i] > b[i]) {
                    temp += 100;
                } else if (a[i] == b[i]) {
                    temp += 0;
                } else {
                    temp -= 100;
                }
            }
        }
        return temp;
    }
}
