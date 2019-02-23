package SingleClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class SingleThreadClient {
    public static void main(String[] args) throws IOException {
        int port=6666;
        if(args.length>0){
            port=Integer.parseInt(args[0]);
        }
        try {
            //准备Socket对象，连接到服务器端
            Socket client = new Socket("127.0.0.1", port);
            System.out.println("与服务端连接成功");

                //发送数据
                OutputStreamWriter clientoutput = new OutputStreamWriter(client.getOutputStream());
                clientoutput.write("你好，服务器，我是小张\n");
                System.out.println(client.getPort());
                clientoutput.flush();
                //接受数据
                Scanner serviceInput = new Scanner(client.getInputStream());
                String str = serviceInput.next();
                System.out.println(str);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
