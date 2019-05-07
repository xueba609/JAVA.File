package offerTest.otherWhat;



import java.util.Scanner;
/*
*给n个数据
* 1 2 3 ....n
* 若为偶数，执行n/2
* 若为奇数，执行3n+1;
* n个数据到达一的次数
 */


public class back1Path {
    public static void main(String[] args) {

        Scanner scanner=new Scanner(System.in);
       int T=scanner.nextInt();
       int [] data=new int[T];
       for(int i=0;i<T;i++){
           data[i]=scanner.nextInt();
       }
       SrarchPath(data,T);
    }

    private static void SrarchPath(int[] data, int t) {
        for(int i=0;i<t;i++){
            int temp=data[i];
            int count=0;
            while(temp!=1){
                if(temp%2==0){
                    temp=temp/2;
                    count++;
                    continue;
                }
                if(temp%2==1){
                    temp=3*temp+1;
                    count++;
                    continue;
                }
            }
            data[i]=count;
        }
        for(int j=0;j<t;j++){
            System.out.println(data[j]);
        }
    }
}
