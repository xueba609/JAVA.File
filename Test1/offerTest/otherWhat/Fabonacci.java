package offerTest.otherWhat;

import java.util.Scanner;

public class Fabonacci {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        System.out.println(Fabonacci1(n));
    }

    private static int Fabonacci1(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n <= 2) {
            return 1;
        }
        int tem1 = 0;
        int tem2 = 1;
        int sum = 0;
        for (int i = 2; i <= n; i++) {
            sum = tem1 + tem2;
            tem1 = tem2;
            tem2 = sum;
        }
        return sum;
    }
    }
