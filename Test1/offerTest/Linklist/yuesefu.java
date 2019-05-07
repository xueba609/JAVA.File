package offerTest.Linklist;
/*
*约瑟夫环
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class yuesefu {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        int m=scanner.nextInt();
        yusefuCircle(n,m);
    }
    private static void yusefuCircle(int n, int m) {
        List<Integer> begin=new ArrayList<>();
        for(int i=1;i<=n;i++){
            begin.add(i);
        }
        int k=0;
        while(begin.size()>0){
            k=k+m;
            //k的位置就是剔除的那个人
            k=k%(begin.size())-1;
            //小于0，只剩最后一个了
            if(k<0){
                System.out.println(begin.get(begin.size()-1));
                begin.remove(begin.size()-1);
                k=0;
            }else {
                System.out.print(begin.get(k)+" ");
                begin.remove(k);
            }
        }
    }
}
