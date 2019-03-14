package Test1;

import java.util.Scanner;

public class reversalTest {
    public static void main(String[] args) {
        Scanner str=new Scanner(System.in );
        String s=str.nextLine();
        char [] s1=s.toCharArray();
      System.out.println(swap(s1,0,s1.length));
    }
    private static char[] swap(char[] s1, int i, int length) {
        int begin=i;
        int end=length-1;
        while(begin<end){
            char temp=s1[begin];
            s1[begin]=s1[end];
            s1[end]=temp;
            begin++;
            end--;
            swap(s1,begin,end);
        }

        return s1;
    }
}

