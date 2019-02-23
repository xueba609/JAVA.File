package SingleService;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SingleThreadService {
    public static void main(String[] args) throws IOException {
        int port=6666;
        if(args.length>0){
            port=Integer.parseInt(args[0]);
        }
        try{
        //准备ServerSocket对象
        ServerSocket serverSocket = new ServerSocket(port);
        //接受客户端数据连接
        System.out.println("等待客户端输入");
        Socket client = serverSocket.accept();


                //接受数据
                Scanner ClientInput = new Scanner(client.getInputStream());
                String str = ClientInput.next();
                System.out.println(str);
                //发送数据
                OutputStreamWriter seiviceOutput = new OutputStreamWriter(client.getOutputStream());

                seiviceOutput.write("你好，服务器，我是小张");
                System.out.println(serverSocket.getLocalPort());
                seiviceOutput.flush();

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}