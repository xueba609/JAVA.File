package Test1.Test326;
/*
*找出红包里面超过一半的
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class redPackageTest {
    public static void main(String[] args) {

    int [] data=new int []{1,5,6,3,7,8,9,1,2,3,5,5,5,5,5,5,5,5,5,5,5};
    System.out.println(getValue(data,data.length));
    }
    private static int getValue(int[] data, int n) {
        Arrays.sort(data);
        int ang=data[n/2];
        int count=0;
        for(int i=0;i<n;i++){
            if(data[i]==ang){
                count++;
            }
        }
        return count<=n/2?0:ang;
    }
    /*private static int getValue(int[] gifts, int n) {
    //Map用于保存值和数量
        Map<Integer,Integer> table=new HashMap<>();
        for(int i=0;i<n;i++){
            if(table.containsKey(gifts[i])){
                table.put(gifts[i],table.get(gifts[i])+1);
            }else{
                table.put(gifts[i],1);
            }
        }

        for(Map.Entry<Integer,Integer> entry:table.entrySet()) {
            //这里拿两个数组进行接受，要不然getvalue和n/2比对不出结果
            int key=(Integer)entry.getKey();
            int value=(Integer)entry.getValue();
            if(value>=n/2){
                return key;
            }
        }
             return 0;
    }
    */
}
