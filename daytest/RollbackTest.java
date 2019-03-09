package Test1;

import java.util.Locale;
import java.util.Scanner;

public class RollbackTest {
    public static void main(String[] args) {
        Scanner src = new Scanner(System.in);
        while (src.hasNext()) {
            String s = src.nextLine();
            int n = src.nextInt();
            String ss = trans(s, n);
            System.out.println(ss);
        }
    }

    private static String trans(String s, int n) {
        if (s != null) {
            if (n > 0 && n <= 500) {
                String[] ss = s.split(" ");
                String a = ss[0];
                String b = ss[1];
                String c = transform(a, a.length());
                String d = transform(b, b.length());
                return d+" "+c;
            }
        }
        return null;
    }
    private static String transform(String a, int length) {
        String s=a.toLowerCase();
        String s1=s.substring(0,1).toLowerCase()+s.substring(1).toUpperCase();
        return s1;
    }
}
