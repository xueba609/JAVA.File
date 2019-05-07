package offerTest.otherWhat;

import java.util.Scanner;
/*
*有n个学生，k个技能，m个讲师，每分钟一个讲师只能给一个学生传授一个技能
*现在要求n个学生学会k个技能的时间
 */
public class whatStudy {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        int k=scanner.nextInt();
        int m=scanner.nextInt();
        timeSum(n,k,m);
    }

    private static void timeSum(int n, int k, int m) {
        //理想情况
        //讲师大于学生
        if(m>=n){
            System.out.println(k);
        }
        //学生多于讲师
        if(n%m==0){
            System.out.println((n/m)*k);
        }else {
            System.out.println((n/m)*k+k);
        }
    }
}
