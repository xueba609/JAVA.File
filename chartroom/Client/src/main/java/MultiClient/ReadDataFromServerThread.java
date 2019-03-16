package MultiClient;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ReadDataFromServerThread implements Runnable {
    private final Socket client ;
    public ReadDataFromServerThread(Socket client) {
        this.client=client;
    }
    public void run() {
        try {
            Scanner str=new Scanner(client.getInputStream());
            while(str.hasNext()){
                String message=str.nextLine();
                System.out.println(message);
                if(message.equals("bye")){
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
