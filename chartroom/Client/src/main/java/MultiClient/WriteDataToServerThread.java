package MultiClient;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class WriteDataToServerThread implements Runnable{
    private final Socket client;
    public WriteDataToServerThread(Socket client) {
        this.client=client;
    }
    public void run() {
        try {
            OutputStreamWriter writer = new OutputStreamWriter(client.getOutputStream());
            System.out.println("输入：A表示登录，按B表示注册");
            Scanner str = new Scanner(System.in);
            while (true) {
                while (str.hasNextLine()) {
                    String message = str.nextLine();
                    writer.write(message + "\n");
                    writer.flush();
                    if (message.equals("byebye")) {
                        break;
                    }
                }
            }
            } catch(IOException e){
                e.printStackTrace();
            }
        }
    }

