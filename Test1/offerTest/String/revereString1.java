package offerTest.String;
/*
*hao are you
* 编程 you are hao
 */
import java.io.IOException;
import java.util.Scanner;

public class revereString1{
    public static void main(String[] args) throws IOException {
      /* BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in)
        );*/
        Scanner reader=new Scanner(System.in);
        String line = reader.nextLine();
        String[] words = line.split(" ");
        for (int i = 0; i < words.length ; i++) {
            System.out.format("%s ", words[words.length - 1 - i]);
        }

    }
}