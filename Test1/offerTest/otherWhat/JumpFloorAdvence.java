package offerTest.otherWhat;

import java.util.Scanner;

public class JumpFloorAdvence {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        System.out.println( JumpFloor1(n));
    }

    private static int JumpFloor1(int target) {
        if(target<=0){
            return 0;
        }
        if(target<=2){
            return target;
        }
        int sum=2;
        for(int i=3;i<=target;i++){
           sum*=2;
        }
        return sum;
    }
}
