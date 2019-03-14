package Test1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class hundredChickenTest {
    public static void main(String[] args) {
        Scanner src = new Scanner(System.in);
        if (src.hasNext()) {
            int i, j, k;
            for (i = 0; i < 20; i += 2) {
                for (k = 0; k < 100; k += 3) {
                    for (j = 0; j < 30; j++) {
                        if (i + j + k == 100) {
                            if (((i * 5) + (3 * j) + k / 3) == 100) {
                                System.out.print(i + " ");
                                System.out.print(j + " ");
                                System.out.print(k);
                                System.out.println();
                            }
                        }
                    }
                }
            }
        }
    }
}
