package offerTest.Array;
/*
*给一个股票走势，计算利益最大
* 可以买两次，卖两次，买第二次保证第一次都卖了
 */
public class benefitMax {
    public static void main(String[] args) {
        int [] data={5,8,1,6,5,8};
        System.out.println(calculateMax(data));
    }

    private static int calculateMax(int[] data) {
        int sum=0;
        int count=0;
        for(int i=0;i<data.length;i++){
            int temp=0;
            for (int j=i+1;j<data.length;j++) {
                if(data[j] - data[i]>=temp) {
                    temp = data[j] - data[i];
                }
                if(data[j] - data[i]<0)
                i=j;
                count++;
                break;
            }

            sum+=temp;
            if(count==2){
                break;
            }
        }
        return sum;
    }
}
