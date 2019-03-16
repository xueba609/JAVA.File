package MultiClient;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class MultiThreadClient {
    public static void main(String[] args) {
        //默认端口号
        int port=6667;
        String host="127.0.0.1";
        if(args.length>1){
            port=Integer.parseInt(args[0]);
        }
            try {
                Socket  client = new Socket(host,port);
                System.out.println("与服务器连接成功");
                //从客户端写入到服务端
                new Thread(new WriteDataToServerThread(client)).start();
                //从服务器读到客户端
                new Thread(new ReadDataFromServerThread(client)).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

}
