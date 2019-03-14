package Test1;

import java.util.Scanner;

public class binaryTest {
    public static void main(String[] args) {
        Scanner src = new Scanner(System.in);
        int i = 0, j = 0, k = 0, m = 0;
            i = src.nextInt();
            j = src.nextInt();
            k = src.nextInt();
            m = src.nextInt();
        System.out.println(binInsert(i, j, k, m));

    }
    private static int binInsert(int i, int j, int k, int m) {
        return i | (j << k);
    }
}

