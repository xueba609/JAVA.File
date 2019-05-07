package offerTest.String;

import java.util.Scanner;
/*
 *hao are you
 * 编程 you are hao
 */

public class revereString {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        String s=scanner.nextLine();
        swapString(s);
    }
    private static void swapString(String s) {
        //1.先将整个字符串反转
        char [] c=s.toCharArray();
        swap(c,0,c.length-1);
        //再将每个单词 反转
        int start=0;
            for (int i = 0; i < c.length; i++) {
                //3.当为空格时。停止
                if (c[i] == ' ') {
                    swap(c, start, i - 1);
                    start = i + 1;
                }
            }
            swap(c,start,c.length-1);
        for(int j=0;j<c.length;j++) {
            System.out.print(c[j]);
        }
    }
    private static void swap(char[] c, int begin, int end) {
        while(begin<end){
            char temp=c[begin];
            c[begin]=c[end];
            c[end]=temp;
            begin++;
            end--;
        }
    }
}
