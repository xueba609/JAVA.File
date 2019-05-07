package offerTest.otherWhat;
/*问题描述：
*一只青蛙一次可以跳上1级台阶，也可以跳上2级。
* 求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
 */

/*
*思想：找规律 一个台阶一种方法   2个台阶2种方法，
* 三个台阶3中方法，四个台阶五中方法
* 类似于斐波那契
 */

import java.util.Scanner;

public class JumpFloor {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
       System.out.println( JumpFloor1(n));
    }

    private static int  JumpFloor1(int target) {
       /* if(target<=0)
            return 0;
        else if(target==1)
            return 1;
        else if(target==2)
            return 2;
        else
            return JumpFloor1(target-1)+JumpFloor1(target-2);
*/
      if(target<=0){
          return 0;
      }
      if(target<=3){
          return target;
        }
        int temp1=2;
        int temp2=3;
        int sum=0;
        for(int i=4;i<=target;i++){
            sum=temp1+temp2;
            temp1=temp2;
            temp2=sum;
        }
        return sum;
    }
}
