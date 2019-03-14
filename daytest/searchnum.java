package Test1;
/*给你n个数，找出其中大于n/3的数
 */
import java.util.Arrays;
import java.util.Scanner;
import java.util.*;
public class searchnum{
    public static void main(String[] args) {
        Scanner str = new Scanner(System.in);
        while (str.hasNextLine()) {
            String s = str.nextLine();
            String[] s1 = s.split(" ");
            Integer[] a = new Integer[s1.length];
            for (int i = 0; i < s1.length; i++) {
                a[i] = Integer.parseInt(s1[i]);
            }
            System.out.println(bigequalHalf(a, a.length));
        }
    }
    private static int bigequalHalf(Integer[] a, int length) {
        Arrays.sort(a);
        int i,j;
        int count=0;
        for( i=0;i<length;i++){
            for (j=0;j<length;j++){
                if(a[i]==a[j]){
                    count++;
                }
                if(count>length/2){
                    return a[i];
                }
            }
        }
        return 0;
    }
}
