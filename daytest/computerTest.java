package Test1;

import java.util.Scanner;

public class computerTest {
    public static void main(String[] args) {
       Scanner str=new Scanner(System.in);
        if(str.hasNextInt()) {
            long a = str.nextInt();
            long b = str.nextInt();
            long c = str.nextInt();
            long d = str.nextInt();
            figureComouter(a, b, c, d);
        }

    }
    private static void   figureComouter(long a, long b, long c, long d) {

            double A = (a + c) / 2+((a+c)%2)/2.0;
             double B = (c - a) / 2+((c-a)%2)/2.0;
            double C = (d - b) / 2+((d-b)%2)/2.0;
            if (((A > -30 && A < 30) && (A % 1 == 0)) && ((B > -30 && B < 30) && (B % 1 == 0)) && ((C > -30 && C < 30) && (C % 1 == 0))) {
                System.out.println(A + " " + B + " " + C);
            }else {
                System.out.println("No");
            }
        }
    }
