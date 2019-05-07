package offerTest.otherWhat;

import java.util.Scanner;
/*
*知道圆心坐标和圆上一点，求半径，和体积
 */
public class circlr {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            String[] data = s.split(" ");
            double[] data1 = new double[6];
            for (int i = 0; i < 6; i++){
                data1[i]=Double.parseDouble(data[i]);
            }
            double r=Math.pow(Math.pow((data1[3]-data1[0]),2)+Math.pow((data1[4]-data1[1]),2)+Math.pow((data1[5]-data1[2]),2),0.5);
            System.out.printf("%.3f %.3f\n",r,4*Math.acos(-1)*r*r*r/3);
        }
    }
}
