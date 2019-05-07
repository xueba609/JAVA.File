package offerTest.Array;
/*题目描述：
*在一个二维数组中（每个一维数组的长度相同），
* 每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
* 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 */

/*
*解题思路：找到右上角的元素
* 如果等于则返回true
* 如果大于，则删除列
* 如果小于，则删除行
 */
public class arraySearchTest {
    public static void main(String[] args) {
        int[][] data = new int[][]{{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {11, 12, 13, 14, 15}};
      System.out.println( Find(15, data));
    }
    private static boolean Find(int target, int[][] data){
        int x=data.length;
        int y=data[0].length;
        if(x>0&&y>0){
            int x1=0;
            int y1=y-1;
            while(x1<x&&y1>=0){
                //最右边的数等于目标值
                if(data[x1][y1]==target){
                    return true;
                }else if(data[x1][y1]>target){
                    //如果大于，则把对应的那一列删除
                    y1--;
                }else {
                    //如果小于，就把对应飞哪一行删除
                    x1++;
                }
            }
        }
        return false;
    }

}