package Test1;

import java.util.Scanner;

public class boardTest {
    public static void main(String[] args) {
        int[][] data = new int[][]{{1, 0, 1}, {1, -1, -1}, {1, -1, 0}};
        System.out.println(checkWon(data));
    }
    public static boolean checkWon(int[][] board) {
        int a=0;
      for(int i=0;i<board.length;i++){
          for(int j=0;j<board[i].length;j++){
              a+=board[i][j];
          }
    }
    if(a>0){
          return true;
    }
    return false;
}
}
