package Test1.Test326;

import javax.swing.plaf.synth.SynthScrollBarUI;
import java.security.KeyStore;
import java.util.*;

/*
*按用户成绩，从高到低排序
 */
public class serchTest {
    public static void main(String[] args) {
        Scanner src = new Scanner(System.in);
        Integer n = 0;
        Map<String, String> map = new HashMap<>();
                n = src.nextInt();
                for(int i=0;i<n;i++) {
                    map.put(src.next(), src.next());
                }
                int [] data=new int[n];
                int i=0;
                if(i<n) {
                    for (Map.Entry<String, String> entry : map.entrySet()) {
                        data[i] = Integer.parseInt(entry.getValue());
                        i++;
                    }
                }
        Arrays.sort(data);
        for (int j = 0; i < n; i++) {
            System.out.println(data[j]);
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if(data[i]==Integer.parseInt(entry.getValue())){
                    System.out.println(entry.getKey()+" "+entry.getValue());
                }
            }
        }
    }
}
